package org.mca.ewall.extensions;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.Extension;
import org.jboss.weld.bootstrap.api.Service;
import org.mca.ewall.beans.context.ActionContext;

/**
 * Extension adding the action-context
 * @author mcalpay
 */
public class Extensions implements Extension, Service{

    public void afterBeanDiscovery(@Observes AfterBeanDiscovery event, BeanManager manager) {
        event.addContext(ActionContext.getInstance());
    }

    public void cleanup() {
    }
}
