package com.qa.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ships {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(length = 30, nullable = false)
	private String shipName;
	@Column(length = 16, nullable = false)
	private String rarity;
	@Column(nullable = false)
	private int level;
	@Column(nullable = false)
	private String limitBreak;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShipName() {
		return shipName;
	}

	public void setShipName(String shipName) {
		this.shipName = shipName;
	}

	public String getRarity() {
		return rarity;
	}

	public void setRarity(String rarity) {
		this.rarity = rarity;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getLimitBreak() {
		return limitBreak;
	}

	public void setLimitBreak(String limitBreak) {
		this.limitBreak = limitBreak;
	}

	public Ships(Long id, String shipName, String rarity, int level, String limitBreak) {
		super();
		this.id = id;
		this.shipName = shipName;
		this.rarity = rarity;
		this.level = level;
		this.limitBreak = limitBreak;
	}

	public Ships(String shipName, String rarity, int level, String limitBreak) {
		super();
		this.shipName = shipName;
		this.rarity = rarity;
		this.level = level;
		this.limitBreak = limitBreak;

	}

	public Ships() {
		super();
	}

	@Override
	public String toString() {
		return "Ship [id=" + id + ", shipName=" + shipName + ", rarity=" + rarity + ", level=" + level + ", limitBreak="
				+ limitBreak + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, level, limitBreak, rarity, shipName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ships other = (Ships) obj;
		return id == other.id && level == other.level && Objects.equals(limitBreak, other.limitBreak)
				&& Objects.equals(rarity, other.rarity) && Objects.equals(shipName, other.shipName);
	}

}
