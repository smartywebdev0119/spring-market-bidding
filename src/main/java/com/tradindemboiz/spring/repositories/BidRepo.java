package com.tradindemboiz.spring.repositories;

import com.tradindemboiz.spring.entities.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidRepo extends JpaRepository<Bid, Long> {
}
