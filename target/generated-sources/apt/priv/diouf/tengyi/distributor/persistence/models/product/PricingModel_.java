package priv.diouf.tengyi.distributor.persistence.models.product;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import priv.diouf.tengyi.distributor.common.models.enums.PriceStrategy;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PricingModel.class)
public abstract class PricingModel_ {

	public static volatile SingularAttribute<PricingModel, BigDecimal> terminalPice;
	public static volatile SingularAttribute<PricingModel, Product> product;
	public static volatile SingularAttribute<PricingModel, BigDecimal> factoryPrice;
	public static volatile SingularAttribute<PricingModel, BigDecimal> storePrice;
	public static volatile SingularAttribute<PricingModel, Long> id;
	public static volatile SingularAttribute<PricingModel, BigDecimal> unifiedPrice;
	public static volatile SingularAttribute<PricingModel, PriceStrategy> strategy;

}

