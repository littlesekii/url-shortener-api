package cat.linky.urlshortener_api.core.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cat.linky.urlshortener_api.core.util.Utils;


@RestController
@RequestMapping("/short")
public class MainController {
    
    @GetMapping
    public String get() {
        StringBuilder res = new StringBuilder();

        int ranges[][] = {
            {48, 57}, // '0 to '9' char
            {65, 90}, // 'A' to 'Z' char
            {97, 122} // 'a' to 'z' char
        };

        for(int i = 0; i < 5; i++)
        {
            int randomRangeIndex = Utils.randomInt(0, 2);
            int range[] = ranges[randomRangeIndex];

            int randomCharInt = Utils.randomInt(range[0], range[1]);

            res.append((char)randomCharInt);
        }
        
        return "sh.linky.cat/" + res.toString();
    }
    
}
