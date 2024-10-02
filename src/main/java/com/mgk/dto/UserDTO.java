package com.mgk.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String email;
    private String nickname;
    private String snsToken;
    private String snsType;
    private String favorList;
}