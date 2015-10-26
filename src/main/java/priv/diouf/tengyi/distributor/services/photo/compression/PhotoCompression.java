package priv.diouf.tengyi.distributor.services.photo.compression;

import java.awt.image.BufferedImage;

import net.coobird.thumbnailator.Thumbnails.Builder;
import priv.diouf.tengyi.distributor.common.models.enums.PhotoType;
import priv.diouf.tengyi.distributor.services.photo.ImageOperation;

public interface PhotoCompression extends ImageOperation {

	boolean accept(PhotoType photoType);

	Builder<BufferedImage> compress(Builder<BufferedImage> imageBuilder, BufferedImage image, PhotoType photoType);

}
