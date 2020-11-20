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
  private double bid_price;
  private User user;
  private Auction auction;
}
