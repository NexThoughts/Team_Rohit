<#import "../layout/layout.ftl" as layout>
<@layout.myLayout>
<div class="row">
    <header class="main-box-header clearfix">

        <span style="float: right">
            <a href="/project/add" class="create btn btn-large btn-primary" rel="link">Add</a>
        </span>
    </header>
    <div class="col-sm-3">

    </div>
    <div class="col-sm-6">
        <table class="table" id="customers">
            <thead>
            <tr>
                <th scope="col">Project Name</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
                <#list context.projectList>
                    <#items as project>

                    <tr>
                        <td>
                        <#--${project.getString("_id")}-->
                        ${(project.name)!""}
                        </td>
                        <td>
                            <a href="/project/edit/${(project.id)!id}" rel="link">Edit</a>
                        <#--<a href="http://localhost:8085/modify?projectId=${(project.id)!id}" rel="link">Edit</a>-->

                        </td>
                        <td>
                            <a href="/project/delete/${(project.id)!id}" rel="link">Delete</a>
                        <#--<a href="http://localhost:8085/modify?projectId=${(project.id)!id}" rel="link">Edit</a>-->

                        </td>
                        <td>
                            <a href="/project/manage/${(project.id)!id}" class="create btn btn-large btn-primary" rel="link">Manage</a>

                        <#--<a href="http://localhost:8085/modify?projectId=${(project.id)!id}" rel="link">Edit</a>-->

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