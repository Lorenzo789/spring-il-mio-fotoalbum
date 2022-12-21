package org.generation.italy.demo.controller;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Category;
import org.generation.italy.demo.pojo.Foto;
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
@RequestMapping("/admin/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private FotoService fotoService;
	
	@GetMapping
	public String index(Model model) {
		
		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);
		
		return "category-index";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable("id") int id, Model model) {
		
		Category selectedCategory = categoryService.findById(id);
		
		model.addAttribute("selectedCategory", selectedCategory);
		
		return "category-show";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		
		Category category = new Category();
		model.addAttribute("category", category);
		
		List<Foto> fotos = fotoService.findAll();
		model.addAttribute("fotos", fotos);
		
		return "category-create";
	}
	
	@PostMapping("/create")
	public String store(@Valid Category category, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors()) {
			
			System.err.println("ERROR ------------------------------------------");
			System.err.println(bindingResult.getAllErrors());
			System.err.println("------------------------------------------------");
			
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			
			return "redirect:/admin/category/create";
		}
		
		List<Foto> fotos = category.getFoto();
		
		for(Foto foto : fotos) {
			
			foto.getCategories().add(category);
		}
		
		categoryService.save(category);
		
		return "redirect:/admin/category";
	}
	
	@GetMapping("/update/{id}")
	public String edit(@PathVariable("id") int id, Model model) {
		
		Category category = categoryService.findById(id);
		
		model.addAttribute("category", category);
		
		List<Foto> fotos = fotoService.findAll();
		
		model.addAttribute("fotos", fotos);
		
		return "category-edit";
	}
	
	@PostMapping("/update")
	public String update(@Valid Category category, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors()) {
			
			System.err.println("ERROR ------------------------------------------");
			System.err.println(bindingResult.getAllErrors());
			System.err.println("------------------------------------------------");
			
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			
			return "redirect:/admin/category/update/" + category.getId();
		}
		
		Category oldCategory = categoryService.findById(category.getId());
		
		for (Foto f : oldCategory.getFoto()) {

			f.removeCategory(category);
			
			List<Foto> fotos = category.getFoto();
			
			for (Foto foto : fotos) {
				
				foto.addCategory(category);
			}
		}
		
		categoryService.save(category);		
		
		return "redirect:/admin/category";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		
		Category category = categoryService.findById(id);
		
		for (Foto f : category.getFoto()) {
			
			f.removeCategory(category);
			
		}
		
		categoryService.delete(category);
		
		return "redirect:/admin/category";
	}
}
