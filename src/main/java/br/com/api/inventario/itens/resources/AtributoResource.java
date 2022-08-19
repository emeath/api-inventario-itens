package br.com.api.inventario.itens.resources;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.inventario.itens.dto.AtributoDTO;
import br.com.api.inventario.itens.services.AtributoService;

@RestController
@RequestMapping("/atributos")
public class AtributoResource {
	
	private final AtributoService atributoService;
	
	public AtributoResource(AtributoService atributoService) {
		this.atributoService = atributoService;
	}
	
	@GetMapping
	public ResponseEntity<List<AtributoDTO>> buscarTodos(){
		return ResponseEntity.ok().body(atributoService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AtributoDTO> buscarPorId(@PathVariable(value = "id") Long id) {
		return ResponseEntity.ok().body(atributoService.findById(id));
	}

}
