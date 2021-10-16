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
@Table(name="ticket_room")
public class TicketRoom {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_room_id")
    private Long ticketRoomId;
	
	@Column(name = "active_yn",columnDefinition = "char NOT NULL DEFAULT 'Y'")
	private String activeYn;
	
	@Column(name = "created_dt",columnDefinition = "timestamp NOT NULL DEFAULT now()")
    @CreationTimestamp
    private LocalDateTime createdDt;

    @Column(name = "updated_dt",columnDefinition = "timestamp NOT NULL DEFAULT now()")
    @UpdateTimestamp
    private LocalDateTime updatedDt;
	
	@ManyToOne(targetEntity=Ticket.class, fetch=FetchType.LAZY)
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;
	
	@ManyToOne(targetEntity=RoomInfo.class, fetch=FetchType.LAZY)
    @JoinColumn(name = "room_info_id")
    private RoomInfo roomInfo;	
	
	
}
