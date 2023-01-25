package es.codeurjc.rest.items;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class ItemRepositoryTest {

	@Autowired
    private MockMvc mvc;

	@MockBean
	private ItemRepository itemRepository;

	@Autowired
	ObjectMapper objectMapper;

	private Item itemPrueba1;
	private Item itemPrueba2;

	@BeforeEach
	public void setUp() throws Exception {

		itemPrueba1 = new Item();
		itemPrueba1.setDescription("Leche");
		itemPrueba1.setChecked(false);

		itemPrueba2 = new Item();
		itemPrueba2.setDescription("Pan");
		itemPrueba2.setChecked(true);
	}
	
	@Test
	public void getAllItemsTest() throws Exception {

		when(itemRepository.findAll()).thenReturn(Arrays.asList(itemPrueba1, itemPrueba2));
		
		mvc.perform(
			get("/items/")
				.contentType(MediaType.APPLICATION_JSON)
		  )
          .andExpect(status().isOk())
          .andExpect(jsonPath("$", hasSize(2)));
	}

	@Test
	public void getOneItemTest() throws Exception {

		when(itemRepository.findById(anyLong())).thenReturn(Optional.of(itemPrueba1));
		
		mvc.perform(
			get("/items/1")
				.contentType(MediaType.APPLICATION_JSON)
		  )
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.description", equalTo(itemPrueba1.getDescription())));
	}

	@Test
	public void getOneItemTest_notFound() throws Exception {

		when(itemRepository.findById(anyLong())).thenReturn(Optional.empty());
		
		mvc.perform(
			get("/items/1")
				.contentType(MediaType.APPLICATION_JSON)
		  )
          .andExpect(status().isNotFound());
	}

	@Test
	public void postItemTest() throws Exception {

		Item createdItem = new Item();
		createdItem.setId(1L);
		createdItem.setChecked(itemPrueba1.isChecked());
		createdItem.setDescription(itemPrueba1.getDescription());

		when(itemRepository.save(any(Item.class))).thenReturn(createdItem);
		
		mvc.perform(
			post("/items/")
				.content(objectMapper.writeValueAsString(itemPrueba1))
				.contentType(MediaType.APPLICATION_JSON)
		  )
          .andExpect(status().isCreated())
		  .andExpect(jsonPath("$.description", equalTo(itemPrueba1.getDescription())))
		  .andExpect(jsonPath("$.description", equalTo(itemPrueba1.getDescription())));
	}

	@Test
	public void putItemTest() throws Exception {

		Item updatedItem = new Item();
		updatedItem.setId(1L);
		updatedItem.setChecked(itemPrueba1.isChecked());
		updatedItem.setDescription(itemPrueba1.getDescription());

		when(itemRepository.save(any(Item.class))).thenReturn(updatedItem);
		when(itemRepository.findById(anyLong())).thenReturn(Optional.of(itemPrueba1));

		mvc.perform(
			put("/items/1")
				.content(objectMapper.writeValueAsString(itemPrueba1))
				.contentType(MediaType.APPLICATION_JSON)
		  )
          .andExpect(status().isOk())
		  .andExpect(jsonPath("$.description", equalTo(itemPrueba1.getDescription())))
		  .andExpect(jsonPath("$.description", equalTo(itemPrueba1.getDescription())));
	}

	@Test
	public void removeItemTest() throws Exception {

		when(itemRepository.findById(anyLong())).thenReturn(Optional.of(itemPrueba1));

		mvc.perform(delete("/items/1")
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk());
	}

}
