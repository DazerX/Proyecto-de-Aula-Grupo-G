package Entidades;

public class Balanceo extends Servicio{
    
    private int numRing;
    private double precio1;
    private double precio2;
    public Balanceo(  String descripcion, double porcentaje,double precio1,double precio2) {
        super("Balanceo", descripcion, porcentaje);
        this.numRing =0;
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
    public void calcularCosto(int noLlantas) {
        if(this.getNumRing()==13 || this.getNumRing()==14){
            super.setCosto(this.precio1*noLlantas);
        }
        if(this.getNumRing() > 14 || this.getNumRing()<19){
            super.setCosto(this.precio2*noLlantas);
        }else{
            System.out.println("Error numero de Ring Incorrecto");
        }

    }
    
    
    @Override
    public String toString() {
        return super.toString()+
                "Precio por llanta de Ring #13 y #14     = "+this.precio1+"\n"+
                "Precio por llanta de Ring #15-18 = "+this.precio2;
    }


}
