package controlLayer;
import java.util.ArrayList;

import modelLayer.Staff;
import dbLayer.DBConnect;
import dbLayer.DBStaff;
import exceptionsLayer.DatabaseException;

public class StaffController {
	DBStaff dbStaff = new DBStaff();;
	
	public Staff findWaiterByCpr(String cprNo) throws DatabaseException{
		return dbStaff.findStaff(cprNo);
	}
	public ArrayList<Staff> getAllStaff(){
		return dbStaff.getAllStaffs();
	}
	public Staff findStaffByName(String name){
		try {
			return dbStaff.findStaffByName(name);
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public int updateStaff(String cprNo, Staff staff){
		return dbStaff.updateStaff(cprNo, staff);		
	}
	
	public void createStaff(String name,String bankAccount, String address, String phoneNo, String profession, String exists){
		Staff staff = new Staff();
		staff.setName(name);
		staff.setBankAccount(bankAccount);
		staff.setAddress(address);
		staff.setPhoneNo(phoneNo);
		staff.setProfession(profession);
		staff.setCprNo("12345678");
		if(exists.equals("Yes")){
			staff.setExists(true);
		}else{
			staff.setExists(false);
		}
		
		try {
			DBConnect.startTransaction();
			dbStaff.insertStaff(staff);
			DBConnect.commitTransaction();
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			DBConnect.rollbackTransaction();
			e.printStackTrace();
		}
	}
}
