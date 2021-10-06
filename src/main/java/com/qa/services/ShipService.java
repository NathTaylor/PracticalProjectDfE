package com.qa.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qa.domain.Ships;
import com.qa.repo.ShipsRepo;

@Service
public class ShipService {

	private ShipsRepo repo;

	public ShipService(ShipsRepo repo) {
        this.repo = repo;
	}

	// Create
	public Ships addShip(Ships ship) {
		return this.repo.saveAndFlush(ship);
	}

	// Read
	public List<Ships> getAllShips() {
		return this.repo.findAll();
	}
	
	// Read by id
	public Ships findById(Long id) {
		return this.repo.findById(id).orElseThrow();
	}

	// Update
	public Ships updateShips(Ships ship, Long id) {
		Ships exists = this.repo.findById(id).orElseThrow();
		exists.setLevel(ship.getLevel());
		exists.setShipName(ship.getShipName());
		exists.setRarity(ship.getRarity());
		exists.setLimitBreak(ship.getLimitBreak());
		return this.repo.saveAndFlush(exists);
	}

	// Delete
	public boolean removeShip(Long id) throws Exception {
		if (!this.repo.existsById(id)) {
			throw new Exception("No Ship Found");
		}
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}
}
