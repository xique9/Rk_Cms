/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.rekoe.cms.socialauth;

import org.apache.shiro.authc.AuthenticationToken;
import org.brickred.socialauth.Profile;

/**
 * This class represents a token for an OAuth authentication process (OAuth
 * credential + user identifier after authentication).
 * 
 * @author Jerome Leleu
 * @since 1.0.0
 */
public final class OAuthToken implements AuthenticationToken {

	private static final long serialVersionUID = 3376624432421737333L;
	private Profile credential;
	private String userId;
	private String addr;
	public OAuthToken(Profile credential,String addr) {
		this.credential = credential;
		this.addr = addr;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Object getPrincipal() {
		return userId;
	}

	public String getAddr() {
		return addr;
	}

	public Profile getCredentials() {
		return credential;
	}
}
