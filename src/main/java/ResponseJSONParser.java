import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Created by toshiba on 16.03.2017.
 */
public class ResponseJSONParser implements ResponseParser {

    @Override
    public String parseResponse(String response) {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject;
        try {
            String res = "";
            jsonObject = (JSONObject) jsonParser.parse(response);
            JSONArray text= (JSONArray) jsonObject.get("text");
            for(int i = 0; i < text.size(); i++){
                String subRes = (String) text.get(i);
                System.out.println(subRes);
                res += subRes;

            }

            return res;
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
