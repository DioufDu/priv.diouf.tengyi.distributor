package priv.diouf.tengyi.distributor.persistence.models;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("THUMBNAIL")
public class ThumbnailPhoto extends Photo implements Serializable {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = -6613954183559671026L;

	/*
	 * Constructors
	 */

	public ThumbnailPhoto() {

	}
}
