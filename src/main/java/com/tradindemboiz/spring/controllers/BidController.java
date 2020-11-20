package com.tradindemboiz.spring.controllers;

import com.tradindemboiz.spring.entities.Bid;
import com.tradindemboiz.spring.services.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/bids")
public class BidController {

  @Autowired
  BidService bidService;

  @GetMapping("/auction/{auctionId}")
  public ResponseEntity<List<Bid>> getAllBidsByAuctionId(@PathVariable long auctionId) {
    var bids = bidService.getAllBidsByAuctionId(auctionId);
    return ResponseEntity.ok(bids);
    // Vi hämtar alla bids här på en specifik auktion. Vi sköter sortering tills vidare i frontend.
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<Bid>> getAllBidsByUserId(@PathVariable long userId) {
    var bids = bidService.getAllBidsByUserId(userId);
    return ResponseEntity.ok(bids);
  }


  @PostMapping
  public ResponseEntity<Bid> addBid(@Validated @RequestBody Bid bid) {
    var newBid = bidService.addBid(bid);
    var uri = URI.create("/api/v1/bids/" + newBid.getBid_id());
    return ResponseEntity.created(uri).body(newBid);
  }
}
