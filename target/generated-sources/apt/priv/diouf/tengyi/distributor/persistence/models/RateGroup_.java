package priv.diouf.tengyi.distributor.persistence.models;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RateGroup.class)
public abstract class RateGroup_ {

	public static volatile SingularAttribute<RateGroup, Dish> dish;
	public static volatile SingularAttribute<RateGroup, BigDecimal> service;
	public static volatile SingularAttribute<RateGroup, BigDecimal> taste;
	public static volatile SetAttribute<RateGroup, Rate> rates;
	public static volatile SingularAttribute<RateGroup, Long> count;
	public static volatile SingularAttribute<RateGroup, BigDecimal> style;
	public static volatile SingularAttribute<RateGroup, Long> id;

}

