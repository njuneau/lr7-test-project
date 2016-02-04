package com.liferay.training.portletremover.web;
import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

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

}
