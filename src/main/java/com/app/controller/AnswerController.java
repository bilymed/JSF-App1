package com.app.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.app.model.Answer;
import com.app.model.Question;

@ManagedBean
public class AnswerController {

	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("app");
	EntityManager entitymanager = emfactory.createEntityManager();

	@ManagedProperty("#{homeController.question}")
	private Question question;

	private List<Answer> answers;
	private Answer answer = new Answer();

	@PostConstruct
	public void init() {
		answers = entitymanager.createNamedQuery("Answer.findByQuestion", Answer.class).setParameter("question", question).getResultList();
	}
	
	public void save(){
		answer.setQuestion(question);
		entitymanager.getTransaction().begin();
		entitymanager.persist(answer);
		answers = entitymanager.createNamedQuery("Answer.findByQuestion", Answer.class).setParameter("question", question).getResultList();
		entitymanager.getTransaction().commit();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Answer Saved!", null));
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

}
