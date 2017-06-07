package hello.cache;

import hello.Metrics;
import hello.MyProperties;
import hello.util.Util;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@Scope(value = "singleton")
@Component
public class MyCache {
	
	private LoadingCache<Integer, String> values;
	private MyProperties myProperties = new MyProperties();
	
	public MyCache() {
	
		
		values = CacheBuilder.newBuilder()
				.recordStats()
		       .maximumSize(myProperties.getCache_size())
		       .expireAfterWrite(30, TimeUnit.MINUTES)
		       .build(
		           new CacheLoader<Integer, String>() {
		             public String load(Integer key)  {
		               Util.sleepInMilliSeconde(10);
		               return "A"+key;
		             }
		           });
		
		Metrics.cacheMetrics.addCache("myCache", values);
	
	}
	
	public String getValue() {
		String res = null;
		try {
			res = values.get(Util.randomInt(myProperties.getCache_value_size()));
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return res;
	}

}
