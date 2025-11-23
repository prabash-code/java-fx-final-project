package edu.icet.model.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Supplier {
    private String supplierId;
    private String name;
    private String company;
    private String email;
    private String phone;
}
