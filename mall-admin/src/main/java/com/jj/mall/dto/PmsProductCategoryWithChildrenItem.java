package com.jj.mall.dto;

import com.jj.mall.model.PmsProductCategory;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 *
 * @author 任人子
 * @date 2022/3/10  - {TIME}
 */
public class PmsProductCategoryWithChildrenItem extends PmsProductCategory {
    @Getter
    @Setter
    @ApiModelProperty(value = "子级菜单")
    List<PmsProductCategoryWithChildrenItem> children;
}
