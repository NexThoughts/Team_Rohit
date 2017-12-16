package com.todo.app.server

import com.bookazon.models.Book
import io.vertx.core.AbstractVerticle
import io.vertx.core.http.HttpHeaders
import io.vertx.core.json.JsonArray
import io.vertx.core.json.JsonObject
import io.vertx.ext.auth.mongo.MongoAuth
import io.vertx.ext.mongo.MongoClient
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.handler.BodyHandler
import io.vertx.ext.web.templ.FreeMarkerTemplateEngine

class MasterVerticle extends AbstractVerticle {
    FreeMarkerTemplateEngine engine = null
    def mongoClient = null
    private final static String DEFAULT_COLLECTION = "books"

    void start() {


        JsonObject mongoClientConfig = new JsonObject()
        mongoClientConfig.put("connection_string", "mongodb://localhost:27017")
        mongoClientConfig.put("db_name", "Store")


        mongoClient = MongoClient.createShared(vertx, mongoClientConfig)
        engine = FreeMarkerTemplateEngine.create()
        Router router = Router.router(vertx)

        router.route().handler(BodyHandler.create())

        router.get("/").handler({ ctx ->
            ctx.put("title", "Bookazon")
            ctx.put("name", "Books List")
            com.bookazon.models.Book book = null
            List<com.bookazon.models.Book> bookList = []
            JsonArray array = new JsonArray()
            JsonObject query = new JsonObject()
            mongoClient.find(DEFAULT_COLLECTION, query, { response ->
                if (response.succeeded()) {
                    for (JsonObject json : response.result()) {
                        System.out.println(json.encodePrettily());
                        String name = json.getString("name")
                        String author = json.getString("author")
                        String publisher = json.getString("publisher")
                        String bookPrice = json.getString("bookPrice")
                        String _id = json.getString("_id")
                        book = new com.bookazon.models.Book(
                                name: name,
                                author: author,
                                publisher: publisher,
                                bookPrice: bookPrice,
                                uuid: _id
                        )
                        bookList.add(book)
                    }
                    ctx.put("books", bookList)
                    engine.render(ctx, "templates/landingpage.ftl", { res ->
                        if (res.succeeded()) {
                            ctx.response().putHeader(HttpHeaders.CONTENT_TYPE, "text/html").end(res.result())
                        } else {
                            ctx.fail(res.cause())
                        }
                    })
                }
            })
        })
        router.get("/modify").handler(this.&addBooks)
        router.get("/users/login").handler(this.&doLogin)
        router.get("/users/signup").handler(this.&doSignUp)
        router.post("/modify/saveBook").handler(this.&saveBook)
        router.post("/modify/editBook").handler(this.&editBook)
        router.get("/modify/editBookById").handler(this.&editBookById)
        router.post("/modify/deleteBook").handler(this.&deleteBook)
        vertx.createHttpServer().requestHandler(router.&accept).listen(8085)

    }

    void doSignUp(RoutingContext ctx) {
        JsonObject config = new JsonObject()
        MongoAuth mongoAuth = MongoAuth.create(mongoClient, config)
        String username = ctx.request().getFormAttribute("username")
        String password = ctx.request().getFormAttribute("password")
        mongoAuth.insertUser(username, password, null, null, { result ->
            if (result.succeeded()) {
                println "================Signup done===============" + result.result()
            } else {
                println "===========User signup not done==========="
            }
        })
    }

    void doLogin(RoutingContext ctx) {
        MongoAuth authProvider = MongoAuth.create(mongoClient, new JsonObject())
        JsonObject authInfo = new JsonObject()
        authInfo.put("username", ctx.request().getFormAttribute("username"))
        authInfo.put("password", ctx.request().getFormAttribute("password"))
        authProvider.authenticate(authInfo, { res ->
            if (res.succeeded()) {
                println "========================User Signup================================"
                ctx.response().putHeader("location", "/").setStatusCode(302).end();
            } else {
                println "===============Authentication not provided====================="
            }
        })
    }

