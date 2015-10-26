package priv.diouf.tengyi.distributor.common.auxiliaries;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.thoughtworks.xstream.core.util.Base64Encoder;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;
import priv.diouf.tengyi.distributor.common.models.enums.PhotoFormat;
import priv.diouf.tengyi.distributor.common.models.enums.PhotoType;
import priv.diouf.tengyi.distributor.persistence.models.photo.Photo;
import priv.diouf.tengyi.distributor.services.exceptions.InvalidPhotoFormatException;

public class ImageHelper {

	/*
	 * Fields
	 */

	protected static final double DEFAULT_RATE = 16f / 9f;

	protected static final Base64Encoder BASE64_ENCODER = new Base64Encoder();

	/*
	 * Actions
	 */

	public static Builder<BufferedImage> transferImage(MultipartFile file, PhotoFormat photoFormat, PhotoType photoType, int angle) {
		try {
			return transferImage(file.getInputStream(), photoType, photoFormat, angle);
		} catch (IOException ex) {
			throw new InvalidPhotoFormatException();
		}
	}

	public static byte[] transferImage(@Valid @NotNull Photo photo, int angle) {
		try (InputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(photo.getContent()), 64 * 1024)) {
			BufferedImage image = ImageIO.read(inputStream);
			Builder<BufferedImage> imageBuilder = Thumbnails.of(image);
			int width = PhotoType.STANDARD.getWidth();
			int heigth = PhotoType.STANDARD.getHeight();
			int height = image.getHeight();
			if (photo.getType() == PhotoType.ORIGINAL) {
				width = image.getWidth();
				height = image.getHeight();
				if (DEFAULT_RATE > width / height) {
					height = (int) (width / DEFAULT_RATE);
				} else {
					width = (int) (height * DEFAULT_RATE);
				}
				return transferToByteArray(imageBuilder.forceSize(width, height).rotate(angle).outputFormat(photo.getPhotoFormat()
						.getName()));
			} else {
				width = photo.getType().getWidth();
				heigth = photo.getType().getHeight();
			}
			if (angle % 360 != 0) {
				imageBuilder.rotate(angle);
			}
			switch (angle % 180) {
			case 90:
			case -90: {
				imageBuilder.forceSize(heigth, width);
			}
				break;
			default: {
				imageBuilder.forceSize(width, heigth);
			}
				break;
			}
			return transferToByteArray(imageBuilder.outputFormat(photo.getPhotoFormat().getName()));
		} catch (IOException ex) {
			throw new InvalidPhotoFormatException();
		}
	}

	public static byte[] transferToByteArray(Builder<BufferedImage> imageBuilder) throws IOException {
		try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream(64 * 1024)) {
			imageBuilder.toOutputStream(outputStream);
			return outputStream.toByteArray();
		}
	}

	public static Builder<BufferedImage> transferImage(InputStream imageStream, PhotoType photoType, PhotoFormat photoFormat, int angle) {
		try {
			BufferedImage image = ImageIO.read(imageStream);
			Builder<BufferedImage> imageBuilder = Thumbnails.of(image);
			int width = PhotoType.STANDARD.getWidth();
			int heigth = PhotoType.STANDARD.getHeight();
			int height = image.getHeight();
			if (photoType == PhotoType.ORIGINAL) {
				width = image.getWidth();
				height = image.getHeight();
				if (DEFAULT_RATE > width / height) {
					height = (int) (width / DEFAULT_RATE);
				} else {
					width = (int) (height * DEFAULT_RATE);
				}
				return imageBuilder.forceSize(width, height).rotate(angle);
			} else {
				width = photoType.getWidth();
				heigth = photoType.getHeight();
			}
			if (angle % 360 != 0) {
				imageBuilder.rotate(angle);
			}
			switch (angle % 180) {
			case 90:
			case -90: {
				imageBuilder.forceSize(heigth, width);
			}
				break;
			default: {
				imageBuilder.forceSize(width, heigth);
			}
				break;
			}
			imageBuilder.outputFormat(photoFormat.getName());
			return imageBuilder;
		} catch (IOException ex) {
			throw new InvalidPhotoFormatException();
		}
	}

	public static String encodeImageAsString(Builder<BufferedImage> imageBuilder, PhotoFormat photoFormat) {
		if (photoFormat == null) {
			return null;
		}
		try (ByteArrayOutputStream imageOutputStream = new ByteArrayOutputStream()) {
			imageBuilder.outputFormat(photoFormat.getName());
			imageBuilder.toOutputStream(imageOutputStream);
			return String.format("%s %s", photoFormat.getMime(), BASE64_ENCODER.encode(imageOutputStream.toByteArray()));
		} catch (IOException ex) {
			throw new InvalidPhotoFormatException(ex);
		}
	}

	public static byte[] encodeImageAsByteArray(Builder<BufferedImage> imageBuilder, PhotoFormat photoFormat) {
		if (photoFormat == null) {
			return null;
		}
		try (ByteArrayOutputStream imageOutputStream = new ByteArrayOutputStream()) {
			imageBuilder.outputFormat(photoFormat.getName());
			imageBuilder.toOutputStream(imageOutputStream);
			return imageOutputStream.toByteArray();
		} catch (IOException ex) {
			throw new InvalidPhotoFormatException(ex);
		}
	}

	public static PhotoFormat identify(MultipartFile file) {
		for (PhotoFormat photoFormat : PhotoFormat.values()) {
			if (StringUtils.contains(file.getContentType(), photoFormat.getContentType())) {
				return photoFormat;
			}
		}
		return PhotoFormat.JPG;
	}

	public static byte[] compress(byte[] imageBytes) throws IOException {
		try (ByteArrayInputStream imageStream = new ByteArrayInputStream(imageBytes)) {
			return compress(imageStream);
		}
	}

	public static byte[] compress(byte[] imageBytes, double rate) throws IOException {
		try (ByteArrayInputStream imageStream = new ByteArrayInputStream(imageBytes)) {
			return compress(imageStream, rate);
		}
	}

	public static byte[] compress(ByteArrayInputStream imageStream, double rate) throws IOException {
		if (imageStream == null) {
			return null;
		} else {
			try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
				BufferedImage image = ImageIO.read(imageStream);
				Builder<BufferedImage> imageBuilder = Thumbnails.of(image);
				int width = image.getWidth();
				int height = image.getHeight();
				if (rate > width / height) {
					height = (int) (width / rate);
				} else {
					width = (int) (height * rate);
				}
				imageBuilder.forceSize(width, height).toOutputStream(out);
				return out.toByteArray();
			}
		}
	}

	public static byte[] compress(byte[] imageBytes, int width, int height) throws IOException {
		try (ByteArrayInputStream imageStream = new ByteArrayInputStream(imageBytes)) {
			return compress(imageStream, width, height);
		}
	}

	public static byte[] compress(InputStream imageStream) throws IOException {
		return compress(imageStream, 150, 100);
	}

	public static byte[] compress(InputStream imageStream, int width, int height) throws IOException {
		if (imageStream == null) {
			return null;
		} else {
			try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
				Thumbnails.of(imageStream).forceSize(width, height).toOutputStream(out);
				return out.toByteArray();
			}
		}
	}

	public static byte[] compress(Builder<? extends InputStream> imageBuilder, int width, int height) throws IOException {
		if (imageBuilder == null) {
			return null;
		} else {
			try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
				imageBuilder.forceSize(width, height).toOutputStream(out);
				return out.toByteArray();
			}
		}
	}

	public static byte[] inputStreamToByteArrays(InputStream in) throws IOException {
		final int BUFFER_SIZE = 4096;
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[BUFFER_SIZE];
		int count = -1;
		while ((count = in.read(data, 0, BUFFER_SIZE)) != -1) {
			outStream.write(data, 0, count);
		}
		return outStream.toByteArray();
	}
}
