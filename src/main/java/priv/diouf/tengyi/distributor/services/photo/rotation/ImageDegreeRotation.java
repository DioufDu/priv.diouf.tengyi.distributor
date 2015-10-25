package priv.diouf.tengyi.distributor.services.photo.rotation;

import java.awt.image.BufferedImage;

import net.coobird.thumbnailator.Thumbnails.Builder;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Lazy(true)
public class ImageDegreeRotation implements PhotoRotation {

	/*
	 * Fields
	 */

	/*
	 * Constructors
	 */

	public ImageDegreeRotation() {
	}

	/*
	 * Actions
	 */

	@Override
	public Builder<BufferedImage> rotate(Builder<BufferedImage> builder, double angle) {
		if (angle != 0) {
			return builder.rotate(angle);
		}
		return builder;
	}

	/*
	 * Private & Protected Methods
	 */
}
