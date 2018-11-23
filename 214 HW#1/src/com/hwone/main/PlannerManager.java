
package com.hwone.main;

import java.util.Scanner;

import com.hwone.exceptions.FullPlannerExpcetion;
import com.hwone.exceptions.IllegalArgumentException;

public class PlannerManager {

	public static void main(String[] args) {
		Planner studentPlanner = new Planner();
		Scanner input = new Scanner(System.in);
		String menu = "(A) Add Course\r\n" + "(G) Get Course\r\n" + "(R) Remove Course\r\n"
				+ "(P) Print Courses in Planner\r\n" + "(F) Filter by Department Code\r\n" + "(L) Look For Course\r\n"
				+ "(S) Size\r\n" + "(B) Backup\r\n" + "(PB) Print Courses in Backup\r\n" + "(RB) Revert to Backup\r\n"
				+ "(Q) Quit";
		String selection = "";
		Planner studentPlannerBackUp = new Planner();
		System.out.println(menu);

		while (selection != "q") {

			selection = input.nextLine().toLowerCase();

			try {

				switch (selection) {
				// add course
				case "a":
					System.out.println("Enter course name: ");
					String course = input.nextLine();

					System.out.println("Enter department: ");
					String department = input.nextLine();

					System.out.println("Enter course code: ");
					int courseCode = input.nextInt();

					System.out.println("Enter course section: ");
					byte courseSection = input.nextByte();
					input.nextLine();// clear

					System.out.println("Enter instructor: ");
					String instructor = input.nextLine();

					System.out.println("Enter position: ");
					int position = input.nextInt();
					// input.close();

					studentPlanner.addCourse(new Course(course, department, courseCode, courseSection, instructor),
							position);

					System.out.println("\n" + department + " " + courseCode + ".0" + courseSection
							+ " successfully added to planner.");
					studentPlanner.printAllCourses();
					System.out.println();// newLine
					break;

				// get course
				case "g":
					System.out.println("Enter No. of course");
					int getPosition = input.nextInt();
					Course getCourse = studentPlanner.getCourse(getPosition);

					///////// HEADER//////////
					System.out.println("Heres your course");
					studentPlanner.printHeader();
					System.out.println(getCourse.toString());

					break;

				/// remove course
				case "r":
					System.out.println("Enter No. you want removed");
					int removePosition = input.nextInt();
					studentPlanner.removeCourse(removePosition);
					System.out.println("Course at No." + removePosition + "has been deleted");
					break;

				// print all courses
				case "p":
					System.out.println("Here's all your courses");
					studentPlanner.printAllCourses();
					break;

				// filter
				case "f":
					System.out.println("Enter department: ");
					String selectedDepartment = input.nextLine();

					System.out.println("Heres all courses from the " + selectedDepartment + " department");
					String filterHeader = String.format("%-8s%-21s%-19s%10s%16s%18s", "No.", "Course Name",
							"Department", "Code", "Section", "Instructor");
					System.out.println(filterHeader);
					System.out.println(
							"-----------------------------------------------------------------------------------------------------");
					Planner.filter(studentPlanner, selectedDepartment);
					break;

				// look up
				case "l":
					System.out.println("Enter course name: ");
					String lookUpCourseName = input.nextLine();
					System.out.println("Enter course department: ");
					String lookUpDepartment = input.nextLine();
					System.out.println("Enter course code: ");
					int lookUpCode = input.nextInt();
					System.out.println("Enter course section: ");
					byte lookUpSection = input.nextByte();
					System.out.println("Enter course instructor: ");
					input.nextLine();
					String lookUpInstructor = input.nextLine();
					
					Course lookUpCourse = new Course(lookUpCourseName,lookUpDepartment, lookUpCode, lookUpSection, lookUpInstructor);
					if(studentPlanner.exists(lookUpCourse)){
						System.out.println("This course does exist");
					}
					else {
						System.out.println("This course does not exist");
					}
					
					break;

				// get size
				case "s":
					int size = studentPlanner.size();
					System.out.println("\nThere are " + studentPlanner.size() + " courses in the planner.\n");
					break;

				// create back up
				case "b":
					studentPlannerBackUp = (Planner) studentPlanner.clone();
					System.out.println("Back up has been created");
					break;

				// print back up
				case "pb":
					System.out.println("Back Up list");
					studentPlannerBackUp.printAllCourses();
					break;

				// revert to back up
				case "rb":
					studentPlanner = studentPlannerBackUp;
					System.out.println("Revert to back up");
					break;

				// quit
				case "q":
					System.out.println("TERMINATED");
					System.exit(1);

				default:
					System.out.println(menu);

				}

			} catch (IllegalArgumentException e) {
				System.out.println(e);
			} catch (FullPlannerExpcetion e) {
				System.out.println(e);
			}

		}
	}

}
