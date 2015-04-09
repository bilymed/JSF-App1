package com.app.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.app.model.Category;
import com.app.model.Question;

@ManagedBean
@SessionScoped
public class HomeController {

	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("app");
	EntityManager entitymanager = emfactory.createEntityManager();

	private List<Question> questions;
	private Question question = new Question();
	private List<Category> categories;
	private Category category = new Category();

	@PostConstruct
	public void init() {
		categories = entitymanager.createNamedQuery("Category.findAll", Category.class).getResultList();
		//questions = entitymanager.createNamedQuery("Question.findByCategory", Question.class).setParameter("category", category).getResultList();
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
