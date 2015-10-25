package priv.diouf.tengyi.distributor.persistence.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import priv.diouf.tengyi.distributor.common.model.PhotoFormat;
import priv.diouf.tengyi.distributor.common.model.PhotoType;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Photo.class)
public abstract class Photo_ {

	public static volatile SingularAttribute<Photo, PhotoFormat> photoFormat;
	public static volatile SingularAttribute<Photo, PhotoType> photoType;
	public static volatile SingularAttribute<Photo, Long> id;
	public static volatile SingularAttribute<Photo, String> version;
	public static volatile SingularAttribute<Photo, byte[]> content;

}

