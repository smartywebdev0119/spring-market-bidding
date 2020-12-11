package com.tradindemboiz.spring.controllers;

import com.tradindemboiz.spring.dto.AuctionCreateDto;
import com.tradindemboiz.spring.entities.Auction;
import com.tradindemboiz.spring.entities.Bid;
import com.tradindemboiz.spring.entities.User;
import com.tradindemboiz.spring.services.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/auctions")
public class AuctionController {

  @Autowired
  AuctionService auctionService;

  @GetMapping
  public ResponseEntity<List<Auction>> getAllAuctions(@RequestParam(required = false) String search) {
    var auctions = auctionService.getAllAuctions(search);
    return ResponseEntity.ok(auctions);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Auction> getAuctionById(@PathVariable long id) {
    var auction = auctionService.getAuctionById(id);
    return ResponseEntity.ok(auction);
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<Auction>> getAllAuctionsByUserId(@PathVariable long userId) {
    var auctions = auctionService.getAllAuctionsByUserId(userId);
    return ResponseEntity.ok(auctions);
  }

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Auction> addAuction(@RequestBody AuctionCreateDto auctionToAdd) {
    System.out.println("INNAN SERVICE");
    var newAuction = auctionService.addAuction(auctionToAdd);
    var uri = URI.create("/api/v1/auctions/" + newAuction.getAuction_id());
    return ResponseEntity.created(uri).body(newAuction);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void updateAuction(@Validated @RequestBody Auction auction, @PathVariable long id) {
    auctionService.updateAuction(auction, id);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteAuction(@PathVariable long id) {
    auctionService.deleteAuction(id);
  }

}
