<#import "../layout/layout.ftl" as layout>
<@layout.myLayout>
<form action="/project/createTask" method="post" class="form-center">
    <div class="form-border">
        <div class="row">
            <div class="col-sm-5">
                <label>${(context.project.name)}</label>
            ${(context.project.id)}

            <#--<input type="text" class="form-control" id="name" name="name" value="${(context.projectName)!"Project"}"/>-->
            <#--<input type="hidden" class="form-control" id="projectId" name="projectId" value="${(context.projectId)!""}"/>-->
            </div>
        </div>
        <div class="row">
            <div class="col-sm-5">
                <label>Task Name</label>
                <input type="text" class="form-control" id="name" name="name"/>
                <input type="hidden" class="form-control" id="projectId" name="projectId"
                       value="${(context.project.id)}"/>
                <button type="submit" class="btn btn-primary" style="margin-top:27%;margin-left:23%;">Submit</button>

            </div>
        </div>

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
</form>

</@layout.myLayout>