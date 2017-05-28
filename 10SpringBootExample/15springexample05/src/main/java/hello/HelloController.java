package hello;

import java.util.ArrayList;
import java.util.List;
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
    	
    	//memory usage...
    	List<String> list  = new ArrayList<String>();
    	for(int i=0; i<200000; i++)
    		list.add("eeeeeeeeeeeeeeeeeee"+i);
    	
    	logger.info("main page OK");
        return "Greetings from Spring Boot!";
    }

}
