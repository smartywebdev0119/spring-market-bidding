package com.tradindemboiz.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuctionCreateDto {

    private long end_date;
    private double start_price;
    private String title;
    private String description;
    private Set<String> images;
    private Long user;
}
