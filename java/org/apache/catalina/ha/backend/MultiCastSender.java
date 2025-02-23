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

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.nio.charset.StandardCharsets;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.apache.tomcat.util.res.StringManager;

/**
 * Sender to proxies using multicast socket.
 */
public class MultiCastSender implements Sender {

    private static final Log log = LogFactory.getLog(HeartbeatListener.class);
    private static final StringManager sm = StringManager.getManager(MultiCastSender.class);

    HeartbeatListener config = null;

    /* for multicasting stuff */
    MulticastSocket s = null;
    InetAddress group = null;

    @Override
    public void init(HeartbeatListener config) throws Exception {
        this.config = config;
    }

    @Override
    public int send(String mess) throws Exception {
        if (s == null) {
            try {
                group = InetAddress.getByName(config.getGroup());
                if (config.getHost() != null) {
                    InetAddress addr = InetAddress.getByName(config.getHost());
                    InetSocketAddress addrs = new InetSocketAddress(addr, config.getMultiport());
                    s = new MulticastSocket(addrs);
                } else {
                    s = new MulticastSocket(config.getMultiport());
                }

                s.setTimeToLive(config.getTtl());
                s.joinGroup(new InetSocketAddress(group, 0), null);
            } catch (Exception ex) {
                log.error(sm.getString("multiCastSender.multiCastFailed"), ex);
                s = null;
                return -1;
            }
        }

        byte[] buf;
        buf = mess.getBytes(StandardCharsets.US_ASCII);
        DatagramPacket data = new DatagramPacket(buf, buf.length, group, config.getMultiport());
        try {
            s.send(data);
        } catch (Exception ex) {
            log.error(sm.getString("multiCastSender.sendFailed"), ex);
            s.close();
            s = null;
            return -1;
        }
        return 0;
    }

}
