package dbLayer;

import java.util.ArrayList;

import modelLayer.Course;
import exceptionsLayer.DatabaseException;

public interface IFDBCourse {
	// create one
	public int insertCourse(Course course) throws DatabaseException;;

	// read all
	public ArrayList<Course> getAllCourses();

	// read one
	public Course findCourse(String name) throws DatabaseException;

	// update one
	public int updateCourse(String name, Course c) throws DatabaseException;

}
