package priv.diouf.tengyi.distributor.persistence.models.product;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import priv.diouf.tengyi.distributor.common.models.enums.ProductStatus;
import priv.diouf.tengyi.distributor.persistence.models.Modification;
import priv.diouf.tengyi.distributor.persistence.models.photo.ProductPhotoGroup;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Product.class)
public abstract class Product_ {

	public static volatile SingularAttribute<Product, PricingModel> pricingModel;
	public static volatile SingularAttribute<Product, String> name;
	public static volatile SingularAttribute<Product, String> serie;
	public static volatile SingularAttribute<Product, String> specification;
	public static volatile SingularAttribute<Product, String> model;
	public static volatile SingularAttribute<Product, String> comment;
	public static volatile SingularAttribute<Product, Long> id;
	public static volatile ListAttribute<Product, ProductPhotoGroup> productPhotoGroups;
	public static volatile SingularAttribute<Product, Modification> modification;
	public static volatile SingularAttribute<Product, ProductStatus> status;

}

