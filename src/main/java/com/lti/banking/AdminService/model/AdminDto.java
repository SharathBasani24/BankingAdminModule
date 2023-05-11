package com.lti.banking.AdminService.model;

public class AdminDto {
    
    private String username;
    private String password;
    private String email;
    private String phone;
    //private long uniqueId;
    private int accountNo;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

//    public long getUniqueId() {
//        return uniqueId;
//    }
//
//    public void setUniqueId(long uniqueId) {
//        this.uniqueId = uniqueId;
//    }

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public Admin getUserFromDto(){
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);
        admin.setEmail(email);
        admin.setPhone(phone);
        //admin.setUniqueId(uniqueId);
        admin.setAccountNo(accountNo);
        
        return admin;
    }
    
}