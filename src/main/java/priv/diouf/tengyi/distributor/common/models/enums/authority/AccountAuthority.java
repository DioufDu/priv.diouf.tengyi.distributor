package priv.diouf.tengyi.distributor.common.models.enums.authority;

import org.springframework.security.core.GrantedAuthority;

public enum AccountAuthority implements GrantedAuthority {

	// Batch
	VIEW_ALL, CREATE_ONE, UPDATE_ONE, DELETE_ONE,

	// Specific
	DEACTIVE_ONE, VIEW_MY, UPDATE_MY;

	/*
	 * Actions
	 */

	@Override
	public String getAuthority() {
		return this.name();
	}

}
