package hyundaimeta.com.comingmapi.apis.auth.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description="UserCreateDto")
public class MemberCreateDto {
	
	 @ApiModelProperty(position=1,value="유저 계정(unique)")
	 private String account;

	 @ApiModelProperty(position=2,value="유저 패스워드")
	 private String password;
	 
	 @ApiModelProperty(position=3,value="유저 이름")
	 private String name;
	 
	 @ApiModelProperty(position=4,value="개인정보 동의여부",allowableValues="Y")
	 @Pattern(regexp="Y")
	 private String indvdlinfoAgreYn;

}
