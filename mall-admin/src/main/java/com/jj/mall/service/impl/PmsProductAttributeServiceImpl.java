package com.jj.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.jj.mall.mapper.PmsProductAttributeMapper;
import com.jj.mall.model.PmsProductAttribute;
import com.jj.mall.model.PmsProductAttributeExample;
import com.jj.mall.service.PmsProductAttributeService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 后台上商品参数ServiceImpl
 * @author 任人子
 * @date 2022/3/10  - {TIME}
 */
@RestController
public class PmsProductAttributeServiceImpl implements PmsProductAttributeService {
    @Resource
    private PmsProductAttributeMapper pmsProductAttributeMapper;
    @Override
    public List<PmsProductAttribute> category(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return pmsProductAttributeMapper.selectByExample(new PmsProductAttributeExample());
    }



}
