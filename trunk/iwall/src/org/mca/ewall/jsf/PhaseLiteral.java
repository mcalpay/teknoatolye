/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mca.ewall.jsf;

import javax.enterprise.util.AnnotationLiteral;

/**
 *
 * @author malpay
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
