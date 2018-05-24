/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basededatos;

/**
 *
 * @author dani
 */
public class Coche{
    String matricula,marca,motor;

    public Coche(String matricula, String marca, String motor){
        this.matricula=matricula;
        this.marca=marca;
        this.motor=motor;
    }

    public String getMatricula(){
        return matricula;
    }

    public void setMatricula(String matricula){
        this.matricula=matricula;
    }

    public String getMarca(){
        return marca;
    }

    public void setMarca(String marca){
        this.marca=marca;
    }

    public String getMotor(){
        return motor;
    }

    public void setMotor(String motor){
        this.motor=motor;
    }
    
    public void guardar(){
        Conexion conexion=new Conexion();
        conexion.insert(this);
    }

    @Override
    public String toString(){
        return "Coche{"+"matricula="+matricula+", marca="+marca+", motor="+motor+'}';
    }
    
    
}
