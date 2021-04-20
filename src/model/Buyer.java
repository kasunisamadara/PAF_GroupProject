package model;

import java.sql.*; 

public class Buyer {


//A common method to connect to the DB
   private Connection connect() 
     { 
       Connection con = null; 
   try
     { 
     Class.forName("com.mysql.jdbc.Driver"); 

      //Provide the correct details: DBServer/DBName, username, password 
     con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/buyer", "root", ""); 
     } 
    catch (Exception e) 
    {e.printStackTrace();} 
     return con; 
    } 
   
    public String insertBuyer(String code, String name, String email, Integer buyerContactNumber, String address) 
    { 
      String output = ""; 
      try
     { 
      Connection con = connect(); 
      if (con == null) 
      {
    	  return "Error while connecting to the database for inserting."; 
       }
      
      // create a prepared statement
      
      String query = " insert into Buyer (`Buyer ID`,`Buyer Code`,`Buyer Name`,`Buyer Email`,`Buyer Contact Number`,`Buyer Address`)"+ " values (?, ?, ?, ?, ?, ?)"; 
      PreparedStatement preparedStmt = con.prepareStatement(query); 
      
     // binding values
     preparedStmt.setInt(1, 0); 
     preparedStmt.setString(2, code); 
     preparedStmt.setString(3, name); 
     preparedStmt.setString(4, email); 
     preparedStmt.setInt(5, buyerContactNumber); 
     preparedStmt.setString(6,address);
     
     System.out.println(code);
     System.out.println(name);
     
    // execute the statement3
    preparedStmt.execute(); 
    con.close(); 
    output = "Inserted successfully"; 
    } 
    catch (Exception e) 
    { 
    output = "Error while inserting buyer.";
    e.printStackTrace();
    //System.err.println(e.getMessage()); 
    } 
    return output; 
} 
    
    public String readBuyer() 
{ 
    String output = ""; 
try
{ 
    Connection con = connect(); 
    if (con == null) 
{
    	return "Error while connecting to the database for reading."; 
 } 
// Prepare the html table to be displayed
   output = "<table border='1'><tr><th>Buyer Code</th><th>Buyer Name</th>" + "<th>Buyer Email</th>" + "<th>Buyer Conatact Number</th>" +"<th>Buyer Address</th>" +"<th>Update</th><th>Remove</th></tr>"; 

    String query = "select * from Buyer"; 
    Statement stmt = con.createStatement(); 
    ResultSet rs = stmt.executeQuery(query); 
   // iterate through the rows in the result set
    while (rs.next()) 
{ 
    String BuyerID = Integer.toString(rs.getInt("Buyer ID")); 
    String BuyerCode = rs.getString("Buyer Code"); 
    String BuyerName = rs.getString("Buyer Name");
    String BuyerEmail = rs.getString("Buyer Email");
    String BuyerContactNumber = rs.getString("Buyer Contact Number"); 
    String BuyerAddress = rs.getString("Buyer Address"); 

    // Add into the html table
     output += "<tr><td>" + BuyerCode + "</td>"; 
     output += "<td>" + BuyerName + "</td>"; 
     output += "<td>" + BuyerEmail + "</td>"; 
     output += "<td>" + BuyerContactNumber + "</td>"; 
     output += "<td>" + BuyerAddress +"</td>";
     
    // buttons
    output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"+ "<td><form method='post' action='items.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"+ "<input name='itemID' type='hidden' value='" + BuyerID + "'>" + "</form></td></tr>"; 
} 
    con.close(); 
    // Complete the html table
    output += "</table>"; 
} 
    catch (Exception e) 
{ 
    output = "Error while reading Buyer."; 
    System.err.println(e.getMessage()); 
} 
    return output; 
} 
     public String updateBuyer(String ID, String code, String name, String email, String contactNumber, String address)
{ 
      String output = ""; 
      try
{ 
    Connection con = connect(); 
    if (con == null) 
   {
	return "Error while connecting to the database for updating."; 
	} 
// create a prepared statement
     String query = "UPDATE Buyer SET Buyer Code=?,Buyer Name=?,Buyer Email=?,Buyer Contact Number=?,Buyer Address=? WHERE Buyer ID=?"; 
     PreparedStatement preparedStmt = con.prepareStatement(query); 
// binding values
     preparedStmt.setString(1, code); 
     preparedStmt.setString(2, name);
     preparedStmt.setString(2, email); 
     preparedStmt.setString(3,contactNumber); 
     preparedStmt.setString(4, address); 
     preparedStmt.setInt(5, Integer.parseInt(ID)); 

     // execute the statement
     preparedStmt.execute(); 
     con.close(); 
     output = "Updated Successfully"; 
     } 
     catch (Exception e) 
     { 
     output = "Error while updating the Buyer."; 
     System.err.println(e.getMessage()); 
     } 
     return output; 
     } 
     public String deleteBuyer(String BuyerID) 
     { 
     String output = ""; 
     try
     { 
     Connection con = connect(); 
      if (con == null) 
     {
    	return "Error while connecting to the database for deleting."; 
      } 
      
// create a prepared statement
     String query = "delete from Buyer where Buyer ID=?"; 
     PreparedStatement preparedStmt = con.prepareStatement(query); 
// binding values
     preparedStmt.setInt(1, Integer.parseInt(BuyerID)); 
// execute the statement
    preparedStmt.execute(); 
    con.close(); 
    output = "Deleted successfully"; 
} 
    catch (Exception e) 
{ 
    output = "Error while deleting the Buyer.";
    System.err.println(e.getMessage());
} 
return output;
}
}