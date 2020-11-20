package com.tradindemboiz.spring.repositories;

import com.tradindemboiz.spring.entities.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidRepo extends JpaRepository<Bid, Long> {

  @Query(value = "SELECT * FROM bids WHERE auction_id = :auctionId", nativeQuery = true)
  List<Bid> findAllByAuctionId(@Param("auctionId") long auctionId);

  @Query(value = "SELECT * FROM bids WHERE user_id = :userId", nativeQuery = true)
  List<Bid> findAllByUserId(@Param("userId") long userId);
}
