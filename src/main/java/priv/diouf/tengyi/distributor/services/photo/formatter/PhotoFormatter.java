package priv.diouf.tengyi.distributor.services.photo.formatter;

import org.springframework.web.multipart.MultipartFile;

import priv.diouf.tengyi.distributor.common.models.enums.PhotoFormat;
import priv.diouf.tengyi.distributor.services.photo.ImageOperation;

public interface PhotoFormatter extends ImageOperation {

	PhotoFormat identify(MultipartFile file);
}
