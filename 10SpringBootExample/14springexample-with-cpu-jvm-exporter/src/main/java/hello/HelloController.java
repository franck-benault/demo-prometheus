package hello;

import hello.util.Util;

import java.util.Random;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {
	
	private org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(HelloController.class);


    @RequestMapping("/")
    public String index() {
    	
    	Random rn = new Random();
    	int answer = rn.nextInt(100) + 1;
    	if(answer>97) {
    		logger.error("main page http 404");
    		throw new ResourceNotFoundException();
    	}
    	
    	Util.consumeMemory();
    	
    	logger.info("main page OK");
        return "Greetings from Spring Boot!";
    }

}
