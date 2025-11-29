package com.parking.prakinglot.common;

public class UserDto {
    private String username;
    private String email;
    private Long id;

    public UserDto(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public Long getId() { return id; }
}
