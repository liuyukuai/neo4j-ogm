/*
 * Copyright (c) 2002-2015 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This product is licensed to you under the Apache License, Version 2.0 (the "License").
 * You may not use this product except in compliance with the License.
 *
 * This product may include a number of subcomponents with
 * separate copyright notices and license terms. Your use of the source
 * code for these subcomponents is subject to the terms and
 * conditions of the subcomponent's license, as noted in the LICENSE file.
 *
 */

package org.neo4j.ogm.drivers.http.response;

import static junit.framework.TestCase.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;
import org.neo4j.ogm.model.GraphModel;
import org.neo4j.ogm.model.Node;
import org.neo4j.ogm.response.Response;
import org.neo4j.ogm.result.ResultGraphModel;

/**
 * @author Luanne Misquitta
 */
public class JsonGraphResponseTest {


   @Test
    public void shouldParseColumnsInGraphResponseCorrectly() {
        try (Response<GraphModel> rsp = new TestGraphHttpResponse((graphResultsAndNoErrors()))) {
            assertEquals(1, rsp.columns().length);
            assertEquals("_0",rsp.columns()[0]);
        }
    }

    @Test
    public void shouldParseColumnsInGraphResponseWithNoColumnsCorrectly() {
        try (Response<GraphModel> rsp = new TestGraphHttpResponse((noGraphResultsAndNoErrors()))) {
            assertEquals(1, rsp.columns().length);
            assertEquals("_0",rsp.columns()[0]);
        }
    }

    @Test
    public void shouldParseDataInLoadByIdsGraphResponseCorrectly() {
        try (Response<GraphModel> rsp = new TestGraphHttpResponse((loadByIdsGraphResults()))) {
            GraphModel graphModel = rsp.next();
            assertNotNull(graphModel);
            Set<Node> nodes = graphModel.getNodes();
            assertEquals(1,nodes.size());
            assertEquals("adam",nodes.iterator().next().getPropertyList().get(0).getValue());
            assertEquals(0,graphModel.getRelationships().size());

            graphModel = rsp.next();
            assertNotNull(graphModel);
            nodes = graphModel.getNodes();
            assertEquals(2,nodes.size());
            Iterator<Node> nodeIterator = nodes.iterator();
            nodeIterator.next(); //skip adam
            assertEquals("GraphAware",nodeIterator.next().getPropertyList().get(0).getValue());
            assertEquals(1,graphModel.getRelationships().size());
            assertEquals("EMPLOYED_BY",graphModel.getRelationships().iterator().next().getType());

            for (int i=0;i<4;i++) {
                assertNotNull(rsp.next());
            }
            assertNull(rsp.next());
        }
    }

    private InputStream graphResultsAndNoErrors() {

        final String s= "{\n" +
                "  \"results\": [\n" +
                "    {\n" +
                "      \"columns\": [\n" +
                "        \"_0\"\n" +
                "      ],\n" +
                "      \"data\": [\n" +
                "        {\n" +
                "          \"graph\": {\n" +
                "            \"nodes\": [\n" +
                "              {\n" +
                "                \"id\": \"381\",\n" +
                "                \"labels\": [\n" +
                "                  \"School\"\n" +
                "                ],\n" +
                "                \"properties\": {}\n" +
                "              }\n" +
                "            ],\n" +
                "            \"relationships\": []\n" +
                "          }\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ],\n" +
                "  \"errors\": []\n" +
                "}";

        return new ByteArrayInputStream(s.getBytes());
    }

    private InputStream noGraphResultsAndNoErrors() {

        final String s = "{\n" +
                "  \"results\": [\n" +
                "    {\n" +
                "      \"columns\": [\n" +
                "        \"_0\"\n" +
                "      ],\n" +
                "      \"data\": []\n" +
                "    }\n" +
                "  ],\n" +
                "  \"errors\": []\n" +
                "}";

        return new ByteArrayInputStream(s.getBytes());
    }

