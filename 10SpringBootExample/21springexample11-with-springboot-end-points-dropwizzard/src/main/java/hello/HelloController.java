package hello;

import java.util.Random;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codahale.metrics.annotation.Timed;

@RestController
public class HelloController {
	
	private org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(HelloController.class);


    @Timed
    @RequestMapping("/")
    public String index() {
    	
    	Random rn = new Random();
    	int answer = rn.nextInt(100) + 1;
    	if(answer>97) {
    		logger.error("main page http 404");
    		throw new ResourceNotFoundException();
    	}
    	
    	logger.info("main page OK");
        return "Greetings from Spring Boot!";
    }

}
