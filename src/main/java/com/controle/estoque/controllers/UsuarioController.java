package com.controle.estoque.controllers;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.controle.estoque.dao.UsuarioDao;
import com.controle.estoque.entity.Usuario;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioDao dao;

	@RequestMapping("usuarios/form")
	private ModelAndView form(Usuario usuario) {
		ModelAndView modelAndView = new ModelAndView("usuarios/form");
		return modelAndView;
	}

	@PostMapping(value = "usuarios/form")
	@Transactional
	public ModelAndView gravar(@Valid Usuario usuario, BindingResult result, RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return form(usuario);
		}
		dao.gravar(usuario);

		redirectAttributes.addFlashAttribute("message", "Usuário " + usuario.getNome() + " cadastrado com sucesso!");

		return new ModelAndView("redirect:/usuarios");
	}

	@GetMapping("/usuarios")
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("/usuarios");
		modelAndView.addObject("usuarios", dao.listar());
		return modelAndView;
	}
}
