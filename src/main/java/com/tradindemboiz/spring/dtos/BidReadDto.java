package com.tradindemboiz.spring.dtos;

import com.tradindemboiz.spring.entities.Bid;
import lombok.Data;

@Data
public class BidReadDto {
    private long bidId;
    private long timestamp;
    private int bidPrice;
    private long bidOwnerId;
    private long bidAuctionId;

    public BidReadDto(Bid bid) {
        bidId = bid.getBid_id();
        timestamp = bid.getTimestamp();
        bidPrice = bid.getBid_price();
        bidOwnerId = bid.getBidOwner().getUser_id();
        bidAuctionId = bid.getBidAuction().getAuction_id();
    }
}
