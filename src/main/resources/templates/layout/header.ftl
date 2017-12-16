<#--
<div id="header">
    <div id="logo">
        <h1><a href="index.html">Presentable</a></h1>
        <p>Free Website Template</p>
    </div>
    <div id="search">
        <form action="#" method="post">
            <fieldset>
                <legend>Site Search</legend>
                <input type="text" value="Search the site&hellip;"  onfocus="this.value=(this.value=='Search the site&hellip;')? '' : this.value ;" />
                <input type="submit" name="go" id="go" value="GO" />
            </fieldset>
        </form>
    </div>
    <div id="topnav">
        <ul>
            <li class="last"><a href="#">A Long Link Text</a></li>
            <li><a href="#">DropDown</a>
                <ul>
                    <li><a href="#">Link 1</a></li>
                    <li><a href="#">Link 2</a></li>
                    <li><a href="#">Link 3</a></li>
                </ul>
            </li>
            <li><a href="pages/full-width.html">Full Width</a></li>
            <li><a href="pages/style-demo.html">Style Demo</a></li>
            <li class="active"><a href="index.html">Home</a></li>
        </ul>
    </div>
    <br class="clear" />
</div>
-->
<nav class="navbar navbar-inverse bottom-paddding">
    <div class="container-fluid">
        <div class="navbar-header">
        <#--                <a class="navbar-brand" href="#">
                    <img src="/images/bookazon.png"/>
                </a>-->
        </div>
        <form class="navbar-form navbar-left search-form" action="/action_page.php">
            <div class="form-group">
                <input type="text" class="form-control" placeholder="Search">
            </div>
            <button type="submit" class="btn btn-default btn-sm">
                <span class="glyphicon glyphicon-search"></span> Search
            </button>
        </form>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
            <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
        </ul>
    </div>
</nav>
