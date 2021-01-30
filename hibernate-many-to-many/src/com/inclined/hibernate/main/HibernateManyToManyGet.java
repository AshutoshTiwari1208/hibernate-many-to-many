package com.inclined.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.inclined.hibernate.table.relations.Course;
import com.inclined.hibernate.table.relations.Instructor;
import com.inclined.hibernate.table.relations.InstructorDetail;
import com.inclined.hibernate.table.relations.Review;
import com.inclined.hibernate.table.relations.Student;

public class HibernateManyToManyGet {

	public static void main(String[] args) {

		// Session Start
		// Session, SessionFactory implements AutoClosable()
		// try with resources
		try (SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class).addAnnotatedClass(Review.class).addAnnotatedClass(Student.class)
				.buildSessionFactory(); Session session = factory.getCurrentSession();) {
			session.beginTransaction();

			Student savedStudent = session.get(Student.class, 3);

			System.out.println("XX:: Saved courses are==> " + savedStudent.getCourses());

			// commit Transaction
			session.getTransaction().commit();
		}

	}

}
