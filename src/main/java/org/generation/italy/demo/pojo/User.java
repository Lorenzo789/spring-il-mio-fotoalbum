package org.generation.italy.demo.pojo;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(unique = true)
	@NotNull
	private String username;
	
	@NotNull
	@NotEmpty
	private String password; 
	
	//fetch type per non avere errori lazyness
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Role> roles;
	
	public User() { }
	//piu ruoli
	public User(String username, String password, Set<Role> roles) {
		
		setUsername(username);
		setPassword(password);
		setRoles(roles);
	}
	//un solo ruolo
	public User(String username, String password, Role role) {
		
		setUsername(username);
		setPassword(password);
		addRole(role);
	}
	
	//setter and getter
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	//setter and getter
	
	//metodi custom
	private void addRole(Role role) {
		//se il get roles restituisce null
		if(getRoles() == null) {
			//il roles diventa una nuova set vuota
			roles = new HashSet<>();
		}
		//e al get role ci aggiungo un ruolo
		getRoles().add(role);
	}
	//metodi custom
	
	@Override
	public String toString() {

		return "Id: " + getId()
		+ "\nUsername: " + getUsername();
	}
	
}
