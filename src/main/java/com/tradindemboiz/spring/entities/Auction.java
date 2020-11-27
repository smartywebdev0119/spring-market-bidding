package com.tradindemboiz.spring.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tradindemboiz.spring.dto.AuctionCreateDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
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

  public Auction(AuctionCreateDto auctionCreateDto, User auctionOwner) {
    this.timestamp = auctionCreateDto.getTimestamp();
    this.end_date = auctionCreateDto.getEnd_date();
    this.start_price = auctionCreateDto.getStart_price();
    this.title = auctionCreateDto.getTitle();
    this.description = auctionCreateDto.getDescription();
    this.image_URL = auctionCreateDto.getImage_URL();
    this.auctionOwner = auctionOwner;
  }

  @ManyToOne
  @JoinColumn(name = "user_id")
  @JsonManagedReference(value = "auctionOwner")
  private User auctionOwner;

  @OneToMany(mappedBy = "bidAuction", cascade = CascadeType.ALL)
  @JsonBackReference(value = "bidAuctions")
  private Set<Bid> auctionBids;

}
