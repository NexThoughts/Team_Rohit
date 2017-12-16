package com.todo.app.server.comment

import com.todo.app.model.Comment
import com.todo.app.model.Comment
import com.todo.app.util.BaseUtil
import io.vertx.core.AbstractVerticle
import io.vertx.core.http.HttpHeaders
import io.vertx.core.json.JsonArray
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.templ.FreeMarkerTemplateEngine


class CommentVerticle extends AbstractVerticle{
    private static final MAIL_CLIENT = "@gmail.com"
    private static final DEFAULT_PASSWORD = "user_"
    JsonObject authInfo = null
    private static  mongoAuth = BaseUtil.mongoAuth

    def mongoClient = BaseUtil.mongoClient

    public void start(){
        println "===================In the bootstrap================="
        mongoClient=BaseUtil.mongoClient
        Router router=BaseUtil.router
        router.get("/comment/:commentID").handler(this.&getComment)
        router.post("/comment/save").handler(this.&saveComment)
        router.post("/comment/save").handler(this.&saveComment)
//        router.get("/books/delete/:bookID").handler(this.&handleDeleteUser)
//        router.get("/books/edit/:bookID").handler(this.&handleEditUser)

    }

    void list(RoutingContext ctx) {
        ctx.put("title", "Comment")
        ctx.put("name", "Comment List")
        JsonArray array = new JsonArray()
        List<Comment> labelList = []
        JsonObject query = new JsonObject()
        mongoClient.find(BaseUtil.COMMENT_COLLECTION, query, { response ->
            if (response.succeeded()) {
                for (JsonObject label : response.result()) {
                    labelList.add(new Comment(label))
                }
                ctx.put("labelList", labelList)
                engine.render(ctx, "templates/label/list", { res ->
                    if (res.succeeded()) {
                        ctx.response().putHeader(HttpHeaders.CONTENT_TYPE, "text/html").end(res.result())
                    } else {
                        ctx.fail(res.cause())
                    }
                })
            }
        })

    }
    public void getComment(RoutingContext ctx){
        JsonObject commentDocument = new JsonObject().put("comment",ctx.request().getParam("commentID"))
        commentDocument.put("task", ctx.request().getFormAttribute("taskID"))
        mongoClient.find(BaseUtil.COMMENT_COLLECTION, commentDocument, { res ->
            if (res.succeeded()) {
                JsonObject jsonObject=res.result();
                Comment comment=new Comment(jsonObject)
                System.out.println("Comment uuid is " + comment?.description);
                ctx.response().putHeader("location", "/").setStatusCode(302).end();
            } else {
                res.cause().printStackTrace();
            }
        });
    }


    public void saveComment(RoutingContext ctx){
        println "========Going to save book============="
        JsonObject commentDocument = new JsonObject().put("name", ctx.request().getFormAttribute("name"))
        commentDocument.put("task", ctx.request().getFormAttribute("taskID"))
       /* JsonObject taskQuery=new JsonObject()
        taskQuery?.put("task",ctx.request().getFormAttribute("taskID"))
*/

        /*mongoClient.find(BaseUtil.TASK_COLLECTION, taskQuery, { res ->
            if (res.succeeded()) {

                for (JsonObject json : res.result()) {

                    System.out.println(json.encodePrettily());

                }
            } else {
                res.cause().printStackTrace();
            }

        })
        mongoClient.find(BaseUtil.USER_COLLECTION, taskQuery, { res ->
            if (res.succeeded()) {

                for (JsonObject json : res.result()) {

                    System.out.println(json.encodePrettily());

                }
            } else {
                res.cause().printStackTrace();
            }

        })*/

        mongoClient.save(BaseUtil.COMMENT_COLLECTION, commentDocument, { res ->
            if (res.succeeded()) {
                String id = res.result();
                System.out.println("Saved book with id " + id);
                ctx.response().putHeader("location", "/").setStatusCode(302).end();
            } else {
                res.cause().printStackTrace();
            }
        });
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
