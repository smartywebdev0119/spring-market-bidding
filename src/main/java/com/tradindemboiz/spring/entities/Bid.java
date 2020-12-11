package com.tradindemboiz.spring.entities;

import com.tradindemboiz.spring.dtos.BidCreateDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bids")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bid_id;
    private long timestamp;
    private int bid_price;
    private long auction_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User bidOwner;

    public Bid(BidCreateDto bidCreateDto) {
        this.bid_price = bidCreateDto.getBidPrice();
        this.timestamp = new Date().getTime();
    }

    public long getBidOwner() {
        return bidOwner.getUser_id();
    }
}
