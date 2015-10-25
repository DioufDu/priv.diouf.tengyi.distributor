package priv.diouf.tengyi.distributor.services.exceptions;

public class RequestParameterMissingException extends CommonBusinessException {

	/**
	 * Generate Serial Version UID
	 */

	private static final long serialVersionUID = 8480677863916385835L;

	/**
	 * Fields
	 */

	protected static final String DEFAULT_DESCRIPTION = "The required parameter <%s> is missing in request body.";

	/**
	 * Constructors
	 */

	public RequestParameterMissingException(String missingParameter) {
		super(String.format(DEFAULT_DESCRIPTION, missingParameter));
	}

	public RequestParameterMissingException(String missingParameter, Exception ex) {
		super(String.format(DEFAULT_DESCRIPTION, missingParameter), ex);
	}
}
