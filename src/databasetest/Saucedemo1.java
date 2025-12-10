package databasetest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Saucedemo1 {
	WebDriver driver = new ChromeDriver();
	Connection con;
	Statement stmt;
	ResultSet rs;
	String firstname;
	String Lastname;
	@BeforeTest
	
	public void startUp() throws SQLException {
		driver.get("https://smartbuy-me.com/account/register");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();
		//database server
		con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/classicmodels",  "root",  "abc123"	);
		}

	
	
	@Test(priority=1)
	public void UpdateTheData() throws SQLException, InterruptedException {
		
		String Query ="update customers set customerName='layan' where customerNumber=103";
	     stmt= con.createStatement();
	    int RowUpdated = stmt.executeUpdate(Query);
	    System.out.println(RowUpdated);
	    Thread.sleep(1000);
	
	}
	
	
	
	@Test(priority=2)
	public void ReadDataInsideTheBrowser() throws SQLException {
	String query="select * from customers where customerNumber =103";
	stmt=con.createStatement(); // to connect to url
	rs=stmt.executeQuery(query);
	while(rs.next()) {//thats means he data is back or not
		//System.out.println(rs.getInt("customerNumber")); 103
	//	System.out.println(rs.getString("customerName")); name
		firstname=rs.getString("customerName");
		Lastname=rs.getString("contactLastName");
	}
	//rs used only in readdata
	
	WebElement fname =driver.findElement(By.id("customer[first_name]"))	;
	fname.sendKeys(firstname);
	WebElement lname =driver.findElement(By.id("customer[last_name]"))	;
	lname.sendKeys(Lastname);
	}
	
	
	@AfterTest
	public void After() {}

}
