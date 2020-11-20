package com.tradindemboiz.spring.services;

import com.tradindemboiz.spring.entities.Bid;
import com.tradindemboiz.spring.repositories.AuctionRepo;
import com.tradindemboiz.spring.repositories.BidRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BidService {

  @Autowired
  BidRepo bidRepo;

  @Autowired
  AuctionRepo auctionRepo;

  public List<Bid> getAllBidsByAuctionId(long auctionId) {
    if (auctionRepo.existsById(auctionId)) {
      return bidRepo.findAllByAuctionId(auctionId);
    }
    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "An auction with that id does not exist.");
  }

  public Bid addBid(Bid bid) {
    return bidRepo.save(bid);
  }
}
