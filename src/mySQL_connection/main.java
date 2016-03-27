package mySQL_connection;

import mySQL_connection.mySQLAccess;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		mySQLAccess db = new mySQLAccess();
	    try {
			db.readDataBase();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
