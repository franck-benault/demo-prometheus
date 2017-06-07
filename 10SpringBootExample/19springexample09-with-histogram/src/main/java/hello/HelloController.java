package hello;

import hello.thread.MyThread;
import hello.util.Util;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Histogram;
import io.prometheus.client.Summary;

import java.io.StringWriter;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	


	private org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(HelloController.class);
	
    private MyProperties myProperties = new MyProperties();
	
    @RequestMapping("/")
    public String index() {

    	int answer = Util.randomInt(100);
    	if(answer>myProperties.getIndex_ok_case()) {
        	Metrics.requestTotal.labels("index","ERROR").inc();
    		logger.error("main page http 404");
    		throw new ResourceNotFoundException();
    	}
    	
    	//memory usage...
    	Util.consumeMemory();
    	
    	logger.info("main page OK");
       	Metrics.requestTotal.labels("index","OK").inc();
        return "Greetings from Spring Boot!";
    }
    
    @RequestMapping("/endpointA")
    public String handlerA() throws InterruptedException {

    	MyThread t = new MyThread(myProperties);
    	t.start();
    	
      	Metrics.requestTotal.labels("endpointA","OK").inc();
    	return "Greetings from Spring Boot! page A";
    }

    @RequestMapping("/endpointB")
    public String handlerB() throws InterruptedException {
    	
        Summary.Timer timer = Metrics.responseTime.labels("endpointB").startTimer();
        //some works
		Util.sleepInMilliSeconde(10);
        
        timer.observeDuration();
    	Metrics.requestTotal.labels("endpointB","OK").inc();
       	return "Greetings from Spring Boot! page B";
    }
    
    @RequestMapping("/endpointC")
    public String handlerC() throws InterruptedException {
    	
       Histogram.Timer timer = Metrics.fullResponseTime.labels("endpointC").startTimer();

        //some works
       	int answer = Util.randomInt(100);
   		if(answer>myProperties.getEnd_point_c_ok_rate()) {
   			Util.sleepInMilliSeconde(Util.randomInt(500));
   		}
		
        timer.observeDuration();
    	Metrics.requestTotal.labels("endpointC","OK").inc();
       	return "Greetings from Spring Boot! page C";
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
