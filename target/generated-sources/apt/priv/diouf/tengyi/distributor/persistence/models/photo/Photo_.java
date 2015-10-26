package priv.diouf.tengyi.distributor.persistence.models.photo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import priv.diouf.tengyi.distributor.common.models.enums.PhotoFormat;
import priv.diouf.tengyi.distributor.common.models.enums.PhotoType;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Photo.class)
public abstract class Photo_ {

	public static volatile SingularAttribute<Photo, PhotoFormat> format;
	public static volatile SingularAttribute<Photo, Long> id;
	public static volatile SingularAttribute<Photo, PhotoType> type;
	public static volatile SingularAttribute<Photo, String> version;
	public static volatile SingularAttribute<Photo, byte[]> content;

}

