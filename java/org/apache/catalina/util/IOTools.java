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
package org.apache.catalina.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;


/**
 * Contains commonly needed I/O-related methods
 *
 * @author Dan Sandberg
 */
public class IOTools {
    protected static final int DEFAULT_BUFFER_SIZE = 4 * 1024; // 4k

    private IOTools() {
        // Ensure non-instantiability
    }

    /**
     * Read input from reader and write it to writer until there is no more input from reader.
     *
     * @param reader the reader to read from.
     * @param writer the writer to write to.
     * @param buf    the char array to use as a buffer
     *
     * @throws IOException IO error
     */
    public static void flow(Reader reader, Writer writer, char[] buf) throws IOException {
        int numRead;
        while ((numRead = reader.read(buf)) >= 0) {
            writer.write(buf, 0, numRead);
        }
    }

    /**
     * Read input from reader and write it to writer until there is no more input from reader.
     *
     * @param reader the reader to read from.
     * @param writer the writer to write to.
     *
     * @throws IOException IO error
     *
     * @see #flow( Reader, Writer, char[] )
     */
    public static void flow(Reader reader, Writer writer) throws IOException {
        char[] buf = new char[DEFAULT_BUFFER_SIZE];
        flow(reader, writer, buf);
    }


    /**
     * Read input from input stream and write it to output stream until there is no more input from input stream using a
     * new buffer of the default size (4 KiB).
     *
     * @param is input stream the input stream to read from.
     * @param os output stream the output stream to write to.
     *
     * @throws IOException If an I/O error occurs during the copy
     */
    public static void flow(InputStream is, OutputStream os) throws IOException {
        byte[] buf = new byte[DEFAULT_BUFFER_SIZE];
        int numRead;
        while ((numRead = is.read(buf)) >= 0) {
            if (os != null) {
                os.write(buf, 0, numRead);
            }
        }
    }


    /**
     * Read until EOF or the buffer is filled.
     *
     * @param is  The source to read from
     * @param buf The buffer to write to
     *
     * @return The number of bytes read
     *
     * @throws IOException If an I/O error occurs during the read
     */
    public static int readFully(InputStream is, byte[] buf) throws IOException {
        int bytesRead = 0;
        int read;
        while (bytesRead < buf.length && ((read = is.read(buf, bytesRead, buf.length - bytesRead)) >= 0)) {
            bytesRead += read;
        }
        return bytesRead;
    }
}
