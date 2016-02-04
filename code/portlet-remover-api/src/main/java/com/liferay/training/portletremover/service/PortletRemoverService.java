package com.liferay.training.portletremover.service;

import java.util.List;

import com.liferay.portal.model.Layout;
import com.liferay.portal.model.Portlet;

/**
 * This service is used to detect the portlets in a given layout
 */
public interface PortletRemoverService {

    public List<Portlet> getPortlets(Layout l);

}