package org.generation.italy.demo.controller;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Category;
import org.generation.italy.demo.pojo.Foto;
import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.service.CategoryService;
import org.generation.italy.demo.service.CommentService;
import org.generation.italy.demo.service.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
public class FotoController {

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private FotoService fotoService;
	
	@GetMapping
	public String index(Model model) {
		
		List<Foto> fotos = fotoService.findAllWComment();
		model.addAttribute("fotos", fotos);
		
		return "foto-index";
	}
	
	@GetMapping("/foto/{id}")
	public String show(@PathVariable("id") int id, Model model) {
		
		Optional<Foto> optFoto = fotoService.findById(id);
		
		Foto selectedFoto = optFoto.get();
		
		model.addAttribute("selectedFoto", selectedFoto);
		
		return "foto-show";
	}
	
	@GetMapping("/foto/create")
	public String create(Model model) {
		
		Foto foto = new Foto();
		model.addAttribute("foto", foto);
		
		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);
		
		return "foto-create";
	}
	
	@PostMapping("/foto/create")
	public String store(@Valid Foto foto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors()) {
			
			System.err.println("ERROR ------------------------------------------");
			System.err.println(bindingResult.getAllErrors());
			System.err.println("------------------------------------------------");
			
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			
			return "redirect:/admin/foto/create";
		}
		
		List<Category> categories = foto.getCategories();
		
		for(Category category : categories) {
			
			category.getFoto().add(foto);
		}
		
		fotoService.save(foto);
		
		return "redirect:/admin";
	}
	
	@GetMapping("/foto/update/{id}")
	public String edit(@PathVariable("id") int id, Model model) {
		
		Optional<Foto> optFoto = fotoService.findById(id);
		Foto foto = optFoto.get();
		
		model.addAttribute("foto", foto);
		
		List<Category> categories = categoryService.findAll();
		
		model.addAttribute("categories", categories);
		
		return "foto-edit";
	}
	
	@PostMapping("/foto/update")
	public String update(@Valid Foto foto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors()) {
			
			System.err.println("ERROR ------------------------------------------");
			System.err.println(bindingResult.getAllErrors());
			System.err.println("------------------------------------------------");
			
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			
			return "redirect:/admin/foto/update/" + foto.getId();
		}
		
		fotoService.save(foto);		
		
		return "redirect:/admin";
	}
	
	@GetMapping("/foto/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		
		Optional<Foto> optFoto = fotoService.findById(id);
		Foto foto = optFoto.get();
		
		fotoService.delete(foto);
		
		return "redirect:/admin";
	}
	
}
