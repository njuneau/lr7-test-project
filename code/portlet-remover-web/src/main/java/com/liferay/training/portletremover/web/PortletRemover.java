package com.liferay.training.portletremover.web;
import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
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
        "javax.portlet.init-param.template-path=/",
        "javax.portlet.init-param.view-template=/view.jsp",
        "javax.portlet.resource-bundle=content.Language"
    }
)
public class PortletRemover extends MVCPortlet {

    private PortletRemoverService serviceRef;

    @Override
    public void doView(RenderRequest request, RenderResponse response) throws IOException, PortletException {
        String result = this.serviceRef.testService();
        request.setAttribute("result", result);
        super.doView(request, response);
    }

    @Reference
    protected void setServiceRef(PortletRemoverService serviceRef) {
        this.serviceRef = serviceRef;
    }

}