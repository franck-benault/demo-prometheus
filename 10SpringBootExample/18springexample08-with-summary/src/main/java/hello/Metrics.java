package hello;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.Summary;

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
			.quantile(0.5, 0.05)
			.quantile(0.9, 0.01)
			.name("response_time_summary")
			.labelNames("method")
			.help("request response time summary").register();
}
