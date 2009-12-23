package org.mca.ewall.jsf;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;
import javax.inject.Qualifier;

@Qualifier
@Target({FIELD,PARAMETER})
@Retention(RUNTIME)
public @interface PhaseEventDefinition {
    CycleId value();
    AfterBeforeEnum when();
}
