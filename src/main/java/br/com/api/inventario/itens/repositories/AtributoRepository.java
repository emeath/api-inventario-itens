package br.com.api.inventario.itens.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.inventario.itens.entities.Atributo;

@Repository
public interface AtributoRepository extends JpaRepository<Atributo, Long> {

}
