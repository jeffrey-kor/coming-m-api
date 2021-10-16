package hyundaimeta.com.comingmapi.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name="timeline")
public class Timeline {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "timeline_id")
    private Long timelineId;
	
    @Column(name = "content",columnDefinition = "TEXT NOT NULL")
    private String content;
	
    @Column(name = "created_dt",columnDefinition = "timestamp NOT NULL DEFAULT now()")
    @CreationTimestamp
    private LocalDateTime createdDt;

    @Column(name = "updated_dt",columnDefinition = "timestamp NOT NULL DEFAULT now()")
    @UpdateTimestamp
    private LocalDateTime updatedDt;
    
    @ManyToOne(targetEntity=Member.class, fetch=FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member member;
      
    @ManyToOne(targetEntity=EventInfo.class, fetch=FetchType.LAZY)
    @JoinColumn(name = "event_info_id")
    private EventInfo eventInfo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Timeline parent;

    @OneToMany(mappedBy = "parent", orphanRemoval = true)
    private List<Timeline> children = new ArrayList<>();

}
