package hello;

import io.prometheus.client.Counter;

public class Metrics {

	public static final Counter requestTotal = Counter.build()
			.name("http_requests_total")
			.labelNames("method","status")
			.help("Http Request Total").register();
}
