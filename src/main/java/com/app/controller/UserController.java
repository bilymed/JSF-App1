package com.app.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.app.model.User;

@ManagedBean
@SessionScoped
public class UserController {

	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("app");
	EntityManager entitymanager = emfactory.createEntityManager();

	private List<User> users;
	private User user = new User();

	@PostConstruct
	public void findAll() {
		users = entitymanager.createNamedQuery("User.findAll", User.class).getResultList();
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
