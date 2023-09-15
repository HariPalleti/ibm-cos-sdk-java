/*
 * Copyright 2011-2023 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.ibm.cloud.objectstorage.transform;

import com.ibm.cloud.objectstorage.util.IdempotentUtils;
import com.ibm.cloud.objectstorage.util.SdkHttpUtils;
import com.ibm.cloud.objectstorage.util.StringUtils;

import static com.ibm.cloud.objectstorage.util.ValidationUtils.assertNotNull;
import static com.ibm.cloud.objectstorage.util.ValidationUtils.assertStringNotEmpty;

public class PathMarshallers {

    /**
     * Marshaller for non greedy path labels. Value is URL encoded and then replaced in the request URI.
     */
    public static final PathMarshaller NON_GREEDY = new NonGreedyPathMarshaller();

    /**
     * Marshaller for greedy path labels. Value is not URL encoded and replaced in the request URI.
     */
    public static final PathMarshaller GREEDY = new GreedyPathMarshaller();

    /**
     * Marshaller for members marked with the idempotency trait. If the path value is null, a value will
     * be auto generated by the SDK.
     */
    public static final PathMarshaller IDEMPOTENCY = new IdempotencyPathMarshaller();

    /**
     * Interface for marshalling to a path parameter.
     */
    public interface PathMarshaller {

        /**
         * @param resourcePath Current resource path with path param placeholder
         * @param paramName    Name of parameter (i.e. placeholder value {Foo})
         * @param pathValue    String value of path parameter.
         * @return New URI with placeholder replaced with marshalled value.
         */
        String marshall(String resourcePath, String paramName, String pathValue);

        /**
         * @param resourcePath Current resource path with path param placeholder
         * @param paramName    Name of parameter (i.e. placeholder value {Foo})
         * @param pathValue    Integer value of path parameter.
         * @return New URI with placeholder replaced with marshalled value.
         */
        String marshall(String resourcePath, String paramName, Integer pathValue);

        /**
         * @param resourcePath Current resource path with path param placeholder
         * @param paramName    Name of parameter (i.e. placeholder value {Foo})
         * @param pathValue    Long value of path parameter.
         * @return New URI with placeholder replaced with marshalled value.
         */
        String marshall(String resourcePath, String paramName, Long pathValue);
    }

    private static class NonGreedyPathMarshaller implements PathMarshaller {
        @Override
        public String marshall(String resourcePath, String paramName, String pathValue) {
            assertStringNotEmpty(pathValue, paramName);
            return resourcePath.replace(String.format("{%s}", paramName), SdkHttpUtils.urlEncode(pathValue, false));
        }

        @Override
        public String marshall(String resourcePath, String paramName, Integer pathValue) {
            assertNotNull(pathValue, paramName);
            return marshall(resourcePath, paramName, StringUtils.fromInteger(pathValue));
        }

        @Override
        public String marshall(String resourcePath, String paramName, Long pathValue) {
            assertNotNull(pathValue, paramName);
            return marshall(resourcePath, paramName, StringUtils.fromLong(pathValue));
        }
    }

    private static class GreedyPathMarshaller implements PathMarshaller {

        @Override
        public String marshall(String resourcePath, String paramName, String pathValue) {
            assertStringNotEmpty(pathValue, paramName);
            return resourcePath.replace(String.format("{%s+}", paramName), trimLeadingSlash(pathValue));
        }

        @Override
        public String marshall(String resourcePath, String paramName, Integer pathValue) {
            assertNotNull(pathValue, paramName);
            return marshall(resourcePath, paramName, StringUtils.fromInteger(pathValue));
        }

        @Override
        public String marshall(String resourcePath, String paramName, Long pathValue) {
            assertNotNull(pathValue, paramName);
            return marshall(resourcePath, paramName, StringUtils.fromLong(pathValue));
        }
    }

    private static class IdempotencyPathMarshaller implements PathMarshaller {
        @Override
        public String marshall(String resourcePath, String paramName, String pathValue) {
            if (pathValue != null && pathValue.isEmpty()) {
                throw new IllegalArgumentException(paramName + " must not be empty. If not set a value will be auto generated");
            }
            return resourcePath.replace(String.format("{%s}", paramName),
                                        SdkHttpUtils.urlEncode(IdempotentUtils.resolveString(pathValue), false));
        }

        @Override
        public String marshall(String resourcePath, String paramName, Integer pathValue) {
            throw new UnsupportedOperationException("Integer idempotency tokens not yet supported");
        }

        @Override
        public String marshall(String resourcePath, String paramName, Long pathValue) {
            throw new UnsupportedOperationException("Long idempotency tokens not yet supported");
        }
    }

    private static String trimLeadingSlash(String value) {
        if (value.startsWith("/")) {
            return value.replaceFirst("/", "");
        }
        return value;
    }
}
