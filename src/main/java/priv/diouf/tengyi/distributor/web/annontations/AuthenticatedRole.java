package priv.diouf.tengyi.distributor.web.annontations;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation for all UI consumable REST services that require a specific privilege in order to be executed.
 */
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface AuthenticatedRole {

	String[] value();

}