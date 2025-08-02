package com.telusko.app;
import java.sql.Connection;
import java.sql.DriverManager;

public class check {
    @SuppressWarnings({"ConvertToTryWithResources", "UseSpecificCatch", "CallToPrintStackTrace"})
    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/hiberlearning", 
                "root", 
                "benjiro344");
            
            System.out.println("Connection successful!");
            conn.close();
        } catch (Exception e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
    }
}