package edu.icet.model.dto;

import lombok.*;

import java.time.LocalDate;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Sale {
    private String saleId;
    private LocalDate saleDate;
    private String customerName;
    private String customerEmail;
    private double total;

}
