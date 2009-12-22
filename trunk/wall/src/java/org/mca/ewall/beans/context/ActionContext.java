/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mca.ewall.beans.context;

import org.jboss.weld.context.AbstractThreadLocalMapContext;
import org.jboss.weld.context.api.helpers.ConcurrentHashMapBeanStore;
import org.mca.ewall.jsf.AfterPhase;
import org.mca.ewall.jsf.BeforePhase;
import org.mca.ewall.jsf.CycleId;


public class ActionContext extends AbstractThreadLocalMapContext {

    private static ActionContext instance = new ActionContext();

    public ActionContext() {
        super(ActionScoped.class);
    }

    public static ActionContext getInstance() {
        return instance;
    }

    @Override
    protected boolean isCreationLockRequired() {
        return false;
    }

    @BeforePhase(CycleId.RESTORE_VIEW)
    public static void activate() {
        instance.setActive(true);
        instance.setBeanStore(new ConcurrentHashMapBeanStore());
    }

    @AfterPhase(CycleId.RENDER_RESPONSE)
    public static void deActivate() {
        instance.destroy();
        instance.setActive(false);
        instance.setBeanStore(null);
    }

    @AfterPhase(CycleId.INVOKE_APPLICATION)
    public static void reActivate() {
        instance.deActivate();
        instance.activate();
    }
    
}
