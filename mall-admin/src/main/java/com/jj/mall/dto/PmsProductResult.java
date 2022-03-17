package com.jj.mall.dto;

/**
 * @author 任人子
 * @date 2022/3/17  - {TIME}
 */

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 查询单个产品进行修改时返回的结果
 */
public class PmsProductResult extends PmsProductParam {
    @Getter
    @Setter
    @ApiModelProperty("商品所选分类的父id")
    private Long cateParentId;
}