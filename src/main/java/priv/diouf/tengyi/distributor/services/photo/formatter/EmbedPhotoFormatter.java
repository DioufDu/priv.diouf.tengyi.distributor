package priv.diouf.tengyi.distributor.services.photo.formatter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import priv.diouf.tengyi.distributor.common.models.enums.PhotoFormat;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Lazy(true)
public class EmbedPhotoFormatter implements PhotoFormatter {

	/*
	 * Fields
	 */

	/*
	 * Constructors
	 */

	public EmbedPhotoFormatter() {
	}

	/*
	 * Actions
	 */

	@Override
	public PhotoFormat identify(MultipartFile file) {
		for (PhotoFormat photoFormat : PhotoFormat.values()) {
			if (StringUtils.contains(file.getContentType(), photoFormat.getContentType())) {
				return photoFormat;
			}
		}
		return null;
	}

	/*
	 * Private & Protected Methods
	 */

}
