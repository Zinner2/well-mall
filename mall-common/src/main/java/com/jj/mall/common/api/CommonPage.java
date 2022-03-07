package com.jj.mall.common.api;

import com.github.pagehelper.PageInfo;
import lombok.Data;
import java.util.List;

/**
 * 分页数据封装
 * @author 任人子
 * @date 2022/3/6  - {TIME}
 */
@Data
public class CommonPage<T> {
   private Integer pageNum;
   private Integer pageSize;
   private Integer totalPage;
   private Long total;
   private List<T> list;

    /**
     * 将PageHeleper分页后的list转换为分页信息
     * @param list
     * @param <T>
     * @return
     */
    public static <T> CommonPage <T> restPage(List<T> list){
        CommonPage<T> result = new CommonPage<T>();
        PageInfo<T> pageInfo = new PageInfo<T>(list);
        result.setPageNum(pageInfo.getPageNum());
        result.setPageSize(pageInfo.getPageSize());
        result.setTotalPage(pageInfo.getPages());
        result.setTotal(pageInfo.getTotal());
        result.setList(pageInfo.getList());
        return result;
    }

}
