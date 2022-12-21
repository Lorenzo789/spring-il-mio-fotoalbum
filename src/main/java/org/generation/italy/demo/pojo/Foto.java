package org.generation.italy.demo.pojo;

import java.util.List;

import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table
public class Foto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	@NotEmpty(message = "the title can't be empty")
	private String title;
	
	@Column
	@Lob
	private String description;
	
	@Column
	@Lob
	@NotEmpty(message = "this field is required")
	@URL(message = "the url is not valid")
	private String url;
	
	@Column
	private String tag;
	
	@Column
	@NotNull
	private boolean visible;
	
	@ManyToMany(cascade = CascadeType.DETACH)
	private List<Category> categories;
	
	@OneToMany(mappedBy = "foto")
	@JsonIgnore
	private List<Comment> comments;
	
	public Foto() {}
	public Foto(String title, String description, String url, String tag, boolean visible) {
		setTitle(title);
		setDescription(description);
		setUrl(url);
		setTag(tag);
		setVisible(visible);
	}
	public Foto(String title, String description, String url, String tag, boolean visible, List<Category> category) {
		this(title, description, url, tag, visible);
		setCategories(category);
	}
	public Foto(String title, String description, String url, String tag, boolean visible, List<Category> category, List<Comment> comment) {
		this(title, description, url, tag, visible, category);
		setComments(comment);
	}
	
	//setter and getter
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	//setter and getter
	
	//custom methods
	public void addCategory(Category category) {
		
		boolean finded = false;
		
		for(Category cat : getCategories()) {
		
			if (cat.getId() == category.getId()) {
				finded = true;
			}
			
		}
		
		if (!finded) {
			getCategories().add(category);
		}
	}
	
	public void removeCategory(Category category) {
		
		getCategories().remove(category);
	}
	//custom methods
	
	@Override
	public String toString() {
		
		return "Id " + getId()
		+ "\nTitle " + getTitle()
		+ "\nDescription " + getDescription()
		+ "\nUrl " + getUrl()
		+ "\nTag " + getTag()
		+ "\nIs Visible " + isVisible();
	}
	
}
