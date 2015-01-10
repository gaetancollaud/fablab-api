package net.collaud.fablab.api.data;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Entity
@Table(name = "t_reservation")
public class ReservationEO extends AbstractDataEO implements Serializable {

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
	private Integer reservationId;

	@Column(name = "date_start", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateStart;

	@Column(name = "date_end", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateEnd;

	@JsonManagedReference
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private UserEO user;

	@JsonManagedReference
	@JoinColumn(name = "machine_id", referencedColumnName = "machine_id")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private MachineEO machine;

	public ReservationEO() {
	}

	public Integer getReservationId() {
		return reservationId;
	}

	public void setReservationId(Integer reservationId) {
		this.reservationId = reservationId;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public UserEO getUser() {
		return user;
	}

	public void setUser(UserEO user) {
		this.user = user;
	}

	public MachineEO getMachine() {
		return machine;
	}

	public void setMachine(MachineEO machine) {
		this.machine = machine;
	}

	@Override
	public String toString() {
		return "net.collaud.fablab.data.ReservationEO[ reservationId=" + reservationId + " ]";
	}

}
