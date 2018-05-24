/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basededatos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author dani
 */
public class Metodos{

    public static ArrayList<Coche> coches=new ArrayList();

    public static void insertarCoche(Coche coche){
        Conexion.insert(coche);
        verCoches();
    }

    public static void verCoches(){

        try{
            ResultSet resultado=Conexion.select();
            
            coches.clear();
            
            while(resultado.next()){
                coches.add(new Coche(resultado.getString("matricula"), resultado.getString("marca"), resultado.getString("motor")));
            }
            
            resultado.close();
        }catch(SQLException ex){
            System.out.println("Error:"+ex.getMessage());
        }
        Conexion.close();
    }

    public static void eliminarCoche(Coche coche){
        Conexion.delete(coche);
        verCoches();
    }

    public static void modificarCoche(Coche coche1, Coche coche2){
        Conexion.update(coche1, coche2);
        verCoches();
    }

    public static void buscarCoche(String busqueda){
        try{
            ResultSet rs=Conexion.selectWhere(busqueda);
            coches.clear();
            while(rs.next()){
                coches.add(new Coche(rs.getString("matricula"), rs.getString("marca"), rs.getString("motor")));
            }
        }catch(SQLException ex){
            System.out.println("Error al añadir coches al ArrayList");
        }
        Conexion.close();
    }

}
