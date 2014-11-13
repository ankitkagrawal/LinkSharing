<div class="profile">
    <div class="profileImage">
        %{--<img src="<% 1 %>" width="100" height="100" >--}%
        <g:img uri="${grailsApplication.config.user.photo.location.profile+user.userPhoto}" width="100" height="100" ></g:img>
    </div>
    <div class="profileInfo">
        <% println user.firstName+" "+user.lastName %>
        <br/>
        @ankit
        <br/><br/>
        Subscription Topic
        <br/>
        40  20
    </div>

</div>