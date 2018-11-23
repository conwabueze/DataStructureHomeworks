
package com.hwone.main;

public class Course {
	// instance variables
	private String courseName;
	private String department;
	private int code;
	private byte section;
	private String instructor;

	// empty constructor
	public Course() {

	}

	// constructor
	public Course(String courseName, String department, int code, byte section, String instructor) {
		this.courseName = courseName;
		this.department = department;
		this.code = code;
		this.section = section;
		this.instructor = instructor;
	}
	
	/**
	 * Creates clone of Object
	 * 
	 * @return new Course Object of type Object
	 * 
	 */
	@Override
	public Object clone() {
		return new Course(courseName, department, code, section, instructor);
	}
	
	/**
	 * This methods compares two course items
	 * 
	 * @param obj, represents object to compare
	 * @return boolean if equal or not
	 */
	@Override
	public boolean equals(Object obj) {
		Course course = (Course) obj;

		return courseName.equals(course.getCourseName()) && department.equals(course.getDepartment())
				&& Integer.compare(code, course.getCode()) == 0 && Byte.compare(section, course.getSection()) == 0
				&& instructor.equals(course.getInstructor());
	}

	// GETTERS AND SETTERS

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public byte getSection() {
		return section;
	}

	public void setSection(byte section) {
		this.section = section;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	// TO STRING
	@Override
	public String toString() {
		String str = String.format("%-21s%-25s%-14d%-14d%-15s", courseName, department, code, section, instructor);
		return str;

	}
}
