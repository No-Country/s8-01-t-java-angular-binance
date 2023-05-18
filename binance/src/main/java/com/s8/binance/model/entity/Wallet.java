package com.s8.binance.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "WALLET")
public class Wallet {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "CRYPTO_ASSET")
//    private List<CryptoAsset> cryptoAssets;
//
//    @Column(name = "FIAT_ASSET")
//    private List<FiatAsset> fiatAssets;

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL)
    private List<CryptoAsset> cryptoAssets;

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL)
    private List<FiatAsset> fiatAssets;
}
