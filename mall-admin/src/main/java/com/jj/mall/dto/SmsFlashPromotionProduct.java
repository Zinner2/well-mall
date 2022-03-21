package com.jj.mall.dto;

import com.jj.mall.model.PmsProduct;
import com.jj.mall.model.SmsFlashPromotionProductRelation;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 限时购及商品信息封装
 * @author 任人子
 * @date 2022/3/21  - {TIME}
 */
public class SmsFlashPromotionProduct extends SmsFlashPromotionProductRelation {

    @Getter
    @Setter
    @ApiModelProperty("关联商品")
    private PmsProduct product;
}
