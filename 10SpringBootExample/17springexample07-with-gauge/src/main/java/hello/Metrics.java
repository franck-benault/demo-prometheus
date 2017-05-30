package hello;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;

public class Metrics {

	public static final Counter requestTotal = Counter.build()
			.name("http_requests_total")
			.labelNames("method","status")
			.help("Http Request Total").register();
	
	public static final Gauge nbTemporaryFile = Gauge.build()
			.name("nb_temporary_files")
			.labelNames("method")
			.help("Number of temporary files open").register();
}
