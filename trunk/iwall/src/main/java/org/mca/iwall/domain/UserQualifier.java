package org.mca.iwall.domain;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;
import javax.inject.Qualifier;

/**
 * Qualifies a phases event
 * @author mcalpay
 */
@Qualifier
@Target({PARAMETER, METHOD, FIELD})
@Retention(RUNTIME)
public @interface UserQualifier {
    User.Qualifiers value();
}
