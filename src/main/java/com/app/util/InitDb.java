package com.app.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

import com.app.model.Answer;
import com.app.model.Category;
import com.app.model.Question;
import com.app.model.User;

@WebListener
public class InitDb implements javax.servlet.ServletContextListener {

	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("app");
	EntityManager entitymanager = emfactory.createEntityManager();

	// RandomNumberGenerator rng = new SecureRandomNumberGenerator();
	// Object salt = rng.nextBytes();
	// String amdinPass = new Sha256Hash("admin", "1234", 1024).toBase64();

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		entitymanager.getTransaction().begin();

		User userAdmin = new User();
		userAdmin.setUsername("admin");
		userAdmin.setPassword("admin");
		userAdmin.setEmail("email");
		userAdmin.setRole("admin");
		entitymanager.persist(userAdmin);
		
		User user1 = new User();
		user1.setUsername("user1");
		user1.setPassword("password1");
		user1.setEmail("email");
		user1.setRole("user");
		entitymanager.persist(user1);
		
		User user2 = new User();
		user2.setUsername("user2");
		user2.setPassword("password2");
		user2.setEmail("email");
		user2.setRole("user");
		entitymanager.persist(user2);
		
		Category category1 = new Category();
		category1.setName("Java EE");
		entitymanager.persist(category1);

		Category category2 = new Category();
		category2.setName(".Net Framework");
		entitymanager.persist(category2);

		Category category3 = new Category();
		category3.setName("Play framework");
		entitymanager.persist(category3);
		
		Category category5 = new Category();
		category5.setName("Vaadin");
		entitymanager.persist(category5);
		
		Category category4 = new Category();
		category4.setName("GWT");
		entitymanager.persist(category4);

		Question question1 = new Question();
		question1.setTitle("JPA OneToMany not deleting child ?");
		question1.setContent("I have a problem with a simple OneToMany " + "mapping between a parent and a child entity. All works well, "
				+ "only that child records are not deleted when i remove them " + "from the collection.");
		question1.setCategory(category1);
		question1.setUser(user1);
		entitymanager.persist(question1);
		
		Question question2 = new Question();
		question2.setTitle("Problems with Self-referencing classes in Hibernate");
		question2.setContent("I have an entity that models the parent/child structure, here is my hibernate mapping file"
				+ "My problem is that the association many-to-one generates exception whenever I try to display the object in the view");
		question2.setCategory(category1);
		question2.setUser(user2);
		entitymanager.persist(question2);

		Question question3 = new Question();
		question3.setTitle("RESTful on Play framework");
		question3.setContent("We are planning a project primarily serving content to mobile apps, but need to have a website."
				+ " My question is whether is makes sense to use Jersey or Restlet to develop REST APIs for our mobile apps, and then use Play! to serve the website.");
		question3.setCategory(category3);
		question3.setUser(user1);
		entitymanager.persist(question3);
		
		Answer answer1 = new Answer();
		answer1.setContent("You need to either use vendor-specific extensions " + "to force this behaviour or explicitly delete the child " + "AND remove it from the parent's collection.");
		answer1.setQuestion(question1);
		answer1.setUser(user1);
		entitymanager.persist(answer1);

		Answer answer2 = new Answer();
		answer2.setContent("answer2");
		answer2.setQuestion(question3);
		answer2.setUser(user2);
		entitymanager.persist(answer2);


		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

}
