package models;

import lombok.Data;

@Data
public class LoginReqBodyModel {
    String userName, password;

    public LoginReqBodyModel(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}