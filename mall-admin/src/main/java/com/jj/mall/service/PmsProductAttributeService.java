package com.jj.mall.service;

import com.jj.mall.dto.PmsProductAttributeParam;
import com.jj.mall.dto.ProductAttrInfo;
import com.jj.mall.model.PmsProductAttribute;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 后台商品参数Service
 * @author 任人子
 * @date 2022/3/10  - {TIME}
 */
public interface PmsProductAttributeService {

    /**
     * 分页获取商品参数列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<PmsProductAttribute> category(Integer pageNum, Integer pageSize);


    /**
     * 分页获取商品属性或参数列表
     * @param id
     * @param type
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<PmsProductAttribute> list(Long id, Integer type, Integer pageNum, Integer pageSize);

    /**
     * 根据商品分类的id获取商品属性及属性分类
     * @param productCategoryId
     * @return
     */
    List<ProductAttrInfo> getProductAttrInfo(Long productCategoryId);

    /**
     * 添加商品属性信息
     * @param productAttributeParam
     * @return
     */
    @Transactional
    int create(PmsProductAttributeParam productAttributeParam);

    /**
     * 修改商品属性
     * @param id
     * @param productAttributeParam
     * @return
     */
    int update(Long id, PmsProductAttributeParam productAttributeParam);

    /**
     *获取单个商品属性信息
     * @param id
     * @return
     */
    PmsProductAttribute getItem(Long id);

    /**
     * 批量删除商品属性
     * @param ids
     * @return
     */
    @Transactional
    int delete(List<Long> ids);
}
