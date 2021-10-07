package hyundaimeta.com.comingmapi.configs;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Configuration
public class ResponseConfig {

	@Bean
	public ResponseEntity<Object> OK() {
		
		Map<String,Object> map = new HashMap<>();
		map.put("status","200");
		map.put("message","OK");
		
		HttpHeaders headers = new HttpHeaders();
		Charset utf8 = Charset.forName("UTF-8");
		MediaType mediaType = new MediaType("application","json",utf8);
		headers.setContentType(mediaType);
			
		return new ResponseEntity<Object>(map, headers, HttpStatus.OK);
	}

	@Bean
	public ResponseEntity<Object> ERROR() {
		
		Map<String,Object> map = new HashMap<>();
		map.put("status","500");
		map.put("message","ERROR");
		
		HttpHeaders headers = new HttpHeaders();
		Charset utf8 = Charset.forName("UTF-8");
		MediaType mediaType = new MediaType("application","json",utf8);
		headers.setContentType(mediaType);
			
		return new ResponseEntity<Object>(map, headers, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
