package com.liferay.training.portletremover.web;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ProcessAction;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.training.portletremover.service.PortletRemoverService;

/**
 * This component scans for the portlets in the current page in order to be able
 * to remove them, whether they're deployed or not.
 */
@Component(
    immediate = true,
    service = Portlet.class,
    property = {
        "com.liferay.portlet.display-category=category.training",
        "com.liferay.portlet.instanceable=false",
        "javax.portlet.display-name=Portlet Remover",
        "javax.portlet.security-role-ref=power-user,user",
        "javax.portlet.init-param.template-path=/WEB-INF/",
        "javax.portlet.init-param.view-template=/WEB-INF/view.jsp",
        "javax.portlet.resource-bundle=content.Language"
    }
)
public class PortletRemover extends MVCPortlet {

    private static final Log LOG = LogFactoryUtil.getLog(PortletRemover.class);

    private static final String RQ_ATTR_FOUND_PORTLETS = "foundPortlets";
    private static final String RQ_PARAM_PORTLET_ID = "portletId";

    private static final Pattern PATTERN_PORTLET_ID = Pattern.compile("^[a-zA-Z0-9_\\-]+$");

    private PortletRemoverService serviceRef;

    /**
     * Main view
     *
     * @param request The request
     * @param response The response
     */
    @Override
    public void doView(RenderRequest request, RenderResponse response) throws IOException, PortletException {
        Layout currentLayout = (Layout) request.getAttribute(WebKeys.LAYOUT);
        request.setAttribute(RQ_ATTR_FOUND_PORTLETS, this.serviceRef.getPortlets(currentLayout));
        super.doView(request, response);
    }

    /**
     * Remove portlet from the page
     *
     * @param request The request
     * @param response The response
     */
    @ProcessAction(name = "removePortlet")
    public void removePortletAction(ActionRequest request, ActionResponse response) throws IOException {
        Layout currentLayout = (Layout) request.getAttribute(WebKeys.LAYOUT);
        String portletId = ParamUtil.getString(request, RQ_PARAM_PORTLET_ID, null);

        if(portletId != null) {
            Matcher m = PATTERN_PORTLET_ID.matcher(portletId);

            if(m.matches()) {
                try {
                    this.serviceRef.removePortlet(currentLayout, portletId);
                } catch(PortalException e) {
                    LOG.error("Unable to remove portlet", e);
                }
            }
        }

    }

    @Reference
    protected void setServiceRef(PortletRemoverService serviceRef) {
        this.serviceRef = serviceRef;
    }

}
