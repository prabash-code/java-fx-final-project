package edu.icet.model.dto;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerLoginDetails {

    private String userName;
    private String email;
    private String password;
}
