package com.lti.banking.AdminService.service;

import com.lti.banking.AdminService.model.Admin;
import com.lti.banking.AdminService.model.AdminDto;

import java.util.List;

public interface AdminService {
    Admin save(AdminDto user);
    List<Admin> findAll(AdminDto users);
    Admin findOne(String username);
}
