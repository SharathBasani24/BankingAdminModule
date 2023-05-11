package com.lti.banking.AdminService.dao;

import com.lti.banking.AdminService.model.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDao extends CrudRepository<Admin, Long> {
    Admin findByUsername(String username);
}