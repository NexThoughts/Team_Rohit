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
    NO USERS
    NO USERS
    NO USERS
</#if>
</div>

<script type="text/javascript">
    document.getElementById("userList").onchange = function()
    {
        console.log(this.value);
        var uuid = this.value
        console.log(uuid)
        $.ajax({
            url: "user/assignProject?userId="+uuid,
            type: 'GET',
//            dataType: 'json',
            success: function (data) {
                console.log("2@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")
                console.log("2@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")
                console.log(data);

                $("#loanActivityTable").html(data);
            },
            error: function (data) {
                setTimeout(function () {
                    $.notify("${message(code:'default.server.error')}", "error");
                }, 600)

            }
        })
    }

</script>
