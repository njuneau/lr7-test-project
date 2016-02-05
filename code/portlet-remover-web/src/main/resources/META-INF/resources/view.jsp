<%@ include file="init.jsp" %>

<%@ page import="java.util.List" %>

<%
    List<com.liferay.portal.model.Portlet> portlets = (List<com.liferay.portal.model.Portlet>) request.getAttribute("foundPortlets");
%>
<div class="table-responsive">
    <table class="table">
        <thead>
            <tr><th>Portlet Name</th><th>Action</th></tr>
        </thead>
        <tbody>
            <%
            for(com.liferay.portal.model.Portlet p : portlets) {
            %>
            <tr>
                <td><span title="<%= p.getPortletId() %>"><%= p.getDisplayName() %></span></td>
                <td>
                    <form action="<portlet:actionURL name="removePortlet"><portlet:param name="portletId" value="<%= p.getPortletId() %>"/></portlet:actionURL>"
                        method="POST">
                        <input class="btn btn-danger" type="submit" value="Remove" />
                    </form>
                </td>
            </tr>
            <%
            }
            %>
        </tbody>
    </table>
</div>