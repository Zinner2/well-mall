package com.jj.mall.dto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 *  Oss上传成功的回调结果
 * @author 任人子
 * @date 2022/3/12  - {TIME}
 */
@Data
public class OssCallbackResult {
    @ApiModelProperty("文件名称")
    private String filename;
    @ApiModelProperty("文件大小")
    private String size;
    @ApiModelProperty("文件的mimeType")
    private String mimeType;
    @ApiModelProperty("图片文件的宽")
    private String width;
    @ApiModelProperty("图片文件的高")
    private String height;

}
