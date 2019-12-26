package com.controle.estoque.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.controle.estoque.entity.Produto;

@Repository
@Transactional
public class ProdutoDao {
	
	@PersistenceContext
	private EntityManager manager;
	
	public void gravar(Produto produto) {
		manager.persist(produto);
		
	}

	public List<Produto> listar() {
		return manager.createQuery("Select distinct (p) from Prdutos", Produto.class)
				.getResultList();
		
	}

}
