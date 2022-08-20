package br.com.api.inventario.itens.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.api.inventario.itens.dto.AtributoDTO;
import br.com.api.inventario.itens.entities.Atributo;
import br.com.api.inventario.itens.repositories.AtributoRepository;
import br.com.api.inventario.itens.services.exceptions.ResourceNotFoundException;

@Service
public class AtributoService {
	
	private AtributoRepository atributoRepository;
	
	public AtributoService(AtributoRepository atributoRepository) {
		this.atributoRepository = atributoRepository;
	}

	public List<AtributoDTO> findAll() {
		List<Atributo> atributoList = atributoRepository.findAll();
		return atributoList.stream().map(atributo -> new AtributoDTO(atributo)).collect(Collectors.toList());
	}

	public AtributoDTO findById(Long id) {
		Optional<Atributo> atributoOptional = atributoRepository.findById(id);
		Atributo atributo = atributoOptional.orElseThrow(() -> new ResourceNotFoundException("Atributo não encontrado!"));
		return new AtributoDTO(atributo);
	}

	public Page<AtributoDTO> findAllPaged(PageRequest pageRequest) {
		Page<Atributo> atributoPage = atributoRepository.findAll(pageRequest);
		return atributoPage.map(x -> new AtributoDTO(x));
	}

	public AtributoDTO insert(AtributoDTO atributoDTO) {
		Atributo atributo = new Atributo();
		copiaDtoParaEntidade(atributoDTO, atributo);
		Atributo inserido = atributoRepository.save(atributo);
		return new AtributoDTO(inserido);
	}

	private void copiaDtoParaEntidade(AtributoDTO atributoDTO, Atributo atributo) {
		atributo.setBeneficio(atributoDTO.getBeneficio());
		atributo.setDescricao(atributoDTO.getDescricao());
	}

}
