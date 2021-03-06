/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Openstreetview.paths;

/**
 *
 * @author Carlos de Dios
 */



public class EStreet {

    private VCorner cornerCurr;
    private VCorner cornerAdj;
    private String streetName;
    double distance;

    public EStreet(){}
    public EStreet(VCorner cornerCurr, VCorner cornerAdj, String street, double dist){
       this.cornerAdj = cornerAdj;
       this.cornerCurr= cornerCurr;
       this.streetName = street;
       this.distance = dist;
    }

    public void setCornerAdj(VCorner cornerAdj){
        this.cornerAdj = cornerAdj;

    }

    public void setCornerCurr(VCorner cornerCurr){
        this.cornerCurr = cornerCurr;

    }

    public void setStreetName(String name){
        this.streetName = name;

    }

    public void setDistance(double dist){
        this.distance = dist;

    }

    public double getDistance(){

        return this.distance;
    }

    public String getStreetName(){

        return this.streetName;
    }
    public VCorner getCornerAdj(){

        return this.cornerAdj;
    }

    public VCorner getCornerCurr(){

        return this.cornerCurr;
    }

    public String toString(){

        return "\n street name="+this.streetName+"\n"+
                "corner 1="+this.cornerCurr.getName()+
                "("+this.cornerCurr.getId()+")"+"\n"+
                "corner 2="+this.cornerAdj.getName()+
                "("+this.cornerAdj.getId()+")"+"\n"+
                "distance="+this.distance+"\n\n";
                

    }

}
