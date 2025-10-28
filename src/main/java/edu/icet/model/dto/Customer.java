package edu.icet.model.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Customer {
    private Long customerId;
    private String customerName;
    private String phoneNumber;
    private String email;
    private String address;
}
