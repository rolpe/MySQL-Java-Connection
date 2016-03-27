package mySQL_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class mySQLAccess {
	private Connection connect = null;
	  private Statement statement = null;
	  private ResultSet resultSet = null;

	  //puts query into resultSet and writes it to console
	  public void setUpQuery(String query) throws SQLException {
		  resultSet = statement.executeQuery(query);
		  writeResultSet(resultSet);
	  }
	  
	  public void readDataBase() throws Exception {
	    try {
	      Class.forName("com.mysql.jdbc.Driver");
	      // Setup the connection with the DB
	      connect = DriverManager.getConnection("jdbc:mysql://lawtech.law.miami.edu:3306/BTE423SPR16_roli423", "roli423", "n07206");

	      statement = connect.createStatement();
	      System.out.println("Query 1:");
	      setUpQuery("select * from Hotel");
	      
	      System.out.println("Query 2:");
	      setUpQuery("select * from Hotel " +
	    		  	 "where city = 'London'");
	      
	      System.out.println("Query 3:");
	      setUpQuery("select guestName, guestAddress from Guest"
	    		     + " join Booking on Booking.guestNo = Guest.guestNo"
	    		     + " join Hotel on Hotel.hotelNo = Booking.hotelNo" 
	    		     + " where city = 'London'"
	    		     + " and '2016-03-27' between dateFrom and dateTo"
	    		     + " order by guestName asc");
	      
	      System.out.println("Query 4:");
	      setUpQuery("select avg(price) from Room");
	      
	      System.out.println("Query 5:");
	      setUpQuery("select price, type from Room" 
	    		  	 + " join Hotel on Hotel.hotelNo = Room.hotelNo"
	    		     + " where hotelName = 'Grosvenor'");
	      
	      System.out.println("Query 6:");
	      setUpQuery("select sum(price) as income from Room"
	    		     + " join Hotel on Hotel.hotelNo = Room.hotelNo"
	    		     + " join Booking on Booking.roomNo = Room.roomNo"
	    		     + " where hotelName = 'Grosvenor'"
	    		     + " and '2016-3-27' between dateFrom and dateTo");
	      
	      System.out.println("Query 7:");
	      setUpQuery("select hotelName, count(*) from Room"
	    		     + " join Hotel on Hotel.hotelNo = Room.hotelNo"
	    		     + " where city = 'London'"
	    		     + " group by hotelName");
	      
	      System.out.println("Query 8:");
	      setUpQuery("select hotelName, type, count(*) from Room"
	    		     + " join Hotel on Hotel.hotelNo = Room.roomNo"
	    		     + " join Booking on Booking.roomNo = Room.roomNo"
	    		     + " and Booking.hotelNo = Room.hotelNo"
	    		     + " where city = 'London'"
	    		     + " group by Booking.hotelNo, Room.type"
	    		     + " order by count(*) desc");
	      
	    } catch (Exception e) {
	      throw e;
	    } finally {
	      close();
	    }

	  }

	  private void writeResultSet(ResultSet rs) throws SQLException {
		  int columns = rs.getMetaData().getColumnCount();

		  StringBuilder message = new StringBuilder();

		  while (rs.next()) {
		      for (int i = 1; i <= columns; i++) {
		          message.append(rs.getString(i) + " ");
		      }
		      message.append("\n");
		  }

		  System.out.println(message);
	  }

	  private void close() {
	    try {
	      if (resultSet != null) {
	        resultSet.close();
	      }

	      if (statement != null) {
	        statement.close();
	      }

	      if (connect != null) {
	        connect.close();
	      }
	    } catch (Exception e) {

	    }
	  }

}
