package edu.icet.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class SaleDetails {
    private String saleId;
    private String itemCode;
    private Integer orderQuantity;

}
