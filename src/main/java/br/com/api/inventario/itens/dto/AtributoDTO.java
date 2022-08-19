package br.com.api.inventario.itens.dto;

import br.com.api.inventario.itens.entities.Atributo;

public class AtributoDTO {

	private Long id;
	private Double beneficio;
	private String descricao;

	public AtributoDTO() {
	}

	public AtributoDTO(Long id, Double beneficio, String descricao) {
		this.id = id;
		this.beneficio = beneficio;
		this.descricao = descricao;
	}

	public AtributoDTO(Atributo atributo) {
		id = atributo.getId();
		beneficio = atributo.getBeneficio();
		descricao = atributo.getDescricao();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getBeneficio() {
		return beneficio;
	}

	public void setBeneficio(Double beneficio) {
		this.beneficio = beneficio;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
