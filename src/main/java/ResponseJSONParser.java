import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Класс для извлечения из json текста ответа от сервера
 */
public class ResponseJSONParser implements ResponseParser {

    /***
     * Функция для извлечения из json ответа от сервера, т.е. перевода
     * @param response входной json текст
     * @return ответ сервера
     */
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
