package com.s8.binance.model.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "WALLETS")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_WALLET")
    private Long id;



//    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL)
//  @Column(name = "TRANSACTIONS")
//    private List<Transaction> transactions = new ArrayList<>();

@OneToMany(fetch = FetchType.EAGER, mappedBy = "wallets")
@JsonIgnore
List<Transaction> transactions =new ArrayList<>();

    // relacion con usuario
    // private User user;
}
