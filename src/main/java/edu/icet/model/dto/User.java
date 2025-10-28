package edu.icet.model.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private Long userId;
    private String name;
    private String email;
    private String password;
    private String role;
}
