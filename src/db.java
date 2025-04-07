/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author User
 */
public class db {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/pospro";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";
    db(){}
    
    public static String getUrl() {
        return DB_URL;
    }
    
    public static String getUser(){
        return DB_USER;
    }
    
    public static String getPass(){
        return DB_PASS;
    }
}
