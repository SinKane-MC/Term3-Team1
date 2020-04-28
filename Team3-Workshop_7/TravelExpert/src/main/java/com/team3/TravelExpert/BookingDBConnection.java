package com.team3.TravelExpert;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class BookingDBConnection {
	
	
	Connection con = null;
	
	
	public BookingDBConnection(){
		
		
		String url="jdbc:mariadb://localhost:3306/travelexperts";
		String username= "root";
		String password ="";
		try
		
		{
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection(url,username,password);		
		}
		
		catch (Exception e)
		{
			System.out.println(e);
		}
			
	}
	
	public List<Bookings> getBookings(){
		
		List<Bookings> bookingslist = new ArrayList<Bookings>();
		String sql = "select * from  bookings";
		 try 
		 {
			 Statement st = con.createStatement();
			 ResultSet rs = st.executeQuery(sql);
			 
			 int i=0;
			 while(rs.next())
			 {
				  Bookings booking= new Bookings();
				 	
				 booking.setBookingId(rs.getInt("BookingID"));	
				 booking.setBookingDate(rs.getDate("BookingDate"));
				 booking.setBookingNo(rs.getString("BookingNo"));
				 booking.setTravelerCount(rs.getDouble("TravelerCount"));
				 booking.setCustomerId(rs.getInt("CustomerId"));
				 booking.setTripTypeId(rs.getString("TripTypeId"));
				 booking.setPackageId(rs.getInt("PackageId"));
				 
				 bookingslist.add(booking);
			 }
		 }
		 
		 catch(Exception e)
		 {
			 System.out.println(e);
			 
		 }
		 
		 return bookingslist;
		
		
	}
	
	public Bookings getBooking(int id){
		
		
		String sql = "select * from  bookings where Bookingid="+id;
	
		 Bookings booking= new Bookings();
		
		 try 
		 {
			 Statement st = con.createStatement();
			 ResultSet rs = st.executeQuery(sql);
			 
			 if (rs.next())
			 {	
				 booking.setBookingId(rs.getInt("BookingID"));	
				 booking.setBookingDate(rs.getDate("BookingDate"));
				 booking.setBookingNo(rs.getString("BookingNo"));
				 booking.setTravelerCount(rs.getDouble("TravelerCount"));
				 booking.setCustomerId(rs.getInt("CustomerId"));
				 booking.setTripTypeId(rs.getString("TripTypeId"));
				 booking.setPackageId(rs.getInt("PackageId"));
				 
			 }
		 }
		 
		 catch(Exception e)
		 {
			 System.out.println(e);
			 
		 }
		 
		return booking;
	
		
	}
	
	public void addBooking(Bookings b)
	{
		String sql= "insert into  bookings values (?,?,?,?,?,?,?)";
		try 
		 {	System.out.println("Im heer");
			 PreparedStatement st = con.prepareStatement(sql);
			 st.setInt(1, b.getBookingId());
			 if (b.getBookingDate()==null){
				 st.setNull(2, java.sql.Types.DATE);
				
			 }
			 else{
				 st.setDate(2,b.getBookingDate());
			 }
		
			 st.setString(3, b.getBookingNo());
			
			 st.setDouble(4, b.getTravelerCount());
			 
			 if (b.getCustomerId()==0){
				 st.setNull(5, Types.INTEGER);
			 }
			 else
			 {
				 st.setInt(5, b.getCustomerId());
			 }
			 st.setString(6, b.getTripTypeId());
			 if (b.getPackageId()==0){
				 st.setNull(7, Types.INTEGER);
			 }
			 else
			 {
				 st.setInt(7, b.getPackageId());	 
			 }
		
			 
			 
			 
			 st.executeUpdate();
		 }
		 
		 catch(Exception e)
		 {
			 System.out.println(e);
			 
		 }
	}
		
		public void updateBooking(Bookings b)
		{
			String sql= "update bookings set BookingDate=?, BookingNo=?, TravelerCount=?, CustomerId=?, TripTypeId=?, PackageId=? where BookingId=?";
			try 
			 {	System.out.println("Im here");
				 PreparedStatement st = con.prepareStatement(sql);
				 
				 if (b.getBookingDate()==null){
					 st.setNull(1, java.sql.Types.DATE);
					
				 }
				 else{
					 st.setDate(1,b.getBookingDate());
				 }
			
				 st.setString(2, b.getBookingNo());
				
				 st.setDouble(3, b.getTravelerCount());
				 
				 if (b.getCustomerId()==0){
					 st.setNull(4, Types.INTEGER);
				 }
				 else
				 {
					 st.setInt(4, b.getCustomerId());
				 }
				 st.setString(5, b.getTripTypeId());
				 if (b.getPackageId()==0){
					 st.setNull(6, Types.INTEGER);
				 }
				 else
				 {
					 st.setInt(6, b.getPackageId());	 
				 }
				 st.setInt(7, b.getBookingId());
			
				 
				 
				 
				 st.executeUpdate();
			 }
			 
			 catch(Exception e)
			 {
				 System.out.println(e);
				 
			 }
		 
		
	
		
	}
		
		public void deleteBooking(int id)
		{
			String sql= "delete from bookings where BookingId=?";
			try 
			 {	System.out.println("Im here to delete");
				 PreparedStatement st = con.prepareStatement(sql);
			

				 st.setInt(1, id);
			
				
				 st.executeUpdate();
			 }
			 
			 catch(Exception e)
			 {
				 System.out.println(e);
				 
			 }
		}
	
	
	
	


}
