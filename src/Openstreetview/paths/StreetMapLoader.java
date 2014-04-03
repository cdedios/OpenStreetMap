/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Openstreetview.paths;

/**
 *
 * @author Carlos de Dios
 */

import Openstreetview.utils.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class StreetMapLoader {


 private void loadStreets(StreetMap stMap, HashMap<String,NamedNodeOSM> namedNodeMap)
  throws NonExistingCornerException{

     CornerOSM current = null;

     for(NamedNodeOSM street: namedNodeMap.values()){

       List<CornerOSM> lcorners = street.getCorners();

       if (lcorners.size()>1){
           current = lcorners.get(0);
       }
       for (int i=1;i < lcorners.size();i++){

           stMap.addStreet(street.getName(),current.getId(),lcorners.get(i).getId());
           current = lcorners.get(i);
         }

     }
 }


  private void loadCorners(StreetMap stMap, HashMap<String,CornerOSM> cornerMap){

     for(CornerOSM corner: cornerMap.values()){
         LinkedList<String> lnames = new LinkedList<String>();
         for (NamedNodeOSM nod: corner.getComponentSts()){
             lnames.add(nod.getName());
         }

         VCorner vcor = new VCorner(corner.getId(),corner.getLat(),
                                    corner.getLon(),lnames,corner.getNodeFeatures());

         stMap.addCorner(vcor);
    }

  }

  public StreetMap loadStreetMap(String mapname){

    StreetMap stMap = new StreetMap();

    OSMReader reader = new OSMReader();

    try{
     NodeMaps nodemaps = reader.readOSM(mapname);


     loadCorners(stMap, nodemaps.cornerMap);
     loadStreets(stMap,nodemaps.namedNodMap);
      }
    catch(NonExistingCornerException e){
        System.out.println("Non existing corner");
        e.printStackTrace();}
    catch(Exception e){e.printStackTrace();}

  return stMap;
 }

}
