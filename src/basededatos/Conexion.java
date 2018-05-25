
package basededatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase modelo con los métodos de conexión, cerrado y consultas o actualizaciones de una base de datos sqlite.
 */
public class Conexion{

    private static PreparedStatement st;
    private static ResultSet resultado;
    private static Connection conexion;
/**
 * Método para conectarse a la base de datos.
 */
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
/**
 * Método para cerrar la base de datos
 */
    public static void close(){
        try{
            conexion.close();
        }catch(SQLException ex){
            System.out.println("Error al cerrar la base de datos");
        }
    }
/**
 * Método para realizar una inserción en la base de datos de un nuevo elemento.
 * @param coche Objeto a introducir en la base de datos.
 */
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
/**
 * Método para realizar una consulta de todos los elementos de la tabla Coches
 * @return Resultado de la consulta
 */
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
/**
 * Método para borrar un elemento concreto de la tabla coches.
 * @param coche Elemento a eliminar.
 */
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
/**
 * Método para actualizar un elemento de la tabla coches
 * @param coche Elemento a actualizar.
 * @param coche2 Nuevo elemento a introducir en el lugar de coche.
 */
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
/**
 * Método para realizar una consulta condicionada por una búsqueda, si algún campo del Objeto coincide con el String que recibe.
 * @param busqueda String por el cuál se va a buscar el elemento.
 * @return Resultado de la consulta
 */
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
