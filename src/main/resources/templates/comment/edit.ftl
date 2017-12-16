<#import "../layout/layout.ftl" as layout>
<@layout.myLayout>
<form action="/comment/update" method="post" class="form-center">
    <div class="form-border">
        <div class="row">
            <div class="col-sm-5">
                <label>Comment Name</label>
                <input type="text" class="form-control" id="name" name="name" value="${(context.description)!"Label"}"/>
                <input type="hidden" class="form-control" id="commentId" name="commentId" value="${(context.id)!""}"/>

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