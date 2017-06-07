package hello;




public class MyProperties {

	private int index_ok_case;
	private int end_point_c_ok_rate;
	private int cache_size;
	private int cache_value_size;
	private int temp_file_ok_rate;


	public MyProperties() {
		
		//default values
		index_ok_case =97;
		end_point_c_ok_rate=90;
		cache_size = 100;
		cache_value_size =110;
		temp_file_ok_rate=97;


		if(Application.myargs != null) {
			System.out.println("Application.myargs="+Application.myargs[0]);
			String[] keyValues =Application.myargs[0].split(";");
			
			for(String keyValue : keyValues) {
				System.out.println("keyValue="+keyValue);
				String res[] = keyValue.split("=");
				if(res[0].equals("INDEX_OK_RATE")) {
					index_ok_case= Integer.valueOf(res[1]);
				}
				if(res[0].equals("END_POINT_C_OK_RATE")) {
					end_point_c_ok_rate= Integer.valueOf(res[1]);
				}
				
				if(res[0].equals("CACHE_SIZE")) {
					cache_size= Integer.valueOf(res[1]);
				}
				
				if(res[0].equals("CACHE_VALUE_SIZE")) {
					cache_value_size= Integer.valueOf(res[1]);
				}
				
				if(res[0].equals("TEMP_FILE_OK_RATE")) {
					temp_file_ok_rate= Integer.valueOf(res[1]);
				}
			}
		}

		System.out.println("index_ok_case="+index_ok_case);
		System.out.println("end_point_c_ok_rate="+end_point_c_ok_rate);
		
		System.out.println("cache_size="+cache_size);
		System.out.println("cache_value_size="+cache_value_size);
		System.out.println("temp_file_ok_rate="+temp_file_ok_rate);
		
	}


	public int getIndex_ok_case() {
		return index_ok_case;
	}
	
	public int getEnd_point_c_ok_rate() {
		return end_point_c_ok_rate;
	}
	
	public int getCache_size() {
		return cache_size;
	}
	
	public int getCache_value_size() {
		return cache_value_size;
	}
	
	public int getTemp_file_ok_rate() {
		return temp_file_ok_rate;
	}

}