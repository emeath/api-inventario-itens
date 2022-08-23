package br.com.api.inventario.itens.dto;

import java.util.HashSet;
import java.util.Set;

import br.com.api.inventario.itens.entities.Atributo;
import br.com.api.inventario.itens.entities.Item;

public class ItemDTO {

	private Long id;
	private String nome;
	private String descricao;
	private Integer custo;

	private Set<AtributoDTO> atributos = new HashSet<>();

	public ItemDTO() {

	}

	public ItemDTO(Long id, String nome, String descricao, Integer custo) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.custo = custo;
	}

	public ItemDTO(Item item) {
		this.id = item.getId();
		this.nome = item.getNome();
		this.descricao = item.getDescricao();
		this.custo = item.getCusto();
	}

	public ItemDTO(Item item, Set<Atributo> atributos) {
		this(item);
		atributos.forEach(atributo -> this.atributos.add(new AtributoDTO(atributo)));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getCusto() {
		return custo;
	}

	public void setCusto(Integer custo) {
		this.custo = custo;
	}

	public Set<AtributoDTO> getAtributos() {
		return atributos;
	}

	public void setAtributos(Set<AtributoDTO> atributos) {
		this.atributos = atributos;
	}

}
