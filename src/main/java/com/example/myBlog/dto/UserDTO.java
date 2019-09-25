package com.example.myBlog.dto;

import lombok.Data;

@Data
public class UserDTO {
	private Integer id;
    private String name;
    private String token;
    private String avatarUrl;
    private String password;
}
