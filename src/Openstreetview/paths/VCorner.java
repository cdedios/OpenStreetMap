/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Openstreetview.paths;

/**
 *
 * @author Carlos de Dios
 */

import java.util.LinkedList;
import java.util.List;

public class VCorner {

    private String id;
    private double lat;
    private double lon;
    private LinkedList<String> streets;
    private List<String> features;

    public VCorner(){
        streets = new LinkedList<String>();
        id = "";
   }

   public VCorner(String id, double lat, double lon, 
                  LinkedList<String> streets, List<String> features){
        this.streets = streets;
        this.features = features;
        this.lat = lat;
        this.lon = lon;
        this.id = id;

    }

   public String getId(){

    return this.id;
   }

   public String getName(){

       String corner = "corner ";
       boolean first = true;

       for (String st : streets){
           if (first){first = false;}
           else {corner = corner+"-";}
           corner = corner + st;

       }
    return corner;
   }

   public double getLat(){

    return this.lat;
   }

   public double getLon(){

    return this.lon;
   }

   public List<String> getStreets(){

       return streets;
       
   }

   public List<String> getFeatures(){

       return features;

   }


   public String toString(){

       return "Corner: "+this.id+"("+this.getName()+")"+" Lon="+
               this.lon+" Lat="+this.lat+ "Features="+features;

   }
}
