/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Openstreetview.utils;

/**
 *
 * @author Carlos de Dios
 */
import java.util.LinkedList;
import java.util.List;

public class CornerOSM extends NodeOSM{

    private LinkedList<NamedNodeOSM> componentSt;
    private List<String> nodeFeatures;
    private double lat;
    private double lon;

    public CornerOSM(){

        componentSt = new LinkedList<NamedNodeOSM>();

    }

    public CornerOSM(String id){

        super(id);
        componentSt = new LinkedList<NamedNodeOSM>();

    }


    public CornerOSM(String id, double lat, double lon, List<String> features){

        super(id);
        this.lat = lat;
        this.lon = lon;
        this.componentSt = new LinkedList<NamedNodeOSM>();
        this.nodeFeatures = features;

    }

    public String getType(){
        return "Corner";
    }

    public void addStreetOrSquare(NamedNodeOSM nod){

        componentSt.addLast(nod);
    }

    public List<NamedNodeOSM> getComponentSts(){
        return this.componentSt;

    }

    public void setLon(double lon){
        this.lon = lon;
    }

    public void setLat(double lat){
        this.lat = lat;
    }

    public double getLat(){
        return this.lat;
    }
    public double getLon(){
        return this.lon;
    }


    public List<String> getNodeFeatures(){

        return this.nodeFeatures;
    }

     public String toString(){
        String st = super.toString()+
                    "Lat="+this.lat+"\n"+
                    "Lon="+this.lon+"\n"+
                    "Corner between=\n";
        for (NamedNodeOSM nod: this.componentSt){

            st=st+"------>name="+nod.getName()+"\n";

        }
        st = st + "Corner features=\n"+this.nodeFeatures.toString();
        return st;

    }


}
