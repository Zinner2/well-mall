package com.jj.mall.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.github.pagehelper.PageHelper;
import com.github.xiaoymin.knife4j.core.util.CollectionUtils;
import com.jj.mall.dao.*;
import com.jj.mall.dto.PmsProductParam;
import com.jj.mall.dto.PmsProductQueryParam;
import com.jj.mall.dto.PmsProductResult;
import com.jj.mall.mapper.*;
import com.jj.mall.model.*;
import com.jj.mall.service.PmsProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 后台商品管理ServiceImpl
 * @author 任人子
 * @date 2022/3/10  - {TIME}
 */
@Service
public class PmsProductServiceImpl implements PmsProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PmsProductServiceImpl.class);

    @Resource
    private PmsProductMapper productMapper;
    @Resource
    private PmsMemberPriceDao memberPriceDao;
    @Resource
    private PmsProductLadderDao productLadderDao;
    @Resource
    private PmsProductFullReductionDao productFullReductionDao;
    @Resource
    private PmsSkuStockDao skuStockDao;
    @Resource
    private PmsProductAttributeValueDao productAttributeValueDao;
    @Resource
    private CmsSubjectProductRelationDao subjectProductRelationDao;
    @Resource
    private CmsPrefrenceAreaProductRelationDao prefrenceAreaProductRelationDao;
    @Resource
    private PmsProductDao productDao;
    @Resource
    private PmsMemberPriceMapper memberPriceMapper;
    @Resource
    private PmsProductLadderMapper productLadderMapper;
    @Resource
    private PmsProductFullReductionMapper productFullReductionMapper;
    @Resource
    private PmsProductAttributeValueMapper productAttributeValueMapper;
    @Resource
    private CmsSubjectProductRelationMapper subjectProductRelationMapper;
    @Resource
    private CmsPrefrenceAreaProductRelationMapper prefrenceAreaProductRelationMapper;
    @Resource
    private PmsSkuStockMapper skuStockMapper;

    @Override
    public int update(Long id, PmsProductParam productParam) {
        int count;
        //更新商品信息
        PmsProduct product = productParam;
        product.setId(id);
        productMapper.updateByPrimaryKeySelective(product);
        //会员价格
        PmsMemberPriceExample pmsMemberPriceExample = new PmsMemberPriceExample();
        pmsMemberPriceExample.createCriteria().andProductIdEqualTo(id);
        memberPriceMapper.deleteByExample(pmsMemberPriceExample);
        relateAndInsertList(memberPriceDao, productParam.getMemberPriceList(), id);
        //阶梯价格
        PmsProductLadderExample ladderExample = new PmsProductLadderExample();
        ladderExample.createCriteria().andProductIdEqualTo(id);
        productLadderMapper.deleteByExample(ladderExample);
        relateAndInsertList(productLadderDao, productParam.getProductLadderList(), id);
        //满减价格
        PmsProductFullReductionExample fullReductionExample = new PmsProductFullReductionExample();
        fullReductionExample.createCriteria().andProductIdEqualTo(id);
        productFullReductionMapper.deleteByExample(fullReductionExample);
        relateAndInsertList(productFullReductionDao, productParam.getProductFullReductionList(), id);
        //修改sku库存信息
        handleUpdateSkuStockList(id, productParam);
        //修改商品参数,添加自定义商品规格
        PmsProductAttributeValueExample productAttributeValueExample = new PmsProductAttributeValueExample();
        productAttributeValueExample.createCriteria().andProductIdEqualTo(id);
        productAttributeValueMapper.deleteByExample(productAttributeValueExample);
        relateAndInsertList(productAttributeValueDao, productParam.getProductAttributeValueList(), id);
        //关联专题
        CmsSubjectProductRelationExample subjectProductRelationExample = new CmsSubjectProductRelationExample();
        subjectProductRelationExample.createCriteria().andProductIdEqualTo(id);
        subjectProductRelationMapper.deleteByExample(subjectProductRelationExample);
        relateAndInsertList(subjectProductRelationDao, productParam.getSubjectProductRelationList(), id);
        //关联优选
        CmsPrefrenceAreaProductRelationExample prefrenceAreaExample = new CmsPrefrenceAreaProductRelationExample();
        prefrenceAreaExample.createCriteria().andProductIdEqualTo(id);
        prefrenceAreaProductRelationMapper.deleteByExample(prefrenceAreaExample);
        relateAndInsertList(prefrenceAreaProductRelationDao, productParam.getPrefrenceAreaProductRelationList(), id);
        count = 1;
        return count;
    }
    private void handleUpdateSkuStockList(Long id, PmsProductParam productParam) {
        //当前的sku信息
        List<PmsSkuStock> currSkuList = productParam.getSkuStockList();
        //当前没有sku直接删除
        if(CollUtil.isEmpty(currSkuList)){
            PmsSkuStockExample skuStockExample = new PmsSkuStockExample();
            skuStockExample.createCriteria().andProductIdEqualTo(id);
            skuStockMapper.deleteByExample(skuStockExample);
            return;
        }
        //获取初始sku信息
        PmsSkuStockExample skuStockExample = new PmsSkuStockExample();
        skuStockExample.createCriteria().andProductIdEqualTo(id);
        List<PmsSkuStock> oriStuList = skuStockMapper.selectByExample(skuStockExample);
        //获取新增sku信息
        List<PmsSkuStock> insertSkuList = currSkuList.stream().filter(item->item.getId()==null).collect(Collectors.toList());
        //获取需要更新的sku信息
        List<PmsSkuStock> updateSkuList = currSkuList.stream().filter(item->item.getId()!=null).collect(Collectors.toList());
        List<Long> updateSkuIds = updateSkuList.stream().map(PmsSkuStock::getId).collect(Collectors.toList());
        //获取需要删除的sku信息
        List<PmsSkuStock> removeSkuList = oriStuList.stream().filter(item-> !updateSkuIds.contains(item.getId())).collect(Collectors.toList());
        handleSkuStockCode(insertSkuList,id);
        handleSkuStockCode(updateSkuList,id);
        //新增sku
        if(CollUtil.isNotEmpty(insertSkuList)){
            relateAndInsertList(skuStockDao, insertSkuList, id);
        }
        //删除sku
        if(CollUtil.isNotEmpty(removeSkuList)){
            List<Long> removeSkuIds = removeSkuList.stream().map(PmsSkuStock::getId).collect(Collectors.toList());
            PmsSkuStockExample removeExample = new PmsSkuStockExample();
            removeExample.createCriteria().andIdIn(removeSkuIds);
            skuStockMapper.deleteByExample(removeExample);
        }
        //修改sku
        if(CollUtil.isNotEmpty(updateSkuList)){
            for (PmsSkuStock pmsSkuStock : updateSkuList) {
                skuStockMapper.updateByPrimaryKeySelective(pmsSkuStock);
            }
        }

    }
    @Override
    public PmsProductResult getUpdateInfo(Long id) {
        return productDao.getUpdateInfo(id);
    }

    @Override
    public int updateDeleteStatus(List<Long> ids, Integer deleteStatus) {
        PmsProduct product = new PmsProduct();
        product.setDeleteStatus(deleteStatus);
        PmsProductExample example = new PmsProductExample();
        example.createCriteria().andIdIn(ids);
        return productMapper.updateByExampleSelective(product, example);
    }

    @Override
    public int updateNewStatus(List<Long> ids, Integer newStatus) {
        PmsProduct product = new PmsProduct();
        product.setNewStatus(newStatus);
        PmsProductExample example = new PmsProductExample();
        example.createCriteria().andIdIn(ids);
        return productMapper.updateByExampleSelective(product, example);
    }

    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        PmsProduct product = new PmsProduct();
        product.setRecommandStatus(recommendStatus);
        PmsProductExample example = new PmsProductExample();
        example.createCriteria().andIdIn(ids);
        return productMapper.updateByExampleSelective(product, example);
    }

    @Override
    public int updatePublishStatus(List<Long> ids, Integer publishStatus) {
        PmsProduct product = new PmsProduct();
        product.setPublishStatus(publishStatus);
        PmsProductExample example = new PmsProductExample();
        example.createCriteria().andIdIn(ids);
        return productMapper.updateByExampleSelective(product, example);
    }

    @Override
    public int create(PmsProductParam productParam) {
        int count = 0;
        PmsProduct product = productParam;
        product.setId(null);
        productMapper.insertSelective(product);
        // 根据促销类型设置价格：会员价格、阶梯价格、满减价格
        Long productId = product.getId();
        // 会员价格
        relateAndInsertList(memberPriceDao, productParam.getMemberPriceList(), productId);
        // 阶梯价格
        relateAndInsertList(productLadderDao, productParam.getProductLadderList(), productId);
        // 满减价格
        relateAndInsertList(productFullReductionDao, productParam.getProductFullReductionList(), productId);
        //处理sku的编码
        handleSkuStockCode(productParam.getSkuStockList(),productId);
        //添加sku库存信息
        relateAndInsertList(skuStockDao, productParam.getSkuStockList(), productId);
        //添加商品参数,添加自定义商品规格
        relateAndInsertList(productAttributeValueDao, productParam.getProductAttributeValueList(), productId);
        //关联专题
        relateAndInsertList(subjectProductRelationDao, productParam.getSubjectProductRelationList(), productId);
        //关联优选
        relateAndInsertList(prefrenceAreaProductRelationDao, productParam.getPrefrenceAreaProductRelationList(), productId);

        count = 1;
        return count;
    }

    @Override
    public List<PmsProduct> list(PmsProductQueryParam productQueryParam, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PmsProductExample example = new PmsProductExample();
        PmsProductExample.Criteria criteria = example.createCriteria();
        criteria.andDeleteStatusEqualTo(0);
        if(productQueryParam.getBrandId() != null){
            criteria.andBrandIdEqualTo(productQueryParam.getBrandId());
        }
        if(productQueryParam.getProductCategoryId() != null){
            criteria.andProductCategoryIdEqualTo(productQueryParam.getProductCategoryId());
        }
        if(productQueryParam.getPublishStatus() != null){
            criteria.andPublishStatusEqualTo(productQueryParam.getPublishStatus());
        }
        if(productQueryParam.getVerifyStatus() != null){
            criteria.andVerifyStatusEqualTo(productQueryParam.getVerifyStatus());
        }
        if (!StringUtils.isEmpty(productQueryParam.getKeyword())){
            criteria.andNameLike("%" + productQueryParam.getKeyword() + "%");
        }
        if (!StringUtils.isEmpty(productQueryParam.getProductSn())){
            criteria.andProductSnEqualTo(productQueryParam.getProductSn());
        }
        return productMapper.selectByExample(example);
    }

    private void handleSkuStockCode(List<PmsSkuStock> skuStockList, Long productId) {
        if(org.springframework.util.CollectionUtils.isEmpty(skuStockList))return;
        for(int i=0;i<skuStockList.size();i++){
            PmsSkuStock skuStock = skuStockList.get(i);
            if(StringUtils.isEmpty(skuStock.getSkuCode())){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                StringBuilder sb = new StringBuilder();
                //日期
                sb.append(sdf.format(new Date()));
                //四位商品id
                sb.append(String.format("%04d", productId));
                //三位索引id
                sb.append(String.format("%03d", i+1));
                skuStock.setSkuCode(sb.toString());
            }
        }
    }

    /**
     * 建立和插入表关系
     * @param dao
     * @param dataList
     * @param productId
     */
    private void relateAndInsertList(Object dao, List dataList, Long productId){
        try{
            if(CollectionUtils.isEmpty(dataList)){ return; }
            for(Object item : dataList){
                Method setId = item.getClass().getMethod("setId", Long.class);
                setId.invoke(item, (Long)null);
                Method setProductId = item.getClass().getMethod("setProductId", Long.class);
                setProductId.invoke(item, productId);
            }
            Method insertList = dao.getClass().getMethod("insertList", List.class);
            insertList.invoke(dao, dataList);
        }catch(Exception e){
            LOGGER.warn("创建产品出错:{}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
