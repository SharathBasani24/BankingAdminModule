package com.lti.banking.AdminService.service;

import com.lti.banking.AdminService.model.Role;

public interface RoleService {
    Role findByName(String name);
}
