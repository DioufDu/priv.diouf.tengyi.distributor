package priv.diouf.tengyi.distributor.persistence.models;

import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import priv.diouf.tengyi.distributor.persistence.models.account.Account;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Modification.class)
public abstract class Modification_ {

	public static volatile SingularAttribute<Modification, Account> createBy;
	public static volatile SingularAttribute<Modification, Account> updateBy;
	public static volatile SingularAttribute<Modification, Calendar> createOn;
	public static volatile SingularAttribute<Modification, Long> id;
	public static volatile SingularAttribute<Modification, Calendar> updateOn;

}

