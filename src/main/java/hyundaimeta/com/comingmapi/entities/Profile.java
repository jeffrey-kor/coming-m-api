package hyundaimeta.com.comingmapi.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="profile")
public class Profile {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="profile_id")
    private Long profileId;

    //영문명
    @Column(name="eng_nm",columnDefinition = "varchar(50) not null")
    private String engNm;

    //한글명
    @Column(name="kor_nm",columnDefinition = "varchar(50) not null")
    private String korNm;

    //소속
    @Column(name="belong_to",columnDefinition = "varchar(50) null")
    private String belongTo;

    //이메일
    @Column(name="email",columnDefinition = "varchar(50) null")
    private String email;

    //블로그,sns주소
    @Column(name="homepage",columnDefinition = "varchar(50) null")
    private String homepage;

    //직위(OFFICIAL POSITION)
    @Column(name="ofcps",columnDefinition = "varchar(50) null")
    private String ofcps;

    //xrcloud token
    @Column(name="xrcloud_token",columnDefinition = "varchar(255)")
    private String xrToken;
    
    @Column(name="created_dt",columnDefinition = "timestamp NOT NULL DEFAULT now()")
    private LocalDateTime createdDt;

    @Column(name="updated_dt",columnDefinition = "timestamp NOT NULL DEFAULT now()")
    private LocalDateTime updatedDt;

    @Column(name="delete_yn",columnDefinition = "char NOT NULL DEFAULT 'N'")
    private char deleteYn;
    

}
