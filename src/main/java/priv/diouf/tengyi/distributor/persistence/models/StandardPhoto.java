package priv.diouf.tengyi.distributor.persistence.models;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("STANDARD")
public class StandardPhoto extends Photo implements Serializable {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = 7151363363484283789L;

	/*
	 * Constructors
	 */

	public StandardPhoto() {

	}
}
