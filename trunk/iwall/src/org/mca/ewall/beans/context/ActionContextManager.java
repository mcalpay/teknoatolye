/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mca.ewall.beans.context;

import org.jboss.weld.context.api.helpers.ConcurrentHashMapBeanStore;
import org.mca.ewall.jsf.lifecycle.AfterBeforeEnum;
import org.mca.ewall.jsf.lifecycle.CycleId;
import org.mca.ewall.jsf.lifecycle.PhaseEventDefinition;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.faces.event.PhaseEvent;

/**
 * @author malpay
 */
@ApplicationScoped
public class ActionContextManager {

    private void activate(
            @Observes
            @PhaseEventDefinition(value= CycleId.RESTORE_VIEW, when= AfterBeforeEnum.BEFORE)
            PhaseEvent event
    ) {
        ActionContext.getInstance().setActive(true);
        ActionContext.getInstance().setBeanStore(new ConcurrentHashMapBeanStore());
    }

    private  void deActivate(
            @Observes
            @PhaseEventDefinition(value= CycleId.RENDER_RESPONSE, when= AfterBeforeEnum.AFTER)
            PhaseEvent event
    ) {
        ActionContext.getInstance().destroy();
        ActionContext.getInstance().setActive(false);
        ActionContext.getInstance().setBeanStore(null);
    }

    private void reActivate(
            @Observes
            @PhaseEventDefinition(value= CycleId.INVOKE_APPLICATION, when= AfterBeforeEnum.AFTER)
            PhaseEvent event
    ) {
        deActivate(event);
        activate(event);
    }
}
