/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Openstreetview.utils;

/**
 *
 * @author Carlos de Dios
 */

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.util.Collection;

public class OSMReader {

  public void setIdAndName(NamedNodeOSM stsq, Element elmnt){

      String name = null;
      String id = elmnt.getAttribute("id");

      NodeList lTags = elmnt.getElementsByTagName("tag");

      int s =0;
      while (s < lTags.getLength() && name == null) {
        Node fstNode = lTags.item(s);
        if (fstNode.getNodeType() == Node.ELEMENT_NODE) {

          Element tagEl = (Element) fstNode;

          String type = tagEl.getAttribute("k");

          if (type.equals("name")) name = tagEl.getAttribute("v");

        }
        s++;
      }
          stsq.setId(id);
          stsq.setName(name);
     }

private List<CornerOSM> getCorners(NodeList cIdList, HashMap<String,CornerOSM>
                                                                        nodMap){

    LinkedList<CornerOSM> lcorners = new LinkedList<CornerOSM>();

  for (int s = 0; s < cIdList.getLength(); s++) {

     Node fstNode = cIdList.item(s);
     if (fstNode.getNodeType() == Node.ELEMENT_NODE) {

           Element elmnt = (Element) fstNode;


           CornerOSM corner = (CornerOSM) nodMap.get(elmnt.getAttribute("ref"));

           if (corner != null) lcorners.addLast(corner);
           //Només ens interessen els nodes integrants d'un carrer que siguin
           //autenticament cantonades....
           //A readCorners haurem eliminat aquells nodes que siguin traffic-signals
           //o altres coses no cantonades (ens en deixarem alguns).
     }
    }

    return lcorners;



}

private void associateCornersToStSq(NamedNodeOSM stsq,
                                    List<CornerOSM> cornerList){

    stsq.setCornerList(cornerList);

}


private void associateStSqToCorners (List<CornerOSM> cornerList,
                                     NamedNodeOSM stsq){

     for (CornerOSM c : cornerList){
         c.getComponentSts().add(stsq);
     }



}

private boolean isAttributeFeature(String s){
    return (s.equals("highway") || s.equals("amenity") || s.equals("crossing") ||
            s.equals("landuse") || s.equals("railway"));

//Compte: cal llegir-ho d'un fitxer de propietats.

}



private List<String> getFeatures(NodeList nodel){

    LinkedList<String> lfeatures = new LinkedList<String>();


   for (int s = 0; s < nodel.getLength(); s++) {

     Node node = nodel.item(s);


     if (node.getNodeType() == Node.ELEMENT_NODE){

         Element nodeE = (Element)node;
         if (isAttributeFeature(nodeE.getAttribute("k"))){
              lfeatures.add(nodeE.getAttribute("v"));

         }

     }
   }
    return lfeatures;
}


private  void   readCorners(NodeList nodeLst, NodeMaps nodeMaps){
   try{
    for (int s = 0; s < nodeLst.getLength(); s++) {
      Node fstNode = nodeLst.item(s);
      if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
         Element elmnt = (Element) fstNode;

         String id = elmnt.getAttribute("id");
         nodeMaps.cornerMap.
            put(id,
                new CornerOSM(id,
                              Double.parseDouble(elmnt.getAttribute("lat")),
                              Double.parseDouble(elmnt.getAttribute("lon")),
                              getFeatures(elmnt.getElementsByTagName("tag"))
                             )
              );
          
      }
     }
    }
    catch(Exception e){e.printStackTrace();}

}

private void readSquaresStreets(NodeList nodeLst,
                                NodeMaps nodeMaps){

    String id = null;
    String name = null;

   for (int s = 0; s < nodeLst.getLength(); s++) {

     Node fstNode = nodeLst.item(s);
     if (fstNode.getNodeType() == Node.ELEMENT_NODE){


           Element elmnt = (Element) fstNode;

         
           NamedNodeOSM stsq = new NamedNodeOSM();
           setIdAndName(stsq,elmnt);
           if (stsq.getName()!=null){


            NodeList cornerIdList = elmnt.getElementsByTagName("nd");
            List<CornerOSM> cornerList =
                    getCorners(cornerIdList,nodeMaps.cornerMap);
            associateCornersToStSq(stsq,cornerList);
            associateStSqToCorners(cornerList,stsq);
            nodeMaps.namedNodMap.put(stsq.getId(), stsq);
         }

       }
    }
}

private void pruneCorners(NodeMaps nodeMaps){

    Collection<CornerOSM> corners = nodeMaps.cornerMap.values();
    LinkedList<CornerOSM> lis = new LinkedList<CornerOSM>();
    LinkedList<CornerOSM> lis1 = new LinkedList<CornerOSM>();

    for (CornerOSM corner: corners){

        if (corner.getComponentSts().isEmpty() ){

            lis.add(corner);

            
        }
        else if (corner.getComponentSts().size() == 1 ){
            lis1.add(corner);
        }
    }

    for (CornerOSM corner: lis){
       nodeMaps.cornerMap.remove(corner.getId());
    }

    for (CornerOSM corner: lis1){

       List<CornerOSM> l= nodeMaps.namedNodMap.get(
               corner.getComponentSts().get(0).getId()).getCorners();

           l.remove(l.indexOf(corner));
           nodeMaps.cornerMap.remove(corner.getId());

    }


 }

  public NodeMaps readOSM(String filename) {

   NodeMaps nodeMaps = new NodeMaps();
   try {
    File file = new File(filename);
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder db = dbf.newDocumentBuilder();
    Document doc = db.parse(file);
  
    NodeList nodeLst = doc.getElementsByTagName("node");
    readCorners(nodeLst,nodeMaps);

    NodeList nodeLst2 = doc.getElementsByTagName("way");
    readSquaresStreets(nodeLst2,nodeMaps);

    pruneCorners(nodeMaps);

   }
   catch(Exception e){e.printStackTrace();}

   return nodeMaps;
 }
}










