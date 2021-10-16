package hyundaimeta.com.comingmapi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="event_info")
public class EventInfo {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_info_id")
    private Long eventInfoId;

	@Column(name = "title",columnDefinition = "varchar(50) NOT NULL")
	private String title;
	
	@Column(name = "content",columnDefinition = "TEXT NOT NULL")
	private String content;
	
	
	
}
