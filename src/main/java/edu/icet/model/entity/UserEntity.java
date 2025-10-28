package edu.icet.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity

public class UserEntity {
    @Id
    private Long userId;
    private String name;
    private String email;
    private String password;
    private String role;
}
