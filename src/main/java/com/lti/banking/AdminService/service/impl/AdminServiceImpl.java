package com.lti.banking.AdminService.service.impl;

import com.lti.banking.AdminService.dao.AdminDao;
import com.lti.banking.AdminService.model.Admin;
import com.lti.banking.AdminService.model.Role;
import com.lti.banking.AdminService.model.AdminDto;
import com.lti.banking.AdminService.service.RoleService;
import com.lti.banking.AdminService.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service(value = "userService")
public class AdminServiceImpl implements UserDetailsService, AdminService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminDao.findByUsername(username);
        if(admin == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(admin.getUsername(), admin.getPassword(), getAuthority(admin));
    }

    private Set<SimpleGrantedAuthority> getAuthority(Admin admin) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        admin.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
    }

    public List<Admin> findAll(AdminDto users) {
        List<Admin> list = new ArrayList<>();
        adminDao.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Admin findOne(String username) {
        return adminDao.findByUsername(username);
    }

    @Override
    public Admin save(AdminDto user) {

        Admin nAdmin = user.getUserFromDto();
        nAdmin.setPassword(bcryptEncoder.encode(user.getPassword()));

        Role role = roleService.findByName("USER");
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);

        if(nAdmin.getEmail().split("@")[1].equals("admin.bank")){
            role = roleService.findByName("ADMIN");
            roleSet.add(role);
        }

        nAdmin.setRoles(roleSet);
        return adminDao.save(nAdmin);
    }
}
