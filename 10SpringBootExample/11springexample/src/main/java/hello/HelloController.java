package hello;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {
	
	private org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(HelloController.class);

    @RequestMapping("/")
    public String index() {
    	logger.info("main page");
    	
        return "Greetings from Spring Boot!";
    }

}
