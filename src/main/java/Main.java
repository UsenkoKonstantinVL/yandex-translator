import java.util.Scanner;


/**
 * Главная часть программы
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
}
