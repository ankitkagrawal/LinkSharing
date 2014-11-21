<%@ page import="linksharing.User" %>
<div class="profile">
    %{--<div class="profileImage">
        <g:img uri="${grailsApplication.config.user.photo.location.profile+user.userPhoto}" width="100" height="100" ></g:img>
    </div>
    <div class="profileInfo">
        <% println user.firstName+" "+user.lastName %>
        <br/>
        <% println "@${user?.firstName}" %>
    <br/><br/>
   Subscription Topic
    <br/>
  2 10 --}%%{--${user?.topics?.size()}--${user?.resources?.size()}--}%%{--
    </div>--}%
       <%
           User userNew = User.findById(user?.id)
       %>
       <table class="topicTable">
           <ls:showUsersList  usersList="${[userNew]}" ></ls:showUsersList>
       </table>


</div>



