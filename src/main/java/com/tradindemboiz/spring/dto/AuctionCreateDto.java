package com.tradindemboiz.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuctionCreateDto {

    private long end_date;
    private double start_price;
    private String title;
    private String description;
    private String image_URL;
    private Long user;



}
