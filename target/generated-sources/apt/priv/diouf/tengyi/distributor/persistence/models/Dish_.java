package priv.diouf.tengyi.distributor.persistence.models;

import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Dish.class)
public abstract class Dish_ {

	public static volatile SingularAttribute<Dish, String> englishName;
	public static volatile SingularAttribute<Dish, String> line;
	public static volatile SingularAttribute<Dish, String> recommendedReason;
	public static volatile SingularAttribute<Dish, String> description;
	public static volatile SingularAttribute<Dish, RateGroup> rateGroup;
	public static volatile SingularAttribute<Dish, Modification> modification;
	public static volatile SingularAttribute<Dish, StandardPhoto> standardPhoto;
	public static volatile SingularAttribute<Dish, Calendar> offerDate;
	public static volatile SingularAttribute<Dish, ThumbnailPhoto> thumbnailPhoto;
	public static volatile SingularAttribute<Dish, FullScreenPhoto> fullScreenPhoto;
	public static volatile SingularAttribute<Dish, String> chineseName;
	public static volatile SingularAttribute<Dish, Boolean> isRecommended;
	public static volatile SingularAttribute<Dish, OriginalPhoto> originalPhoto;
	public static volatile SingularAttribute<Dish, Long> id;

}

