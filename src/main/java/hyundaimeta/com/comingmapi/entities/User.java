package hyundaimeta.com.comingmapi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import java.time.LocalDateTime;


@Entity
@Table( name="users",
        indexes = {
                    @Index(name = "user_index_1",  columnList="name"),
                    @Index(name = "user_index_2",  columnList="id"),
                    @Index(name = "user_index_3",  columnList="indvdlinfo_agre_yn")
                  })
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "id",columnDefinition = "varchar(50) not null")
    private String id;

    @Column(name = "password",columnDefinition = "varchar(50) not null")
    private String password;

    @Column(name = "name",columnDefinition = "varchar(50) not null")
    private String name;

    @Column(name = "indvdlinfo_agre_yn",columnDefinition = "char not null")
    private char indvdlinfoAgreYn;

    @Column(name = "indvdlinfo_agre_dt",columnDefinition = "timestamp NOT NULL DEFAULT now()")
    private LocalDateTime indvdlinfoAgreDt;

    @Column(name = "created_dt",columnDefinition = "timestamp NOT NULL DEFAULT now()")
    private LocalDateTime createdDt;

    @Column(name = "updated_dt",columnDefinition = "timestamp NOT NULL DEFAULT now()")
    private LocalDateTime updatedDt;

    @Column(name = "secsn_yn",columnDefinition = "char NOT NULL DEFAULT 'N'")
    private char secsnYn;

    @OneToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

}
