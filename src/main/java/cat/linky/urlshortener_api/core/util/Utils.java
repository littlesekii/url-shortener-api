package cat.linky.urlshortener_api.core.util;

public class Utils {
    
    public static int randomInt(int startRange, int endRange)
    {
        int number = (int) (Math.random() * (1 + endRange - startRange));
        number += startRange;

        return number;
    }
}
