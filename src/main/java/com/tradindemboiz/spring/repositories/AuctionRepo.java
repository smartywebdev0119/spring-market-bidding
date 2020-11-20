package com.tradindemboiz.spring.repositories;

import com.tradindemboiz.spring.entities.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionRepo extends JpaRepository<Auction, Long> {
}
