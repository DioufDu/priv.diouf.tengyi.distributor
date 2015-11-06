package priv.diouf.tengyi.distributor.common.models.enums.authority;

import org.springframework.security.core.GrantedAuthority;

public enum ProductAuthority implements GrantedAuthority {

	// Batch
	VIEW_ALL, CREATE_ONE, UPDATE_ONE, DELETE_ONE,

	// Specific
	PUBLISH_ONE, DEACTIVE_ONE;

	/*
	 * Actions
	 */

	@Override
	public String getAuthority() {
		return this.name();
	}

}
