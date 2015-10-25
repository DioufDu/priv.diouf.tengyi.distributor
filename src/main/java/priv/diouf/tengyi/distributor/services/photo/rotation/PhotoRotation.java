package priv.diouf.tengyi.distributor.services.photo.rotation;

import java.awt.image.BufferedImage;

import net.coobird.thumbnailator.Thumbnails.Builder;
import priv.diouf.tengyi.distributor.services.photo.ImageOperation;

public interface PhotoRotation extends ImageOperation {

	Builder<BufferedImage> rotate(Builder<BufferedImage> builder, double angle);

}
