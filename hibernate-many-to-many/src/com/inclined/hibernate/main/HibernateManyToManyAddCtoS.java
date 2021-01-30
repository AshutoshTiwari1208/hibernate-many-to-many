package com.inclined.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.inclined.hibernate.table.relations.Course;
import com.inclined.hibernate.table.relations.Instructor;
import com.inclined.hibernate.table.relations.InstructorDetail;
import com.inclined.hibernate.table.relations.Review;
import com.inclined.hibernate.table.relations.Student;

public class HibernateManyToManyAddCtoS {

	public static void main(String[] args) {

		// Session Start
		// Session, SessionFactory implements AutoClosable()
		// try with resources
		try (SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class).addAnnotatedClass(Review.class).addAnnotatedClass(Student.class)
				.buildSessionFactory(); Session session = factory.getCurrentSession();) {
			session.beginTransaction();

			Course newCourse1 = new Course("IT Ultimate");
			Course newCourse2 = new Course("Physics Plus");
			Course newCourse3 = new Course("Silver Chemistry");

			session.save(newCourse1);
			session.save(newCourse2);
			session.save(newCourse3);

			Student savedStudent = session.get(Student.class, 3);
			System.out.println("XX:: Saved Student extracted is :: " + savedStudent);

			savedStudent.addCourse(newCourse1);
			savedStudent.addCourse(newCourse2);
			savedStudent.addCourse(newCourse3);

			System.out.println("XX:: Updated Student with 3 courses");
			// commit Transaction
			session.getTransaction().commit();
		}

	}

}
