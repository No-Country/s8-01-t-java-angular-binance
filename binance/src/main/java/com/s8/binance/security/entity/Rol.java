package com.s8.binance.security.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.s8.binance.security.enums.RolName;

//@AllArgsConstructor
//@NoArgsConstructor
@Entity
public class Rol {
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int id;

	@NotNull
	//Se indica que va a ser un Enum de tipo String
	@Enumerated(EnumType.STRING)
	private RolName rolName;

	public Rol() {
	}

	public Rol(@NotNull RolName rolName) {
		this.rolName = rolName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public RolName getRolName() {
		return rolName;
	}

	public void setRolName(RolName rolName) {
		this.rolName = rolName;
	}
}