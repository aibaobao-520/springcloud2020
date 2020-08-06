package com.ns.dto;

import lombok.Data;

@Data
public class UserDto {
    private long id;
    private String username;
    private String nickname;
    private String gender;
    private String telephone;
    private String address;
    private RoleDto rid;
}
