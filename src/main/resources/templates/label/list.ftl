<#import "../layout/layout.ftl" as layout>
<@layout.myLayout>
<div class="row">
    <header class="main-box-header clearfix">

        <span style="float: right">
            <a href="/label/add" class="create btn btn-large btn-primary" rel="link">Add</a>
        </span>
    </header>
    <div class="col-sm-3">

    </div>
    <div class="col-sm-6">
        <table class="table" id="customers">
            <thead>
            <tr>
                <th scope="col">Label Name</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
                <#list context.labelList>
                    <#items as label>

                    <tr>
                        <td>
                        <#--${label.getString("_id")}-->
                        ${(label.name)!""}
                        </td>
                        <td>
                            <a href="/label/edit/${(label.id)!id}" rel="link">Edit</a>
                            <#--<a href="http://localhost:8085/modify?labelId=${(label.id)!id}" rel="link">Edit</a>-->

                        </td>
                        <td>
                            <a href="/label/delete/${(label.id)!id}" rel="link">Delete</a>
                            <#--<a href="http://localhost:8085/modify?labelId=${(label.id)!id}" rel="link">Edit</a>-->

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