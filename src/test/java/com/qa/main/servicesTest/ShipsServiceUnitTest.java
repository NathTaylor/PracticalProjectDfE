package com.qa.main.servicesTest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.domain.Ships;
import com.qa.repo.ShipsRepo;
import com.qa.services.ShipService;

@RunWith(MockitoJUnitRunner.class)
public class ShipsServiceUnitTest {
	
	@InjectMocks
	private ShipService service;
	
	@Mock
	private ShipsRepo repo;
	
	@Test
	public void addShipTest() {
		Ships input = new Ships("Warspite", "Ultra Rare", 116, "6 Star");
		Ships output = new Ships(1L,"Warspite", "Ultra Rare", 116, "6 Star");
		
		Mockito.when(this.repo.saveAndFlush(input)).thenReturn(output);
		
		assertEquals(output, this.service.addShip(input));
		
		Mockito.verify(this.repo, Mockito.times(1)).saveAndFlush(input);
	}
	
	@Test
	public void getAllShipsTest() {
		List<Ships> output = new ArrayList<>();
		output.add(new Ships("Warspite", "Ultra Rare", 116, "6 Star"));
		
		Mockito.when(this.repo.findAll()).thenReturn(output);
		
		assertEquals(output, this.service.getAllShips());
		
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
	
	@Test
	public void findByIdTest() {
		Long input = 1L;
		Ships output = new Ships("Warspite", "Ultra Rare", 116, "6 Star");
		Optional<Ships> opt = Optional.of(output);
		
		Mockito.when(this.repo.findById(input)).thenReturn(opt);
		
		assertEquals(output, this.service.findById(input));
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(input);
	}
	
	@Test
	public void updateShipsTest() {
		Long input = 1L;
		Ships output = new Ships("Warspite", "Ultra Rare", 116, "6 Star");
		Optional<Ships> opt = Optional.of(output);
		
		
		Mockito.when(this.repo.findById(input)).thenReturn(opt);
		Mockito.when(this.repo.saveAndFlush(output)).thenReturn(output);
		
		assertEquals(output, this.service.updateShips(output, input));
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(input);
		
	}
	
	@Test
	public void removeShipTest() throws Exception {
		Long input = 1L;
				
		Mockito.when(this.repo.existsById(input)).thenReturn(true, false);
		
		assertEquals(true, this.service.removeShip(input));
		
		Mockito.verify(this.repo, Mockito.times(2)).existsById(input);
	}
	
	
	
	
	
	
	
	
}
