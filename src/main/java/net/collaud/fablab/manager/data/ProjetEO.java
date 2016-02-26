package net.collaud.fablab.manager.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
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
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Entity
@Table(name = "t_projet")
@Data
@NoArgsConstructor
public class ProjetEO extends AbstractDataEO<Integer> implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "project_id", nullable = false)
	private Integer id;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "state", nullable = false)
	private String state;

	@Column(name = "date_start", nullable = false)
	private Date dateStart;
	
	@Column(name = "date_end")
	private Date dateEnd;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "machine", fetch = FetchType.LAZY)
	private Set<UserEO> reservationList;

	@JoinColumn(name = "machine_type_id", referencedColumnName = "machine_type_id")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private MachineTypeEO machineType;

	public ProjetEO(Integer id) {
		this.id = id;
	}

}
