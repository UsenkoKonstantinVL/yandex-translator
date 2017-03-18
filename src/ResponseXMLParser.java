/**
 * Created by toshiba on 16.03.2017.
 */
import java.io.IOException;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import static java.awt.SystemColor.text;

public class ResponseXMLParser implements ResponseParser {
    @Override
    public void parseResponse(String response) {
        try {
            InputSource is = new InputSource(new StringReader(response));
            DocumentBuilderFactory  documentBuilder = DocumentBuilderFactory .newInstance();
            DocumentBuilder  builder = documentBuilder.newDocumentBuilder();
            Document document = builder.parse(is);
            Node root = document.getDocumentElement();
            NodeList text = root.getChildNodes();
            for(int i = 0; i < text.getLength(); i++) {
                System.out.println(text.item(i).getTextContent());
            }
           //
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
