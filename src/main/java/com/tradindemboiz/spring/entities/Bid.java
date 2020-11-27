package com.tradindemboiz.spring.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
  @JsonManagedReference(value = "bidOwner")
  private User bidOwner;

  @ManyToOne
  @JoinColumn(name = "auction_id")
  @JsonManagedReference(value = "bidAuction")
  private Auction bidAuction;

}
