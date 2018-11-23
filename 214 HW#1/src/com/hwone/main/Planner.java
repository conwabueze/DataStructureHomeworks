
package com.hwone.main;

import com.hwone.exceptions.FullPlannerExpcetion;
import com.hwone.exceptions.IllegalArgumentException;

public class Planner {
	private int length;// also tells last course in the list
	private final int MAX_COURSES;
	private Course[] courseList;

	public Planner() {
		length = 0;
		MAX_COURSES = 50;
		courseList = new Course[MAX_COURSES + 1];
	}

	/**
	 * method to acquire size of list
	 * 
	 * @return size of list
	 */
	public int size() {
		int size = 0;
		for (int i = 1; i <= length; i++) {
			size++;
		}
		return size;
	}
	
	/**
	 * this method adds anew course to list in a certain position
	 * 
	 * @param newCourse, course you want to insert into list
	 * @param position, index in which you want to insert course
	 * @throws FullPlannerException, no more space in planner
	 * @throws IllegalArgumentException, position 0 or bigger than space in list
	 * 
	 */
	public void addCourse(Course newCourse, int position) throws FullPlannerExpcetion, IllegalArgumentException {
		if (courseList[1] == null)
			addCourse(newCourse);
		else if (length == MAX_COURSES)
			throw new FullPlannerExpcetion("Course List is Full");
		else if (position > length + 1 || position == 0) {
			throw new IllegalArgumentException("Dont get ahead of yourself");
		} else if (position >= 1 && position <= length + 1) {
			if (position == length + 1)
				addCourse(newCourse);
			else if (courseList[1] == null) {
				courseList[1] = newCourse;
				length++;
			} else {
				for (int i = length + 1; i >= position; i--) {
					courseList[i] = courseList[i - 1];
				}
				courseList[position] = newCourse;
				length++;
			}
		}
	}

	/**
	 * this method adds anew course to end of list
	 * 
	 * @param newCourse, course you want to insert into list
	 * 
	 */
	public void addCourse(Course newCourse) {
		courseList[length + 1] = newCourse;
		length++;
	}
	
	/**
	 * this method removes a course in list in a certain position
	 * @param position, index in which you want to remove course
	 * @throws IllegalArgumentException, position 0 or bigger than space in list
	 * 
	 */
	public void removeCourse(int position) throws IllegalArgumentException {
		if (position > length)
			throw new IllegalArgumentException("Dont get ahead of yourself");
		else {
			for (int i = position; i <= length - 1; i++) {
				courseList[i] = courseList[i + 1];
			}
			length--;
		}
	}
	
	
	/**
	 * this method gets a course in list in a certain position
	 * @param position, index in which you want to get course
	 * @throws IllegalArgumentException, position 0 or bigger than space in list
	 * @return course at postion
	 */
	public Course getCourse(int position) throws IllegalArgumentException {
		if (position > length || position == 0)
			throw new IllegalArgumentException("Dont get ahead of yourself");
		else {
			// System.out.println("Return course at index(position) "+ position + ": " +
			// courseList[position]);
			return courseList[position];
		}
	}
	
	/**
	 * this method gets and prints all course of the same department name
	 * @param planner, list you which to search through
	 * @param department, department name of course
	 * @throws IllegalArgumentException, position 0 or bigger than space in list
	 * 
	 */
	public static void filter(Planner planner, String department) {
		System.out.println("Filtered");
		for (int i = 1; i <= planner.length; i++) {
			if (planner.courseList[i].getDepartment().equals(department)) {
				System.out.println(planner.courseList[i].toString());
			}

		}
		System.out.println();
	}
	
	/**
	 * this method looks to see if a certain course exist
	 * @param Course, course to look for
	 * @return boolean, if found or not
	 * 
	 */
	public boolean exists(Course course) {
		for (int i = 1; i <= length; i++) {
			if (courseList[i].equals(course))
				return true;
		}
		return false;
	}
	
	
	/**
	 * this method clones a planner
	 *
	 * @return Object, clone planner
	 * 
	 */
	public Object clone() {
		Planner plannerCopy = new Planner();
		for (int i = 1; i <= size(); i++) {
			plannerCopy.addCourse(courseList[i]);
		}
		return (Object) plannerCopy;
	}
	
	
	/**
	 * this method prints all courses in the planner
	 *
	 * 
	 * 
	 */
	public void printAllCourses() {
		printHeader();
		for (int i = 1; i <= length; i++) {
			System.out.println(i + "\t" + courseList[i].toString());
		}

	}
	
	/**
	 * this method prints a header for course console print outs
	 *
	 * 
	 * 
	 */
	public void printHeader() {
		String header = String.format("%-5s%-21s%-17s%-10s%-10s%-10s", "No.", "Course Name", "Department", "Code",
				"Section", "Instructor");
		System.out.println(header);
		System.out.println(
				"-----------------------------------------------------------------------------------------------------");
	}



}
