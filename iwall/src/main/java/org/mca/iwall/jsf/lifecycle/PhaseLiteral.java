package org.mca.iwall.jsf.lifecycle;


import javax.enterprise.util.AnnotationLiteral;

/**
 * Used the select a phase event qualifier
 * @author mcalpay
 */
public class PhaseLiteral extends AnnotationLiteral<PhaseEventDefinition> implements PhaseEventDefinition {

    private CycleId cycle;
    
    private AfterBeforePhaseEnum when;

    public PhaseLiteral(CycleId cycle, AfterBeforePhaseEnum when) {
        this.cycle = cycle;
        this.when = when;
    }

    public CycleId value() {
        return cycle;
    }

    public AfterBeforePhaseEnum when() {
        return when;
    }
}
