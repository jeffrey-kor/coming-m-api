package hyundaimeta.com.comingmapi.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name="followers")
public class Followers {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "followers_id")
    private Long followersId;
	
	@Column(name = "active_yn",columnDefinition = "char NOT NULL DEFAULT 'Y'")
	private String activeYn;
	
	@Column(name = "both_yn",columnDefinition = "char NOT NULL DEFAULT 'N'")
	private String bothYn;
	
	@Column(name = "created_dt",columnDefinition = "timestamp NOT NULL DEFAULT now()")
    @CreationTimestamp
    private LocalDateTime createdDt;

    @Column(name = "updated_dt",columnDefinition = "timestamp NOT NULL DEFAULT now()")
    @UpdateTimestamp
    private LocalDateTime updatedDt;
	
	@ManyToOne(targetEntity=Member.class, fetch=FetchType.EAGER)
	@JoinColumn(name="from_member_id")
	private Member from;

	@ManyToOne(targetEntity=Member.class, fetch=FetchType.EAGER)
	@JoinColumn(name="to_member_id")
	private Member to;

	
}
