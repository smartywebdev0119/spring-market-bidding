package com.tradindemboiz.spring.repositories;

import com.tradindemboiz.spring.entities.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuctionRepo extends JpaRepository<Auction, Long> {

  @Query(value = "SELECT * FROM auctions WHERE user_id = :userId", nativeQuery = true)
  List<Auction> findAllByUserId(@Param("userId") long userId);

  @Query(value = "SELECT * FROM auctions WHERE title LIKE %:searchQuery% OR description LIKE %:searchQuery%", nativeQuery = true)
  List<Auction> findAllAuctionBySearchQuery(@Param("searchQuery") String searchQuery);
}