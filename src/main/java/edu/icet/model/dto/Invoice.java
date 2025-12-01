package edu.icet.model.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {
    private String saleId;
    private String itemCode;
    private Integer orderQuantity;
    private String customerName;
    private String total;
}
