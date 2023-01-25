package es.codeurjc.rest.items;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ItemRepositoryTest {

	@Autowired
    private MockMvc mvc;

	@MockBean
	private ItemRepository itemRepository;

	@Test
	public void getOneItemTest() throws Exception {

		when(itemRepository.findById(anyLong())).thenReturn(Optional.of(new Item("Leche", false)));
		
		mvc.perform(
			get("/items/1")
				.contentType(MediaType.APPLICATION_JSON)
		  )
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.description", equalTo("Leche")));
	}

}
