/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.tomcat.websocket.server;

/**
 * Internal implementation constants.
 */
public class Constants {

    public static final String BINARY_BUFFER_SIZE_SERVLET_CONTEXT_INIT_PARAM = "org.apache.tomcat.websocket.binaryBufferSize";
    public static final String TEXT_BUFFER_SIZE_SERVLET_CONTEXT_INIT_PARAM = "org.apache.tomcat.websocket.textBufferSize";

    /**
     * Allows the deployment restriction defined in section 6.4 of the Jakarta WebSocket specification to be ignored.
     *
     * @deprecated This is no longer required in Jakarta EE 10 onwards and will be removed in Tomcat 10.1.
     */
    @Deprecated
    public static final String ENFORCE_NO_ADD_AFTER_HANDSHAKE_CONTEXT_INIT_PARAM = "org.apache.tomcat.websocket.noAddAfterHandshake";

    public static final String SERVER_CONTAINER_SERVLET_CONTEXT_ATTRIBUTE = "javax.websocket.server.ServerContainer";


    private Constants() {
        // Hide default constructor
    }
}
