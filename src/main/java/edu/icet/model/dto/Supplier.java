package edu.icet.model.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Supplier {
    private Long supplierId;
    private String name;
    private String company;
    private String email;
    private String phone;
    private String address;
}
