package es.codeurjc.rest.items;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;

public class ItemRepositoryTest {

	@Test
	public void getOneItemTest() throws Exception {
		
		ItemRepository itemRepository = mock(ItemRepository.class);
		
		ItemService itemService = new ItemService(itemRepository);

		when(itemRepository.findById(anyLong())).thenReturn(Optional.of(new Item("Leche", false)));
		
		Item item = itemService.getItem(1L);

		assertThat(item.getDescription().equals("Leche"));
		
		verify(itemRepository).findById(anyLong());
	}

}
