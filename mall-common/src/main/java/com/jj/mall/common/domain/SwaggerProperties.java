package com.jj.mall.common.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author 任人子
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class SwaggerProperties {

    /**
     * Api文档生成基础路径
     */
    private String apiBasePackage;

    /**
     * 是否启用安全策略
     */
    private boolean enableSecurity;

    /**
     * 文档标题
     */
    private String title;

    /**
     * 文档描述
     */
    private String Description;

    /**
     * 文档版本号
     */
    private String version;

    /**
     * 文档联系人姓名
     */
    private String contactName;

    /**
     * 文档联系人网址
     */
    private String contactUrl;

    /**
     * 文档联系人邮箱
     */
    private String contactEmail;

}
