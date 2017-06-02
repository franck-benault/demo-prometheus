package hello;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.Histogram;
import io.prometheus.client.Summary;
import io.prometheus.client.guava.cache.CacheMetricsCollector;

public class Metrics {

	public static final Counter requestTotal = Counter.build()
			.name("http_requests_total")
			.labelNames("method","status")
			.help("Http Request Total").register();
	
	public static final Gauge nbTemporaryFile = Gauge.build()
			.name("nb_temporary_files")
			.labelNames("method")
			.help("Number of temporary files open").register();
	
	public static final Summary responseTime = Summary.build()
			.name("response_time_summary")
			.labelNames("method")
			.help("request response time summary").register();
	
	public static final Histogram fullResponseTime = Histogram.build()
			.name("response_time_histogram")
			.labelNames("method")
			.help("request response time histogram").register();
	
	public static final CacheMetricsCollector cacheMetrics = 
			new CacheMetricsCollector().register();
}
