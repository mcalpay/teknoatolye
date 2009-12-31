package org.mca.iwall.jsf.lifecycle;


import javax.enterprise.util.AnnotationLiteral;

/**
 * Used the select a phase event qualifier
 * @author mcalpay
 */
public class PhaseLiteral extends AnnotationLiteral<PhaseEventDefinition> implements PhaseEventDefinition {

    private CycleId cycle;
    
    private AfterBeforeEnum when;

    public PhaseLiteral(CycleId cycle, AfterBeforeEnum when) {
        this.cycle = cycle;
        this.when = when;
    }

    public CycleId value() {
        return cycle;
    }

    public AfterBeforeEnum when() {
        return when;
    }
}
