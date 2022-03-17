package com.jj.mall.service;

import com.jj.mall.dto.PmsBrandParam;
import com.jj.mall.model.PmsBrand;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 后台品牌管理Service
 *
 * @author 任人子
 * @date 2022/3/10  - {TIME}
 */
public interface PmsBrandService {
    /**
     * 分页获取后台品牌列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<PmsBrand> list(Integer pageNum, Integer pageSize);

    /**
     * 获取品牌信息
     * @param id
     * @return
     */
    PmsBrand getBrandId(Long id);


    /**
     * 修改品牌信息
     * @param id
     * @param brandParam
     * @return
     */
    @Transactional
    int update(Long id, PmsBrandParam brandParam);

    /**
     * 添加品牌
     * @param pmsBrand
     * @return
     */
    int createBrand(PmsBrandParam pmsBrand);

    /**
     * 删除品牌
     * @param id
     * @return
     */
    int deleteBrand(Long id);

    /**
     * 批量更新显示状态
     * @param ids
     * @param showStatus
     * @return
     */
    int updateShowStatus(List<Long> ids, Integer showStatus);

    /**
     * 批量更新厂家制造商状态
     * @param ids
     * @param factoryStatus
     * @return
     */
    int updateFactoryStatus(List<Long> ids, Integer factoryStatus);
}
