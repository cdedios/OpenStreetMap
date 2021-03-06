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


public class NamedNodeOSM extends NodeOSM{

      String name;
      LinkedList<CornerOSM> corners;

    public NamedNodeOSM(){
      corners = new LinkedList<CornerOSM>();

    }

    public NamedNodeOSM(String id, String name){
        super(id);
        this.name = name;
        corners = new LinkedList<CornerOSM>();


    }

    public NamedNodeOSM(String name){

        this.name = name;
        corners = new LinkedList<CornerOSM>();


    }

    public void setCornerList(List<CornerOSM> cl){

        corners = (LinkedList<CornerOSM>) cl;
    }

    public List<CornerOSM> getStreetsOrSquares(){
        return this.corners;

    }

    public List<CornerOSM> getCorners(){

        return this.corners;

    }

    public void setName(String name){

        this.name = name;
    }
    public String getName(){
        return this.name;
    }

    public String toString(){

        String st = "Named node\n"+super.toString()+
                    "Name="+this.name+"\n"+"Corners=\n";
        for (CornerOSM c: this.corners){

            st=st+"------>id="+c.getId()+"\n";

        }
        return st;

    }


}
