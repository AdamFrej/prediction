package pl.waw.frej.prediction;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController {

    @RequestMapping(value = "/adres", method = RequestMethod.GET)
    public String test(){
        return "test";
    }
}
