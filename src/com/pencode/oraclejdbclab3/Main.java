/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pencode.oraclejdbclab3;

import com.pencode.oraclejdbclab3.dao.DAO;
import com.pencode.oraclejdbclab3.dao.DeptDAO;
import com.pencode.oraclejdbclab3.model.Dept;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author RAJAB IMAM
 */
public class Main {

    static String url = "jdbc:oracle:thin:@localhost:1521:XE";
    static String user = "rajabimam";
    static String pass = "reason";
    static Connection conn = null;

    //Exercise 1: Display Department with the location
    public static void displayDepartment() throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet resultat = statement.executeQuery("SELECT deptno, dname, loc FROM dept");

        while (resultat.next()) {
            int deptno = resultat.getInt("deptno");
            String dname = resultat.getString("dname");
            String loc = resultat.getString("loc");

            System.out.println("Department " + deptno + " is for "
                    + dname + " and located in  " + loc);
        }
        resultat.close();
    }

    //Exercise 2: Move Department
    public static void moveDepartment(int newDeptno, int empno) throws SQLException {
        Statement statement = conn.createStatement();
        String updateQuery = "UPDATE EMP SET deptno = " + "\'" + newDeptno + "\' WHERE empno = " + empno;
        int result = statement.executeUpdate(updateQuery);
        if (result == 1) {
            System.out.println("Table updated successfully");
        }
        
    }

    //Exercise 3: Generic Display of Tables
    public static void displayTable(String tableName) throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet resultat = statement.executeQuery("SELECT * FROM " + tableName);

        ResultSetMetaData metaData = resultat.getMetaData();

        int rowCount = metaData.getColumnCount();

        for (int i = 1; i <= rowCount; i++) {
            System.out.print(metaData.getColumnLabel(i) + " | ");
        }
        System.out.println();

        while (resultat.next()) {
            for (int i = 1; i <= rowCount; i++) {
                System.out.print(resultat.getString(i) + " | ");
            }
            System.out.println();

        }
        resultat.close();
    }

    //Exercise 4: Security
    //What is the Security flow to the method above ? What are others Cons from this basic native method ?
    //-SQL Injection -Exposed to any malicious intent
    
    //Exercise 5: Modify moveDepatment to use Prepared Statement
    public static void moveDepartmentPrepSt() throws SQLException {
        String updateQuery = "UPDATE EMP SET deptno = ? WHERE empno = ?";
        PreparedStatement prepSt = conn.prepareStatement(updateQuery);
        
        prepSt.setInt(1, 30);
        prepSt.setInt(2, 7499);
        prepSt.executeUpdate();
        System.out.println("Update done");
    }
    //Exercise 6: Try with displayTable
    //It won't work, because prepared statement is for the column values and not for table name
    
       public static void displayTablePrepSt(String tableName) throws SQLException {
        String selectStat = "SELECT * FROM ? ";
        PreparedStatement prepSt = conn.prepareStatement(selectStat);
        prepSt.setString(1, tableName);
        ResultSet resultat = prepSt.executeQuery();
        ResultSetMetaData metaData = resultat.getMetaData();

        int rowCount = metaData.getColumnCount();

        for (int i = 1; i <= rowCount; i++) {
            System.out.print(metaData.getColumnLabel(i) + " | ");
        }
        System.out.println();

        while (resultat.next()) {
            for (int i = 1; i <= rowCount; i++) {
                System.out.print(resultat.getString(i) + " | ");
            }
            System.out.println();

        }
        resultat.close();

        
    }
    //Exercise 7: J2EE - Display answers on the browser
    
    //Part 2
       
    //Exercise 8: What are the cons of JDBC used above?
    //- No encapsulation - Complex when used in large projects - Not suitable for MVC
       
    //Exercise 9: Write the Bean for Department
    //Dept class in package com.pencode.oraclejdbclab3web.model;
       
    //Exercise 10: Write implementation for method find for department DAO
       
    //Exercise 11: Write Implementation for method find for employee DAO.
       
     
    public static void main(String[] args) throws SQLException {
        Scanner input = new Scanner(System.in);
        //System.out.println("Enter table name to display");
        //String tableName = input.nextLine();

        try {
            //step1: load the driver class
            Class.forName("oracle.jdbc.OracleDriver");

            //step2: create the connection object
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Connection Established");

            //step3: create the statement object
            Statement stmt = conn.createStatement();

            //displayDepartment();
            
            //Table name is taken from the keyboard
            //displayTable(tableName);
            
            //moveDepartment(20, 7499);
            
            //moveDepartmentPrepSt();
            
            //displayTablePrepSt(tableName);
            
           
DAO<Dept> departmentDao = new DeptDAO(conn);
Dept dept20 = departmentDao.find(20);
System.out.println(dept20); 

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
