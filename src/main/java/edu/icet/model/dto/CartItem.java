package edu.icet.model.dto;

import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartItem {
    private String itemId;
    private String name;
    private Integer quantity;
    private Double unitPrice;
    private Double total;
    private LocalDate date;

}
