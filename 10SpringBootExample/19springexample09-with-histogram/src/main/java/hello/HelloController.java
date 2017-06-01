package hello;

import hello.thread.MyThread;
import hello.util.Util;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Histogram;
import io.prometheus.client.Summary;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	
	private static final int HTTP_OK_RATE = 97;
	private static final int END_POINT_C_OK_RATE = 90;

	private org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(HelloController.class);
	
    @RequestMapping("/")
    public String index() {

    	int answer = Util.randomInt(100);
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

    	MyThread t = new MyThread();
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
   		if(answer>END_POINT_C_OK_RATE) {
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
