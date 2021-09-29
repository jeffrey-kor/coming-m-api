package hyundaimeta.com.comingmapi.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="profile_id")
    private Long profileId;

    @Column(name="photo_path",columnDefinition = "varchar(50) null")
    private String photoPath;

    @Column(name="eng_nm",columnDefinition = "varchar(50) not null")
    private String engNm;

    @Column(name="kor_nm",columnDefinition = "varchar(50) not null")
    private String korNm;

    @Column(name="belong_to",columnDefinition = "varchar(50) null")
    private String belongTo;

    @Column(name="email",columnDefinition = "varchar(50) null")
    private String email;

    @Column(name="homepage",columnDefinition = "varchar(50) null")
    private String homepage;

    @Column(name="career",columnDefinition = "varchar(50) null")
    private String career;

    @Column(name="created_dt",columnDefinition = "timestamp NOT NULL DEFAULT now()")
    private LocalDateTime createdDt;

    @Column(name="updated_dt",columnDefinition = "timestamp NOT NULL DEFAULT now()")
    private LocalDateTime updatedDt;

    @Column(name="delete_yn",columnDefinition = "char NOT NULL DEFAULT 'N'")
    private char deleteYn;

}
