/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author USER
 */
public class Alineacion extends Servicio{
    
    private int numRing;
    private double precio1;
    private double precio2;

    public Alineacion(  String descripcion, double porcentaje,double precio1,double precio2) {
        super("Alineacion", descripcion, porcentaje);
        this.numRing=0;
        this.precio1=precio1;
        this.precio2=precio2;
        
    }

    public int getNumRing() {
        return numRing;
    }

    public void setNumRing(int numRing) {
        this.numRing = numRing;
    }

    public double getPrecio1() {
        return precio1;
    }

    public void setPrecio1(double precio1) {
        this.precio1 = precio1;
    }

    public double getPrecio2() {
        return precio2;
    }

    public void setPrecio2(double precio2) {
        this.precio2 = precio2;
    }

    
    
    @Override
    public void calcularCosto(int tipoVehiculo) {
        if(tipoVehiculo==1){
            super.setCosto(this.precio1);
        }else{
            super.setCosto(this.precio2);
        }
    }

    @Override
    public String toString() {
       return super.toString() +
               "Precio Camioneta= "+this.precio1+"\n"+
               "Precio Automovil= "+this.precio2;
    }

    

    
   
}
