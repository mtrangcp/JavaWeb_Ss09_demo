package com.demo.ss9.model.dto;

public class UserLogin {
    private String userName;
    private String password;
    private boolean  savePass;

    public UserLogin() {
    }

    public UserLogin(String userName, String password, Boolean savePass) {
        this.userName = userName;
        this.password = password;
        this.savePass = savePass;
    }

    public boolean isSavePass() {
        return savePass;
    }

    public void setSavePass(boolean savePass) {
        this.savePass = savePass;
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
