package com.todo.app.server.comment

import com.todo.app.model.Comment
import com.todo.app.model.Label
import com.todo.app.util.BaseUtil
import io.vertx.core.AbstractVerticle
import io.vertx.core.http.HttpHeaders
import io.vertx.core.http.HttpServerResponse
import io.vertx.core.json.JsonArray
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.handler.BodyHandler
import io.vertx.ext.web.templ.FreeMarkerTemplateEngine

class CommentVerticle extends AbstractVerticle {
    private static final MAIL_CLIENT = "@gmail.com"
    private static final DEFAULT_PASSWORD = "user_"
    JsonObject authInfo = null
    private static mongoAuth = BaseUtil.mongoAuth
    FreeMarkerTemplateEngine engine = null

    def mongoClient = BaseUtil.mongoClient

    public void start(){
        println "===================In the bootstrap================="
        mongoClient = BaseUtil.mongoClient
        Router router = BaseUtil.router
        engine = FreeMarkerTemplateEngine.create()

        router.route().handler(BodyHandler.create())

        router.post("/comment/save").handler(this.&saveComment)
        router.get("/comment/list").handler(this.&list)
        router.get("/comment/edit/:commentID").handler(this.&edit)
        router.post("/comment/update").handler(this.&update)
        router.get("/comment/add").handler(this.&add)

        vertx.createHttpServer().requestHandler(router.&accept).listen(8086)

    }

    void list(RoutingContext ctx) {
        ctx.put("title", "Comment")
        ctx.put("name", "Comment List")
        JsonArray array = new JsonArray()
        List<Comment> commentList = []
        JsonObject query = new JsonObject()
        mongoClient.find(BaseUtil.COMMENT_COLLECTION, query, { response ->
            if (response.succeeded()) {
                for (JsonObject comment : response.result()) {
                    commentList.add(new Comment(comment))
                }
                ctx.put("commentList", commentList)
                engine.render(ctx, "templates/comment/list.ftl", { res ->
                    if (res.succeeded()) {
                        ctx.response().putHeader(HttpHeaders.CONTENT_TYPE, "text/html").end(res.result())
                    } else {
                        ctx.fail(res.cause())
                    }
                })
            }
        })

    }


    public void saveComment(RoutingContext ctx) {
        println "========Going to save book============="
        println "asdsadasdasdasdsadasd" + ctx.request().getParam("description")
        println "QQQQQQQQQQQQQQQQQQQQQQQ" + ctx.request().getFormAttribute("description")
        JsonObject commentDocument = new JsonObject().put("description", ctx.request().getFormAttribute("description"))

        mongoClient.save(BaseUtil.COMMENT_COLLECTION, commentDocument, { res ->
            if (res.succeeded()) {
                String id = res.result();
                System.out.println("Saved book with id " + id);
                ctx.response().putHeader("location", "/comment/list").setStatusCode(302).end();
            } else {
                res.cause().printStackTrace();
            }
        });
    }

    void update(RoutingContext ctx) {
        println "==========Editing the Label====================" + ctx.request().getParam("commentId")
        String labelId = ctx.request().getParam("commentId")
        JsonObject query = new JsonObject().put("_id", labelId)
        mongoClient.find(BaseUtil.COMMENT_COLLECTION, query, { res ->
            if (res.succeeded()) {
                for (JsonObject json : res.result()) {
                    println "json  " + json
                    Label label = new Label(json)
                    ctx.put("label", label)
                }


            } else {

                res.cause().printStackTrace();

            }

        })

        engine.render(ctx, "templates/comment/edit", { res ->
            if (res.succeeded()) {
                ctx.response().putHeader(HttpHeaders.CONTENT_TYPE, "text/html").end(res.result())
            } else {
                ctx.fail(res.cause())
            }
        })
    }



    void add(RoutingContext ctx) {
        JsonObject query = new JsonObject()
        mongoClient.find(BaseUtil.COMMENT_COLLECTION, query, { response ->
            if (response.succeeded()) {
                engine.render(ctx, "templates/comment/add.ftl", { res ->
                    if (res.succeeded()) {
                        ctx.response().putHeader(HttpHeaders.CONTENT_TYPE, "text/html").end(res.result())
                    } else {
                        ctx.fail(res.cause())
                    }
                })
            }
        })


    }

    void edit(RoutingContext ctx) {
        String commentID = ctx.request().getParam("commentID")
        ctx.put("title", "Book Info")
        ctx.put("name", "Book Info")
        JsonObject query = new JsonObject()?.put("_id", commentID)

        List<Comment> comments = []

        mongoClient.find(BaseUtil.COMMENT_COLLECTION, query, { res ->
            if (res.succeeded()) {
                res?.result()?.each {
                    Comment comment = new Comment(it)
                    comments.add(comment)

                    ctx.put("description", comment?.description)
                    ctx.put("id", comment?.id)

                }
                engine.render(ctx, "templates/comment/edit.ftl", { engres ->
                    if (res.succeeded()) {


                        ctx.response().putHeader(HttpHeaders.CONTENT_TYPE, "text/html").end(engres.result())

                    } else {
                        ctx.fail(engres.cause())
                    }
                })
            } else {
                res.cause().printStackTrace();

            }
        })

    }

    public String randomIdentifier() {
        final Set<String> identifiers = new HashSet<String>();
        final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final java.util.Random rand = new java.util.Random();
        StringBuilder builder = new StringBuilder();
        while (builder.toString().length() == 0) {
            int length = rand.nextInt(5) + 5;
            for (int i = 0; i < length; i++) {
                builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
            }
            if (identifiers.contains(builder.toString())) {
                builder = new StringBuilder();
            }
        }
        return builder.toString();
    }
}
