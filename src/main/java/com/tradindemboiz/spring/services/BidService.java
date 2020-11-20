package com.tradindemboiz.spring.services;

import com.tradindemboiz.spring.entities.Bid;
import com.tradindemboiz.spring.repositories.AuctionRepo;
import com.tradindemboiz.spring.repositories.BidRepo;
import com.tradindemboiz.spring.repositories.UserRepo;
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

  @Autowired
  UserRepo userRepo;

  public List<Bid> getAllBidsByAuctionId(long auctionId) {
    if (auctionRepo.existsById(auctionId)) {
      return bidRepo.findAllByAuctionId(auctionId);
    }
    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "An auction with that id does not exist.");
  }

  public List<Bid> getAllBidsByUserId(long userId){
    if (userRepo.existsById(userId)) {
      return bidRepo.findAllByUserId(userId);
    }
    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "An user with that id does not exist.");
  }

  public Bid addBid(Bid bid) {
    return bidRepo.save(bid);
  }
}
