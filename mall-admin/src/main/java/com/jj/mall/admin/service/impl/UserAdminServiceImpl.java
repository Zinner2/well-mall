package com.jj.mall.admin.service.impl;

import com.jj.mall.admin.service.UserAdminService;
import com.jj.mall.common.domain.UserDto;
import com.jj.mall.mapper.UmsAdminMapper;
import com.jj.mall.model.UmsAdmin;
import com.jj.mall.model.UmsAdminExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 任人子
 * @date 2022/2/24  - {TIME}
 */
@Service
public class UserAdminServiceImpl implements UserAdminService {

    @Resource
    private UmsAdminMapper umsAdminMapper;

    @Override
    public UmsAdmin getAdminByUsername(String username) {
        UmsAdminExample adminExample = new UmsAdminExample();
        adminExample.createCriteria().andUsernameEqualTo(username);
        List<UmsAdmin> umsAdminList = umsAdminMapper.selectByExample(adminExample);
        if(umsAdminList != null && umsAdminList.size() > 0){
            return umsAdminList.get(0);
        }
        return null;
    }

    @Override
    public UserDto loadUserByUsername(String username) {
        UmsAdmin admin = getAdminByUsername(username);
        if(admin != null){

        }
        return null;
    }
}
