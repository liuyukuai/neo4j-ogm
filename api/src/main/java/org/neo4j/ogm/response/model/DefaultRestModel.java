/*
 * Copyright (c) 2002-2016 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This product is licensed to you under the Apache License, Version 2.0 (the "License").
 * You may not use this product except in compliance with the License.
 *
 * This product may include a number of subcomponents with
 * separate copyright notices and license terms. Your use of the source
 * code for these subcomponents is subject to the terms and
 *  conditions of the subcomponent's license, as noted in the LICENSE file.
 */
package org.neo4j.ogm.response.model;

import org.neo4j.ogm.model.QueryStatistics;
import org.neo4j.ogm.model.RestModel;

/**
 * The results of a query, modelled as rest response data.
 * //TODO this must be refactored to decouple from REST
 * @author Luanne Misquitta
 */
public class DefaultRestModel implements RestModel{

	private final Object[] values;
	private QueryStatistics stats = new QueryStatisticsModel();

	public DefaultRestModel(Object[] values) {
		this.values = values;
	}

	@Override
	public Object[] getValues() {
		return values;
	}

	public QueryStatistics getStats() {
		return stats;
	}

	public void setStats(QueryStatistics stats) {
		this.stats = stats;
	}

}
