package helpers.input;

import java.io.InputStream;

import entities.Hotel;
import helpers.PriceRange;
import entities.activities.*;
import helpers.BusinessHours;
import helpers.Location;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XMLReader {

    public static List<Hotel> readHotels(String filePath) {
        List<Hotel> hotels = new ArrayList<>();

        try {
            InputStream inputFile = XMLReader.class.getClassLoader().getResourceAsStream(filePath);
            if (inputFile == null) {
                System.err.println("File not found: " + filePath);
                return hotels;
            }

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("hotel");


            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    String name = eElement.getElementsByTagName("name").item(0).getTextContent();
                    double latitude = Double.parseDouble(eElement.getElementsByTagName("latitude").item(0).getTextContent());
                    double longitude = Double.parseDouble(eElement.getElementsByTagName("longitude").item(0).getTextContent());
                    Location location = new Location(latitude, longitude);

                    Hotel hotel = new Hotel(location, name);
                    hotels.add(hotel);
                }
            }

        } catch (Exception e) {
            System.out.println("Erro em readHotels");
        }
        return hotels;
    }


    public static List<I_Activity> readActivities(String filePath) {
        List<I_Activity> activities = new ArrayList<>();
        try {
            InputStream inputFile = XMLReader.class.getClassLoader().getResourceAsStream(filePath);
            if (inputFile == null) {
                System.err.println("File not found: " + filePath);
                return activities;
            }
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("activity");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String tipo = eElement.getElementsByTagName("tipo").item(0).getTextContent();
                    String name = eElement.getElementsByTagName("name").item(0).getTextContent();
                    double latitude = Double.parseDouble(eElement.getElementsByTagName("latitude").item(0).getTextContent());
                    double longitude = Double.parseDouble(eElement.getElementsByTagName("longitude").item(0).getTextContent());
                    Location location = new Location(latitude, longitude);
                    BusinessHours businessHours = new BusinessHours();

                    NodeList openTimeList = eElement.getElementsByTagName("openTime").item(0).getChildNodes();
                    for (int i = 0; i < openTimeList.getLength(); i++) {
                        Node dayNode = openTimeList.item(i);
                        if (dayNode.getNodeType() == Node.ELEMENT_NODE) {
                            String day = dayNode.getNodeName().substring(0, 1).toUpperCase() + dayNode.getNodeName().substring(1).toLowerCase();
                            String hours = dayNode.getTextContent().trim();
                            businessHours.setHours(day.toUpperCase(), hours);
                        }
                    }


                    if (tipo.equals("attraction")) {
                        String description = eElement.getElementsByTagName("description").item(0).getTextContent();
                        String imageThumbURL = eElement.getElementsByTagName("imageThumbURL").item(0).getTextContent();
                        Places activity = new Places(location, name, businessHours, description, imageThumbURL, Categories.ALL, 0);
                        activities.add(activity);
                    } else if (tipo.equals("museum")) {
                        String description = eElement.getElementsByTagName("description").item(0).getTextContent();
                        String imageThumbURL = eElement.getElementsByTagName("imageThumbURL").item(0).getTextContent();
                        String currentExpoName = eElement.getElementsByTagName("currentExpoName").item(0).getTextContent();
                        String mostFamousWork = eElement.getElementsByTagName("mostFamousWork").item(0).getTextContent();
                        String website = eElement.getElementsByTagName("website").item(0).getTextContent();
                        Museum activity = new Museum(location, name, businessHours, description, imageThumbURL, currentExpoName, mostFamousWork, website, 0);
                        activities.add(activity);
                    } else if (tipo.equals("culturalEvent")) {
                        // TO DO
                        /*String description = eElement.getElementsByTagName("description").item(0).getTextContent();
                        String imageThumbURL = eElement.getElementsByTagName("imageThumbURL").item(0).getTextContent();
                        CulturalEventType eventType = CulturalEventType.valueOf(eElement.getElementsByTagName("eventType").item(0).getTextContent());
                        String linkToBuy = eElement.getElementsByTagName("linkToBuy").item(0).getTextContent();
                        CulturalEvent activity = new CulturalEvent(location, name, businessHours, description, imageThumbURL, eventType, linkToBuy);
                        activities.add(activity);*/
                    } else if (tipo.equals("restaurant")) {
                        float max = Float.parseFloat(eElement.getElementsByTagName("max").item(0).getTextContent());
                        float min = Float.parseFloat(eElement.getElementsByTagName("min").item(0).getTextContent());
                        PriceRange priceRange = new PriceRange(min, max);
                        Restaurant activity = new Restaurant(location, name, businessHours, "", "", priceRange.min(), Restaurant.Avaliacao.CINCO_ESTRELAS);
                        activities.add(activity);
                    } else if (tipo.equals("sight")) {
                        String description = eElement.getElementsByTagName("description").item(0).getTextContent();
                        String imageThumbURL = eElement.getElementsByTagName("imageThumbURL").item(0).getTextContent();
                        NodeList imagesNodeList = eElement.getElementsByTagName("image").item(0).getChildNodes();
                        List<String> images = new ArrayList<>();
                        for (int i = 0; i < imagesNodeList.getLength(); i++) {
                            Node node = imagesNodeList.item(i);
                            if (node.getNodeType() == Node.ELEMENT_NODE) {
                                Element urlElement = (Element) node;
                                String url = urlElement.getTextContent();
                                images.add(url);
                            }
                        }
                        TouristicSights activity = new TouristicSights(location, name, businessHours, description, imageThumbURL, 0, images);
                        activities.add(activity);
                    }

                }
            }
        } catch (Exception e) {
            System.out.println("Erro em readActivities");
        }
        return activities;
    }


}
