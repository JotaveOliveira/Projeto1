package com.controle.estoque.entity;

import javax.persistence.Id;

public class Usuario {
	
	@Id
	private Long id;
	
	private String nome;
	private String email;
	private String cpf;
	private String telefone;
	
}
