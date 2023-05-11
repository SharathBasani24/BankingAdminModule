package com.lti.banking.AdminService.controller;

import com.lti.banking.AdminService.config.TokenProvider;
import com.lti.banking.AdminService.model.Admin;
import com.lti.banking.AdminService.model.AuthToken;
import com.lti.banking.AdminService.model.LoginAdmin;
import com.lti.banking.AdminService.model.AdminDto;
import com.lti.banking.AdminService.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AdminController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody LoginAdmin loginAdmin) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginAdmin.getUsername(),
                        loginAdmin.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        return ResponseEntity.ok(new AuthToken(token));
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public Admin saveUser(@RequestBody AdminDto user){
        return adminService.save(user);
    }

    @RequestMapping(value="/getAllUsers", method = RequestMethod.GET)
    public List<Admin> allUser(@RequestBody AdminDto users ){ return adminService.findAll(users);}

//    @PreAuthorize("hasRole('ADMIN')")
//    @RequestMapping(value="/adminping", method = RequestMethod.GET)
//    public String adminPing(){
//        return "Only Admins Can Read This";
//    }
//
//    @PreAuthorize("hasRole('USER')")
//    @RequestMapping(value="/userping", method = RequestMethod.GET)
//    public String userPing(){
//        return "Any User Can Read This";
//    }

}
