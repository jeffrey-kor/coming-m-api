package hyundaimeta.com.comingmapi.apis.healthCheck;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import hyundaimeta.com.comingmapi.apis.healthCheck.controller.HealthCheckController;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@WebMvcTest(HealthCheckController.class)
@AutoConfigureRestDocs
public class HealthCheckControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    @DisplayName("컨택하기")
    void healthCheck() throws Exception{
  
   
    }
}