    private InputStream loadByIdsGraphResults() {
        final String s = "{\n" +
                "  \"results\": [\n" +
                "    {\n" +
                "      \"columns\": [\n" +
                "        \"p\"\n" +
                "      ],\n" +
                "      \"data\": [\n" +
                "        {\n" +
                "          \"graph\": {\n" +
                "            \"nodes\": [\n" +
                "              {\n" +
                "                \"id\": \"343\",\n" +
                "                \"labels\": [\n" +
                "                  \"User\"\n" +
                "                ],\n" +
                "                \"properties\": {\n" +
                "                  \"firstName\": \"adam\"\n" +
                "                }\n" +
                "              }\n" +
                "            ],\n" +
                "            \"relationships\": []\n" +
                "          }\n" +
                "        },\n" +
                "        {\n" +
                "          \"graph\": {\n" +
                "            \"nodes\": [\n" +
                "              {\n" +
                "                \"id\": \"343\",\n" +
                "                \"labels\": [\n" +
                "                  \"User\"\n" +
                "                ],\n" +
                "                \"properties\": {\n" +
                "                  \"firstName\": \"adam\"\n" +
                "                }\n" +
                "              },\n" +
                "              {\n" +
                "                \"id\": \"26\",\n" +
                "                \"labels\": [\n" +
                "                  \"Customer\"\n" +
                "                ],\n" +
                "                \"properties\": {\n" +
                "                  \"name\": \"GraphAware\"\n" +
                "                }\n" +
                "              }\n" +
                "            ],\n" +
                "            \"relationships\": [\n" +
                "              {\n" +
                "                \"id\": \"18\",\n" +
                "                \"type\": \"EMPLOYED_BY\",\n" +
                "                \"startNode\": \"343\",\n" +
                "                \"endNode\": \"26\",\n" +
                "                \"properties\": {}\n" +
                "              }\n" +
                "            ]\n" +
                "          }\n" +
                "        },\n" +
                "        {\n" +
                "          \"graph\": {\n" +
                "            \"nodes\": [\n" +
                "              {\n" +
                "                \"id\": \"343\",\n" +
                "                \"labels\": [\n" +
                "                  \"User\"\n" +
                "                ],\n" +
                "                \"properties\": {\n" +
                "                  \"firstName\": \"adam\"\n" +
                "                }\n" +
                "              },\n" +
                "              {\n" +
                "                \"id\": \"347\",\n" +
                "                \"labels\": [\n" +
                "                  \"Issue\"\n" +
                "                ],\n" +
                "                \"properties\": {\n" +
                "                  \"title\": \"fake 7\",\n" +
                "                  \"number\": \"7\",\n" +
                "                  \"title\": \"fake 7\"\n" +
                "                }\n" +
                "              }\n" +
                "            ],\n" +
                "            \"relationships\": [\n" +
                "              {\n" +
                "                \"id\": \"506\",\n" +
                "                \"type\": \"ASSIGNED_TO\",\n" +
                "                \"startNode\": \"347\",\n" +
                "                \"endNode\": \"343\",\n" +
                "                \"properties\": {}\n" +
                "              }\n" +
                "            ]\n" +
                "          }\n" +
                "        },\n" +
                "        {\n" +
                "          \"graph\": {\n" +
                "            \"nodes\": [\n" +
                "              {\n" +
                "                \"id\": \"344\",\n" +
                "                \"labels\": [\n" +
                "                  \"User\"\n" +
                "                ],\n" +
                "                \"properties\": {\n" +
                "                  \"firstName\": \"vince\"\n" +
                "                }\n" +
                "              }\n" +
                "            ],\n" +
                "            \"relationships\": []\n" +
                "          }\n" +
                "        },\n" +
                "        {\n" +
                "          \"graph\": {\n" +
                "            \"nodes\": [\n" +
                "              {\n" +
                "                \"id\": \"26\",\n" +
                "                \"labels\": [\n" +
                "                  \"Customer\"\n" +
                "                ],\n" +
                "                \"properties\": {\n" +
                "                  \"name\": \"GraphAware\"\n" +
                "                }\n" +
                "              },\n" +
                "              {\n" +
                "                \"id\": \"344\",\n" +
                "                \"labels\": [\n" +
                "                  \"User\"\n" +
                "                ],\n" +
                "                \"properties\": {\n" +
                "                  \"firstName\": \"vince\"\n" +
                "                }\n" +
                "              }\n" +
                "            ],\n" +
                "            \"relationships\": [\n" +
                "              {\n" +
                "                \"id\": \"19\",\n" +
                "                \"type\": \"EMPLOYED_BY\",\n" +
                "                \"startNode\": \"344\",\n" +
                "                \"endNode\": \"26\",\n" +
                "                \"properties\": {}\n" +
                "              }\n" +
                "            ]\n" +
                "          }\n" +
                "        },\n" +
                "        {\n" +
                "          \"graph\": {\n" +
                "            \"nodes\": [\n" +
                "              {\n" +
                "                \"id\": \"346\",\n" +
                "                \"labels\": [\n" +
                "                  \"Issue\"\n" +
                "                ],\n" +
                "                \"properties\": {\n" +
                "                  \"title\": \"fake 1\",\n" +
                "                  \"number\": \"1\",\n" +
                "                  \"title\": \"fake 1\"\n" +
                "                }\n" +
                "              },\n" +
                "              {\n" +
                "                \"id\": \"344\",\n" +
                "                \"labels\": [\n" +
                "                  \"User\"\n" +
                "                ],\n" +
                "                \"properties\": {\n" +
                "                  \"firstName\": \"vince\"\n" +
                "                }\n" +
                "              }\n" +
                "            ],\n" +
                "            \"relationships\": [\n" +
                "              {\n" +
                "                \"id\": \"509\",\n" +
                "                \"type\": \"CREATED\",\n" +
                "                \"startNode\": \"344\",\n" +
                "                \"endNode\": \"346\",\n" +
                "                \"properties\": {}\n" +
                "              }\n" +
                "            ]\n" +
                "          }\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ],\n" +
                "  \"errors\": []\n" +
                "}";
        return new ByteArrayInputStream(s.getBytes());

    }

    static class TestGraphHttpResponse extends AbstractHttpResponse<ResultGraphModel> implements Response<GraphModel> {

        public TestGraphHttpResponse(InputStream inputStream) {
            super(inputStream, ResultGraphModel.class);
        }

        @Override
        public GraphModel next() {
            ResultGraphModel graphModel = nextDataRecord("graph");

            if (graphModel != null) {
                return graphModel.queryResults();
            }
            return null;
        }

        @Override
        public void close() {
            //Nothing to do, the response has been closed already
        }

    }

}
