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
package org.apache.catalina;

import java.util.concurrent.TimeUnit;

public interface Executor extends java.util.concurrent.Executor, Lifecycle {

    String getName();

    /**
     * Executes the given command at some time in the future. The command may execute in a new thread, in a pooled
     * thread, or in the calling thread, at the discretion of the <code>Executor</code> implementation. If no threads
     * are available, it will be added to the work queue. If the work queue is full, the system will wait for the
     * specified time until it throws a RejectedExecutionException
     *
     * @param command the runnable task
     * @param timeout the length of time to wait for the task to complete
     * @param unit    the units in which timeout is expressed
     *
     * @throws java.util.concurrent.RejectedExecutionException if this task cannot be accepted for execution - the queue
     *                                                             is full
     * @throws NullPointerException                            if command or unit is null
     *
     * @deprecated Unused. Will be removed in Tomcat 10.1.x onwards.
     */
    @Deprecated
    void execute(Runnable command, long timeout, TimeUnit unit);
}