<div class="inbox">
    <table>
        <th>Inbox</th>
        <% itemList.each { resource -> %>
        <tr><td>
            <% println "Topic Name - ${resource.topic.name} Title - ${resource.title}"
               println "Description - ${resource.description}" %>
            <br/>
            <% println "Download----View full site----Mark as read----View Post" %>
        </td></tr>
        <% } %>
    </table>

</div>