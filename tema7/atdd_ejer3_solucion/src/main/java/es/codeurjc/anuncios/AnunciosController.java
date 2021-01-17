package es.codeurjc.anuncios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/anuncios")
public class AnunciosController {

	@Autowired
	private AnunciosRepository repository;

	@GetMapping("/")
	public List<Anuncio> getAnuncios() {
		return repository.findAll();
	}

	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public Anuncio nuevoAnuncio(@RequestBody Anuncio anuncio) {
		return repository.save(anuncio);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Anuncio> verAnuncio(@PathVariable long id) {
		Optional<Anuncio> anuncio =  repository.findById(id);
		if(anuncio.isPresent()){
			return new ResponseEntity<>(anuncio.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Anuncio> borrarAnuncio(@PathVariable long id) {
		
		Optional<Anuncio> anuncioToDelete = repository.findById(id);
		if (anuncioToDelete.isPresent()) {
			repository.delete(anuncioToDelete.get());
			return new ResponseEntity<>(anuncioToDelete.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}