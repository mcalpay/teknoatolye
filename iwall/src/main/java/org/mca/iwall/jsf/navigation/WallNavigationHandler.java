package org.mca.iwall.jsf.navigation;

import com.sun.faces.application.NavigationHandlerImpl;
import org.apache.commons.lang.StringUtils;

import javax.faces.application.NavigationCase;
import javax.faces.context.FacesContext;
import java.io.IOException;

/**
 * User: malpay
 * Date: 04.Aðu.2010
 * Time: 10:43:40
 */
public class WallNavigationHandler extends NavigationHandlerImpl {
    private static final String REDIRECT = "redirect:";

    @Override
    public void handleNavigation(FacesContext context, String fromAction, String outcome) {
        if (!StringUtils.isEmpty(outcome)
                && outcome.startsWith(REDIRECT)) {
            try {
                String viewId = outcome.substring(REDIRECT.length());
                String url = context.getApplication().getViewHandler().getRedirectURL(context,viewId,null,false) + ".xhtml";
                context.getExternalContext().redirect(url);
                context.responseComplete();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            super.handleNavigation(context, fromAction, outcome);
        }
    }

}
