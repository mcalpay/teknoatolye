package org.mca.ewall.jsf.lifecycle;

import org.mca.ewall.jsf.lifecycle.PhaseEventDefinition;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Any;
import javax.enterprise.util.AnnotationLiteral;
import javax.faces.event.PhaseEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Fires phase events
 * @author mcalpay
 */
@ApplicationScoped
@Named
public class PhaseProducer implements Serializable {

    @Inject @Any
    private Event<PhaseEvent> phaseEvent;


    public void fireEvent(PhaseEvent phaseEventHolder, AnnotationLiteral<PhaseEventDefinition> annotationLiteral) {
        phaseEvent.select(annotationLiteral).fire(phaseEventHolder);
    }
}
