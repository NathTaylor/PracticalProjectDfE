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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.domain.Ships;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:TestSCHMA.sql", "classpath:TestData.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class ShipsControllerIntergrationTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Test
	public void createTest() throws Exception {
	Ships entry = new Ships("Warspite", "Ultra Rare", 116, "6 Star");
	Ships result = new Ships(2L, "Warspite", "Ultra Rare", 116, "6 Star");
	
	String entryAsJSON = this.mapper.writeValueAsString(entry);
	String resultAsJSON = this.mapper.writeValueAsString(result);

	
	mvc.perform(post("/Ships/create")
			.contentType(MediaType.APPLICATION_JSON)
			.content(entryAsJSON))
	        .andExpect(status().isAccepted())
	        .andExpect(content().json(resultAsJSON));
	}
	
	@Test
	public void getAllTest() throws Exception {
	List<Ships> result = new ArrayList<>();
	result.add(new Ships(1L, "Monarch", "Super Rare", 125, "6 Star"));
	
	
	String resultAsJSON = this.mapper.writeValueAsString(result);
	
	mvc.perform(get("/Ships/getAll"))
	        .andExpect(status().isOk())
            .andExpect(content().json(resultAsJSON));
} 
	
	@Test
	public void readIdTest() throws Exception {
		Long input = 1L;
    	Ships output = new Ships(1L, "Monarch", "Super Rare", 125, "6 Star");
    	
    	String entryAsJSON = this.mapper.writeValueAsString(input);
    	String resultAsJSON = this.mapper.writeValueAsString(output);

		
		mvc.perform(get("/Ships/read/1")
    	        .contentType(MediaType.APPLICATION_JSON)
		        .content(entryAsJSON))
    	        .andExpect(status().isOk())
    	        .andExpect(content().json(resultAsJSON));
	}
	
	@Test
	public void updateTest() throws Exception {
		Ships entry = new Ships("Monarch", "Super Rare", 126, "6 Star");
    	Ships output = new Ships(1L, "Monarch", "Super Rare", 126, "6 Star");
    	
    	
    	String entryAsJSON = this.mapper.writeValueAsString(entry);
    	String resultAsJSON = this.mapper.writeValueAsString(output);
		
	mvc.perform(put("/Ships/update/1")
            .contentType(MediaType.APPLICATION_JSON)
        	.content(entryAsJSON))
            .andExpect(status().isAccepted())
            .andExpect(content().json(resultAsJSON));
	}
	
	@Test
	public void deleteTest() throws Exception {
		
	mvc.perform(delete("/Ships/delete/1"))
	        .andExpect(status().isNoContent());
	}

}
