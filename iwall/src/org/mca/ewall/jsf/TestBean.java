package org.mca.ewall.jsf;

import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Named;
import org.jboss.weld.Container;
import org.mca.ewall.jsf.event.PhaseEventHolder;

/**
 * @author mcalpay
 */
@ApplicationScoped
@Named
public class TestBean implements Serializable {

    public void afterRestoreView(@Observes 
            @PhaseEventDefinition(value=CycleId.RESTORE_VIEW, when=AfterBeforeEnum.AFTER)
            PhaseEventHolder holder) {
        System.out.println("after restore view : " + holder.getEvent().getPhaseId());
    }

}
