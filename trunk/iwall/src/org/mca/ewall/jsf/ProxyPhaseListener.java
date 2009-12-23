package org.mca.ewall.jsf;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Any;
import javax.enterprise.util.AnnotationLiteral;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;

import org.jboss.weld.BeanManagerImpl;
import org.jboss.weld.Container;
import org.mca.ewall.jsf.event.PhaseEventHolder;
import org.mca.ewall.jsf.event.PhaseProducer;

/**
 * @author mcalpay
 */
public class ProxyPhaseListener
        implements PhaseListener {

    private PhaseProducer phaseProducer;

    public void afterPhase(PhaseEvent event) {
        fireEvent(event,AfterBeforeEnum.AFTER);
    }


    public void beforePhase(PhaseEvent event) {
        fireEvent(event,AfterBeforeEnum.BEFORE);
    }

    private void fireEvent(PhaseEvent event, AfterBeforeEnum when) {
        final int ordinal = event.getPhaseId().getOrdinal();
        final CycleId[] cycles = CycleId.values();
        PhaseEventHolder phaseEventHolder = new PhaseEventHolder(event);
        AnnotationLiteral<PhaseEventDefinition> annotationLiteral =
                new PhaseLiteral(cycles[ordinal], when);
        getPhaseProducer().fireEvent(phaseEventHolder,annotationLiteral);
    }

    private PhaseProducer getPhaseProducer() {
        if(phaseProducer == null) {
            FacesContext context = FacesContext.getCurrentInstance();
            phaseProducer = (PhaseProducer) context.getApplication()
                .getELResolver().getValue(context.getELContext(),null,"phaseProducer");
        }
        return phaseProducer;
    }

    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }
}
