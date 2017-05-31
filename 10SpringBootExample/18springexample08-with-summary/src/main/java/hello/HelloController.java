package hello;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Summary;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	
	private static final int HTTP_OK_RATE = 97;

	private org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(HelloController.class);

	
	private int randomInt(int maxValue) {
    	Random rn = new Random();
    	return rn.nextInt(maxValue) + 1;
	}
	
    @RequestMapping("/")
    public String index() {

    	int answer = randomInt(100);
    	if(answer>HTTP_OK_RATE) {
        	Metrics.requestTotal.labels("index","ERROR").inc();
    		logger.error("main page http 404");
    		throw new ResourceNotFoundException();
    	}
    	
    	//memory usage...
    	List<String> list  = new ArrayList<String>();
    	for(int i=0; i<200000; i++)
    		list.add("eeeeeeeeeeeeeeeeeee"+i);
    	
    	logger.info("main page OK");
       	Metrics.requestTotal.labels("index","OK").inc();
        return "Greetings from Spring Boot!";
    }
    
    @RequestMapping("/endpointA")
    public String handlerA() throws InterruptedException {
  
    	int answer = randomInt(100);
    	if(answer>50)
    		Metrics.nbTemporaryFile.labels("handlerA").inc(randomInt(5));
    	else {
    		int prevValue = (int)Metrics.nbTemporaryFile.labels("handlerA").get();
    		if(prevValue>0) {
    			if(prevValue<5) 
    				Metrics.nbTemporaryFile.labels("handlerA").dec(randomInt(prevValue));
        		else
        			Metrics.nbTemporaryFile.labels("handlerA").dec(randomInt(5));
        		
    		}
    	}

    	
      	Metrics.requestTotal.labels("endpointA","OK").inc();
    	return "Greetings from Spring Boot! page A";
    }

    @RequestMapping("/endpointB")
    public String handlerB() throws InterruptedException {
        Summary.Timer timer = Metrics.responseTime.startTimer();
        // do some work ...
        int answer = randomInt(100);
        
        timer.observeDuration();
    	Metrics.requestTotal.labels("endpointB","OK").inc();
       	return "Greetings from Spring Boot! page B";
    }
    
    @RequestMapping(value="/metrics" ,produces={"text/plain"})
    public String metrics() {
    	StringWriter writer = new StringWriter();
        try {
            io.prometheus.client.exporter.common.TextFormat.write004(
                    writer, CollectorRegistry.defaultRegistry.metricFamilySamples());
        } catch (Exception e) {
            return e.getMessage();
        }
        return writer.toString();
    }

}
