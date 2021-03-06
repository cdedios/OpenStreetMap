/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Openstreetview.paths;

/**
 *
 * @author Carlos de Dios
 */

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;

public class StreetMap {

    HashMap<String, VCorner> nodes;
    List<EStreet> arestes;

    public StreetMap(){
        this.nodes = new HashMap<String, VCorner>();
        this.arestes = new LinkedList<EStreet>();
    }

    public String getCornerId(List<String> streets)throws NonExistingCornerException{
        HashMap<String, VCorner> vertexs = new HashMap<String, VCorner>();
        LinkedList<String> identificadors = new LinkedList<String>();

        for(String str : streets) {
            for(EStreet street: arestes) {
                if(street.getStreetName().compareToIgnoreCase(str) == 0) {

                    VCorner corn = vertexs.put(street.getCornerCurr().getId(), street.getCornerCurr());
                    if(corn != null)
                        identificadors.add(corn.getId());
                }
            }
        }

        if(identificadors.size() > 1 || identificadors.size() == 0)
            throw new NonExistingCornerException();

        return identificadors.get(0);

    }

    public void addCorner(VCorner corner){
       nodes.put(corner.getId(), corner);
    }
    
    public void addStreet(String streetName, String idCorner1, String idCorner2)
           throws NonExistingCornerException{
        if(nodes.containsKey(idCorner1) && nodes.containsKey(idCorner2)){
            VCorner c1 = nodes.get(idCorner1);
            VCorner c2 = nodes.get(idCorner2);
            double dist= calculateDistance(c1,c2);
            EStreet newEStreet = new EStreet(c1, c2, streetName, dist);
            arestes.add(newEStreet);
        }else{
            throw new NonExistingCornerException();
        }   
    }

    public List<EStreet> getAdjacentCorners(String id){
        LinkedList<EStreet> arestesAdjacents = new LinkedList<EStreet>();

        for ( EStreet street : arestes) {
            if ( street.getCornerCurr().getId().compareTo(id)==0 || street.getCornerAdj().getId().compareTo(id)==0) {
                arestesAdjacents.add(street);
            }
        }

        return arestesAdjacents;
        
    }

    public List<VCorner> getCorners(){
        return (List) nodes.values();
    }

    @Override
    public String toString(){
        String st = null;
        List<String> lis = null;
        Collection<VCorner> corners = nodes.values(); 
        Iterator<VCorner> iterator = corners.iterator();
        
        while(iterator.hasNext()){ 
            VCorner next = iterator.next(); 
            List<EStreet> arestesAdjacents= getAdjacentCorners(next.getId()); 
            st="Cantonada:"+next.toString()+"Adjacents:"+st+arestesAdjacents.toString();
        }
        return st;
    }

    private double calculateDistance(VCorner c1, VCorner c2) {
        double R = 6371.0;
        double lat1 = c1.getLat(); 
        double lon1 = c1.getLon();
        double lat2 = c2.getLat();
        double lon2 = c2.getLon();        
        double dlon = lon2-lon1; 
        double dlat = lat2-lat1;
        double a = Math.pow(Math.sin(dlat/2),2)+Math.cos(lat1)*Math.cos(lat2)*Math.sin(dlon/2);
        double c = 2*Math.asin(Math.min(1,Math.sqrt(Math.abs(a))));
        return R*c;
        
    } 
    private double toRadians(double x){ 
       return (x/360)*(2*Math.PI);
    }
}
