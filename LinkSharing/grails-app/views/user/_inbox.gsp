<div class="inbox">
    <table>
        <th>Inbox</th>
        <% itemList.each { resource -> %>
        <tr><td>
            <% println "Topic Name - ${resource.topic.name} Title - ${resource.title}"
               println "Description - ${resource.description}" %>
            <br/>
            <% println "Download----View full site----" %>

            <g:link controller="readingItem" action="markAsRead" params="${[resource:resource.id,user:session['user'].id]}">
                Mark as read</g:link>

            <% println "----View Post" %>
        </td></tr>
        <% } %>
    </table>

</div>