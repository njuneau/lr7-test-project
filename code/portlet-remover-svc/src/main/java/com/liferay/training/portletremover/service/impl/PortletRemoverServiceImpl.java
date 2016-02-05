package com.liferay.training.portletremover.service.impl;

import java.util.Collections;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutType;
import com.liferay.portal.model.LayoutTypePortlet;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.User;
import com.liferay.portal.service.CompanyService;
import com.liferay.portal.service.LayoutService;
import com.liferay.training.portletremover.service.PortletRemoverService;

/**
 * Default implementation for the portlet remover service
 */
@Component(
    immediate = true,
    service = PortletRemoverService.class
)
public class PortletRemoverServiceImpl implements PortletRemoverService {

    private CompanyService companyService;
    private LayoutService layoutService;

    @Override
    public List<Portlet> getPortlets(Layout l) {
        List<Portlet> portlets;
        LayoutType layoutTypeBase = l.getLayoutType();

        // Obtain a list of portlets only if we are on a Portlet layout
        if(layoutTypeBase instanceof LayoutTypePortlet) {
            LayoutTypePortlet layoutType = (LayoutTypePortlet) layoutTypeBase;
            portlets = layoutType.getAllPortlets();
        } else {
            portlets = Collections.emptyList();
        }

        return portlets;
    }

    @Override
    public boolean removePortlet(Layout layout, String portletRemoveId) throws PortalException {
        boolean removed = false;
        List<Portlet> portlets = this.getPortlets(layout);
        for(Portlet p : portlets) {
            LayoutTypePortlet layoutType = (LayoutTypePortlet) layout.getLayoutType();
            String portletId = p.getPortletId();
            Company company = this.companyService.getCompanyById(layout.getCompanyId());
            User defaultUser = company.getDefaultUser();
            if(portletId != null && portletId.equals(portletRemoveId)) {
                layoutType.removePortletId(defaultUser.getUserId(), portletId);
                this.layoutService.updateLayout(
                    layout.getGroupId(),
                    layout.isPrivateLayout(),
                    layout.getLayoutId(),
                    layout.getTypeSettings()
                );
                removed = true;
                break;
            }
        }
        return removed;
    }

    @Reference
    private void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Reference
    private void setLayoutService(LayoutService layoutService) {
        this.layoutService = layoutService;
    }

}
