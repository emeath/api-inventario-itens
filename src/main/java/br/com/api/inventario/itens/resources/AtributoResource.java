package br.com.api.inventario.itens.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.api.inventario.itens.dto.AtributoDTO;
import br.com.api.inventario.itens.services.AtributoService;

@RestController
@RequestMapping("/atributos")
public class AtributoResource {
	
	private final AtributoService atributoService;
	
	public AtributoResource(AtributoService atributoService) {
		this.atributoService = atributoService;
	}
	
	@PostMapping
	public ResponseEntity<AtributoDTO> inserir(@RequestBody @Valid AtributoDTO atributoDTO){
		AtributoDTO inserido = atributoService.insert(atributoDTO);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(inserido.getId())
				.toUri();
		return ResponseEntity.created(uri).body(inserido);
	}
	
	@GetMapping
	public ResponseEntity<Page<AtributoDTO>> buscarTodos(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="12") Integer linesPerPage,
			@RequestParam(value="direction", defaultValue="ASC") String direction,
			@RequestParam(value="orderBy", defaultValue="beneficio") String orderBy){
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		Page<AtributoDTO> atributoDtoPage = atributoService.findAllPaged(pageRequest);
		return ResponseEntity.ok().body(atributoDtoPage);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AtributoDTO> buscarPorId(@PathVariable(value = "id") Long id) {
		return ResponseEntity.ok().body(atributoService.findById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<AtributoDTO> update(
			@PathVariable(value = "id") Long id,
			@RequestBody AtributoDTO dto) {
		AtributoDTO updatedDto = atributoService.update(id, dto);
		return ResponseEntity.ok().body(updatedDto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable(value="id") Long id) {
		atributoService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	

}
