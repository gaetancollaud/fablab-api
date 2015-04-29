package net.collaud.fablab.api.data.type;

import lombok.Getter;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public enum ConfigurationKey {
	ACCOUNTING_EDIT_HISTORY_LIMIT("7"),
	FABLAB_NAME("No-Name fablab"),
	FABLAB_URL("http://perdu.com"),
	GOOGLE_CALENDAR_API_KEY(null),
	RECAPTCHA_SITE_KEY(null);
	
	@Getter
	private final String def;

	private ConfigurationKey(String def) {
		this.def = def;
	}
}
