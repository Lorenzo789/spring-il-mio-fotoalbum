package org.generation.italy.demo.service;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Category;
import org.generation.italy.demo.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	
	public void save(Category category) {
		
		categoryRepo.save(category);
	}
	
	public List<Category> findAll() {
		
		return categoryRepo.findAll();
	}
	
	public Category findById(int id) {
		
		return categoryRepo.findById(id).get();
	}
	
	public void delete(Category category) {
		
		categoryRepo.delete(category);
	}
	
	public void deleteById(int id) {
		
		categoryRepo.deleteById(id);
	}
}
