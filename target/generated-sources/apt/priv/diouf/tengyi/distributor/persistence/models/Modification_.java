package priv.diouf.tengyi.distributor.persistence.models;

import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Modification.class)
public abstract class Modification_ {

	public static volatile SingularAttribute<Modification, String> createUserId;
	public static volatile SingularAttribute<Modification, Calendar> lastModifiedDateTime;
	public static volatile SingularAttribute<Modification, String> lastModifiedUserId;
	public static volatile SingularAttribute<Modification, Calendar> createDateTime;

}

