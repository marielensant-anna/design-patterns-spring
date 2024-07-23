package one.digitalinnovation.gof.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import one.digitalinnovation.gof.model.Book;

/**
 * Client HTTP, criado via <b>OpenFeign</b>, para o consumo da API da
 * <b>Brasil API ISBN</b>.
 * 
 * @see <a href="https://spring.io/projects/spring-cloud-openfeign">Spring Cloud OpenFeign</a>
 * @see <a href="https://brasilapi.com.br/docs#tag/ISBN">Brasil API ISBN</a>
 */

@FeignClient(name = "brasil-api-isbn", url = "https://brasilapi.com.br/api/")
public interface BrasilApiService {

	@GetMapping("isbn/v1/{isbn}")
	Book consultarISBN(@PathVariable("isbn") String isbn);
}

