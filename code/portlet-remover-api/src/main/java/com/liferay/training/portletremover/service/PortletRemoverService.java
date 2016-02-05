package com.liferay.training.portletremover.service;

import java.util.List;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.Portlet;

/**
 * This service is used to detect the portlets in a given layout
 */
public interface PortletRemoverService {

    public List<Portlet> getPortlets(Layout layout);

    public boolean removePortlet(Layout layout, String portletId) throws PortalException;

}