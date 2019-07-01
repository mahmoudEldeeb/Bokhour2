package com.eldeeb.bokhour.models.dataModels;

import java.util.List;

public class Results {
    boolean result;
    public String message;
    private UserInfo user_info;


    public boolean isResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }


    public void setResult(boolean result) {
        this.result = result;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserInfo getUser_info() {
        return user_info;
    }

    public void setUser_info(UserInfo user_info) {
        this.user_info = user_info;
    }
}
