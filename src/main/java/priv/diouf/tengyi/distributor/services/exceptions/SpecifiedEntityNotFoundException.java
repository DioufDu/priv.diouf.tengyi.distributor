package priv.diouf.tengyi.distributor.services.exceptions;

/**
 * Once the entity haven't been found in repository, this exception would be thrown.
 *
 * @author I071053
 *
 */
public class SpecifiedEntityNotFoundException extends CommonBusinessException {

	/**
	 * Generate Serial Version UID
	 */

	private static final long serialVersionUID = -7151446830410619459L;

	/**
	 * Fields
	 */

	protected static final String DEFAULT_DESCRIPTION = "Can't find specified entity <%s> by %s.";

	protected static final String DEFAULT_CRITERIA = "id";

	/**
	 * Constructors
	 */

	public SpecifiedEntityNotFoundException(String missingEntityName) {
		super(String.format(DEFAULT_DESCRIPTION, missingEntityName, DEFAULT_CRITERIA));
	}

	public SpecifiedEntityNotFoundException(String missingEntityName, String missingCriteria) {
		super(String.format(DEFAULT_DESCRIPTION, missingEntityName, missingCriteria));
	}

	public SpecifiedEntityNotFoundException(String missingEntityName, Exception ex) {
		super(String.format(DEFAULT_DESCRIPTION, missingEntityName, DEFAULT_CRITERIA), ex);
	}

	public SpecifiedEntityNotFoundException(String missingEntityName, String missingCriteria, Exception ex) {
		super(String.format(DEFAULT_DESCRIPTION, missingEntityName, missingCriteria), ex);
	}
}
