package hyundaimeta.com.comingmapi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@Data
@Entity
@Table( name="members",
        indexes = {
                    @Index(name = "user_index_1",  columnList="name"),
                    @Index(name = "user_index_2",  columnList="login_id"),
                    @Index(name = "user_index_3",  columnList="indvdlinfo_agre_yn")
                  }
)
public class Member implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    //계정(이메일)
    @Column(name = "login_id",columnDefinition = "varchar(50) NOT NULL", unique=true)
    private String loginId;

    //비밀번호
    @Column(name = "password",columnDefinition = "varchar(255) NOT NULL")
    private String password;

    //이름
    @Column(name = "name",columnDefinition = "varchar(50) NOT NULL")
    private String name;

    //개인정보 동의 여부(INDIVIDUAL INFORMATION AGREEMENT YN)
    @Column(name = "indvdlinfo_agre_yn",columnDefinition = "char NOT NULL")
    private String indvdlinfoAgreYn;

    //개인정보 동의 날짜(INDIVIDUAL INFORMATION AGREEMENT DATE)
    @Column(name = "indvdlinfo_agre_dt",columnDefinition = "timestamp NOT NULL DEFAULT now()")
    @CreationTimestamp
    private LocalDateTime indvdlinfoAgreDt;

    @Column(name = "created_dt",columnDefinition = "timestamp NOT NULL DEFAULT now()")
    @CreationTimestamp
    private LocalDateTime createdDt;

    @Column(name = "updated_dt",columnDefinition = "timestamp NOT NULL DEFAULT now()")
    @UpdateTimestamp
    private LocalDateTime updatedDt;

    //탈퇴 여부(SECESSION YN)
    @Column(name = "secsn_yn",columnDefinition = "char NOT NULL DEFAULT 'N'")
    private String secsnYn;
    
    //권한
    @Column(name = "role", columnDefinition = "varchar(50) NOT NULL")
    private String role;
    
    //공개여부(OPENING TO THE PUBLIC YN)  레벨0:비공개, 레벨1:팔로우, 레벨2:맞팔, 레벨3:전체
    @Column(name = "othbcLv", columnDefinition = "integer NOT NULL DEFAULT '0'")
    private String othbcLv;
    
    @OneToOne(targetEntity=Profile.class, fetch=FetchType.EAGER)
    @JoinColumn(name = "profile_id")
    private Profile profile;
    
    @OneToMany(targetEntity=Followers.class, mappedBy = "to", fetch=FetchType.EAGER)
    private List<Followers> followers;

    @OneToMany(targetEntity=Followers.class, mappedBy = "from", fetch=FetchType.EAGER)
    private List<Followers> following;
    

     
}
