package dbLayer;

import java.util.ArrayList;

import modelLayer.Staff;
import exceptionsLayer.DatabaseException;

public interface IFDBStaff {
	// create one
	public int insertStaff(Staff staff) throws DatabaseException;

	// read all
	public ArrayList<Staff> getAllStaffs();

	// read one
	public Staff findStaff(String cprNo) throws DatabaseException;

	// update one
	public int updateStaff(String cprNo, Staff staff) throws DatabaseException;

	Staff findStaffById(int id) throws DatabaseException;

	Staff findStaffByName(String name) throws DatabaseException;
}
