package priv.diouf.tengyi.distributor.persistence.models.account;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Contact.class)
public abstract class Contact_ {

	public static volatile SingularAttribute<Contact, String> mobile;
	public static volatile SingularAttribute<Contact, String> alternativePhone;
	public static volatile SingularAttribute<Contact, String> telephone;
	public static volatile SingularAttribute<Contact, Long> id;
	public static volatile SingularAttribute<Contact, String> fax;
	public static volatile SingularAttribute<Contact, String> email;
	public static volatile SingularAttribute<Contact, Account> account;

}

