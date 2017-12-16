<#import "../layout/layout.ftl" as layout>
<@layout.myLayout>
<div class="row">
    <div class="col-sm-3">

    </div>
    <div class="col-sm-6">
        <table class="table" id="customers">
            <thead>
            <tr>
                <th scope="col">task Name</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
                <#list context.taskList>
                    <#items as task>

                    <tr>
                        <td>
                        <#--${label.getString("_id")}-->
                        ${(task.name)!""}
                        </td>
                        <td>
                            <a href="label/edit/taskId=${(task._id)!id}" rel="link">Edit</a>

                        </td>
                    </tr>
                    </#items>
                </#list>
            </tbody>
        </table>
    </div>
    <div class="col-sm-3">

    </div>
</div>
</@layout.myLayout>