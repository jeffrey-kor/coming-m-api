package hyundaimeta.com.comingmapi.configs;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {
	private final ModelMapper modelMapper = new ModelMapper();
	
	 @Bean
	    public ModelMapper strictMapper(){
		 	modelMapper.getConfiguration()
		 		.setMatchingStrategy(MatchingStrategies.STRICT);
	        return modelMapper;
	    }
}
