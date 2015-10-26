package priv.diouf.tengyi.distributor.common.models.enums;

public enum PhotoFormat {
	// PNG
	PNG("png", "image/png", "data:image/png;base64"),
	// JPG
	JPG("jpg", "image/jpg", "data:image/jpg;base64"),
	// JPEG
	JPEG("jpeg", "image/jpeg", "data:image/jpeg;base64"),
	// GIF
	GIF("gif", "image/gif", "data:image/gif;base64");

	private String name;
	private String contentType;
	private String mime;

	private PhotoFormat(String name, String contentType, String mime) {
		this.name = name;
		this.contentType = contentType;
		this.mime = mime;
	}

	public String getName() {
		return this.name;
	}

	public String getContentType() {
		return this.contentType;
	}

	public String getMime() {
		return this.mime;
	}

	@Override
	public String toString() {
		return this.contentType;
	}
}
