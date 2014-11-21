<div class="inbox">
    <table>
        %{--<th>Posts : <% println topic?.name %></th>--}%
        <ls:showResource ListType="Posts : ${topic?.name}" resourceList="${itemList}" user="${session["user"]}"></ls:showResource>
    </table>

</div>