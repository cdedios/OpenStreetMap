/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Openstreetview.utils;

/**
 *
 * @author Carlos de Dios
 */
public class NodeOSM {

    private String id;
    private String type;

    public NodeOSM(){}
    public NodeOSM(String id){
        this.id = id;

    }

public void setId(String id){

    this.id = id;

}

    public String getId(){

        return this.id;
    }

    public String getType(){
        return "Node";
    }

    public String toString(){

        return "Node Information:\n"+"id="+id+"\n";

    }

}
