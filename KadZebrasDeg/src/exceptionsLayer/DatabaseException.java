package exceptionsLayer;

public class DatabaseException extends Exception
{
	String errorString;
	public DatabaseException(String error)
	{
		errorString=error;
	}
	String printError(){
		return "Exception was caught because something when wrong in the"
				+ "database ::" +errorString;
	
	}
}