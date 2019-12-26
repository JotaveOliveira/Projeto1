package com.controle.estoque.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.controle.estoque.entity.Usuario;

public class UsuarioDao {

	@PersistenceContext
	private EntityManager manager;

	public void gravar(Usuario usuario) {
		manager.persist(usuario);
	}
	public boolean ExistEmail(String email) {
		 List<Usuario> usuarios = manager.createQuery("SELECT U FROM Usuario u WHERE u.email = :email", Usuario.class)
	                .setParameter("email", email).getResultList();

	        if (!usuarios.isEmpty()) {
	            return true;
	        } else
	            return false;
	}
	@Transactional
	public void update(Usuario usuario ) {
		 manager.merge(usuario);
	}
	public List<Usuario> listar() {
		TypedQuery<Usuario> query = manager.createQuery("SELECT u FROM Usuario u " , Usuario.class);
		return query.getResultList();		
	}
}
