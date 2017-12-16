
<#macro myLayout>

<html>
<head><title>Abhinav</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        .form-center {
            margin-left: 30%;
            margin-top: 5%;
        }

        #customers {
            font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        #customers td, #customers th {
            border: 1px solid #ddd;
            padding: 8px;
        }

        #customers tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        #customers tr:hover {
            background-color: #ddd;
        }

        #customers th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: left;
            background-color: #4CAF50;
            color: white;
        }

        .bottom-paddding {
            padding-bottom: 1%;
        }

        .search-form {
            margin-left: 51%;
            margin-top: 25px;
        }
    </style>
</head>
<body>
<div class="row">
<#include "header.ftl">
</div>
<div class="row">
    <div class="col-sm-3">

    </div>
    <div class="col-sm-6">
    <#nested>
    </div>
    <div class="col-sm-3">

    </div>
</div>
<div class="row">
<#include "footer.ftl">

</div>
</body>
</html>
</#macro>
