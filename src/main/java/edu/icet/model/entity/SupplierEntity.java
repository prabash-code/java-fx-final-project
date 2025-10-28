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

public class SupplierEntity {
    @Id
    private Long supplierId;
    private String name;
    private String company;
    private String email;
    private String phone;
    private String address;
}
