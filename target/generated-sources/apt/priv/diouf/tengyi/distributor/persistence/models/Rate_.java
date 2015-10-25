package priv.diouf.tengyi.distributor.persistence.models;

import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Rate.class)
public abstract class Rate_ {

	public static volatile SingularAttribute<Rate, Calendar> dateTime;
	public static volatile SingularAttribute<Rate, Integer> service;
	public static volatile SingularAttribute<Rate, Integer> taste;
	public static volatile SingularAttribute<Rate, Integer> style;
	public static volatile SingularAttribute<Rate, RateGroup> rateGroup;
	public static volatile SingularAttribute<Rate, Long> id;
	public static volatile SingularAttribute<Rate, String> userId;

}

