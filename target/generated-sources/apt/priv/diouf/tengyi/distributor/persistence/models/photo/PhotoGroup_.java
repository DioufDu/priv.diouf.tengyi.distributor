package priv.diouf.tengyi.distributor.persistence.models.photo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import priv.diouf.tengyi.distributor.common.models.enums.PhotoGroupType;
import priv.diouf.tengyi.distributor.persistence.models.Modification;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PhotoGroup.class)
public abstract class PhotoGroup_ {

	public static volatile SingularAttribute<PhotoGroup, StandardPhoto> standardPhoto;
	public static volatile SingularAttribute<PhotoGroup, ThumbnailPhoto> thumbnailPhoto;
	public static volatile SingularAttribute<PhotoGroup, FullScreenPhoto> fullScreenPhoto;
	public static volatile SingularAttribute<PhotoGroup, OriginalPhoto> originalPhoto;
	public static volatile SingularAttribute<PhotoGroup, Long> id;
	public static volatile SingularAttribute<PhotoGroup, PhotoGroupType> type;
	public static volatile SingularAttribute<PhotoGroup, Modification> modification;

}

