package priv.diouf.tengyi.distributor.services.photo.encoder;

import java.awt.image.BufferedImage;

import net.coobird.thumbnailator.Thumbnails.Builder;
import priv.diouf.tengyi.distributor.common.model.PhotoFormat;
import priv.diouf.tengyi.distributor.services.photo.ImageOperation;

public interface PhotoEncoder extends ImageOperation {

	String execute(Builder<BufferedImage> imageBuilder, PhotoFormat photoFormat);
}
