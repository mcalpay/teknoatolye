package org.mca.ewall.jsf.event;

import org.mca.ewall.jsf.PhaseEventDefinition;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Any;
import javax.enterprise.util.AnnotationLiteral;
import javax.faces.event.PhaseEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@ApplicationScoped
@Named
public class PhaseProducer implements Serializable {

    @Inject @Any
    private Event<PhaseEvent> phaseEvent;


    public void fireEvent(PhaseEvent phaseEventHolder, AnnotationLiteral<PhaseEventDefinition> annotationLiteral) {
        phaseEvent.select(annotationLiteral).fire(phaseEventHolder);
    }
}
