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
package org.apache.wicket.security.swarm.checks;

import org.apache.wicket.security.actions.WaspAction;
import org.apache.wicket.security.checks.AbstractSecurityCheck;
import org.apache.wicket.security.hive.authorization.permissions.DataPermission;
import org.apache.wicket.security.swarm.strategies.SwarmStrategy;

/**
 * SecurityCheck that uses a {@link DataPermission}.
 * 
 * @author marrink
 */
public class DataSecurityCheck extends AbstractSecurityCheck
{
	private static final long serialVersionUID = 1L;

	private final String securityId;

	/**
	 * Creates a check that will verify if the current user has a DataPermission
	 * with the specified name.
	 * 
	 * @param securityId
	 *            the name of the DataPermission in your policy file
	 */
	public DataSecurityCheck(String securityId)
	{
		this.securityId = securityId;
	}

	/**
	 * @see org.apache.wicket.security.checks.ISecurityCheck#isActionAuthorized(org.apache.wicket.security.actions.WaspAction)
	 */
	public boolean isActionAuthorized(WaspAction action)
	{
		DataPermission permission = new DataPermission(getSecurityId(), action);
		return ((SwarmStrategy)getStrategy()).hasPermission(permission);
	}

	/**
	 * @see org.apache.wicket.security.checks.ISecurityCheck#isAuthenticated()
	 */
	public boolean isAuthenticated()
	{
		return getStrategy().isUserAuthenticated();
	}

	/**
	 * The id / name from the {@link DataPermission}.
	 * 
	 * @return securityId
	 */
	public final String getSecurityId()
	{
		return securityId;
	}

}