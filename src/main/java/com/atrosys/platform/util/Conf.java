package com.atrosys.platform.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * Created by asgari on 12/30/17.
 */
public  class Conf {
    private static File fXmlFile = new File("src/main/java/com/atrosys/platform/constant.xml");

    private static Document doc;
    private static Element element;
    private static NodeList nodeList;



    static {
        try{

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
        }catch(Exception e){
            throw new RuntimeException("Exception occured in creating singleton instance");
        }
    }

    private Conf(){
    }

    public static Url url(){
        return new Url();
    }

    public static class Url{

        private Url(){

            nodeList = doc.getElementsByTagName("url");
            element = (Element) nodeList.item(0);
        }
        public  Admin admin(){return new Admin();}
        public Manager manager(){return new Manager();}
        public Operator operator(){return new Operator();}
        public Client client(){return new Client();}
        public static class Admin{


            private Admin(){
                nodeList = element.getElementsByTagName("admin");
            }
            public  String home(){
                return ((Element)nodeList.item(0)).getElementsByTagName("home").item(0).getTextContent();
            }
        }
        public static class Manager{
            private Manager(){
                nodeList = element.getElementsByTagName("admin");
            }
            public  String home(){
                return ((Element)nodeList.item(0)).getElementsByTagName("home").item(0).getTextContent();
            }
        }

        public static class Operator{
            private Operator(){
                nodeList = element.getElementsByTagName("operator");
            }
            public  String home(){
                return ((Element)nodeList.item(0)).getElementsByTagName("home").item(0).getTextContent();
            }
        }
        public static class Client{
            private Client(){
                nodeList = element.getElementsByTagName("client");
            }
            public  String home(){
                return ((Element)nodeList.item(0)).getElementsByTagName("home").item(0).getTextContent();
            }
        }
    }
}
