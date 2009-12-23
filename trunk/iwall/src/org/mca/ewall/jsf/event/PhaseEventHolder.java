package org.mca.ewall.jsf.event;

import javax.faces.event.PhaseEvent;

public class PhaseEventHolder {

    private PhaseEvent event;

    public PhaseEventHolder(PhaseEvent event) {
        this.event = event;
    }

    public PhaseEvent getEvent() {
        return event;
    }

}
