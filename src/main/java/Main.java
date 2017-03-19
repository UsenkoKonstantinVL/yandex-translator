import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Created by toshiba on 16.03.2017.
 */
public class Main {
    static public void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        Translator translator = new Translator();

        System.out.println("Данная программа переводит с английского языка на русский, для перевода строки нужно нажать на enter.");
        System.out.println(String.format("%10s", "").replace(' ', '='));

        translator.setResponseType(Translator.ResponseType.XML);


        while(true){
            System.out.println("Введите текст:");
            String res = scanner.nextLine();

            if(res.equals("exit")){
                return;
            }
            System.out.println("Перевод:");
            translator.translate(res);
            System.out.println(String.format("%10s", "").replace(' ', '='));
        }

    }

    private static void get(){
        String key = "trnsl.1.1.20170315T212456Z.4a15b1b7d9902867.271a33f600908be40a04905acfa8d35ed564f787";
        String myURL = "https://translate.yandex.net/api/v1.5/tr.json/translate?";
        String params = String.format("key=%s&text=%s&lang=%s", key, "Who is here?!", "en-ru");
        byte[] data = null;
        InputStream is = null;

        try {
            URL url = new URL(myURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            conn.setRequestProperty("Content-Length", "" + Integer.toString(params.getBytes().length));
            OutputStream os = conn.getOutputStream();
            data = params.getBytes("UTF-8");
            os.write(data);
            data = null;

            conn.connect();
            int responseCode= conn.getResponseCode();
            switch (responseCode){
                //Операция выполнена успешно
                case 201:
                {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    is = conn.getInputStream();

                    byte[] buffer = new byte[8192]; // Такого вот размера буфер
                    // Далее, например, вот так читаем ответ
                    int bytesRead;
                    while ((bytesRead = is.read(buffer)) != -1) {
                        baos.write(buffer, 0, bytesRead);
                    }
                    data = baos.toByteArray();
                    String res = new String(data);
                    System.out.println(res);
                    parseJSON(res);
                    break;
                }
                //Неправильный API-ключ
                case 401:
                    System.out.println("Ошибка: Неправильный API-ключ");
                    break;
                //API-ключ заблокирован
                case 402:
                    System.out.println("Ошибка: API-ключ заблокирован");
                    break;
                //Превышено суточное ограничение на объем переведенного текста
                case 404:
                    System.out.println("Ошибка: Превышено суточное ограничение на объем переведенного текста");
                    break;
                //Превышен максимально допустимый размер текста
                case 413:
                    System.out.println("Ошибка: Превышен максимально допустимый размер текста");
                    break;
                //Текст не может быть переведен
                case 422:
                    System.out.println("Ошибка: Текст не может быть переведен");
                    break;
                //Заданное направление перевода не поддерживается
                case 501:
                    System.out.println("Ошибка: Заданное направление перевода не поддерживается");
                    break;
            }


        } catch (Exception e) {
        } finally {
            try {
                if (is != null)
                    is.close();
            } catch (Exception ex) {}
        }
    }

    private static void parseJSON(String data){
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject;
        try {
            jsonObject = (JSONObject) jsonParser.parse(data);
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
