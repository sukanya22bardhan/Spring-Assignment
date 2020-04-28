package com.cg.iter.main;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.cg.iter.bean.Address;
import com.cg.iter.bean.Student;
import com.cg.iter.service.StudentService;

public class StudentMain {
	
	private StudentService studService; 

	public StudentMain() {
		
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.scan("com.cg.iter");
		ctx.refresh();
		
		studService= (StudentService) ctx.getBean("studentService");
		System.out.println(studService);
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		while (true) {
			choice = getChoice(sc);
			switch (choice) {
			case 1:
				System.out.println("Create Student");
				System.out.println("Enter <houseId> <City>");
				Address addr = new Address(sc.nextInt(), sc.next());
				System.out.println("Enter <ID> <Name> <Mobile> <Subject>");
				Student stud = new Student(sc.nextInt(),sc.next(),
						          sc.nextLong(),sc.next(),addr);
				boolean success = studService.create(stud);
				if(success) {
					System.out.println("Student saved");
				}
				else {
					System.out.println("Could not save student");
				}
				break;
			case 2:
				
				System.out.println("Enter Student Id");
				Student student = studService.findStudentById(sc.nextInt());
				if(student==null) {
					System.out.println("Student not found");
				}
				else {
					System.out.println(student);
				}
				break;
			case 3:
				
				System.out.println("Enter Student Id");
				Student upStudent = studService.findStudentById(sc.nextInt());
				if(upStudent!=null) {
					System.out.println("Enter <Name> <Mobile> <Subject>");
					upStudent.setName(sc.next());
					upStudent.setMobile(sc.nextLong());
					upStudent.setSubject(sc.next());
					Address AddressUp = upStudent.getAddress();
					
					System.out.println("Enter <City>");
					
					AddressUp.setCity(sc.next());
				
					upStudent.setAddress(AddressUp);
					
					if(studService.updateStudent(upStudent)) {
						System.out.println("Updated sucessfully");
					}
					else {
						System.out.println("Fail to update");
					}
				}
				else {
					System.out.println("Student not found");
				}
				break;
			case 4:
				System.out.println("Enter Student Id");
				if(studService.deleteStudent(sc.nextInt())) {
					System.out.println("Removed sucessfully");
				}
				
				break;
			case 5:

				System.out.println("Enter Student Name");
				Student studentByName = studService.findStudentByName(sc.next());
				if(studentByName==null) {
					System.out.println("Student not found");
				}
				else {
					System.out.println(studentByName);
				}
				break;
			case 6:
				System.out.println("Exiting Program");
				System.exit(0);
				break;

			default:
				System.out.println("Enter 1 to 4 only");
				break;
			}
		}
}
		private int getChoice(Scanner sc) {
			int choice = 0;
			System.out.println("STUDENT MANAGEMENT");
			System.out.println("1. Create Student");
			System.out.println("2. Find Student");
			System.out.println("3. Update Student");
			System.out.println("4. Delete Student");
			System.out.println("5. Find Student Names");
			System.out.println("6. Exit Program");
			System.out.println("Choose the option from above");
			try {
			choice = sc.nextInt();
			}catch (InputMismatchException e) {
				System.out.println("Please choose a number");
				sc.nextLine();
			}
			return choice;
		}	
		
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			new StudentMain();
		}
		/*
		 * Student stud = (Student) ctx.getBean("stud"); System.out.println(stud);
		 * 
		 * EntityManagerFactory emf =
		 * Persistence.createEntityManagerFactory("studentity"); EntityManager em=
		 * emf.createEntityManager();
		 * 
		 * em.getTransaction().begin(); em.persist(stud); em.getTransaction().commit();
		 * System.out.println("Student Saved");
		 */
	//	Student stud1 = (Student) ctx.getBean("stud1"); 
	//	System.out.println(stud1);

}
