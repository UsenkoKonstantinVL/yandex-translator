import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Created by toshiba on 16.03.2017.
 */
public class ResponseJSONParser implements ResponseParser {

    @Override
    public void parseResponse(String response) {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject;
        try {
            jsonObject = (JSONObject) jsonParser.parse(response);
            JSONArray text= (JSONArray) jsonObject.get("text");
            for(int i = 0; i < text.size(); i++){
                System.out.println(text.get(i));
            }
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
