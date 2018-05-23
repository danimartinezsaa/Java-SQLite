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
import java.util.ArrayList;
/**
 *
 * @author dani
 */
public class Conexion{
    
    private PreparedStatement st;
    private ResultSet resultado;
    private Connection conexion;
    public static ArrayList<Coche> coches=new ArrayList();
    
    public void connect(){
        try{
            conexion=DriverManager.getConnection("jdbc:sqlite:"+"Base.db");
            if(conexion!=null){
                System.out.println("Conectado");
            }
        }catch(SQLException ex){
            System.out.println("Error de conexión a la base de datos");
        }
    }
    
    public void close(){
        try{
            conexion.close();
        }catch(SQLException ex){
            System.out.println("Error al cerrar la base de datos");
        }
    }
    
    public void insertarCoche(Coche coche){
        connect();
        try{
            st=conexion.prepareStatement("insert into coches values('"+coche.getMatricula()+"'"
                    +","+"'"+coche.getMarca()+"'"
                    +","+"'"+coche.getMotor()+"'"+");");
            
            st.execute();
            recibirCoches();
        }catch(SQLException ex){
            System.out.println("Error al insertar en la base de datos.");
        }
        close();
    }
    
    public void recibirCoches(){
        
        connect();
        
        try{
            st=conexion.prepareStatement("select * from coches");
            resultado=st.executeQuery();
            coches.clear();
            
            while(resultado.next()){
                coches.add(new Coche(resultado.getString("matricula"),resultado.getString("marca"),resultado.getString("motor")));
            }

            resultado.close();
            
        }catch(SQLException ex){
            System.out.println("Error al ejecutar la consulta");
        }
        
        close();
    }
    
    public void borrarCoche(Coche coche){
        connect();
        
        try{
            st=conexion.prepareStatement("DELETE FROM coches WHERE matricula='"+coche.getMatricula()+"'");
            st.executeUpdate();
        }catch(SQLException ex){
            System.out.println("Error al eliminar el elemento");
        }
        recibirCoches();
        close();
    }
    
    public void actualizarCoche(Coche coche,Coche coche2){
        connect();
        
        try{  
            st=conexion.prepareStatement("update coches set matricula="+"'"+coche2.getMatricula()+"'"+", marca="+"'"+coche2.getMarca()+"'"+", motor="+"'"+coche2.getMotor()+"'"+" where matricula="+"'"+coche.getMatricula()+"'"+";");
            st.executeUpdate(); 
            
        }catch(SQLException ex){
            System.out.println("Error al actualizar la tabla");
        }
        recibirCoches();
        close();
    }
    
    public void buscarCoche(String busqueda){
        connect();
        try{
            st=conexion.prepareStatement("select * from coches where matricula="+"'"+busqueda+"'"+" or "+"marca="+"'"+busqueda+"'"+" or "+"motor="+"'"+busqueda+"'"+";");
            resultado=st.executeQuery();
            coches.clear();
            while(resultado.next()){
                coches.add(new Coche(resultado.getString("matricula"),resultado.getString("marca"),resultado.getString("motor")));
            }
        }catch(SQLException ex){
            System.out.println("Error al buscar");
        }
        
        close();
    }
}
