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

package org.neo4j.ogm.drivers.http.response;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertNotNull;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;

import org.junit.Test;
import org.neo4j.ogm.response.Response;
import org.neo4j.ogm.response.model.DefaultRestModel;
import org.neo4j.ogm.result.ResultRestModel;

/**
 * @author Luanne Misquitta
 */
public class JsonRestResponseTest {

    @Test
    public void shouldParseColumnsInRowResponseCorrectly() {
        try (Response<DefaultRestModel> rsp = new TestRestHttpResponse((rowResultsAndNoErrors()))) {
            assertEquals(3, rsp.columns().length);
            assertEquals("count",rsp.columns()[0]);
        }
    }

  @Test
    public void shouldParseColumnsInRowResponseWithNoColumnsCorrectly() {
      try (Response<DefaultRestModel> rsp = new TestRestHttpResponse((noRowResultsAndNoErrors()))) {
          assertEquals(3, rsp.columns().length);
            assertEquals("director",rsp.columns()[1]);

        }
    }

     @Test
    public void shouldParseDataInRowResponseCorrectly() {
        try (Response<DefaultRestModel> rsp = new TestRestHttpResponse((rowResultsAndNoErrors()))) {
            DefaultRestModel restModel = rsp.next();
            assertNotNull(restModel);
            Object[] rows = restModel.getValues();
            assertEquals(3,rows.length);
            assertEquals(1, rows[0]);
            Map data = (Map) rows[1];
            assertEquals(1931,((Map)data.get("data")).get("born"));
            data = (Map) rows[2];
            assertEquals("The Birdcage", ((Map)data.get("data")).get("title"));
            assertEquals(395, ((Map)data.get("metadata")).get("id"));

            restModel = rsp.next();
            rows = restModel.getValues();
            assertEquals(3,rows.length);
            assertEquals(1, rows[0]);
            data = (Map) rows[1];
            assertEquals(1931,((Map)data.get("data")).get("born"));
            data = (Map) rows[2];
            assertEquals(2007, ((Map)data.get("data")).get("released"));
        }
    }

