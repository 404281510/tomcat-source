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
package org.apache.tomcat.util.bcel.classfile;

public class ArrayElementValue extends ElementValue
{
    // For array types, this is the array
    private final ElementValue[] elementValues;

    ArrayElementValue(final int type, final ElementValue[] elementValues, final ConstantPool cpool) {
        super(type, cpool);
        if (type != ARRAY) {
            throw new ClassFormatException("Only element values of type array can be built with this ctor - type specified: " + type);
        }
        this.elementValues = elementValues != null ? elementValues : ElementValue.EMPTY_ARRAY;
    }

    public ElementValue[] getElementValuesArray() {
        return elementValues;
    }

    @Override
    public String stringifyValue() {
        final StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < elementValues.length; i++) {
            sb.append(elementValues[i].stringifyValue());
            if (i + 1 < elementValues.length) {
                sb.append(',');
            }
        }
        sb.append(']');
        return sb.toString();
    }
}
