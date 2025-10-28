package edu.icet.model.dto;

import lombok.*;

import java.time.LocalDate;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Sale {
    private Long saleId;
    private Long customerId;
    private LocalDate saleDate;
    private double totalAmount;
}
