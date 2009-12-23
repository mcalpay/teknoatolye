package org.mca.ewall.beans.context;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.enterprise.context.NormalScope;
import static java.lang.annotation.RetentionPolicy.*;
import static java.lang.annotation.ElementType.*;

@NormalScope(passivating=false)
@Retention(RUNTIME)
@Target({TYPE, METHOD})
@Inherited
public @interface ActionScoped {
}
