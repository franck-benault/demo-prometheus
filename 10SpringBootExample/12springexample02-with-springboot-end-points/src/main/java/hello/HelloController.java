package hello;

import java.util.Random;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {
	
	private org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(HelloController.class);


    @RequestMapping("/")
    public String index() {
    	logger.info("main page");
    	
    	Random rn = new Random();
    	int answer = rn.nextInt(100) + 1;
    	if(answer>97) {
    		logger.error("main page http 404");
    		throw new ResourceNotFoundException();
    	}
    	
        return "Greetings from Spring Boot!";
    }

}
