package es.codeurjc.rest.items;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.Matchers.hasSize;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ItemControllerTest {

	@Autowired
	// Este elemento se comparte entre los distintos test
	private MockMvc mvc; 
	
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Test
	@Order(1)
	public void getAllItemsTest() throws Exception {
		
	    mvc.perform(get("/items/")
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk())
		  .andExpect(jsonPath("$", hasSize(1)))
		  .andExpect(jsonPath("$[0].description", equalTo("Leche")))
	      .andExpect(jsonPath("$[0].checked", equalTo(true)));
		
	}

	@Test
	@Order(2)
	public void getOneItemTest() throws Exception {
		
	    mvc.perform(get("/items/1")
	      .contentType(MediaType.APPLICATION_JSON))
		  .andExpect(status().isOk())
		  .andExpect(jsonPath("$.description", equalTo("Leche")))
	      .andExpect(jsonPath("$.checked", equalTo(true)));
		
	}

	@Test
	@Order(3)
	public void postItemTest() throws Exception {

		Item item = new Item();
		String itemDescription = "Pan";
		boolean itemCheked = false;
		item.setDescription(itemDescription);
		item.setChecked(itemCheked);
		
	    mvc.perform(
			post("/items/")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(item))
		)
		.andExpect(status().isCreated());

		mvc.perform(get("/items/2")
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk())
		  .andExpect(jsonPath("$.description", equalTo(itemDescription)))
		  .andExpect(jsonPath("$.checked", equalTo(itemCheked)));
		
	}

	@Test
	@Order(4)
	public void putItemTest() throws Exception {

		Item item = new Item();
		String itemDescription = "Leche de soja";
		boolean itemCheked = true;
		item.setDescription(itemDescription);
		item.setChecked(itemCheked);
		
	    mvc.perform(
			put("/items/1")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(item))
		)
		.andExpect(status().isOk());

		mvc.perform(get("/items/1")
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk())
		  .andExpect(jsonPath("$.description", equalTo(itemDescription)))
		  .andExpect(jsonPath("$.checked", equalTo(itemCheked)));
		
	}

	@Test
	@Order(5)
	public void deleteItemTest() throws Exception {
		
		mvc.perform(delete("/items/1")
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk());
		
	}
}
