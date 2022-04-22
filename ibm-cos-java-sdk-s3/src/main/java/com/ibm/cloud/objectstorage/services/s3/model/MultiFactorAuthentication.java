/*
 * Copyright 2010-2022 Amazon.com, Inc. or its affiliates. All Rights Reserved.
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


/**
 * Contains Multi-Factor Authentication (MFA) information to be included 
 * in Amazon S3 requests,
 * consisting of the serial number of the MFA device associated with your Amazon Web Services
 * account and the current, unique MFA token generated by that device.
 * <p>
 * Each unique token generated by an MFA device can only be used in one request.
 * It is not valid to reuse the same token in additional requests.
 * </p>
 * <p>
 * For more information about uses of Multi-Factor Authentication in S3
 * operations, see {@link BucketVersioningConfiguration} and the explanation
 * of the MFA Delete functionality.
 * </p>
 * <p>
 * For more information on Amazon Web Services Multi-Factor Authentication, including how to get
 * a device and associate it with an Amazon Web Services account, see <a
 * href="http://aws.amazon.com/mfa"/>http://aws.amazon.com/mfa</a>
 * </p>
 */
public class MultiFactorAuthentication implements Serializable {

    /**
     * The serial number of the Multi-Factor Authentication device associated
     * with your Amazon Web Services account.
     */
    private String deviceSerialNumber;

    /**
     * The current, unique Multi-Factor Authentication (MFA) token generated by
     * the MFA device associated with your Amazon Web Services account.
     */
    private String token;

    /**
     * Constructs a new {@link MultiFactorAuthentication} object for use in any 
     * Amazon S3
     * operation that accepts requests with Multi-Factor Authentication (MFA).
     * 
     * @param deviceSerialNumber
     *            The serial number identifying the MFA device associated with
     *            the caller's Amazon Web Services account.
     * @param token
     *            The current unique token generated by the MFA device.
     */
    public MultiFactorAuthentication(String deviceSerialNumber, String token) {
        this.deviceSerialNumber = deviceSerialNumber;
        this.token = token;
    }
    
    /**
     * Gets the Multi-Factor Authentication device serial number.
     * 
     * @return The Multi-Factor Authentication device serial number.
     * 
     * @see MultiFactorAuthentication#setDeviceSerialNumber(String)
     * @see MultiFactorAuthentication#withDeviceSerialNumber(String)
     */
    public String getDeviceSerialNumber() {
        return deviceSerialNumber;
    }

    /**
     * Sets the serial number of the Multi-Factor Authentication device
     * associated with the caller's Amazon Web Services account.
     * 
     * @param deviceSerialNumber
     *            The Multi-Factor Authentication device serial number for the
     *            device associated with the caller's Amazon Web Services account.
     * 
     * @see MultiFactorAuthentication#getDeviceSerialNumber()
     * @see MultiFactorAuthentication#withDeviceSerialNumber(String)
     */
    public void setDeviceSerialNumber(String deviceSerialNumber) {
        this.deviceSerialNumber = deviceSerialNumber;
    }

    /**
     * Sets the Multi-Factor Authentication device serial number to include with
     * this request.     
     * Returns this {@link MultiFactorAuthentication}, enabling additional method
     * calls to be chained together.
     * 
     * @param deviceSerialNumber
     *            The serial number of the Multi-Factor Authentication device
     *            associated with the caller's Amazon Web Services account.
     * 
     * @return This {@link MultiFactorAuthentication}, enabling additional method
     *         calls to be chained together.
     *         
     * @see MultiFactorAuthentication#getDeviceSerialNumber()
     * @see MultiFactorAuthentication#setDeviceSerialNumber(String)       
     */
    public MultiFactorAuthentication withDeviceSerialNumber(String deviceSerialNumber) {
        setDeviceSerialNumber(deviceSerialNumber);
        return this;
    }

    /**
     * Gets the Multi-Factor Authentication token.
     * 
     * @return The Multi-Factor Authentication token.
     * 
     * @see MultiFactorAuthentication#setToken(String)
     * @see MultiFactorAuthentication#withToken(String)
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets the current unique Multi-Factor Authentication token generated by
     * the device associated with the caller's Amazon Web Services account.
     * 
     * @param token
     *            The current, unique Multi-Factor Authentication token generated
     *            by the device associated with the caller's Amazon Web Services account.
     *            
     * @see MultiFactorAuthentication#getToken()
     * @see MultiFactorAuthentication#withToken(String)           
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Sets the current, unique Multi-Factor Authentication token generated by
     * the device associated with the caller's Amazon Web Services account.
     * Returns this {@link MultiFactorAuthentication}, enabling additional method
     * calls to be chained together.
     * 
     * @param token
     *            The current, unique Multi-Factor Authentication token
     *            generated by the device associated with the caller's Amazon Web Services account.
     * 
     * @return This {@link MultiFactorAuthentication}, enabling additional method
     *         calls to be chained together.
     *         
     * @see MultiFactorAuthentication#getToken()
     * @see MultiFactorAuthentication#setToken(String)               
     */
    public MultiFactorAuthentication withToken(String token) {
        setToken(token);
        return this;
    }

}
