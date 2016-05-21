package modelLayer;

public class Staff {
private int id;
private String name;
private String bankAccount;
private String address;
private String profession;
private String phoneNo;
private String cprNo;
private boolean exists;


//constructor
public Staff(){
	
}
//sets and gets
public String getName() {return name;}
public void setName(String name) {this.name = name;}
public String getBankAccount() {return bankAccount;}
public void setBankAccount(String bankAccount) {this.bankAccount = bankAccount;}
public String getAddress() {return address;}
public void setAddress(String address) {this.address = address;}
public String getProfession() {return profession;}
public void setProfession(String profession) {this.profession = profession;}
public String getPhoneNo() {return phoneNo;}
public void setPhoneNo(String phoneNo) {this.phoneNo = phoneNo;}
public String getCprNo() {return cprNo;}
public void setCprNo(String cprNo) {this.cprNo = cprNo;}
public boolean isExists() {return exists;}
public void setExists(boolean exists) {this.exists = exists;}
public int getStaffId() {return id;}
public void setStaffId(int id) {this.id = id;}

//end of sets and gets
}

