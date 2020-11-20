package com.tradindemboiz.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
  private long userId;
  private String username;
  private String email;
  private String password;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  @JsonIgnoreProperties("user")
  private Set<Bid> bids;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  @JsonIgnoreProperties("user")
  private Set<Auction> auctions;

  @JsonIgnore
  public String getPassword() {
    return password;
  }

  @JsonProperty
  public void setPassword(String password) {
    this.password = password;
  }
}


