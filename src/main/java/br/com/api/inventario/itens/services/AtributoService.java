package br.com.api.inventario.itens.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.api.inventario.itens.dto.AtributoDTO;
import br.com.api.inventario.itens.entities.Atributo;
import br.com.api.inventario.itens.repositories.AtributoRepository;
import br.com.api.inventario.itens.services.exceptions.DatabaseException;
import br.com.api.inventario.itens.services.exceptions.ResourceNotFoundException;

@Service
public class AtributoService {

	private AtributoRepository atributoRepository;

	public AtributoService(AtributoRepository atributoRepository) {
		this.atributoRepository = atributoRepository;
	}

	@Transactional(readOnly = true)
	public List<AtributoDTO> findAll() {
		List<Atributo> atributoList = atributoRepository.findAll();
		return atributoList.stream().map(atributo -> new AtributoDTO(atributo)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public AtributoDTO findById(Long id) {
		Optional<Atributo> atributoOptional = atributoRepository.findById(id);
		Atributo atributo = atributoOptional
				.orElseThrow(() -> new ResourceNotFoundException("Atributo não encontrado!"));
		return new AtributoDTO(atributo);
	}

	@Transactional(readOnly = true)
	public Page<AtributoDTO> findAllPaged(PageRequest pageRequest) {
		Page<Atributo> atributoPage = atributoRepository.findAll(pageRequest);
		return atributoPage.map(x -> new AtributoDTO(x));
	}

	@Transactional
	public AtributoDTO insert(AtributoDTO atributoDTO) {
		Atributo atributo = new Atributo();
		copiaDtoParaEntidade(atributoDTO, atributo);
		Atributo inserido = atributoRepository.save(atributo);
		return new AtributoDTO(inserido);
	}

	@Transactional
	public AtributoDTO update(Long id, AtributoDTO dto) {
		try {
			Atributo atributo = atributoRepository.getReferenceById(id);
			copiaDtoParaEntidade(dto, atributo);
			atributo = atributoRepository.save(atributo);
			return new AtributoDTO(atributo);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Atributo de id: " + id + " não encontrado!");
		}
	}

	private void copiaDtoParaEntidade(AtributoDTO atributoDTO, Atributo atributo) {
		
		if(atributoDTO.getBeneficio() != null)
			atributo.setBeneficio(atributoDTO.getBeneficio());
		
		if(atributoDTO.getDescricao() != null) 
			atributo.setDescricao(atributoDTO.getDescricao());
		
	}

	public void delete(Long id) {
		try {
			atributoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Atributo de id: " + id + " não encontrado!");
		}  catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Violação de integridade. Atributo vinculado a outras entidades.");
		}
	}
}
