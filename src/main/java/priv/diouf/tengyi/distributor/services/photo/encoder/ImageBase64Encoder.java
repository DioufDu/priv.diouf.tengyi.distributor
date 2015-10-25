package priv.diouf.tengyi.distributor.services.photo.encoder;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import net.coobird.thumbnailator.Thumbnails.Builder;
import priv.diouf.tengyi.distributor.common.model.PhotoFormat;
import priv.diouf.tengyi.distributor.services.exceptions.InvalidPhotoFormatException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.thoughtworks.xstream.core.util.Base64Encoder;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Lazy(true)
public class ImageBase64Encoder implements PhotoEncoder {

	/*
	 * Fields
	 */

	@Autowired
	protected Base64Encoder BASE64_ENCODER;

	/*
	 * Constructors
	 */

	public ImageBase64Encoder() {
	}

	/*
	 * Actions
	 */

	@Override
	public String execute(Builder<BufferedImage> imageBuilder, PhotoFormat photoFormat) {
		if (photoFormat != null) {
			try (ByteArrayOutputStream imageOutputStream = new ByteArrayOutputStream()) {
				imageBuilder.outputFormat(photoFormat.getName());
				imageBuilder.toOutputStream(imageOutputStream);
				return String.format("%s %s", photoFormat.getMime(),
						BASE64_ENCODER.encode(imageOutputStream.toByteArray()));
			} catch (IOException ex) {
				throw new InvalidPhotoFormatException(ex);
			}
		}
		throw new InvalidPhotoFormatException();
	}

	/*
	 * Private & Protected Methods
	 */
}
