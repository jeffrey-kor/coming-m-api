package hyundaimeta.com.comingmapi.apis.auth.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description="LoginDto")
public class LoginDto {
	@ApiModelProperty(position=1,value="유저 아이디")
	private String id;

	@ApiModelProperty(position=2,value="유저 패스워드")
	private String password;
}
