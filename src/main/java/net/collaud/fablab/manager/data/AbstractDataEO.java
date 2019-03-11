package net.collaud.fablab.manager.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 * @param <ID>
 */
@MappedSuperclass
abstract public class AbstractDataEO<ID> {

	abstract public ID getId();

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj instanceof AbstractDataEO) {
			return Objects.equals(this, ((AbstractDataEO)obj).getId());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return getId().hashCode();
	}
}
