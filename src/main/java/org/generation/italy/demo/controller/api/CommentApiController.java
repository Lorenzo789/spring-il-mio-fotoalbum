package org.generation.italy.demo.controller.api;

import java.util.List;

import org.generation.italy.demo.pojo.Comment;
import org.generation.italy.demo.pojo.Foto;
import org.generation.italy.demo.service.CommentService;
import org.generation.italy.demo.service.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/1/comment")
@CrossOrigin("*")
public class CommentApiController {

	@Autowired
	private CommentService commentService;
	@Autowired
	private FotoService fotoService;
	
	@PostMapping("/create/{id}/{comment}")
	public Comment createComment( @PathVariable("id") int id, @PathVariable("comment") String comment
	) {
		
		System.err.println(id);
		System.err.println(comment);
		
		Foto foto = fotoService.findById(id).get();
		
		System.err.println(foto);
		
		Comment newComment = new Comment(comment, foto);
		commentService.save(newComment);
		
		return newComment;
	}
}
