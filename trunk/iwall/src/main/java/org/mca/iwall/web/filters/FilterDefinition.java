package org.mca.iwall.web.filters;

import org.mca.iwall.web.filters.AfterBeforeFilterEnum;

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
@Target({PARAMETER})
@Retention(RUNTIME)
public @interface FilterDefinition {
    AfterBeforeFilterEnum when();
//    String mapping();
//    String sortHint() default "";
}
