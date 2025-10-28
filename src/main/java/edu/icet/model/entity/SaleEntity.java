package edu.icet.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class SaleEntity {
    @Id
    private Long saleId;
    private Long customerId;
    private LocalDate saleDate;
    private double totalAmount;
}
