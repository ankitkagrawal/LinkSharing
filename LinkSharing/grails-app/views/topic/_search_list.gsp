<div class="inbox">
    <table>
        %{--<th>Search results for : <% println keyword %></th>--}%
        <ls:showResource ListType="Search results for : ${keyword}" resourceList="${itemList}" user="${session["user"]}"></ls:showResource>
    </table>

</div>