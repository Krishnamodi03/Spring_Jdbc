package com.spring.Spring_Jdbc;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.entity.Student;
import com.spring.dao.StudentDao;

public class App {
	public static void main(String[] args) {
		System.out.println("Application Started....");
		ApplicationContext ac = new AnnotationConfigApplicationContext(JdbcConfig.class);
		Scanner sc = new Scanner(System.in);
		StudentDao sd = ac.getBean("studentDao", StudentDao.class);

		while (true) {
			System.out.println("Enter Your Choice : ");
			System.out.println("1. for Insertion");
			System.out.println("2. for Updation");
			System.out.println("3. for Deletion");
			System.out.println("4. for fetching single record");
			System.out.println("5. for fetching all records.");
			System.out.println("6. for EXIT");

			int choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				System.out.println("Enter Id : ");
				int id = sc.nextInt();
				sc.nextLine();
				System.out.println("Enter Name : ");
				String name = sc.nextLine();
				System.out.println("Enter City : ");
				String city = sc.nextLine();

				Student student = new Student(id, name, city);

				int insert = sd.insert(student);
				if (insert == 1) {
					System.out.println(insert+" student Inserted successfully.");
				} else {
					System.out.println("Student not inserted.....!");
				}
				break;
				
			case 2 : 
				System.out.println("Enter Your Id : ");
				int Id = sc.nextInt();
				sc.nextLine();
				System.out.println("Enter new Name : ");
				String nName = sc.nextLine();
				System.out.println("Enter new City : ");
				String nCity = sc.nextLine();
				
				Student s = new Student(Id, nName, nCity);
				
				int update = sd.update(s);
				
				if (update == 1) {
					System.out.println(update+" student Updated successfully.");
				} else {
					System.out.println("Student is not present with Id : "+Id);
				}
				break;
			case 3:
				System.out.println("Enter Student Id : ");
				int sid = sc.nextInt();
				
				int delete = sd.delete(sid);
				if (delete == 1) {
					System.out.println(delete+" student Deleted successfully.");
				} else {
					System.out.println("Student is not present with Id : "+sid);
				}
				break;
			case 4:
				System.out.println("Enter Student Id : ");
				int sId = sc.nextInt();
				
				Student fetchedStudent = sd.fetchStudent(sId);
				if (fetchedStudent != null) {
					System.out.println("===============");
					System.out.println("Student Detail");
					System.out.println("===============");
					System.out.println("Id : "+fetchedStudent.getId());
					System.out.println("Name : "+fetchedStudent.getName());
					System.out.println("City : "+fetchedStudent.getCity());
					System.out.println("_______________");
				} else {
					System.out.println("Student is not present with Id : "+sId);
				}
				break;
			case 5:
				List<Student> students = sd.fetchAllStudents();
				System.out.println("====================");
				System.out.println("Student Details");
				System.out.println("====================");
				for (Student stu : students) {
					System.out.println("Id : "+stu.getId());
					System.out.println("Name : "+stu.getName());
					System.out.println("City : "+stu.getCity());
					System.out.println("____________________");
				}
				break;
			case 6: 
				System.out.println("Thank You...");
				System.out.println("Have a nice day...!!!");
				return;
			default:
				System.out.println("Wrong Choice..!!!");
				System.out.println("Choose from below options only...");
				break;
			}
		}

	}
}
