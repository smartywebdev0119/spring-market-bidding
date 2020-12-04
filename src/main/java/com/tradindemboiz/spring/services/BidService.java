package com.tradindemboiz.spring.services;

import com.tradindemboiz.spring.dtos.BidCreateDto;
import com.tradindemboiz.spring.dtos.SocketDto;
import com.tradindemboiz.spring.entities.Auction;
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

    @Autowired
    UserService userService;

    @Autowired
    SocketService socketService;

    public List<Bid> getAllBidsByAuctionId(long auctionId) {
        if (auctionRepo.existsById(auctionId)) {
            return bidRepo.findAllByAuctionId(auctionId);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "An auction with that id does not exist.");
    }

    public List<Bid> getAllBidsByUserId(long userId) {
        if (userRepo.existsById(userId)) {
            return bidRepo.findAllByUserId(userId);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "An user with that id does not exist.");
    }

    public Bid addBid(Long auctionId, BidCreateDto bidCreateDto) {
        var user = userService.getCurrentUser();
        // TODO: Remove when authentication works.
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You must be logged in to bid.");
        }
        var auction = auctionRepo.findById(auctionId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The auction with that id does not exist."));
        var newBid = new Bid(bidCreateDto);
        checkBidValidity(auction, newBid);
        newBid.setBidOwner(user);
        newBid.setBidAuction(auction);
        var savedBid = bidRepo.save(newBid);

        socketService.prepareSendToAll(new SocketDto("newBid", savedBid));

        return savedBid;
    }

    private void checkBidValidity(Auction auction, Bid newBid) {
        checkBidTimeValidity(auction.getEnd_date(), newBid.getTimestamp());
        checkBidPriceValidity(auction, newBid.getBid_price());
    }

    // Checks if bid is placed before end of auction
    private void checkBidTimeValidity(long auctionEnd, long newBidTime) {
        if (newBidTime > auctionEnd) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Auction has ended.");
        }
    }

    // Checks if placed bid is higher or equal to starting price and higher than current highest bid.
    private void checkBidPriceValidity(Auction auction, int newBid) {
        Bid highestBid = null;
        try {
            highestBid = bidRepo.findAllByAuctionId(auction.getAuction_id()).get(0);
        } catch (IndexOutOfBoundsException e) {
        }
        if(highestBid != null && highestBid.getBid_price() >= newBid) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot place a bid lower than or equal to current highest bid.");
        }
        if (auction.getStart_price() > newBid) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot place a bid lower than starting price.");
        }
    }
}
