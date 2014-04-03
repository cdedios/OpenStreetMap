/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Openstreetview;

/**
 *
 * @author Carlos de Dios
 */

import Openstreetview.utils.*;
import Openstreetview.paths.*;


import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {

    public static void main(String[] args) {
      

        StreetMapLoader stml = new StreetMapLoader();

        StreetMap stm  = stml.loadStreetMap("lleida.osm");
        System.out.println(stm.toString());

        ArrayList<String> lis = new ArrayList<String>();
        lis.add("Carrer Bisbe Ruano");
        lis.add("Carrer de Vallcalent");
        try {
            System.out.println("cantonada ruano-vallcalent (550450190): "+stm.getCornerId(lis));

            //FEU MÉS PROVES....
        } catch (NonExistingCornerException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

}
