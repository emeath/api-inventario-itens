package br.com.api.inventario.itens.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.api.inventario.itens.dto.ItemDTO;
import br.com.api.inventario.itens.entities.Atributo;
import br.com.api.inventario.itens.entities.Item;
import br.com.api.inventario.itens.repositories.AtributoRepository;
import br.com.api.inventario.itens.repositories.ItemRepository;
import br.com.api.inventario.itens.services.exceptions.DatabaseException;
import br.com.api.inventario.itens.services.exceptions.ResourceNotFoundException;

@Service
public class ItemService {

	private final ItemRepository itemRepository;
	private final AtributoRepository atributoRepository;

	public ItemService(ItemRepository itemRepository, AtributoRepository atributoRepository) {
		this.itemRepository = itemRepository;
		this.atributoRepository = atributoRepository;
	}

	@Transactional
	public ItemDTO create(ItemDTO itemDTO) {
		Item item = new Item();
		copiarDTOparaEntidade(itemDTO, item);
		Item itemArmazenado = itemRepository.save(item);
		return new ItemDTO(itemArmazenado, itemArmazenado.getAtributos());
	}

	@Transactional
	public ItemDTO update(Long id, ItemDTO itemDto) {
		try {
			Item item = itemRepository.getReferenceById(id);
			copiarDTOparaEntidade(itemDto, item);
			Item itemUpdated = itemRepository.save(item);
			return new ItemDTO(itemUpdated, itemUpdated.getAtributos());
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Item de ID: " + id + " não encontrado!");
		}
	}
	
	@Transactional(readOnly = true)
	public Page<ItemDTO> retrieveAllPaged(PageRequest pageRequest) {
		Page<Item> page = itemRepository.findAll(pageRequest);
		return page.map(entidade -> new ItemDTO(entidade, entidade.getAtributos()));
	}

	@Transactional(readOnly = true)
	public ItemDTO retrieveById(Long id) {
		Optional<Item> itemOptional = itemRepository.findById(id);
		Item itemFound = itemOptional.orElseThrow(() -> new ResourceNotFoundException("Item de ID: " + id + " não encontrado!"));
		return new ItemDTO(itemFound, itemFound.getAtributos());
	}
	
	public void delete(Long id) {
		try {
			itemRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Item de ID: " + id + " não encontrado!");
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Violação de integridade. Item está vinculado a outras entidades");
		}
	}
	
	private void copiarDTOparaEntidade(ItemDTO dto, Item entidade) {

		if (dto.getCusto() != null)
			entidade.setCusto(dto.getCusto());

		if (dto.getDescricao() != null)
			entidade.setDescricao(dto.getDescricao());

		if (dto.getNome() != null)
			entidade.setNome(dto.getNome());

		entidade.getAtributos().clear();

		dto.getAtributos().forEach(atributoDto -> {
			Atributo atributo = atributoRepository.getReferenceById(atributoDto.getId());
			entidade.getAtributos().add(atributo);
		});

	}
	
}
