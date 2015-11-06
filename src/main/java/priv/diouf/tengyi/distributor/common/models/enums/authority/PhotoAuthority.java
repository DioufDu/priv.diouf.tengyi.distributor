package priv.diouf.tengyi.distributor.common.models.enums.authority;

import org.springframework.security.core.GrantedAuthority;

public enum PhotoAuthority implements GrantedAuthority {

	// Batch
	VIEW_ALL_AVATAR, VIEW_ALL_PRODUCT_PHOTO, UPLOAD_ONE_AVATAR, UPLOAD_ONE_PRODUCT_PHOTO,

	// Specific
	VIEW_MY_AVATAR;

	/*
	 * Actions
	 */

	@Override
	public String getAuthority() {
		return this.name();
	}

}
