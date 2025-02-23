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
package org.apache.catalina.ha.backend;

/**
 * Interface to send data to proxies.
 */
public interface Sender {

    /**
     * Set the configuration parameters
     *
     * @param config The heartbeat listener configuration
     *
     * @throws Exception An error occurred
     */
    void init(HeartbeatListener config) throws Exception;

    /**
     * Send the message to the proxies
     *
     * @param mess The message that will be sent
     *
     * @return <code>0</code> if no error occurred, <code>-1</code> otherwise
     *
     * @throws Exception An error occurred
     */
    int send(String mess) throws Exception;
}
