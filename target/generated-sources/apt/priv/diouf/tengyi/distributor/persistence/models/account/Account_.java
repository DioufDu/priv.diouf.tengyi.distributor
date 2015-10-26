package priv.diouf.tengyi.distributor.persistence.models.account;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import priv.diouf.tengyi.distributor.common.models.enums.AccountStatus;
import priv.diouf.tengyi.distributor.common.models.enums.AccountType;
import priv.diouf.tengyi.distributor.persistence.models.Modification;
import priv.diouf.tengyi.distributor.persistence.models.photo.AvatarPhotoGroup;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Account.class)
public abstract class Account_ {

	public static volatile SingularAttribute<Account, String> clearPassword;
	public static volatile SingularAttribute<Account, String> loginId;
	public static volatile SingularAttribute<Account, AvatarPhotoGroup> avatarPhotoGroup;
	public static volatile SingularAttribute<Account, Address> address;
	public static volatile SingularAttribute<Account, Contact> contact;
	public static volatile SingularAttribute<Account, String> name;
	public static volatile SingularAttribute<Account, Long> id;
	public static volatile SingularAttribute<Account, String> title;
	public static volatile SingularAttribute<Account, AccountType> type;
	public static volatile SingularAttribute<Account, Modification> modification;
	public static volatile SingularAttribute<Account, AccountStatus> status;

}

