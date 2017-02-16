package defaultHandlers;

import protocol.Protocol;
import protocol.response.HttpResponseBuilder;
import protocol.response.IHttpResponse;

import java.io.File;
import java.util.HashMap;

/**
 * Created by CJ on 2/16/2017.
 */
public class StaticResponceBuilder {

    public static IHttpResponse build200Response(File file) {
        HttpResponseBuilder responseBuilder = new HttpResponseBuilder();
        responseBuilder.setStatus(Protocol.OK_CODE);
        responseBuilder.setPhrase(Protocol.OK_TEXT);
        responseBuilder.setHeaders(defaultHeaders());
        responseBuilder.setFileBody(file);
        responseBuilder.setConnection(Protocol.CLOSE);
        return responseBuilder.build();
    }

    public static IHttpResponse build400Response() {
        HttpResponseBuilder responseBuilder = new HttpResponseBuilder();
        responseBuilder.setStatus(Protocol.BAD_REQUEST_CODE);
        responseBuilder.setPhrase(Protocol.BAD_REQUEST_TEXT);
        responseBuilder.setHeaders(defaultHeaders());
        responseBuilder.setConnection(Protocol.CLOSE);

        return responseBuilder.build();
    }

    public static IHttpResponse build404Response() {
        HttpResponseBuilder responseBuilder = new HttpResponseBuilder();
        responseBuilder.setStatus(Protocol.NOT_FOUND_CODE);
        responseBuilder.setPhrase(Protocol.NOT_FOUND_TEXT);
        responseBuilder.setHeaders(defaultHeaders());
        responseBuilder.setConnection(Protocol.CLOSE);
        return responseBuilder.build();
    }

    public static HashMap<String, String> defaultHeaders() {
        HashMap headers = new HashMap();
        headers.put("Access-Control-Allow-Origin", "*");
        return headers;
    }
}
