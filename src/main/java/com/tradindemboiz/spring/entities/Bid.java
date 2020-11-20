package com.tradindemboiz.spring.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "auctions_id")
  private Auction auction;
}