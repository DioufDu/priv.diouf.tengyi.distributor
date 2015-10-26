package priv.diouf.tengyi.distributor.common.models.enums;

public enum PhotoType {
	THUMBNAIL(120, 90), STANDARD(360, 240), FULL_SCREEN(720, 480), ORIGINAL(Integer.MAX_VALUE, Integer.MAX_VALUE);

	private int height;
	private int width;

	private PhotoType(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	@Override
	public String toString() {
		return String.format("%s * %s", String.valueOf(this.height), String.valueOf(this.width));
	}

}
