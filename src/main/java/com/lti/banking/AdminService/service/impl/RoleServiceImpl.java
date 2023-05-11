package com.lti.banking.AdminService.service.impl;

import com.lti.banking.AdminService.dao.RoleDao;
import com.lti.banking.AdminService.model.Role;
import com.lti.banking.AdminService.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public Role findByName(String name) {
        Role role = roleDao.findRoleByName(name);
        return role;
    }
}
