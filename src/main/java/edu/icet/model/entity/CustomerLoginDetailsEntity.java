package edu.icet.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity

public class CustomerLoginDetailsEntity {

    private String userName;
    @Id
    private String email;
    private String password;

}
