package com.jj.mall.dto;

import com.jj.mall.model.SmsFlashPromotionSession;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 包含商品数量的场次信息
 * @author 任人子
 * @date 2022/3/21  - {TIME}
 */

public class SmsFlashPromotionSessionDetail extends SmsFlashPromotionSession {

    @Getter
    @Setter
    @ApiModelProperty("商品数量")
    private Long productCount;
}
