package org.mca.iwall.beans.security;


import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;
import javax.inject.Qualifier;

/**
 * Qualifies the principal
 * @author mcalpay
 */
@Qualifier
@Target({FIELD, METHOD})
@Retention(RUNTIME)
public @interface Anonymous {
}
