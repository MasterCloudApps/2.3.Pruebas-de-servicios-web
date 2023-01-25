package es.codeurjc.rest.items;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repository;
    
    public Collection<Item> getAllItems(){
        return repository.findAll();
    }

    public Item getItem(Long id){
        return repository.findById(id).orElse(null);
    }

    public Item postItem(Item item){
        return repository.save(item);
    }

    public Item putItem(Long id, Item itemActualizado){
        repository.findById(id).orElse(null);
        return repository.save(itemActualizado);
    }

    public Item removeItem(Long id){
        Item item = repository.findById(id).orElse(null);
        repository.deleteById(id);
        return item;
    }

}