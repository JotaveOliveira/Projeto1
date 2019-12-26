package com.controle.estoque.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.controle.estoque.dao.ProdutoDao;
import com.controle.estoque.entity.Produto;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoDao produtoDao;
	
	@RequestMapping("/form")
	public ModelAndView form(Produto produto) {
		ModelAndView modelAndView = new ModelAndView("produtos/form");
		return modelAndView;
		
	}
	
	@GetMapping
	public ModelAndView listar() {
		List<Produto> produtos = produtoDao.listar();
		ModelAndView modelAndView = new ModelAndView("produtos/lista");
		modelAndView.addObject("produtos", produtos);
		return modelAndView;
	}
	
	@PostMapping
	public ModelAndView gravar(MultipartFile sumario, @Valid Produto produto, BindingResult result, 
			RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			return form(produto);
		}
	
		produtoDao.gravar(produto);
		
		redirectAttributes.addFlashAttribute("message", "Produto cadastrado com sucesso!");
		
		return new ModelAndView("redirect:/produtos");
	}
	

}
