package net.collaud.fablab.manager.data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Entity
@Table(name = "t_reservation")
@Getter
@Setter
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ReservationEO extends AbstractDataEO<Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String SELECT_BY_TIME = " SELECT r "
			+ " FROM ReservationEO r "
			+ " JOIN FETCH r.user "
			+ " JOIN FETCH r.machine "
			+ " WHERE r.dateStart >= :" + ReservationEO.PARAM_DATE_START + " "
			+ " AND r.dateEnd <= :" + ReservationEO.PARAM_DATE_END;
	public static final String PARAM_DATE_START = "dateStart";
	public static final String PARAM_DATE_END = "dateEnd";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reservation_id", nullable = false)
	private Long id;

	@Column(name = "date_start", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateStart;

	@Column(name = "date_end", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateEnd;

	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private UserEO user;

	@JoinColumn(name = "machine_id", referencedColumnName = "machine_id")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private MachineEO machine;

	public ReservationEO() {
	}

	
}
