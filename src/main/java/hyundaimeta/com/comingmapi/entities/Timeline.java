package hyundaimeta.com.comingmapi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	
    @ManyToOne(targetEntity=Member.class, fetch=FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    
    @ManyToOne(targetEntity=EventInfo.class, fetch=FetchType.LAZY)
    @JoinColumn(name = "event_info_id")
    private EventInfo eventInfo;
	
}
