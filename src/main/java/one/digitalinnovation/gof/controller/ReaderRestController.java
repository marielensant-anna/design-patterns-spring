package one.digitalinnovation.gof.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import one.digitalinnovation.gof.model.Reader;
import one.digitalinnovation.gof.service.ReaderService;

/**
 * Esse {@link RestController} representa nossa <b>Facade</b>, pois abstrai toda
 * a complexidade de integrações (Banco de Dados H2 e API da Brasil API ISBN) em uma
 * interface simples e coesa (API REST).
 * 
 */
@RestController
@RequestMapping("readers")
public class ReaderRestController {

	@Autowired
	private ReaderService readerService;

	@GetMapping
	public ResponseEntity<Iterable<Reader>> findAll() {
		return ResponseEntity.ok(readerService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Reader> findById(@PathVariable Long id) {
		return ResponseEntity.ok(readerService.findById(id));
	}

	@PostMapping
	public ResponseEntity<Reader> save(@RequestBody Reader reader) {
		readerService.save(reader);
		return ResponseEntity.ok(reader);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Reader> update(@PathVariable Long id, @RequestBody Reader reader) {
		readerService.update(id, reader);
		return ResponseEntity.ok(reader);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		readerService.delete(id);
		return ResponseEntity.ok().build();
	}
}
