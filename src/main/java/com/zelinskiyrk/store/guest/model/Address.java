package com.zelinskiyrk.store.guest.model;

import io.swagger.annotations.ApiModel;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Address", description = "Buyer address")
public class Address {
    private String street;
    private String houseNumber;
    private String apartment;
    private String entrance;
    private String floor;
}
