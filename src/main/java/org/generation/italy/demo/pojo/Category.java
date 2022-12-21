package org.generation.italy.demo.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	@NotEmpty(message = "the name can't be empty")
	private String name;
	
	@ManyToMany(mappedBy = "categories")
	@JsonIgnore
	private List<Foto> foto;
	
	public Category() {}
	public Category(String name) {
		setName(name);
	}
	public Category(String name, List<Foto> foto) {
		this(name);
		setFoto(foto);
	}
	
	//setter and getter
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Foto> getFoto() {
		return foto;
	}
	public void setFoto(List<Foto> foto) {
		this.foto = foto;
	}
	//setter and getter
	
	@Override
	public String toString() {
		
		return "Id " + getId()
		+ "\nName " + getName();
	}
	
}
