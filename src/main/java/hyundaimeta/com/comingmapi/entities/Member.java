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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table( name="members",
        indexes = {
                    @Index(name = "user_index_1",  columnList="name"),
                    @Index(name = "user_index_2",  columnList="account"),
                    @Index(name = "user_index_3",  columnList="indvdlinfo_agre_yn")
                  }
)
public class Member implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    //계정(이메일)
    @Column(name = "account",columnDefinition = "varchar(50) NOT NULL", unique=true)
    private String account;

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
    
    //공개여부(OPENING TO THE PUBLIC YN)
    @Column(name = "othbcYn", columnDefinition = "char NOT NULL")
    private String othbcYn;
    
    @OneToOne(targetEntity=Profile.class, fetch=FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;
    
    @OneToMany(mappedBy = "to")
    private List<Followers> followers;

    @OneToMany(mappedBy = "from")
    private List<Followers> following;
    

     
}
