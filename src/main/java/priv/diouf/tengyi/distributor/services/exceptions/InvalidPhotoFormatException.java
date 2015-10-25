package priv.diouf.tengyi.distributor.services.exceptions;

public class InvalidPhotoFormatException extends CommonBusinessException {

	/**
	 * Generate Serial Version UID
	 */

	private static final long serialVersionUID = 8480677863916385835L;

	/**
	 * Fields
	 */

	protected static final String DEFAULT_DESCRIPTION = "Invalid photo format.";

	/**
	 * Constructors
	 */

	public InvalidPhotoFormatException() {
		super(DEFAULT_DESCRIPTION);
	}

	public InvalidPhotoFormatException(Exception ex) {
		super(DEFAULT_DESCRIPTION, ex);
	}
}
