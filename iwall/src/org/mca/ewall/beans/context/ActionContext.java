package org.mca.ewall.beans.context;

import org.jboss.weld.context.AbstractThreadLocalMapContext;
import org.jboss.weld.context.api.helpers.ConcurrentHashMapBeanStore;
import org.mca.ewall.jsf.CycleId;

import java.lang.annotation.Annotation;


public class ActionContext extends AbstractThreadLocalMapContext {

    private static ActionContext instance = new ActionContext();

    private ActionContext() {
        super(ActionScoped.class);
    }

    public static ActionContext getInstance() {
        return instance;
    }

    @Override
    protected boolean isCreationLockRequired() {
        return false;
    }
    
}
