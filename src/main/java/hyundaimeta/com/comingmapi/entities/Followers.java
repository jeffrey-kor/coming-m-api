package hyundaimeta.com.comingmapi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Followers {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "followers_id")
    private Long followersId;
	
	@ManyToOne
	@JoinColumn(name="from_member_id")
	private Member from;

	@ManyToOne
	@JoinColumn(name="to_member_id")
	private Member to;

	
}
