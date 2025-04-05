package com.funshine.yetusote.models;

import com.funshine.yetusote.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class MyUser {
    @Id
    private String userId;
    private Role role = Role.ADMIN;
    private String firstname;
    private String lastname;
    private String email;
    private Integer phone;
    private String idNumber;
    private String password;


}
