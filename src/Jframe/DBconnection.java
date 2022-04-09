/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Jframe;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author RAVI PANCHAL
 */
public class DBconnection {
    static Connection con = null;
    public static Connection getConnection()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver"); 			//call oracle driver before that add it into enviro. --> classpath add new
            con = DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/ec_bill_system", "root", "");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return con;
    }
}
