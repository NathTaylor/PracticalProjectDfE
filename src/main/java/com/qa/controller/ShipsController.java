package com.qa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.domain.Ships;
import com.qa.services.ShipService;

@RequestMapping("/Ships")
@RestController
public class ShipsController {
	
	@Autowired
	private ShipService service;

    	
//create
    @PostMapping("/create")
    public ResponseEntity<Ships> addShip(@RequestBody Ships ship) {
        return new ResponseEntity<Ships>(this.service.addShip(ship), HttpStatus.ACCEPTED);
    }

//read
    @GetMapping("/getAll")
    public ResponseEntity<List<Ships>> getAllShips() {
        return new ResponseEntity<List<Ships>>(this.service.getAllShips(), HttpStatus.OK);
    }
    
//read id
    @GetMapping("/read/{id}")
    public ResponseEntity<Ships> findById(@PathVariable Long id) {
    	return new ResponseEntity<Ships>(this.service.findById(id), HttpStatus.OK);
    }
    
//update
    @PutMapping("/update/{id}")
    public ResponseEntity<Ships> updateShips(@PathVariable Long id, @RequestBody Ships ship) {
        return new ResponseEntity<Ships>(this.service.updateShips(ship, id), HttpStatus.ACCEPTED);
    }
    
//delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> removeShip(@PathVariable Long id) throws Exception {
        return new ResponseEntity<Boolean>(this.service.removeShip(id), HttpStatus.NO_CONTENT);
    }

    
    
    
    
    
    
    
    
    
    
    
}
