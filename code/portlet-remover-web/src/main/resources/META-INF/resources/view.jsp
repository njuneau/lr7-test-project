<%@ include file="init.jsp" %>

<div class="table-responsive">
    <table class="table">
        <thead>
            <tr><th>Portlet ID</th><th>Portlet Name</th></tr>
        </thead>
        <tbody>
            <%
            java.util.List<com.liferay.portal.model.Portlet> portlets = (java.util.List<com.liferay.portal.model.Portlet>) request.getAttribute("foundPortlets");
            %>
            <%
            for(com.liferay.portal.model.Portlet p : portlets) {
            %>
                <tr><td><%= p.getPortletId() %></td><td><%= p.getPortletName() %></td></tr>
            <%
            }
            %>
        </tbody>
    </table>
</div>