<div class="profile">
    <div class="profileImage">
        <g:img uri="${grailsApplication.config.photo.location.default}" width="80" height="80" ></g:img>
    </div>
    <div class="profileInfo">
        <% println "${topic.name}"%>
        <br>
        <% println "@${topic.name}" %>
        <br/><br/>
        Subscription Topic
        <br/>
        <% println "${topic.subscriptions?.size()?:0}  ${topic.resources?.size()?:0}"%>
    </div>

</div>