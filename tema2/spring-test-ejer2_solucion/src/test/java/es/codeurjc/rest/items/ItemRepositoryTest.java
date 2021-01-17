package es.codeurjc.rest.items;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class ItemRepositoryTest {
	
	@Test
	public void getAllItemsTest() throws Exception {

		ItemsRepository itemsRepository = new ItemsRepository();

		Collection<Item> items = itemsRepository.getAllItems();
		
		assertThat(items).hasSize(1);
	}

	@Test
	public void getOneItemTest() throws Exception {

		ItemsRepository itemsRepository = new ItemsRepository();

		Item item = itemsRepository.getItem(new Long(1));
		
		assertThat(item.getDescription()).isEqualTo("Leche");
	}

	@Test
	public void postItemTest() throws Exception {

		ItemsRepository itemsRepository = new ItemsRepository();

		Item item = new Item();
		String itemDescription = "Pan";
		boolean itemCheked = false;
		item.setDescription(itemDescription);
		item.setChecked(itemCheked);

		Item postedItem = itemsRepository.postItem(item);
		
		assertThat(postedItem.getDescription()).isEqualTo(itemDescription);
	}

	@Test
	public void putItemTest() throws Exception {

		ItemsRepository itemsRepository = new ItemsRepository();

		Item item = new Item();
		String itemDescription = "Leche merengada";
		boolean itemCheked = true;
		item.setDescription(itemDescription);
		item.setChecked(itemCheked);

		Item putItem = itemsRepository.putItem(new Long(1),item);
		
		assertThat(putItem.getDescription()).isEqualTo(itemDescription);
	}

	@Test
	public void removeItemTest() throws Exception {

		ItemsRepository itemsRepository = new ItemsRepository();

		itemsRepository.removeItem(new Long(1));
		
		Collection<Item> items = itemsRepository.getAllItems();
		
		assertThat(items).hasSize(0);
	}

}
