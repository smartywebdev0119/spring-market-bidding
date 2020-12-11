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
    private long auction_id;
    //private boolean is_default;

    public Image(String imagePath, long auction_id) {
        this.image_URL = imagePath;
        this.auction_id = auction_id;
    }
}


