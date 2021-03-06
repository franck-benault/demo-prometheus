package hello;

import hello.util.Util;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codahale.metrics.annotation.Timed;

@RestController
public class HelloController {
	
	private org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(HelloController.class);

    private MyProperties myProperties = new MyProperties();

    @Timed
    @RequestMapping("/")
    public String index() {
    
    	int answer = Util.randomInt(100);
    	
    	if(answer>myProperties.getIndex_ok_case()) {
    		logger.error("main page http 404");
    		throw new ResourceNotFoundException();
    	}
    	
    	logger.info("main page OK");
        return "Greetings from Spring Boot!";
    }



}
