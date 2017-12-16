<#import "../layout/layout.ftl" as layout>
<@layout.myLayout>
<div class="row">
    <header class="main-box-header clearfix">

        <span style="float: right">
            <a href="/task/add" class="create btn btn-large btn-primary" rel="link">Add</a>
        </span>
    </header>
    <div class="col-sm-3">

    </div>
    <div class="col-sm-6">
        <table class="table" id="customers">
            <thead>
            <tr>
                <th scope="col">Task Name</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
                <#list context.taskList>
                    <#items as task>

                    <tr>
                        <td>
                        <#--${task.getString("_id")}-->
                        ${(task.name)!""}
                        </td>
                        <td>
                            <a href="/task/edit/${(task.id)!id}" rel="link">Edit</a>

                        </td>
                        <td>
                            <a href="/task/delete/${(task.id)!id}" rel="link">Delete</a>

                        </td>
                        <td>
                            <a href="/task/manage/${(task.id)!id}" class="create btn btn-large btn-primary" rel="link">Manage</a>

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