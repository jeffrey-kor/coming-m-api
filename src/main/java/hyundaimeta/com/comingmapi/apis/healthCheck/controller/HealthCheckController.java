package hyundaimeta.com.comingmapi.apis.healthCheck.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.http.HttpStatus; 


@RestController
@RequestMapping("health")
@Api(tags = {"AWS 로드밸런스 헬스 체크"})
public class HealthCheckController {
    
    @GetMapping(value="")
    @ApiOperation(value = "Health Check", notes="AWS 로드밸런스에서 ec2 서버 헬스체크를 한다.")
    public ResponseEntity<String> healthCheck(){
        return new ResponseEntity<String>("OK",HttpStatus.OK);
    }
}
