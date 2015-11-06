package priv.diouf.tengyi.distributor.common.models.enums;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import priv.diouf.tengyi.distributor.common.models.enums.authority.AccountAuthority;
import priv.diouf.tengyi.distributor.common.models.enums.authority.PhotoAuthority;
import priv.diouf.tengyi.distributor.common.models.enums.authority.ProductAuthority;

public enum AccountType {

	/*
	 * Constants
	 */

	ADMIN(
			// Account Authorities
			AccountAuthority.CREATE_ONE, AccountAuthority.VIEW_ALL, AccountAuthority.CREATE_ONE, AccountAuthority.UPDATE_ONE,
			AccountAuthority.DELETE_ONE, AccountAuthority.DEACTIVE_ONE, AccountAuthority.VIEW_MY, AccountAuthority.UPDATE_MY,
			// Product Authorities
			ProductAuthority.VIEW_ALL, ProductAuthority.CREATE_ONE, ProductAuthority.UPDATE_ONE, ProductAuthority.DELETE_ONE,
			ProductAuthority.DEACTIVE_ONE, ProductAuthority.PUBLISH_ONE, ProductAuthority.DEACTIVE_ONE,
			// Photo Authorities
			PhotoAuthority.VIEW_ALL_AVATAR, PhotoAuthority.VIEW_ALL_PRODUCT_PHOTO, PhotoAuthority.VIEW_MY_AVATAR,
			PhotoAuthority.UPLOAD_ONE_AVATAR, PhotoAuthority.UPLOAD_ONE_PRODUCT_PHOTO),

	AUTH_RESELLER(
			// Account Authorities
			AccountAuthority.VIEW_MY, AccountAuthority.UPDATE_MY,
			// Product Authorities
			ProductAuthority.VIEW_ALL,
			// Photo Authorities
			PhotoAuthority.VIEW_MY_AVATAR, PhotoAuthority.UPLOAD_ONE_AVATAR),

	TRIAL_RESELLER(
			// Account Authorities
			AccountAuthority.VIEW_MY, AccountAuthority.UPDATE_MY,
			// Product Authorities
			ProductAuthority.VIEW_ALL,
			// Photo Authorities
			PhotoAuthority.VIEW_MY_AVATAR, PhotoAuthority.UPLOAD_ONE_AVATAR);

	/*
	 * Fields
	 */

	private static final String ROLE_PREFIX = "ROLE_";

	private ArrayList<GrantedAuthority> authorities;

	/*
	 * Constructors
	 */

	private AccountType(GrantedAuthority... authorities) {
		if (!ArrayUtils.isEmpty(authorities)) {
			this.authorities = new ArrayList<GrantedAuthority>(authorities.length + 1);
			for (GrantedAuthority grantedAuthority : authorities) {
				this.authorities.add(grantedAuthority);
			}
		} else {
			this.authorities = new ArrayList<GrantedAuthority>(1);
		}
		this.authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + this.name()));
	}

	/*
	 * Actions
	 */

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}
}
