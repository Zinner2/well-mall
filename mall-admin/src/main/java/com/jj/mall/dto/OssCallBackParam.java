package com.jj.mall.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * Oss上传成功后的回调参数
 * @author 任人子
 * @date 2022/3/12  - {TIME}
 */
@Data
public class OssCallBackParam {
    @ApiModelProperty("请求的回调地址")
    private String callbackUrl;
    @ApiModelProperty("回调是传入request中的参数")
    private String callbackBody;
    @ApiModelProperty("回调时传入参数的格式，比如表单提交形式")
    private String callbackBodyType;
}
