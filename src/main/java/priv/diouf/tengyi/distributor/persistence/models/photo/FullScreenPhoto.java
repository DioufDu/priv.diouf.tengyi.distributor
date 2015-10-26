package priv.diouf.tengyi.distributor.persistence.models.photo;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("FULL_SCREEN")
public class FullScreenPhoto extends Photo implements Serializable {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = -6613954183559671026L;

	/*
	 * Constructors
	 */

	public FullScreenPhoto() {

	}
}
