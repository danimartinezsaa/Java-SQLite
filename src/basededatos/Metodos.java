
package basededatos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *Método Controlador con métodos que conectan con los métodos de la clase Modelo que ejecutan las consultas SQL.
 * @author dani
 */
public class Metodos{

    public static ArrayList<Coche> coches=new ArrayList();
/**
 * Método para insertar un nuevo elemento Coche en la base de datos.
 * @param coche Objeto a insertar
 */
    public static void insertarCoche(Coche coche){
        Conexion.insert(coche);
        verCoches();
    }
/**
 * Método que hace una consulta en la base de datos e introduce el resultado en el ArrayList coches.
 */
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
/**
 * Método para eliminar un Objeto coches de la base de datos.
 * @param coche Objeto a eliminar.
 */
    public static void eliminarCoche(Coche coche){
        Conexion.delete(coche);
        verCoches();
    }
/**
 * Método que modifica un Objeto de la tabla Coches.
 * @param coche1 Objeto a modificar.
 * @param coche2 Nuevo objeto tipo Coche que sustituye al anterior.
 */
    public static void modificarCoche(Coche coche1, Coche coche2){
        Conexion.update(coche1, coche2);
        verCoches();
    }
/**
 * Método para buscar un coche en concreto por cualquiera de sus atributos.
 * @param busqueda Texto con la búsqueda a realizar en la base de datos.
 */
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
