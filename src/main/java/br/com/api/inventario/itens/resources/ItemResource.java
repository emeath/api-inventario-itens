package br.com.api.inventario.itens.resources;

import java.net.URI;

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

import br.com.api.inventario.itens.dto.ItemDTO;
import br.com.api.inventario.itens.services.ItemService;

@RestController
@RequestMapping("/itens")
public class ItemResource {

	private final ItemService itemService;
	
	public ItemResource(ItemService itemService) {
		this.itemService = itemService;
	}
	
	@PostMapping
	public ResponseEntity<ItemDTO> create(@RequestBody ItemDTO itemDTO) {
		ItemDTO itemCreated = itemService.create(itemDTO);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(itemCreated.getId())
				.toUri();
		return ResponseEntity.created(uri).body(itemCreated);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ItemDTO> update(
			@PathVariable(value = "id") Long id,
			@RequestBody ItemDTO itemDto) {
		ItemDTO itemUpdated = itemService.update(id, itemDto);
		return ResponseEntity.ok().body(itemUpdated);
		
	}
	
	@GetMapping
	public ResponseEntity<Page<ItemDTO>> retrieveAllPaged(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<ItemDTO> dto = itemService.retrieveAllPaged(pageRequest);
		return ResponseEntity.ok().body(dto);
	}
		
	@GetMapping("/{id}")
	public ResponseEntity<ItemDTO> retriveById(@PathVariable(value = "id") Long id) {
		ItemDTO itemFound = itemService.retrieveById(id);
		return ResponseEntity.ok().body(itemFound);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) {
		itemService.delete(id);
		return  ResponseEntity.noContent().build();
	}
	

}
	
	
