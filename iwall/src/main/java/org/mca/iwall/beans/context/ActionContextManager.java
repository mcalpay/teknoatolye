package org.mca.iwall.beans.context;

import org.jboss.weld.context.api.helpers.ConcurrentHashMapBeanStore;
import org.mca.iwall.jsf.lifecycle.AfterBeforePhaseEnum;
import org.mca.iwall.jsf.lifecycle.CycleId;
import org.mca.iwall.jsf.lifecycle.PhaseEventDefinition;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.faces.event.PhaseEvent;

/**
 * Manages the action-context
 * @author malpay
 */
@ApplicationScoped
public class ActionContextManager {

    private void activate(
            @Observes
            @PhaseEventDefinition(value= CycleId.RESTORE_VIEW, when= AfterBeforePhaseEnum.BEFORE)
            PhaseEvent event
    ) {
        ActionContext.getInstance().setActive(true);
        ActionContext.getInstance().setBeanStore(new ConcurrentHashMapBeanStore());
    }

    private  void deActivate(
            @Observes
            @PhaseEventDefinition(value= CycleId.RENDER_RESPONSE, when= AfterBeforePhaseEnum.AFTER)
            PhaseEvent event
    ) {
        ActionContext.getInstance().destroy();
        ActionContext.getInstance().setActive(false);
        ActionContext.getInstance().setBeanStore(null);
    }

    private void reActivate(
            @Observes
            @PhaseEventDefinition(value= CycleId.INVOKE_APPLICATION, when= AfterBeforePhaseEnum.AFTER)
            PhaseEvent event
    ) {
        deActivate(event);
        activate(event);
    }
}
