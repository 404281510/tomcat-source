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

import java.beans.PropertyChangeListener;

/**
 * A <b>Loader</b> represents a Java ClassLoader implementation that can be used by a Container to load class files
 * (within a repository associated with the Loader) that are designed to be reloaded upon request, as well as a
 * mechanism to detect whether changes have occurred in the underlying repository.
 * <p>
 * In order for a <code>Loader</code> implementation to successfully operate with a <code>Context</code> implementation
 * that implements reloading, it must obey the following constraints:
 * <ul>
 * <li>Must implement <code>Lifecycle</code> so that the Context can indicate that a new class loader is required.
 * <li>The <code>start()</code> method must unconditionally create a new <code>ClassLoader</code> implementation.
 * <li>The <code>stop()</code> method must throw away its reference to the <code>ClassLoader</code> previously utilized,
 * so that the class loader, all classes loaded by it, and all objects of those classes, can be garbage collected.
 * <li>Must allow a call to <code>stop()</code> to be followed by a call to <code>start()</code> on the same
 * <code>Loader</code> instance.
 * <li>Based on a policy chosen by the implementation, must call the <code>Context.reload()</code> method on the owning
 * <code>Context</code> when a change to one or more of the class files loaded by this class loader is detected.
 * </ul>
 *
 * @author Craig R. McClanahan
 */
public interface Loader {


    /**
     * Execute a periodic task, such as reloading, etc. This method will be invoked inside the classloading context of
     * this container. Unexpected throwables will be caught and logged.
     */
    void backgroundProcess();


    /**
     * @return the Java class loader to be used by this Loader.
     */
    ClassLoader getClassLoader();


    /**
     * @return the Context with which this Loader has been associated.
     */
    Context getContext();


    /**
     * Set the Context with which this Loader has been associated.
     *
     * @param context The associated Context
     */
    void setContext(Context context);


    /**
     * @return the "follow standard delegation model" flag used to configure our ClassLoader.
     */
    boolean getDelegate();


    /**
     * Set the "follow standard delegation model" flag used to configure our ClassLoader.
     *
     * @param delegate The new flag
     */
    void setDelegate(boolean delegate);


    /**
     * @return the reloadable flag for this Loader.
     *
     * @deprecated Use {@link Context#getReloadable()}. This method will be removed in Tomcat 10.
     */
    @Deprecated
    boolean getReloadable();


    /**
     * Set the reloadable flag for this Loader.
     *
     * @param reloadable The new reloadable flag
     *
     * @deprecated Use {@link Context#setReloadable(boolean)}. This method will be removed in Tomcat 10.
     */
    @Deprecated
    void setReloadable(boolean reloadable);


    /**
     * Add a property change listener to this component.
     *
     * @param listener The listener to add
     */
    void addPropertyChangeListener(PropertyChangeListener listener);


    /**
     * Has the internal repository associated with this Loader been modified, such that the loaded classes should be
     * reloaded?
     *
     * @return <code>true</code> when the repository has been modified, <code>false</code> otherwise
     */
    boolean modified();


    /**
     * Remove a property change listener from this component.
     *
     * @param listener The listener to remove
     */
    void removePropertyChangeListener(PropertyChangeListener listener);
}
