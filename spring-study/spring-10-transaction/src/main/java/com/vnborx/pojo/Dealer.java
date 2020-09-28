package com.vnborx.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dealer {
    private String dealer_id;
    private String dealer_name;
    private String city;
    private String province;
    private String telephone;
}
