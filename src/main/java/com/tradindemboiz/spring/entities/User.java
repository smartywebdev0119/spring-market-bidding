package com.tradindemboiz.spring.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long user_id;
  private String username;
  private String email;
  private String password;

  @OneToMany(mappedBy = "bidOwner", cascade = CascadeType.ALL)
  @JsonBackReference(value = "bidOwner")
  private Set<Bid> userBids;

  @OneToMany(mappedBy = "auctionOwner", cascade = CascadeType.ALL)
  @JsonBackReference(value = "auctionOwner")
  private Set<Auction> userAuctions;

  @JsonIgnore
  public String getPassword() {
    return password;
  }

  @JsonProperty
  public void setPassword(String password) {
    this.password = password;
  }

}


