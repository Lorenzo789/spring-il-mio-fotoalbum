package org.generation.italy.demo;

import java.util.List;

import org.generation.italy.demo.pojo.Category;
import org.generation.italy.demo.pojo.Foto;
import org.generation.italy.demo.pojo.Role;
import org.generation.italy.demo.pojo.User;
import org.generation.italy.demo.service.CategoryService;
import org.generation.italy.demo.service.CommentService;
import org.generation.italy.demo.service.FotoService;
import org.generation.italy.demo.service.RoleService;
import org.generation.italy.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringIlMioFotoalbum1Application implements CommandLineRunner{
	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private FotoService fotoService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringIlMioFotoalbum1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Role r1 = new Role("USER");
		Role r2 = new Role("ADMIN");
		
		roleService.save(r1);
		roleService.save(r2);
		
		List<Role> roles = roleService.findAll();
		
		User user = new User("user", "{noop}1234", r1);
		User admin = new User("admin", "{noop}admin", r2);
		
		userService.save(user);
		userService.save(admin);
		
		List<User> users = userService.findAll();
		
		Category funny = new Category("funny");
		Category hilarious = new Category("hilarious");
		Category cute = new Category("cute");
		Category sport = new Category("sport");
		Category sad = new Category("sad");
		
		categoryService.save(funny);
		categoryService.save(hilarious);
		categoryService.save(cute);
		categoryService.save(sport);
		categoryService.save(sad);
		
		List<Category> categories = categoryService.findAll();
		System.err.println(categories);
		
		Foto f1 = new Foto("foto1", "bella", "https://picsum.photos/id/237/557/300", "night", true, categories);
		Foto f2 = new Foto("foto2", "bella", "https://picsum.photos/id/237/557/300", "funny", true, categories);
		Foto f3 = new Foto("foto3", "bella", "https://picsum.photos/id/237/557/300", "funny", true, categories);
		Foto f4 = new Foto("foto4", "bella", "https://picsum.photos/id/237/557/300", "funny", true, categories);
		Foto f5 = new Foto("foto5", "bella", "https://picsum.photos/id/237/557/300", "funny", true);
	
		fotoService.save(f1);
		fotoService.save(f2);
		fotoService.save(f3);
		fotoService.save(f4);
		fotoService.save(f5);
		
		List<Foto> fotos = fotoService.findAll();
		System.err.println(fotos);
	}

}
