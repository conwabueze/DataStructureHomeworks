package com.hwone.main;

import com.hwone.exceptions.FullPlannerExpcetion;
import com.hwone.exceptions.IllegalArgumentException;

public class Test {
	public static void main(String[] args) {
		Course course1 = new Course("CSE 214", "Engineering", 214, (byte) 2, "Mr.Computer");
		Course course2 = (Course) course1.clone();
		System.out.println("course1 " + course1.toString());
		System.out.println("course2 " + course2.toString());
		Course course3 = new Course("AFS 200", "Africana Studies", 200, (byte) 1, "Mrs.Africa");

		System.out.println(course1.equals(course2));
		System.out.println(course1.equals(course3));

		Planner planner = new Planner();
		Course course4 = new Course("CSE 4", "Engineering", 214, (byte) 2, "8");
		Course course5 = new Course("CSE 5", "medical", 214, (byte) 2, "5");
		Course course6 = new Course("CSE 6", "Engineering", 214, (byte) 2, "6");
		Course course7 = new Course("CSE 7", "aerospace", 214, (byte) 2, "7");

//        planner.addCourse(course4,0);
		try {
			planner.addCourse(course5, 1);
			planner.addCourse(course4, 2);
			planner.addCourse(course4, 2);
			planner.addCourse(course5, 3);
			planner.addCourse(course7, 3);
			planner.addCourse(course6, 1);
			planner.addCourse(course7);
			planner.addCourse(course4);
			planner.addCourse(course5);
			planner.removeCourse(3);
			planner.removeCourse(5);
			// planner.removeCourse(planner.size());
			planner.removeCourse(1);
			planner.printAllCourses();
			System.out.println("Planner size: " + planner.size());
			System.out.println("COURSE GOTTTEN " + planner.getCourse(2));
			planner.filter(planner, "medical");
			System.out.println(planner.exists(new Course("CSE 6", "aerospace", 214, (byte) 2, "7")));

			System.out.println("\nClone Method Test");
			Planner plannerClone = (Planner) planner.clone();
			plannerClone.printAllCourses();

			// 5 7 5 7 4
			// planner.addCourse(course7,3);
			// planner.removeCourse(2);
			// planner.addCourse(course4,0);

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (FullPlannerExpcetion e) {
			e.printStackTrace();
		}

	}
}
