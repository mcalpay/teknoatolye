package org.mca.iwall.jsf.lifecycle;

import javax.enterprise.util.AnnotationLiteral;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 * Propogates jsf phase events to CDI beans 
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
        AnnotationLiteral<PhaseEventDefinition> annotationLiteral =
                new PhaseLiteral(cycles[ordinal], when);
        getPhaseProducer().fireEvent(event,annotationLiteral);
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
