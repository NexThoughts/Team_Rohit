<#import "../layout/layout.ftl" as layout>
<@layout.myLayout>
<form action="/project/save" method="post" class="form-center">
    <div class="form-border">
        <div class="row">
            <div class="col-sm-5">
                <label>Project Name</label>
                <input type="text" class="form-control" id="name" name="name"/>
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