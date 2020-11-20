package com.tradindemboiz.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "auctions")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = { "auctionOwner", "auctionBids"})
public class Auction {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long auction_id;
  private long timestamp;
  private long end_date;
  private double start_price;
  private String title;
  private String description;
  private String image_URL;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User auctionOwner;

  @OneToMany(mappedBy = "bidAuction", cascade = CascadeType.ALL)
  private Set<Bid> auctionBids;

}