    void addBooks(RoutingContext ctx) {
        println "===============In the add book method============"
        ctx.put("title", "Bookazon")
        ctx.put("name", "Books List")

        engine.render(ctx, "templates/addBookPage.ftl", { res ->
            if (res.succeeded()) {
                println "===========Going  to render form=================e"
                ctx.response().putHeader(HttpHeaders.CONTENT_TYPE, "text/html").end(res.result())
            } else {
                ctx.fail(res.cause())
            }
        })
    }

    void saveBook(RoutingContext ctx) {
        println "========Going to save book============="
        JsonObject bookDocument = new JsonObject().put("name", ctx.request().getFormAttribute("bookName"))
        bookDocument.put("author", ctx.request().getFormAttribute("author"))
        bookDocument.put("publisher", ctx.request().getFormAttribute("publisher"))
        bookDocument.put("bookPrice", ctx.request().getFormAttribute("bookPrice"))
        bookDocument.put("uuid", UUID.randomUUID().toString())
        mongoClient.save("books", bookDocument, { res ->
            if (res.succeeded()) {
                String id = res.result();
                System.out.println("Saved book with id " + id);
                ctx.response().putHeader("location", "/").setStatusCode(302).end();
            } else {
                res.cause().printStackTrace();
            }
        });
    }

    void editBook(RoutingContext ctx) {
        println "==========Editing the new Book===================="

        String bookID = ctx.request().getFormAttribute("bookID")
        String fieldName = ctx.request().getFormAttribute("fieldName")
        String fieldValue = ctx.request().getFormAttribute("fieldValue")
        println bookID + "   " + fieldName + "    " + fieldValue
        JsonObject query = new JsonObject().put("_id", bookID)
        JsonObject update = new JsonObject().put("$set", new JsonObject().put(fieldName, fieldValue));
        mongoClient.update("books", query, update, { res ->
            if (res.succeeded()) {
                System.out.println("Book updated !");
                ctx.response().putHeader("location", "/").setStatusCode(302).end();
            } else {
                res.cause().printStackTrace();
            }

        });
    }

    void editBookById(RoutingContext ctx) {
        println "==========Editing the new Book===================="
        String bookID = ctx.request().getParam("bookId")
        String fieldName = ctx.request().getFormAttribute("fieldName")
        String fieldValue = ctx.request().getFormAttribute("fieldValue")
        println "bookID" + bookID
        JsonObject query = new JsonObject().put("_id", bookID)


        mongoClient.find("books", query, { res ->
            if (res.succeeded()) {

                for (JsonObject json : res.result()) {

                    System.out.println(json.encodePrettily());

                }
                ctx.response().putHeader("location", "/").setStatusCode(302).end();


            } else {

                res.cause().printStackTrace();

            }

        })
    }

    void deleteBook(RoutingContext ctx) {
        println "==========Editing the new Book===================="
        String bookID = ctx.request().getFormAttribute("bookID")
        JsonObject query = new JsonObject().put("_id", bookID)
        mongoClient.remove("books", query, { res ->
            if (res.succeeded()) {
                System.out.println("Book deleted !!!!");
                ctx.response().putHeader("location", "/").setStatusCode(302).end();
            } else {

                res.cause().printStackTrace();
            }

        });
    }

    static String generateTable(JsonArray array) {
        String data = getTableHeader()
        array.each { JsonObject object ->
            println(object)
            data += """<tr>
        <td>${object.getString("name")}</td>
        <td>${object.getString("author")}</td>
        <td>${object.getString("publisher")}</td>
        <td>${object.getString("bookPrice")}</td>
    </tr>"""
        }

        data += getTableFooter()
        return data
    }

    static String getTableHeader() {
        return """<table  border=1>
    <thead>
    <tr>
        <td>Book </td>
        <td>Author </td>
        <td>Publisher </td>
        <td>Price</td>
    </tr>
    </thead>
    <tbody>"""
    }

    static String getTableFooter() {
        """ </tbody>
</table>"""
    }
}