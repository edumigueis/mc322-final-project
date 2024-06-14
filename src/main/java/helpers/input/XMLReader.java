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
import java.util.ArrayList;
import java.util.List;

public class XMLReader {
    private ParsingStrategy parsingStrategy;

    public void setParsingStrategy(ParsingStrategy parsingStrategy) {
        this.parsingStrategy = parsingStrategy;
    }

    public List<?> read(String filePath) {
        return parsingStrategy.parse(filePath);
    }
}
