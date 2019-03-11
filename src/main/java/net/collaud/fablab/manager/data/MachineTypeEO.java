package net.collaud.fablab.manager.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Entity
@Table(name = "t_machine_type")
@Getter
@Setter
@ToString(exclude = {"priceList", "machineList"})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MachineTypeEO extends AbstractDataEO<Long> implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "machine_type_id", nullable = false)
	private Long id;

	@Column(name = "technicalname", nullable = false)
	private String technicalname;

	@Column(name = "name", nullable = false)
	private String name;

	@JsonIgnore
	@Column(name = "restricted", nullable = false)
	private boolean restricted;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "machineType", fetch = FetchType.LAZY)
	private List<MachineEO> machineList;

	@JsonManagedReference("machineType-price")
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "machineTypeEO", fetch = FetchType.LAZY)
	private Set<PriceMachineEO> priceList;

	public MachineTypeEO() {
	}

	public MachineTypeEO(Long machineTypeId) {
		this.id = machineTypeId;
	}

	public MachineTypeEO(Long machineTypeId, String name) {
		this.id = machineTypeId;
		this.name = name;
	}

}
