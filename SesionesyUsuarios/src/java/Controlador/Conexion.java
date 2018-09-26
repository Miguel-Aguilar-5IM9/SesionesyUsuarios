package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion 
{
    String url = "jdbc:mysql://localhost/dbLabIII";
    String usr = "root";
    String pass = "n0m3l0";
    Connection conexion = null;
    Statement sta = null;
    ResultSet rset = null;
    
    public int Conecta (String correo, String contra)
    {
        int tipo = 0;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(url, usr, pass);
        }
        catch (ClassNotFoundException | SQLException e) 
        {
            System.out.println("Error en la conexión: " + e.toString());
        }
        try 
        {
            sta = conexion.createStatement();
            rset = sta.executeQuery("select * from Usuarios where email = '" + correo + "' and password = '" + contra + "'");
            if(rset.next())
            {
                tipo = rset.getInt("tipo");
            }
            else
            {
                tipo = 0;
            }
        } 
        catch (SQLException e) 
        {
            System.out.println("Error: " + e.toString());
        }
        
        return tipo;
    }
}
