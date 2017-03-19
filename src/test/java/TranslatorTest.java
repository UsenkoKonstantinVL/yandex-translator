import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by toshiba on 18.03.2017.
 */
public class TranslatorTest {
    @Test
    public void translate() throws Exception {
        Translator translator = new Translator();
        String s1 = translator.translate("Hello world");

        translator.setResponseType(Translator.ResponseType.XML);

        String s2 = translator.translate("Hello world");

        assertTrue(s1.equals(s2));
    }

}