package controllers;


import com.fasterxml.jackson.databind.JsonNode;
import play.libs.ws.WSRequest;
import javax.inject.Inject;
import play.libs.ws.*;
import java.util.concurrent.CompletionStage;
import play.mvc.*;
import views.html.*;

import akka.stream.Materializer;
import akka.stream.javadsl.*;
import akka.util.ByteString;

import scala.compat.java8.FutureConverters;



public class HomeController extends Controller {
    @Inject WSClient ws;

    public Result index() {
        return ok(index.render());
    }



//    public Result getMessage(){
//        WSRequest request = ws.url("http://example.com");
//
//        WSRequest complexRequest = request.addHeader("headerKey", "headerValue")
//                .addQueryParameter("paramKey", "paramValue");
//        CompletionStage<? extends WSResponse> responsePromise = complexRequest.get();// implements WSBodyReadables or use WSBodyReadables.instance.json()
//
//        CompletionStage<JsonNode> jsonPromise = ws.url(url).get().thenApply(r -> r.getBody(WSBodyReadables.instance.json()));
//        return ok("Hello there");
//    }

    public CompletionStage<Result> getMessage() {
        String url = "http://localhost:8080/greeting";
        return ws.url(url).get().thenApply(response -> {
                    String message = response.asJson().get("content").asText();
                    return ok(message);
                }

        );
    }

    public CompletionStage<Result> getMessageFromRoute() {
        String url = "http://localhost:8080/greetingFromRoute";
        return ws.url(url).get().thenApply(response -> {
                    String message = response.asJson().get("content").asText();
                    return ok(message);
                }

        );
    }

}
