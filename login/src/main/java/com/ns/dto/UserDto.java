package com.ns.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDto {
    private long id;
    private String username;
    private String password;
    private String nickname;
    private RoleDto rid;

}
