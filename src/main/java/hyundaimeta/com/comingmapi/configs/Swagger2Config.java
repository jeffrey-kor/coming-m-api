package hyundaimeta.com.comingmapi.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.service.ApiInfo;

@Configuration
public class Swagger2Config {

    @Bean
    public Docket restApi() {
        return new Docket(DocumentationType.OAS_30) // open api spec 3.0
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("hyundaimeta.com.comingmapi"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("COMING M REST API")
                .version("1.0.0")
                .description("COMING M REST API.")
                .build();
    }
}