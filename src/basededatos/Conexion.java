/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basededatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author dani
 */
public class Conexion{

    private static PreparedStatement st;
    private static ResultSet resultado;
    private static Connection conexion;

    public static void connect(){
        try{
            conexion=DriverManager.getConnection("jdbc:sqlite:"+"Base.db");
            if(conexion!=null){
                System.out.println("Conectado");
            }
        }catch(SQLException ex){
            System.out.println("Error de conexión a la base de datos");
        }
    }

    public static void close(){
        try{
            conexion.close();
        }catch(SQLException ex){
            System.out.println("Error al cerrar la base de datos");
        }
    }

    public static void insert(Coche coche){
        connect();
        try{
            st=conexion.prepareStatement("insert into coches values('"+coche.getMatricula()+"'"
                    +","+"'"+coche.getMarca()+"'"
                    +","+"'"+coche.getMotor()+"'"+");");

            st.execute();
            
        }catch(SQLException ex){
            System.out.println("Error al insertar en la base de datos.");
        }
        close();
    }

    public static ResultSet select(){

        connect();

        try{
            st=conexion.prepareStatement("select * from coches");
            resultado=st.executeQuery();


        }catch(SQLException ex){
            System.out.println("Error al ejecutar la consulta");
        }
        return resultado;
    }

    public static void delete(Coche coche){
        connect();

        try{
            st=conexion.prepareStatement("DELETE FROM coches WHERE matricula='"+coche.getMatricula()+"'");
            st.executeUpdate();
        }catch(SQLException ex){
            System.out.println("Error al eliminar el elemento");
        }
        select();
        close();
    }

    public static void update(Coche coche, Coche coche2){
        connect();

        try{
            st=conexion.prepareStatement("update coches set matricula="
                    +"'"+coche2.getMatricula()+"'"
                    +", marca="+"'"+coche2.getMarca()+"'"+", motor="+"'"+coche2.getMotor()+"'"
                    +" where matricula="+"'"+coche.getMatricula()+"'"+";");
            st.executeUpdate();

        }catch(SQLException ex){
            System.out.println("Error al actualizar la tabla");
        }
        select();
        close();
    }

    public static ResultSet selectWhere(String busqueda){
        connect();
        try{
            st=conexion.prepareStatement("select * from coches where matricula="+"'"+busqueda+"'"+" or "+"marca="+"'"+busqueda+"'"+" or "+"motor="+"'"+busqueda+"'"+";");
            resultado=st.executeQuery();
        }catch(SQLException ex){
            System.out.println("Error al buscar");
        }
        return resultado;
    }
}
