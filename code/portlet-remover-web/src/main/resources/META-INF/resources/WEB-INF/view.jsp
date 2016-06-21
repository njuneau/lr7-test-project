<%@ include file="init.jsp" %>

<c:set var="namespace"><portlet:namespace /></c:set>

<div class="table-responsive">
    <table class="table">
        <thead>
            <tr><th>Portlet Name</th><th>Action</th></tr>
        </thead>
        <tbody>
            <c:forEach items="${foundPortlets}" var="p">
                <portlet:actionURL name="removePortlet" var="actionUrl" />
                <tr>
                    <td><span title="${p.portletId}">${p.displayName}</span></td>
                    <td>
                        <form action="${actionUrl}" method="POST">
                            <input type="hidden" name="${namespace}portletId" value="${p.portletId}">
                            <input class="btn btn-danger" type="submit" value="Remove" />
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>