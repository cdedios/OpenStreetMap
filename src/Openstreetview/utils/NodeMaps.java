/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Openstreetview.utils;

/**
 *
 * @author Carlos de Dios
 */

import java.util.HashMap;

public class NodeMaps {

   public HashMap<String,NamedNodeOSM> namedNodMap =
                                         new HashMap<String,NamedNodeOSM>();
   public HashMap<String,CornerOSM> cornerMap =
                                         new HashMap<String,CornerOSM>();


  public NodeMaps(){

   this.namedNodMap = new HashMap<String,NamedNodeOSM>();
   this.cornerMap =   new HashMap<String,CornerOSM>();

  } 

}
