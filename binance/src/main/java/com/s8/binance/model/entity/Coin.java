package com.s8.binance.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE COIN SET status = true WHERE id=?")
@Where(clause = "status=false")
// @FilterDef(name = "deletedMarcaFilter",parameters = @ParamDef(name = "isDeleted", type = "boolean"))
// @Filter(name = "deletedMarcaFilter",condition = "deleted = :isDeleted")
@Table(name = "COIN")
public class Coin {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    @NotBlank(message = "Empty")
    private String name;

    @Column(name = "STATUS")
    @Default
    private boolean status = Boolean.FALSE;
}