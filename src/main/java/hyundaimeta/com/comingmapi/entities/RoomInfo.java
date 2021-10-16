package hyundaimeta.com.comingmapi.entities;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Data
@Entity
@Table( name="room_info",
	   	uniqueConstraints={
			   @UniqueConstraint(
					   columnNames={"room_number","room_url"}
					   )
		})
public class RoomInfo {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_info_id")
    private Long roomInfoId;

	@Column(name="title",columnDefinition = "varchar(50) NOT NULL", unique=true)
	String title;
	
	@Column(name="open_time",columnDefinition = "date NOT NULL")
	@Temporal(TemporalType.TIMESTAMP)
	private Date openTime;
	
	@Column(name="thumbnail", columnDefinition = "varchar(100) NULL")
	private String thumbnail;
	
	@Column(name="content", columnDefinition = "TEXT NULL")
	private String content;
	
	//방 호수
	@Column(name="room_number", columnDefinition = "integer NOT NULL")
	private Integer roomNumber;
	
	//방 url
	@Column(name="room_url", columnDefinition="varchar(20) NOT NULL")
	private String roomUrl;
	
	@ManyToOne
	@JoinColumn(name = "event_info_id")
	private EventInfo eventInfo;
	
}
