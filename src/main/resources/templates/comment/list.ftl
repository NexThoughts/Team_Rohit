<#import "../layout/layout.ftl" as layout>
<@layout.myLayout>
<div class="row">
    <div class="col-sm-3">

    </div>
    <div class="col-sm-6">
        <table class="table" id="customers">
            <thead>
            <tr>
                <th scope="col">Comment Name</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
                <#list context.commentList>
                    <#items as comment>

                    <tr>
                        <td>
                        <#--${label.getString("_id")}-->
                        ${(comment.name)!"qqqqqq"}
                        </td>
                        <td>
                            <a href="/comment/edit/${(comment.id)!""}" rel="link">Edit</a>

                        </td>
                    </tr>
                    </#items>
                </#list>
            </tbody>
        </table>
    </div>
    <div class="col-sm-3">
        <#if context.users??>
            <#list context.users>
                <select id="userList">

                    <#items as user>
                        <option value="${(user.id)!""}">"${(user.username)!""}"</option>
                    </#items>
                </select>

            </#list>
        <#else>

            NO USERS
        </#if>
    </div>
</div>
</@layout.myLayout>