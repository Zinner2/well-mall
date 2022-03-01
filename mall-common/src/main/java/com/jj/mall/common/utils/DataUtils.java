package com.jj.mall.common.utils;

import java.sql.Timestamp;


/**
 * 获取当前时间工具类
 * @author 任人子
 * @date 2022/2/27  - {TIME}
 */
public class DataUtils {

    /**
     * 获取本地当前时间
     * @return
     */
    public static Timestamp getLocalCurrentTime(){
        return new Timestamp(System.currentTimeMillis());
    }
}
