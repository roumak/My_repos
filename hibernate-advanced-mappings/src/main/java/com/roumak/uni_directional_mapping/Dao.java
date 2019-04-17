package com.roumak.uni_directional_mapping;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Dao {

	private SessionFactory factory;

	private Session session;

	public Dao() {
		factory = new Configuration().configure("uni_directional/hibernate.xml").addAnnotatedClass(InstructorEntity.class)
				.addAnnotatedClass(InstructorDetailsEntity.class).buildSessionFactory();
	}
	
	public InstructorEntity fetchInstructorByEmail(String email) {
		String queryString="from InstructorEntity a where a.email ='"+email+"'";
		return (InstructorEntity) session.createQuery(queryString).getSingleResult();
		
		
	}

	public int insertInstructor(InstructorEntity theInstructor) {
		session = factory.getCurrentSession();
		session.beginTransaction();
		if (fetchInstructorByEmail(theInstructor.getEmail())!=null) {
			return -1;
		}
		try {
			session.save(theInstructor);
			session.getTransaction().commit();
			return 1;
		}catch (HibernateException e) {
			e.printStackTrace();
			return 0;
		}
	}

}
