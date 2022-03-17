package com.jj.mall.dto;

import com.jj.mall.model.PmsProductAttribute;
import com.jj.mall.model.PmsProductAttributeCategory;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 包含有分类及其下属性的Dto
 * @author 任人子
 * @date 2022/3/12  - {TIME}
 */
public class PmsProductAttributeCategoryItem extends PmsProductAttributeCategory {

    @ApiModelProperty(value = "商品属性列表")
    @Setter
    @Getter
    private List<PmsProductAttribute> productAttributeList;
}