    private InputStream rowResultsAndNoErrors() {

        final String s= "{\"results\":[{\"columns\":[\"count\",\"director\",\"movie\"],\"data\":[{\"rest\":[1,{\"outgoing_relationships\":\"http://localhost:7474/db/data/node/396/relationships/out\",\"labels\":\"http://localhost:7474/db/data/node/396/labels\",\"all_typed_relationships\":\"http://localhost:7474/db/data/node/396/relationships/all/{-list|&|types}\",\"traverse\":\"http://localhost:7474/db/data/node/396/traverse/{returnType}\",\"self\":\"http://localhost:7474/db/data/node/396\",\"property\":\"http://localhost:7474/db/data/node/396/properties/{key}\",\"outgoing_typed_relationships\":\"http://localhost:7474/db/data/node/396/relationships/out/{-list|&|types}\",\"properties\":\"http://localhost:7474/db/data/node/396/properties\",\"incoming_relationships\":\"http://localhost:7474/db/data/node/396/relationships/in\",\"create_relationship\":\"http://localhost:7474/db/data/node/396/relationships\",\"paged_traverse\":\"http://localhost:7474/db/data/node/396/paged/traverse/{returnType}{?pageSize,leaseTime}\",\"all_relationships\":\"http://localhost:7474/db/data/node/396/relationships/all\",\"incoming_typed_relationships\":\"http://localhost:7474/db/data/node/396/relationships/in/{-list|&|types}\",\"metadata\":{\"id\":396,\"labels\":[\"Person\"]},\"data\":{\"born\":1931,\"name\":\"Mike Nichols\"}},{\"outgoing_relationships\":\"http://localhost:7474/db/data/node/395/relationships/out\",\"labels\":\"http://localhost:7474/db/data/node/395/labels\",\"all_typed_relationships\":\"http://localhost:7474/db/data/node/395/relationships/all/{-list|&|types}\",\"traverse\":\"http://localhost:7474/db/data/node/395/traverse/{returnType}\",\"self\":\"http://localhost:7474/db/data/node/395\",\"property\":\"http://localhost:7474/db/data/node/395/properties/{key}\",\"outgoing_typed_relationships\":\"http://localhost:7474/db/data/node/395/relationships/out/{-list|&|types}\",\"properties\":\"http://localhost:7474/db/data/node/395/properties\",\"incoming_relationships\":\"http://localhost:7474/db/data/node/395/relationships/in\",\"create_relationship\":\"http://localhost:7474/db/data/node/395/relationships\",\"paged_traverse\":\"http://localhost:7474/db/data/node/395/paged/traverse/{returnType}{?pageSize,leaseTime}\",\"all_relationships\":\"http://localhost:7474/db/data/node/395/relationships/all\",\"incoming_typed_relationships\":\"http://localhost:7474/db/data/node/395/relationships/in/{-list|&|types}\",\"metadata\":{\"id\":395,\"labels\":[\"Movie\"]},\"data\":{\"released\":1996,\"title\":\"The Birdcage\",\"tagline\":\"Come as you are\"}}]},{\"rest\":[1,{\"outgoing_relationships\":\"http://localhost:7474/db/data/node/396/relationships/out\",\"labels\":\"http://localhost:7474/db/data/node/396/labels\",\"all_typed_relationships\":\"http://localhost:7474/db/data/node/396/relationships/all/{-list|&|types}\",\"traverse\":\"http://localhost:7474/db/data/node/396/traverse/{returnType}\",\"self\":\"http://localhost:7474/db/data/node/396\",\"property\":\"http://localhost:7474/db/data/node/396/properties/{key}\",\"outgoing_typed_relationships\":\"http://localhost:7474/db/data/node/396/relationships/out/{-list|&|types}\",\"properties\":\"http://localhost:7474/db/data/node/396/properties\",\"incoming_relationships\":\"http://localhost:7474/db/data/node/396/relationships/in\",\"create_relationship\":\"http://localhost:7474/db/data/node/396/relationships\",\"paged_traverse\":\"http://localhost:7474/db/data/node/396/paged/traverse/{returnType}{?pageSize,leaseTime}\",\"all_relationships\":\"http://localhost:7474/db/data/node/396/relationships/all\",\"incoming_typed_relationships\":\"http://localhost:7474/db/data/node/396/relationships/in/{-list|&|types}\",\"metadata\":{\"id\":396,\"labels\":[\"Person\"]},\"data\":{\"born\":1931,\"name\":\"Mike Nichols\"}},{\"outgoing_relationships\":\"http://localhost:7474/db/data/node/457/relationships/out\",\"labels\":\"http://localhost:7474/db/data/node/457/labels\",\"all_typed_relationships\":\"http://localhost:7474/db/data/node/457/relationships/all/{-list|&|types}\",\"traverse\":\"http://localhost:7474/db/data/node/457/traverse/{returnType}\",\"self\":\"http://localhost:7474/db/data/node/457\",\"property\":\"http://localhost:7474/db/data/node/457/properties/{key}\",\"outgoing_typed_relationships\":\"http://localhost:7474/db/data/node/457/relationships/out/{-list|&|types}\",\"properties\":\"http://localhost:7474/db/data/node/457/properties\",\"incoming_relationships\":\"http://localhost:7474/db/data/node/457/relationships/in\",\"create_relationship\":\"http://localhost:7474/db/data/node/457/relationships\",\"paged_traverse\":\"http://localhost:7474/db/data/node/457/paged/traverse/{returnType}{?pageSize,leaseTime}\",\"all_relationships\":\"http://localhost:7474/db/data/node/457/relationships/all\",\"incoming_typed_relationships\":\"http://localhost:7474/db/data/node/457/relationships/in/{-list|&|types}\",\"metadata\":{\"id\":457,\"labels\":[\"Movie\"]},\"data\":{\"released\":2007,\"title\":\"Charlie Wilson's War\",\"tagline\":\"A stiff drink. A little mascara. A lot of nerve. Who said they couldn't bring down the Soviet empire.\"}}]}]}],\"errors\":[]}";

        return new ByteArrayInputStream(s.getBytes());
    }

    private InputStream noRowResultsAndNoErrors() {

        final String s = "{\"results\":[{\"columns\":[\"count\",\"director\",\"movie\"],\"data\":[]}],\"errors\":[]}";

        return new ByteArrayInputStream(s.getBytes());
    }



    static class TestRestHttpResponse extends AbstractHttpResponse<ResultRestModel> implements Response<DefaultRestModel> {

        public TestRestHttpResponse(InputStream inputStream) {
            super(inputStream, ResultRestModel.class);
        }

        @Override
        public DefaultRestModel next() {
            ResultRestModel restModel = nextDataRecord("rest");

            if (restModel != null) {
                return new DefaultRestModel(restModel.queryResults());
            }
            return null;
        }

        @Override
        public void close() {
            //Nothing to do, the response has been closed already
        }

    }
}
