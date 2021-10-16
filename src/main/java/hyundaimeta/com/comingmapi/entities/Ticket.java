package hyundaimeta.com.comingmapi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="ticket")
public class Ticket {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Long ticketId;
	
	@Column(name="ticket_number",columnDefinition = "varchar(255) NOT NULL")
	private String ticketNumber;
	
	@Column(name="ticket_info",columnDefinition = "varchar(255) NOT NULL")
	private String ticketInfo;
	
	//회원등급(vip or non-vip)
	@Column(name="grade",columnDefinition = "varchar(10) NOT NULL")
	private String grade;

	//입장가능 방호수
	@Column(name="target_rooms", columnDefinition = "varchar(50) NOT NULL")
	private String targetRooms;
	
	@ManyToOne(targetEntity=Member.class, fetch=FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

	@OneToOne(targetEntity=EventInfo.class, fetch=FetchType.LAZY)
	@JoinColumn(name = "event_info_id")
	private EventInfo eventInfo;
}
