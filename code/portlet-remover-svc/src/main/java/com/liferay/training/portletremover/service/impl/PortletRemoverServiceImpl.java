package com.liferay.training.portletremover.service.impl;

import org.osgi.service.component.annotations.Component;

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
    public String testService() {
        return "TEST";
    }

}
