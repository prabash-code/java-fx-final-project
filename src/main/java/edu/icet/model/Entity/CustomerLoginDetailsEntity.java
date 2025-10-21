package edu.icet.model.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity

public class CustomerLoginDetailsEntity {
    @Id
    private String userName;
    private String email;
    private String password;

}
