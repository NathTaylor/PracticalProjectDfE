package com.qa.main.controllertest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.domain.Ships;
import com.qa.services.ShipService;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ShipsControllerUnitTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@MockBean
	private ShipService service;
	
	@Test
	public void createTest() throws Exception {
		Ships entry = new Ships("Warspite", "Ultra Rare", 116, "6 Star");
		String entryAsJSON = this.mapper.writeValueAsString(entry);
		
		Mockito.when(this.service.addShip(entry)).thenReturn(entry);
		
		mvc.perform(post("/Ships/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(entryAsJSON))
		        .andExpect(status().isAccepted())
		        .andExpect(content().json(entryAsJSON));
	}


    @Test
    public void getAllTest() throws Exception {
    	List<Ships> output = new ArrayList<>();
		output.add(new Ships("Warspite", "Ultra Rare", 116, "6 Star"));
		String entryAsJSON = this.mapper.writeValueAsString(output);
		
		 Mockito.when(this.service.getAllShips()).thenReturn(output);
		
		mvc.perform(get("/Ships/getAll"))
				.andExpect(status().isOk())
		        .andExpect(content().json(entryAsJSON));
		
    }
    
    @Test
    public void readIdTest() throws Exception {
    	Long input = 1L;
    	Ships output = new Ships("Warspite", "Ultra Rare", 116, "6 Star");
    	String entryAsJSON = this.mapper.writeValueAsString(output);
    	
    	Mockito.when(this.service.findById(input)).thenReturn(output);
    	
    	mvc.perform(get("/Ships/read/1")
    	        .contentType(MediaType.APPLICATION_JSON)
		        .content(entryAsJSON))
    	        .andExpect(status().isOk())
    	        .andExpect(content().json(entryAsJSON));
    	
    }
    
    @Test
    public void updateTest() throws Exception {
    	Long input = 1L;
    	Ships output = new Ships("Warspite", "Ultra Rare", 116, "6 Star");
    	String entryAsJSON = this.mapper.writeValueAsString(output);
    
        Mockito.when(this.service.updateShips(output, input)).thenReturn(output);
        
        mvc.perform(put("/Ships/update/1")
        		.contentType(MediaType.APPLICATION_JSON)
        		.content(entryAsJSON))
                .andExpect(status().isAccepted())
                .andExpect(content().json(entryAsJSON));
        
    }
    
    
    
    @Test
    public void deleteTest() throws Exception {
    	Long id = 1L;
    	
    	Mockito.when(this.service.removeShip(id)).thenReturn(true);
    	
    	mvc.perform(delete("/Ships/delete/1"))
    	        .andExpect(status().isNoContent());
      }
}




























