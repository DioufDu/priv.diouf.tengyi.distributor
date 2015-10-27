package priv.diouf.tengyi.distributor.persistence.initializer.factories.account;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import priv.diouf.tengyi.distributor.persistence.exceptions.CommonPersistenceException;
import priv.diouf.tengyi.distributor.persistence.initializer.factories.EntityFactory;
import priv.diouf.tengyi.distributor.persistence.initializer.factories.GeneralEntityFactory;
import priv.diouf.tengyi.distributor.persistence.initializer.factories.TransactionalDataEntityFactory;
import priv.diouf.tengyi.distributor.persistence.models.account.Account;
import priv.diouf.tengyi.distributor.persistence.models.photo.AvatarPhotoGroup;
import priv.diouf.tengyi.distributor.persistence.repositories.account.AvatarPhotoGroupRepository;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class AvatarPhotoGroupEntityFactory extends GeneralEntityFactory<AvatarPhotoGroup> implements TransactionalDataEntityFactory<AvatarPhotoGroup> {

	/*
	 * Fields
	 */

	@Autowired
	protected AvatarPhotoGroupRepository avatarPhotoGroupRepository;

	@Autowired
	protected AccountEntityFactory accountEntityFactory;

	/*
	 * Creations
	 */

	@Override
	@Transactional
	protected void fulfill(List<AvatarPhotoGroup> allCreatedEntities) {
		int index = 0;
		for (Account account : accountEntityFactory.getAll()) {
			account.setAvatarPhotoGroup(new AvatarPhotoGroup(account));
			String photoName = "avatars/" + (index++ % 25 + 1) + ".jpg";
			try (InputStream avatarStream = this.getClass().getResourceAsStream(photoName)) {
				if (avatarStream == null || avatarStream.available() < 1) {
					continue;
				}
				byte[] avatarBytes = IOUtils.toByteArray(avatarStream);
				allCreatedEntities.add(avatarPhotoGroupRepository.generatePhotoGroup(account.getAvatarPhotoGroup(), avatarBytes));
			} catch (IOException ex) {
				throw new CommonPersistenceException("Can't store the avatar into account.", ex);
			}
		}
	}

	/*
	 * Metadata Information
	 */

	@Override
	protected EntityFactory<?>[] getDependEntityFactories() {
		return new EntityFactory<?>[] { accountEntityFactory };
	}

	@Override
	public Class<AvatarPhotoGroup> getEntityType() {
		return AvatarPhotoGroup.class;
	}

	@Override
	protected JpaRepository<AvatarPhotoGroup, Long> getGeneralJpaRepository() {
		return avatarPhotoGroupRepository;
	}
}
