package com.roumak.hibernateCRUD;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mysql.cj.jdbc.SuspendableXAConnection;
import com.roumak.entity.Student;

public class StudentCRUD {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure("hibernate.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		try {

			createStudent(factory);					// Create
			retrieveUsingId(factory); 				// retrieve using ID
			retrieveUsingQuery(factory); 			// Retrieve using query
			updateStudentsUsingId(factory); 		// update using id
			updateStudentUsingQuery(factory);		// update using Query
			deleteStudents(factory); 				// delete
			deleteStudentsByQuery(factory); 		// delete using query
			truncateStudent(factory); 				// truncate

		} catch (Exception e) {
			System.out.println("\t\tgot some exception " + e.getMessage());
			e.printStackTrace();
		} finally {
			factory.close();
		}
	}

	private static void truncateStudent(SessionFactory factory) {

		Session session = factory.getCurrentSession();
		session.beginTransaction(); // need for fetching query

		System.out.println("\t\ttruncate table Student");
		session.createSQLQuery("truncate table Student").executeUpdate();
		session.getTransaction().commit(); // closes the session by itself

	}

	private static void createStudent(SessionFactory factory) {
		truncateStudent(factory);
		System.out.println("\n__________________________CREATE___________________________________________");

		Session session = factory.getCurrentSession();

		Student student1 = new Student("Roumak", "Chakraborty", "Rc@email");
		Student student2 = new Student("bipin", "rawat", "BpRWT@qwe");
		Student student3 = new Student("jatin", "chauhan", "Jch@123");
		Student student4 = new Student("blob", "patch", "BP123@gmail.com");
		Student student5 = new Student("bobo", "blackSheep", "bpl@1243");
		Student student6 = new Student("shitty", "coder", "code_some_shit@email.com");
		Student student7 = new Student("lousy", "code", "codin_lousily@gmail.com");
		Student student8 = new Student("louis", "lane", "llLadki@email.com");

		session.beginTransaction();

		session.save(student8);
		session.save(student7);
		session.save(student6);
		session.save(student5);
		session.save(student4);
		session.save(student3);
		session.save(student2);
		session.save(student1);

		session.getTransaction().commit();

		session.close();

	}

	private static void retrieveUsingId(SessionFactory factory) {
		Session session = factory.getCurrentSession();
		System.out.println(
				"\n___________________________RETRIEVE operation using id_________________________________________");
		session.beginTransaction();
		// using find method
		Student specificStudent = session.find(Student.class, 2);
		Student anotherSpecificStudent = session.find(Student.class, 6);

		System.out.println("\t\tRetrieving Student wiht id=2 : " + specificStudent);
		System.out.println("\t\tRetrieving Student with id=6 : " + anotherSpecificStudent);

		System.out.println("\n\t\tfetching all students");
		List<Student> allStudentList = session.createQuery("from Student s").getResultList();

		for (Student s : allStudentList) {
			System.out.println("\t\t" + s);
		}
		System.out.println("\n\n\n");

		session.close();
	}

	private static void retrieveUsingQuery(SessionFactory factory) {
		System.out.println(
				"\n____________________________RETRIEVE operation using query_________________________________________");

		Session session = factory.getCurrentSession();
		// using Query where predicate
		System.out.println("\t\tFetching students with last name coder");
		session.beginTransaction();
		List<Student> filteredStudentList = session.createQuery("from Student s where s.lastName= 'coder'")
				.getResultList();

		for (Student s : filteredStudentList) {
			System.out.println("\t\t" + s);
		}

		// using Like predicate
		System.out.println("\n\t\tfetching students with email gmail.com");
		filteredStudentList = session.createQuery("from Student s where s.email like '%gmail.com' ").getResultList();
		for (Student s : filteredStudentList) {
			System.out.println("\t\t" + s);
		}
		System.out.println("\n\n");

		session.close();
	}

	private static void updateStudentsUsingId(SessionFactory factory) {
		System.out
				.println("\n____________________________________UPDATE using id_____________________________________");

		Session session = factory.getCurrentSession();

		session.beginTransaction(); // important for get
		// Retrieve ------- update---------commit
		int studentId = 3;
		Student fetchedStudent = session.get(Student.class, studentId);
		System.out.println("\t\tfetched student before update: " + fetchedStudent);

		// updating the data in fetchedstudent
		fetchedStudent.setFirstName("Luficer");

		// commiting the change
		session.getTransaction().commit(); // this commit closes the session

		session = factory.getCurrentSession(); // since closed we create a new session and
		session.beginTransaction(); // begin transaction fot get method

		fetchedStudent = session.get(Student.class, studentId);
		System.out.println("\t\tafter update fetched student: " + fetchedStudent);

		session.close();

	}

	private static void updateStudentUsingQuery(SessionFactory factory) {
		System.out.println("\n____________________________UPDATE using Query________________________________________");

		Session session = factory.getCurrentSession();

		session.beginTransaction(); // important for get

		System.out.println("Changing all db email to invalidate@email.com ");
		// UPDATE Query
		session.createQuery("update Student set email='invalidate@email.com'").executeUpdate();
		List<Student> allStudentList = session.createQuery("from Student").getResultList();
		for (Student s : allStudentList) {
			System.out.println("\t\t" + s);
		}

		System.out.println("\n");
		System.out.println("\t\tchanging id=2 email to newUpdatedEmail@gmail.com ");
		// UPDATE QUERY
		session.createQuery("update Student s set s.email='newUpdatedEmail@gmail.com' where s.id=2 ").executeUpdate();
		session.getTransaction().commit(); // commiting the change and closing the session

		session = factory.getCurrentSession(); // getting a new session
		session.beginTransaction();
		Student updatedEmailStudent = (Student) session.createQuery("from Student where id=2").getSingleResult();
		System.out.println("\t\t\n updatedEmailStudent: " + updatedEmailStudent);

		session.close();
	}

	private static void deleteStudents(SessionFactory factory) {
		System.out.println(
				"\n_____________________________DELETE iterating through record______________________________________");
		Session session = factory.getCurrentSession();
		session.beginTransaction(); // need for fetching query

		System.out.println("\n\n students before deleting all");
		List<Student> allStudentList = session.createQuery("from Student").getResultList();
		for (Student s : allStudentList) {
			System.out.println("\t\t" + s);
			session.delete(s); // deleting each data
		}

		session.getTransaction().commit(); // commiting delete operation

	}

	private static void deleteStudentsByQuery(SessionFactory factory) {
		System.out.println("\n____________________________DELETE using Query ___________________________________");
		Session session = factory.getCurrentSession();
		session.beginTransaction(); // need for this query

		// Query for delete
		System.out.println("\n DELETED ALL RECORD FROM STUDENT TABLE");
		session.createQuery("delete from Student").executeUpdate();

		session.close();

	}

}
