package org.mca.ewall.beans.context;

import org.jboss.weld.context.AbstractThreadLocalMapContext;


/**
 * ActionContext for the ActionScope
 * @mcalpay
 */
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
