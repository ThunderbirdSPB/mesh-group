package com.group.mesh.config.log;

import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for storing requestId. Can be expanded to store any necessary attributes.
 */
@NoArgsConstructor
public final class LogAttributesHolder {
    private static final String REQUEST_ID_ATTR_NAME = "requestId";
    private static final String URI_ATTR_NAME = "uri";
    private static final String HTTP_METHOD_ATTR_NAME = "method";

    private static final ThreadLocal<Map<String, String>> ATTRIBUTES_HOLDER = ThreadLocal.withInitial(HashMap::new);

    public static void addRequestID(String reqId) {
        ATTRIBUTES_HOLDER.get().put(REQUEST_ID_ATTR_NAME, reqId);
    }

    public static void addURI(String uri) {
        ATTRIBUTES_HOLDER.get().put(URI_ATTR_NAME, uri);
    }

    public static void addHttpMethod(String method) {
        ATTRIBUTES_HOLDER.get().put(HTTP_METHOD_ATTR_NAME, method);
    }

    public static void clear() {
        ATTRIBUTES_HOLDER.get().clear();
    }

    public static String getRequestID() {
        return ATTRIBUTES_HOLDER.get().get(REQUEST_ID_ATTR_NAME);
    }

    public static String getURI() {
        return ATTRIBUTES_HOLDER.get().get(URI_ATTR_NAME);
    }

    public static String getHttpMethod() {
        return ATTRIBUTES_HOLDER.get().get(HTTP_METHOD_ATTR_NAME);
    }
}
