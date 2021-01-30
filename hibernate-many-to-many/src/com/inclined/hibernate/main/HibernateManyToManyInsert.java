package com.inclined.hibernate.main;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.inclined.hibernate.table.relations.Course;
import com.inclined.hibernate.table.relations.Instructor;
import com.inclined.hibernate.table.relations.InstructorDetail;
import com.inclined.hibernate.table.relations.Review;
import com.inclined.hibernate.table.relations.Student;

public class HibernateManyToManyInsert {

	public static void main(String[] args) {

		// Session Start
		// Session, SessionFactory implements AutoClosable()
		// try with resources
		try (SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class).addAnnotatedClass(Review.class).addAnnotatedClass(Student.class)
				.buildSessionFactory(); Session session = factory.getCurrentSession();) {
			session.beginTransaction();

			Course course = new Course("Namaste Java by IS");
			Student student1 = new Student("John", "Cena", "ucme@gmail.com");
			Student student2 = new Student("Kara", "Singh", "k_singh@gmail.com");
			Student student3 = new Student("Mario", "D_Sou", "mario@yahoo.com");

			List<Student> stuList = new ArrayList<>();
			stuList.add(student1);
			stuList.add(student2);
			stuList.add(student3);

			course.setStudents(stuList);
			session.persist(course);

			System.out.println("All courses and students are saved");

			// commit Transaction
			session.getTransaction().commit();
		}

	}

}
