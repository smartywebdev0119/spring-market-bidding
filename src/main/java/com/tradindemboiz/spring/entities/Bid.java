package com.tradindemboiz.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "bids")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = { "bidOwner", "bidAuction"})
public class Bid {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long bid_id;
  private long timestamp;
  private int bid_price;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User bidOwner;

  @ManyToOne
  @JoinColumn(name = "auction_id")
  private Auction bidAuction;

}
