<#import "../layout/layout.ftl" as layout>
<@layout.myLayout>
<form action="/task/update" method="post" class="form-center">
    <div class="form-border">
        <div class="row">
            <div class="col-sm-5">
                <label>Task Name</label>
                <input type="text" class="form-control" id="name" name="name" value="${(context.taskName)!"Task"}"/>
                <input type="hidden" class="form-control" id="taskId" name="taskId" value="${(context.taskId)!""}"/>
            </div>
        </div>
        <div class="row">
            <div class="button">
                <button type="submit" class="btn btn-primary" style="margin-top:27%;margin-left:23%;">Submit</button>
            </div>
        </div>
    </div>
</form>

</@layout.myLayout>