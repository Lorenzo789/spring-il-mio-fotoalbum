package org.generation.italy.demo.controller.api;

import java.util.List;

import org.generation.italy.demo.pojo.Category;
import org.generation.italy.demo.pojo.Foto;
import org.generation.italy.demo.service.CategoryService;
import org.generation.italy.demo.service.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/1/category")
@CrossOrigin("*")
public class CategoryApiController {

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private FotoService fotoService;
	
	@GetMapping("/all")
	public List<Category> getCategoriesByFotoId() {
		
		return categoryService.findAll();
	}
}
