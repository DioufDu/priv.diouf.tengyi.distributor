package priv.diouf.tengyi.distributor.services.photo.compression;

import java.awt.image.BufferedImage;

import net.coobird.thumbnailator.Thumbnails.Builder;
import priv.diouf.tengyi.distributor.common.models.enums.PhotoType;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Lazy(true)
public class OriginalPhotoCompression implements PhotoCompression {

	/*
	 * Fields
	 */

	protected static final double DEFAULT_RATE = 16f / 9f;

	protected double rate;

	/*
	 * Constructors
	 */

	public OriginalPhotoCompression() {
		this.rate = DEFAULT_RATE;
	}

	public OriginalPhotoCompression(double rate) {
		this.rate = rate;
	}

	/*
	 * Actions
	 */

	@Override
	public boolean accept(PhotoType photoType) {
		return PhotoType.ORIGINAL.equals(photoType);
	}

	@Override
	public Builder<BufferedImage> compress(Builder<BufferedImage> imageBuilder, BufferedImage image, PhotoType photoType) {
		if (imageBuilder == null || !this.accept(photoType)) {
			return null;
		}
		int width = image.getWidth();
		int height = image.getHeight();
		if (rate > width / height) {
			height = (int) (width / rate);
		} else {
			width = (int) (height * rate);
		}
		return imageBuilder.forceSize(width, height);
	}
}
