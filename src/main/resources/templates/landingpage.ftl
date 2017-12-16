<html>
<head><title>${context.title}</title>
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
            margin-top:25px;
        }

        .rcorners2 {
            border-radius: 25px;
            border: 2px solid #73AD21;
            padding: 20px;
            width: 200px;
            height: 150px;
        }
        .signupForm{
           display: none;
        }

    </style>
</head>
<body>
<div class="row">
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
</div>
<div class="row">
    <div class="col-sm-3">
        <div class="col-sm-6">
            <button type="button" class="btn btn-primary" onclick="showForm('signupFOrm');">Login</button>
        </div>
        <div class="col-sm-6">
            <button type="button" class="btn btn-primary" onclick="showForm('loginFOrm');">Signup</button>
        </div>
    </div>
    <div class="col-sm-6" id="signupFOrm">
        <form action="/login" method="post" class="form-center loginFOrm">
            <div class="form-border">
                <div class="row">
                    <div class="col-sm-12">
                        <label>Username</label>
                        <input type="text" class="form-control" id="username" name="username"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <label>Password</label>
                        <input type="text" class="form-control" id="password" name="password"/>
                    </div>
                </div>
                <div class="row">
                    <div class="button col-sm-10">
                        <button type="submit" class="btn btn-primary" style="margin-top:4%;margin-left:4%;">Login</button>
                    </div>
                </div>
            </div>
        </form>


        <#--Signup form-->
        <form action="/signup" method="post" class="form-center">
            <div class="form-border">
                <div class="row">
                    <div class="col-sm-12">
                        <label>Username</label>
                        <input type="text" class="form-control" id="username" name="username"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <label>Password</label>
                        <input type="text" class="form-control" id="password" name="password"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <label>Full Name</label>
                        <input type="text" class="form-control" id="name" name="name"/>
                    </div>
                </div>
                <div class="row">
                    <div class="button col-sm-12">
                        <button type="submit" class="btn btn-primary" style="margin-top:4%;margin-left:14%;">Login</button>
                    </div>
                </div>
            </div>
        </form>

    </div>
    <div class="col-sm-3">

    </div>
</div>
<div class="row">

</div>
</body>
<script type="text/javascript">
    function showForm(form_id)
    {
        console.log(form_id)
        $("#"+form_id).show();
    }
</script>
</html>
