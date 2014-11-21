%{--<table class="topicTable">
    <th>Subscribed Topics</th>--}%
    <ls:showTopics topicType="Subscribed Topics" topicList="${subscribedTopicList}"></ls:showTopics>



    %{--<% subscribedTopicList.each { subTopic -> %>
    <tr><td>
        <a href="" class="topicName"> <% println subTopic.name    %></a><br>
        <a class="topicDetail"><% println "Subscriptions Post"%>
            <br>
            <% println subTopic.subscripstions.size()+"   "+subTopic.resources.size()    %></a>
    </td></tr>

    <% } %>--}%
%{--</table>--}%