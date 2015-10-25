package priv.diouf.tengyi.distributor.web.exceptions;

import org.springframework.validation.BindingResult;

public class InvalidPageParameterException extends CommonWebException {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = 7380099800312811117L;

	/*
	 * Fields
	 */

	protected static final String DEFAULT_DESCRIPTION = "The request page parameters are inavail (size = <%s>, index = <%s>), please make sure the size is between 1 and 100, the index must be greater than 1.";

	protected String errorMessage;

	protected BindingResult bindingResult;

	/*
	 * Constructors
	 */

	public InvalidPageParameterException(int pageIndex, int pageSize) {
		super(String.format(DEFAULT_DESCRIPTION, pageSize, pageIndex));
	}
}
