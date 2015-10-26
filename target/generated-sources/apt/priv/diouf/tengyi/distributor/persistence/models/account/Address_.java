package priv.diouf.tengyi.distributor.persistence.models.account;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Address.class)
public abstract class Address_ {

	public static volatile SingularAttribute<Address, String> zip;
	public static volatile SingularAttribute<Address, String> country;
	public static volatile SingularAttribute<Address, String> province;
	public static volatile SingularAttribute<Address, String> city;
	public static volatile SingularAttribute<Address, String> zone;
	public static volatile SingularAttribute<Address, String> overall;
	public static volatile SingularAttribute<Address, Long> id;
	public static volatile SingularAttribute<Address, Account> account;

}

