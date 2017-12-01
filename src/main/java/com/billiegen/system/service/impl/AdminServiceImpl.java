package com.billiegen.system.service.impl;

import com.billiegen.common.framework.service.BaseServiceImpl;
import com.billiegen.system.dao.AdminDao;
import com.billiegen.system.entity.Admin;
import com.billiegen.system.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author CodePorter
 * @date 2017-12-01
 */
@Service
@Transactional(readOnly = true)
public class AdminServiceImpl extends BaseServiceImpl<AdminDao, Admin, String> implements AdminService {
    @Override
    @Transactional
    public Admin findByUsername(String username) {
        return dao.findAdminByUsernameEquals(username);
    }
}
