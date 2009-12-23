package org.mca.ewall.jsf;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.RetentionPolicy.*;
import static java.lang.annotation.ElementType.*;

@Retention(RUNTIME)
@Target(METHOD)
public @interface AfterPhase {
    public abstract CycleId value();
}
