package models;

import lombok.Data;

@Data
public class LoginResBodyModel {
    String userId;
    String username;
    String password;
    String token;
    String expires;
    String created_date;
    String isActive;
}