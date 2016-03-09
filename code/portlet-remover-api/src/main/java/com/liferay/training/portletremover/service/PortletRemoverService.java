package com.liferay.training.portletremover.service;

import java.util.List;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.Portlet;

/**
 * This service is used to detect the portlets in a given layout and remove them
 * if needed.
 */
public interface PortletRemoverService {

    /**
     * Returns a list of portlets that are installed on the given layout
     *
     * @param layout The layout on which to detect portlets
     * @return A list of portlets that are installed on the given layout
     */
    public List<Portlet> getPortlets(Layout layout);

    /**
     * Removes the given portlet from the given layout
     *
     * @param layout The layout on which to remove the portlet
     * @param portletId The portlet ID to remove
     * @return True if the portlet was removed, false otherwize
     * @throws PortalException Thrown if something goes wrong during deletion
     */
    public boolean removePortlet(Layout layout, String portletId) throws PortalException;

}