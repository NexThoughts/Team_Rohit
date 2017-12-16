<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<#macro myLayout>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Presentable</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
    <link rel="stylesheet" href="theme/layout/styles/layout.css" type="text/css" />
    <link rel="stylesheet" href="theme/layout/styles/forms.css" type="text/css" />
    <link rel="stylesheet" href="theme/layout/styles/layout.css" type="text/css" />
    <link rel="stylesheet" href="theme/layout/styles/navi.css" type="text/css" />
    <link rel="stylesheet" href="theme/layout/styles/tables.css" type="text/css" />
    <script type="text/javascript" src="theme/layout/scripts/jquery.min.js"></script>
    <script type="text/javascript" src="theme/layout/scripts/jquery.innerfade.js"></script>
</head>
<body id="top">
<div class="wrapper col1">
<#include "header.ftl">

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

</div>

<!-- ####################################################################################################### -->
<div class="wrapper col3">
    <div id="container">
        <div id="latest">

        </div>
        <div id="content">
            <#nested/>


        </div>

        <div class="clear"></div>
    </div>
</div>
<!-- ####################################################################################################### -->
<#include "footer.ftl">


<!-- ####################################################################################################### -->
<div class="wrapper col5">
    <div id="copyright">
        <p class="fl_left">Copyright &copy; 2014 - All Rights Reserved - <a href="#">Domain Name</a></p>
        <p class="fl_right">Template by <a target="_blank" href="http://www.os-templates.com/" title="Free Website Templates">OS Templates</a></p>
        <br class="clear" />
    </div>
</div>

</body>
</html>
</#macro>