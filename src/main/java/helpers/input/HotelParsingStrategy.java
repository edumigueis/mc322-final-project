package helpers.input;

import entities.Hotel;
import helpers.Location;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class HotelParsingStrategy implements ParsingStrategy {
    @Override
    public List<Hotel> parse(String filePath) {
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
}
