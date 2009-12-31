package org.mca.iwall.jsf.lifecycle;

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
@Target({FIELD,PARAMETER})
@Retention(RUNTIME)
public @interface PhaseEventDefinition {
    CycleId value();
    AfterBeforeEnum when();
}
