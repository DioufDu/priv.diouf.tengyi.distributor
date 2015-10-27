package priv.diouf.tengyi.distributor.persistence.models.account;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import priv.diouf.tengyi.distributor.common.models.enums.AccountType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("TRIAL_RESELLER")
public class TrialReseller extends Account implements Serializable {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = -1L;

	/*
	 * Constructors
	 */

	public TrialReseller() {
		this.setType(AccountType.TRIAL_RESELLER);
	}
}
