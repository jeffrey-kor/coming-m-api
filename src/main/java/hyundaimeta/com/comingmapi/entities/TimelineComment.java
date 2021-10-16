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
@Table(name="timeline_comment")
public class TimelineComment {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "timeline_comment_id")
    private Long timelineCommentId;
	
    @Column(name = "comment")
    private String comment;
    
    @ManyToOne(targetEntity=Timeline.class, fetch=FetchType.LAZY)
    @JoinColumn(name = "timeline_id")
    private Timeline timeline;
}
