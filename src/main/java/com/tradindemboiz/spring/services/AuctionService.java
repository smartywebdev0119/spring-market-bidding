package com.tradindemboiz.spring.services;

import com.tradindemboiz.spring.entities.Auction;
import com.tradindemboiz.spring.repositories.AuctionRepo;
import com.tradindemboiz.spring.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AuctionService {

  @Autowired
  AuctionRepo auctionRepo;

  @Autowired
  UserRepo userRepo;

  public List<Auction> getAllAuctions(String searchString) {
    if (searchString != null) {
      // Query after an auction with a search string.
      return null;
    }
    return auctionRepo.findAll();
  }

  public List<Auction> getAllAuctionsByUserId(long userId){
    if (userRepo.existsById(userId)) {
      return auctionRepo.findAllByUserId(userId);
    }
    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "An user with that id does not exist.");

  }

  public Auction getAuctionById(long id) {
    return auctionRepo.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The auction with that id does not exist."));
  }

  public Auction addAuction(Auction auction) {
    return auctionRepo.save(auction);
  }

  public void updateAuction(Auction auction, long id) {
    if (!auctionRepo.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The auction with that id does not exist.");
    }
    auction.setAuction_id(id);
    auctionRepo.save(auction);
  }

  public void deleteAuction(long id) {
    if (!auctionRepo.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The auction with that id does not exist.");
    }
    auctionRepo.deleteById(id);
  }
}
