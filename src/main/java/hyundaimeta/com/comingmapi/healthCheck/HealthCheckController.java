package hyundaimeta.com.comingmapi.healthCheck;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.http.HttpStatus; 


@RestController
@RequestMapping("health")
@Api(tags = {"AWS 로드밸런서 Health 체크 관련"})
public class HealthCheckController {
    
    @GetMapping(value="")
    @ApiOperation(value = "Health Check")
    public ResponseEntity<String> healthCheck(){
        return new ResponseEntity<String>("OK",HttpStatus.OK);
    }
}
