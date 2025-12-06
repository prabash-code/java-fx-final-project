package edu.icet.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ContactDetails {
    private String number;
    private String email;
    private String network;
    private String Address;
    private String about;
}
