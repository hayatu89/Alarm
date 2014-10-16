/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package alarmapp;

import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hayatu
 */
public class DateMgr extends TimerTask{
        static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
        static final String DB_URL = "jdbc:mysql://localhost:3306/reminder";
        static final String USER = "username";
        static final String PASS = "password";
        @Override
        public void run(){
            try{
                Statement stmt;
                Connection conn = null ;
                
                try{
                    
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                    //get current date time with Date()
                    java.util.Date d = new java.util.Date();
                    
                    String m = dateFormat.format(d);
                    String s = m + ":00.0";
                    System.out.println(dateFormat.format(s));
                    //STEP 2: Register JDBC driver
                    Class.forName("com.mysql.jdbc.Driver");
                    
                    //STEP 3: Open a connection
                    System.out.println("Connecting to a selected database...");
                    conn = DriverManager.getConnection(DB_URL, USER, PASS);
                    System.out.println("Connected database successfully...");
                    
                    //STEP 4: Execute a query
                    System.out.println("Creating statement...");
                    
                    stmt = conn.createStatement();
                    String sql = "SELECT * FROM  reminder.list";
                    ResultSet rs = stmt.executeQuery(sql);
                    //STEP 5: Extract data from result set
                    while(rs.next()){
                        //Retrieve by column name
                        int id  = rs.getInt("id");
                        String message = rs.getString("Message");
                        String when = rs.getString("When");
                        System.out.println( "Time for " + message + " At " + when);
                    }
                    rs.close();
                }catch (Exception e){
                }
                conn.close();
            }catch (SQLException ex){                
                Logger.getLogger(DateMgr.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
    }