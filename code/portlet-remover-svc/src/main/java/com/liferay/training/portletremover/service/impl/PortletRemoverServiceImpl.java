package com.liferay.training.portletremover.service.impl;

import java.util.List;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.model.Layout;
import com.liferay.portal.model.Portlet;
import com.liferay.training.portletremover.service.PortletRemoverService;

/**
 * Default implementation for the portlet remover service
 */
@Component(
    immediate = true,
    service = PortletRemoverService.class
)
public class PortletRemoverServiceImpl implements PortletRemoverService {

    @Override
    public List<Portlet> getPortlets(Layout l) {
        return l.getEmbeddedPortlets();
    }

}
