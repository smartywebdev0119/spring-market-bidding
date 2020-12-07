package com.tradindemboiz.spring.entities;

import com.tradindemboiz.spring.dto.AuctionCreateDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
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
    this.timestamp = new Date().getTime();
    this.end_date = auctionCreateDto.getEnd_date();
    this.start_price = auctionCreateDto.getStart_price();
    this.title = auctionCreateDto.getTitle();
    this.description = auctionCreateDto.getDescription();
    //this.image_URL = auctionCreateDto.getImage_URL();
    this.auctionOwner = auctionOwner;
  }

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User auctionOwner;

  @OneToMany
  @JoinColumn(name = "auction_id")
  @OrderBy("bid_price DESC")
  private Set<Bid> bids;

  @OneToMany
  @JoinColumn(name = "auction_id")
  private Set<Image> images;


}
