package edu.icet.model.dto;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Medicine {
    private Long medicineId;
    private Long suppliedId;
    private String name;
    private String brand;
    private String category;
    private LocalDate expireDate;
    private String quantity;
    private Double unitPrice;
}
