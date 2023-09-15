/*
 * Copyright 2014-2023 Amazon.com, Inc. or its affiliates. All Rights Reserved.
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
package com.ibm.cloud.objectstorage.services.s3.model;
import java.io.Serializable;

public class SSEAwsKeyManagementParams implements Serializable {

    /**
     * The Amazon Web Services Key Management Key id to be used for Server Side Encryption of
     * the Amazon S3 object.
     */
    private String awsKmsKeyId;

    /**
     * Specifies the Amazon Web Services KMS Encryption Context to use for object encryption.
     * The value of this header is a base64-encoded UTF-8 string holding JSON with the encryption context key-value
     * pairs. This value is stored as object metadata and automatically gets passed on to Amazon Web Services KMS for
     * future <code>GetObject</code> or <code>CopyObject</code> operations on this object.
     */
    private String awsKmsEncryptionContext;

    /**
     * Constructs a new instance of SSEAwsKeyManagementParams. The default Amazon Web Services
     * KMS Key id is used for encryption.
     */
    public SSEAwsKeyManagementParams() {
        this.awsKmsEncryptionContext = null;
        this.awsKmsKeyId = null;
    }

    /**
     * @exclude
     * Constructs a new instance of SSEAwsKeyManagementParams with the user
     * specified Amazon Web Services Key Management System Key Id.
     */
    public SSEAwsKeyManagementParams(String awsKmsKeyId) {
        if (awsKmsKeyId == null || awsKmsKeyId.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "AWS Key Management System Key id cannot be null");
        }
        this.awsKmsKeyId = awsKmsKeyId;
        this.awsKmsEncryptionContext = null;
    }

    /**
     * @exclude
     * Returns the Amazon Web Services Key Management System Key Id used for encryption. Returns
     * null if default Key Id is used.
     */
    public String getAwsKmsKeyId() {
        return awsKmsKeyId;
    }

    /**
     * @exclude
     * Sets the awsKmsKeyId
     *
     * @param awsKmsKeyId The new awsKmsKeyId value.
     * @return This object for method chaining.
     */
    public SSEAwsKeyManagementParams withAwsKmsKeyId(String awsKmsKeyId) {
        setAwsKmsKeyId(awsKmsKeyId);
        return this;
    }

    /*
     * @exclude
     */
    private void setAwsKmsKeyId(String awsKmsKeyId) {
        this.awsKmsKeyId = awsKmsKeyId;
    }

    /**
     * Returns the scheme used for encrypting the Amazon S3 object. Currently
     * the encryption is always "aws:kms".
     */
    public String getEncryption() {
        return SSEAlgorithm.KMS.getAlgorithm();
    }

    /*
     * @exclude
     */
    public String getAwsKmsEncryptionContext() {
        return awsKmsEncryptionContext;
    }

    /**
     * @exclude
     * Sets the awsKmsEncryptionContext
     *
     * @param awsKmsEncryptionContext The new awsKmsEncryptionContext value.
     * @return This object for method chaining.
     */
    public SSEAwsKeyManagementParams withAwsKmsEncryptionContext(String awsKmsEncryptionContext) {
        setAwsKmsEncryptionContext(awsKmsEncryptionContext);
        return this;
    }

    /*
     * @exclude
     */
    private void setAwsKmsEncryptionContext(String awsKmsEncryptionContext) {
        this.awsKmsEncryptionContext = awsKmsEncryptionContext;
    }
}
