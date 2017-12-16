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

    </div>
    <div class="col-sm-6">
        <table class="table" id="customers">
            <thead>
            <tr>
                <th scope="col">Book Name</th>
                <th scope="col">Book Author</th>
                <th scope="col">Book Publisher</th>
                <th scope="col">Book Price</th>
            </tr>
            </thead>
            <tbody>
            <#list context.books>
                <#items as book>

                <tr>
                    <td>
                    ${(book.name)!name}
                    </td>
                    <td>
                    ${(book.author)!author}
                    </td>
                    <td>
                    ${(book.publisher)!publisher}
                    </td>
                    <td>
                    ${(book.bookPrice)!bookPrice}
                    </td>
                    <td>
                        <a href="http://localhost:8085/modify/editBookById?bookId=${(book.uuid)!uuid}" rel="link">Edit</a>

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
<div class="row">

</div>
</body>
</html>
