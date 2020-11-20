package com.tradindemboiz.spring.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
  @JsonManagedReference
  private User auctionOwner;

  @OneToMany(mappedBy = "bidAuction", cascade = CascadeType.ALL)
  @JsonBackReference
  private Set<Bid> auctionBids;

}
