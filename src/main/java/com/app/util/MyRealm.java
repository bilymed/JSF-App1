package com.app.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SaltedAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.jdbc.JdbcRealm;

import com.app.model.User;

public class MyRealm extends JdbcRealm {

	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("app");
	EntityManager entitymanager = emfactory.createEntityManager();

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		// identify account to log to
		UsernamePasswordToken userPassToken = (UsernamePasswordToken) token;
		final String username = userPassToken.getUsername();

		if (username == null) {
			System.out.println("Username is null.");
			return null;
		}

		// read password hash and salt from db
		// Session session = HibernateUtil.getSessionFactory().openSession();
		entitymanager.getTransaction().begin();
		// session.beginTransaction();
		try {
			final User user = (User) entitymanager.createNamedQuery("User.findByName").setParameter(0, username);

			if (user == null) {
				System.out.println("No account found for user [" + username + "]");
				return null;
			}

			// return salted credentials
			SaltedAuthenticationInfo userAuthInfo = new MySalt(username, user.getPassword(), user.getSalt());
			return userAuthInfo;

		} finally {
			entitymanager.getTransaction().commit();
			if (entitymanager.isOpen())
				entitymanager.close();
		}

	}

}
