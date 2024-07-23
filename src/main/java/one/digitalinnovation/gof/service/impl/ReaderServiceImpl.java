package one.digitalinnovation.gof.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.digitalinnovation.gof.model.Reader;
import one.digitalinnovation.gof.model.Book;
import one.digitalinnovation.gof.model.ReaderRepository;
import one.digitalinnovation.gof.model.BookRepository;
import one.digitalinnovation.gof.service.ReaderService;
import one.digitalinnovation.gof.service.BrasilApiService;

/**
 * Implementação da <b>Strategy</b> {@link ReaderService}, a qual pode ser
 * injetada pelo Spring (via {@link Autowired}). Com isso, como essa classe é um
 * {@link Service}, ela será tratada como um <b>Singleton</b>.
 */
@Service
public class ReaderServiceImpl implements ReaderService {

	// Singleton: Injetar os componentes do Spring com @Autowired.
	@Autowired
	private ReaderRepository readerRepository;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private BrasilApiService brasilApiService;
	
	// Strategy: Implementar os métodos definidos na interface.
	// Facade: Abstrair integrações com subsistemas, provendo uma interface simples.

	@Override
	public Iterable<Reader> findAll() {
		// Buscar todos os Leitores(Readers).
		return readerRepository.findAll();
	}

	@Override
	public Reader findById(Long id) {
		// Buscar Reader por ID.
		Optional<Reader> reader = readerRepository.findById(id);
		return reader.get();
	}

	@Override
	public void save(Reader reader) {
		saveReaderWithBook(reader);
	}

	@Override
	public void update(Long id, Reader reader) {
		// Buscar Reader por ID, caso exista:
		Optional<Reader> readerBd = readerRepository.findById(id);
		if (readerBd.isPresent()) {
			saveReaderWithBook(reader);
		}
	}

	@Override
	public void delete(Long id) {
		// Deletar Reader por ID.
		readerRepository.deleteById(id);
	}

	private void saveReaderWithBook(Reader reader) {
		// Verificar se o Livro(Book) do Leitor(Reader) já existe (pelo ISBN).
		String isbn = reader.getFavoriteBook().getIsbn();
		Book book = bookRepository.findById(isbn).orElseGet(() -> {
			// Caso não exista, integrar com o Brasil Api ISBN e persistir o retorno.
			Book newBook = brasilApiService.consultarISBN(isbn);
			bookRepository.save(newBook);
			return newBook;
		});
		reader.setFavoriteBook(book);
		// Inserir Reader, vinculando o Book (novo ou existente).
		readerRepository.save(reader);
	}

}
