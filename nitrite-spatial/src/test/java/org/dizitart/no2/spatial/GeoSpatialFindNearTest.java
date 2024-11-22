/*
 * Copyright (c) 2017-2021 Nitrite author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.dizitart.no2.spatial;

import org.dizitart.no2.collection.DocumentCursor;
import org.dizitart.no2.collection.FindOptions;
import org.dizitart.no2.index.IndexOptions;
import org.dizitart.no2.repository.Cursor;
import org.junit.Test;
import org.locationtech.jts.geom.Coordinate;

import java.util.stream.Collectors;

import static java.util.Collections.emptySet;
import static org.dizitart.no2.collection.FindOptions.orderBy;
import static org.dizitart.no2.common.SortOrder.Ascending;
import static org.dizitart.no2.common.util.Iterables.setOf;
import static org.dizitart.no2.spatial.GeoSpatialTestBase.TestLocations.centerPt;
import static org.dizitart.no2.spatial.SpatialFluentFilter.where;
import static org.dizitart.no2.spatial.SpatialIndexer.SPATIAL_INDEX;
import static org.junit.Assert.assertEquals;

/**
 * Test cases to check whether the Spatial distance search is properly converting meters to/from degrees and
 * accounting for the curvature of the Earth.
 * <p>
 * From the <a href="https://nitrite.dizitart.com/java-sdk/filter/index.html#near-filter">"near filter"
 * documentation</a>, it is clear that the intended use is for the arguments to represent (1) a geo-coordinate
 * as a lat/long pair; and (2) a distance in meters.
 * <p>
 * This appears to disagree with the example provided in <pre>nitrite-spatial/README.md</pre>, which makes no
 * mention of distance units uses a coordinate value outside the space of possible lat/long coordinates.
 **/
public class GeoSpatialFindNearTest extends GeoSpatialTestBase {

    /** 2.5 kilometers, in meters */
    private static final double DIST_2500_M = 2_500.0d;
    /** 20 millimeters, in meters */
    private static final double DIST_20_MM = (20.0d / 1000.0d);

    /** Just sorting by "id" field so that JUnit's "Expected" vs. "Actual" output is easier to visually inspect. */
    public static final FindOptions ORDER_BY_ID = orderBy("id", Ascending);

    @Test
    public void testNearFilter_ObjectRepository_FindsCorrectSubset() {
        Cursor<SpatialData> cursor = repository.find(
            where("geometry").near(centerPt.getCoordinate(), DIST_2500_M),
            ORDER_BY_ID);

        assertEquals("Near filter should only find two locations within 2.5km",
            setOf(obj2_950m_ESE, obj3_930m_W),
            cursor.toSet());
    }

    @Test
    public void testNearFilter_NitriteCollection_FindsCorrectSubset() {
        collection.createIndex(IndexOptions.indexOptions(SPATIAL_INDEX), "location");

        DocumentCursor cursor = collection.find(
            where("location").near(centerPt.getCoordinate(), DIST_2500_M),
            ORDER_BY_ID);

        assertEquals("Near filter should only find two locations within 2.5km",
            setOf(doc2_950m_ESE, doc3_930m_W),
            cursor.toList().stream().map(this::trimMeta).collect(Collectors.toSet()));
    }

    @Test
    public void testNearFilter_ObjectRepository_TinyDistance_ReturnsNoResults() {
        Coordinate coordinate = centerPt.getCoordinate();

        Cursor<SpatialData> cursor = repository.find(where("geometry").near(coordinate, DIST_20_MM));
        assertEquals("Near filter should return no results within 20mm distance", emptySet(), cursor.toSet());
    }

    @Test
    public void testNearFilter_NitriteCollection_TinyDistance_ReturnsNoResults() {
        Coordinate coordinate = centerPt.getCoordinate();

        collection.createIndex(IndexOptions.indexOptions(SPATIAL_INDEX), "location");
        DocumentCursor cursor = collection.find(where("location").near(coordinate, DIST_20_MM));
        assertEquals("Near filter should return no results within 20mm distance", emptySet(), cursor.toSet());
    }
}
