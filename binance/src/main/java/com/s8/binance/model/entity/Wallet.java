package com.s8.binance.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.s8.binance.security.entity.User;

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
    @Column(name = "WALLET_ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_socio")
    private User user;

    // @OneToMany(fetch = FetchType.EAGER, mappedBy = "wallet")
    // @JsonIgnore
    // private List<Transaction> transactions = new ArrayList<>();
}
