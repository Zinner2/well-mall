package com.jj.mall.service.impl;

import com.jj.mall.mapper.OmsCompanyAddressMapper;
import com.jj.mall.model.OmsCompanyAddress;
import com.jj.mall.model.OmsCompanyAddressExample;
import com.jj.mall.service.OmsCompanyAddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 收货地址管理 ServiceImpl
 * @author 任人子
 * @date 2022/3/17  - {TIME}
 */
@Service
public class OmsCompanyAddressServiceImpl implements OmsCompanyAddressService {
    @Resource
    private OmsCompanyAddressMapper companyAddressMapper;
    @Override
    public List<OmsCompanyAddress> list() {
        return companyAddressMapper.selectByExample(new OmsCompanyAddressExample());
    }
}
