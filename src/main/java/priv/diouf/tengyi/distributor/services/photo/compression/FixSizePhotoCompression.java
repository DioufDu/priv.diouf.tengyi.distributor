package priv.diouf.tengyi.distributor.services.photo.compression;

import java.awt.image.BufferedImage;

import net.coobird.thumbnailator.Thumbnails.Builder;
import priv.diouf.tengyi.distributor.common.model.PhotoType;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Lazy(true)
public class FixSizePhotoCompression implements PhotoCompression {

	/*
	 * Fields
	 */

	/*
	 * Constructors
	 */

	public FixSizePhotoCompression() {
	}

	/*
	 * Actions
	 */

	@Override
	public boolean accept(PhotoType photoType) {
		return !PhotoType.ORIGINAL.equals(photoType);
	}

	@Override
	public Builder<BufferedImage> compress(Builder<BufferedImage> imageBuilder, BufferedImage image, PhotoType photoType) {
		if (imageBuilder == null || !this.accept(photoType)) {
			return null;
		}
		return imageBuilder.forceSize(photoType.getWidth(), photoType.getHeight());
	}
}
