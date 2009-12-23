package org.mca.ewall.jsf;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import org.mca.ewall.beans.context.ActionContext;

/**
 * @author malpay
 */
public class ActionScopedPhaseListener implements PhaseListener {

    public void beforePhase(PhaseEvent event) {
        if (event.getPhaseId().equals(PhaseId.RESTORE_VIEW)) {
            ActionContext.getInstance().activate();
        }
    }

    public void afterPhase(PhaseEvent event) {
        if (event.getPhaseId().equals(PhaseId.RENDER_RESPONSE)) {
            ActionContext.getInstance().deActivate();
        } else if (event.getPhaseId().equals(PhaseId.INVOKE_APPLICATION)) {
            ActionContext.getInstance().reActivate();
        }
    }

    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }
}
