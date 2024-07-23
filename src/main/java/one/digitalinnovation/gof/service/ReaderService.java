package one.digitalinnovation.gof.service;

import one.digitalinnovation.gof.model.Reader;

/**
 * Interface que define o padrão <b>Strategy</b> no domínio de reader. Com
 * isso, se necessário, podemos ter multiplas implementações dessa mesma
 * interface.
 */
public interface ReaderService {

	Iterable<Reader> findAll();

	Reader findById(Long id);

	void save(Reader reader);

	void update(Long id, Reader reader);

	void delete(Long id);

}
