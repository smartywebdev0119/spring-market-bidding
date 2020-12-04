package com.tradindemboiz.spring.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "images")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String image_URL;
    @ManyToOne
    @JoinColumn(name = "auction_id")
    @JsonManagedReference
    private Auction auction;

    public Image(String imagePath, Auction auction) {
        this.image_URL = imagePath;
        this.auction = auction;
    }
}


