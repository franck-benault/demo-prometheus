package hello;

import hello.thread.MyThread;
import hello.util.Util;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Summary;

import java.io.StringWriter;
import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	
	private static final int HTTP_OK_RATE = 97;

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
    	Util.consumeMemory();
    	
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
		try {
			TimeUnit.MILLISECONDS.sleep(Util.randomInt(10));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        
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
