// License: GPL. For details, see LICENSE file.
package org.openstreetmap.josm.data.coor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.DecimalFormat;

import org.junit.Rule;
import org.junit.Test;
import org.openstreetmap.josm.data.Bounds;
import org.openstreetmap.josm.testutils.JOSMTestRules;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import nl.jqno.equalsverifier.EqualsVerifier;

/**
 * Unit tests for class {@link LatLon}.
 */
public class LatLonTest {

    /**
     * Setup test.
     */
    @Rule
    @SuppressFBWarnings(value = "URF_UNREAD_PUBLIC_OR_PROTECTED_FIELD")
    public JOSMTestRules test = new JOSMTestRules().projection();

    private static final double EPSILON = 1e-6;

    /**
     * Lat/Lon sample values for unit tests
     */
    @SuppressFBWarnings(value = "MS_PKGPROTECT")
    public static final double[] SAMPLE_VALUES = new double[]{
            // CHECKSTYLE.OFF: SingleSpaceSeparator
            -180.0, -179.9, -179.6, -179.5, -179.4, -179.1, -179.0, -100.0, -99.9, -10.0, -9.9, -1.0, -0.1,
             180.0,  179.9,  179.6,  179.5,  179.4,  179.1,  179.0,  100.0,  99.9,  10.0,  9.9,  1.0,  0.1,
            0.12, 0.123, 0.1234, 0.12345, 0.123456, 0.1234567,
            1.12, 1.123, 1.1234, 1.12345, 1.123456, 1.1234567,
            10.12, 10.123, 10.1234, 10.12345, 10.123456, 10.1234567,
            100.12, 100.123, 100.1234, 100.12345, 100.123456, 100.1234567
            // CHECKSTYLE.ON: SingleSpaceSeparator
           };

    /**
     * Test of {@link LatLon#roundToOsmPrecision}
     */
    @Test
    public void testRoundToOsmPrecision() {

        for (double value : SAMPLE_VALUES) {
            assertEquals(LatLon.roundToOsmPrecision(value), value, 0);
        }

        assertEquals(LatLon.roundToOsmPrecision(0.0), 0.0, 0);
        assertEquals(LatLon.roundToOsmPrecision(-0.0), 0.0, 0);

        // CHECKSTYLE.OFF: SingleSpaceSeparator
        assertEquals(LatLon.roundToOsmPrecision(0.12345678),  0.1234568, 0);
        assertEquals(LatLon.roundToOsmPrecision(0.123456789), 0.1234568, 0);

        assertEquals(LatLon.roundToOsmPrecision(1.12345678),  1.1234568, 0);
        assertEquals(LatLon.roundToOsmPrecision(1.123456789), 1.1234568, 0);

        assertEquals(LatLon.roundToOsmPrecision(10.12345678),  10.1234568, 0);
        assertEquals(LatLon.roundToOsmPrecision(10.123456789), 10.1234568, 0);

        assertEquals(LatLon.roundToOsmPrecision(100.12345678),  100.1234568, 0);
        assertEquals(LatLon.roundToOsmPrecision(100.123456789), 100.1234568, 0);
        // CHECKSTYLE.ON: SingleSpaceSeparator

        assertEquals(LatLon.roundToOsmPrecision(100.00000001), 100.0000000, 0);
        assertEquals(LatLon.roundToOsmPrecision(100.000000001), 100.0000000, 0);
        assertEquals(LatLon.roundToOsmPrecision(100.0000000001), 100.0000000, 0);
        assertEquals(LatLon.roundToOsmPrecision(100.00000000001), 100.0000000, 0);
        assertEquals(LatLon.roundToOsmPrecision(100.000000000001), 100.0000000, 0);
        assertEquals(LatLon.roundToOsmPrecision(100.0000000000001), 100.0000000, 0);
        assertEquals(LatLon.roundToOsmPrecision(100.00000000000001), 100.0000000, 0);
        assertEquals(LatLon.roundToOsmPrecision(100.000000000000001), 100.0000000, 0);
        assertEquals(LatLon.roundToOsmPrecision(100.0000000000000001), 100.0000000, 0);
        assertEquals(LatLon.roundToOsmPrecision(100.00000000000000001), 100.0000000, 0);
        assertEquals(LatLon.roundToOsmPrecision(100.000000000000000001), 100.0000000, 0);
        assertEquals(LatLon.roundToOsmPrecision(100.0000000000000000001), 100.0000000, 0);
        assertEquals(LatLon.roundToOsmPrecision(100.00000000000000000001), 100.0000000, 0);

        assertEquals(LatLon.roundToOsmPrecision(99.999999999999999999999), 100.0000000, 0);
        assertEquals(LatLon.roundToOsmPrecision(99.99999999999999999999), 100.0000000, 0);
        assertEquals(LatLon.roundToOsmPrecision(99.9999999999999999999), 100.0000000, 0);
        assertEquals(LatLon.roundToOsmPrecision(99.999999999999999999), 100.0000000, 0);
        assertEquals(LatLon.roundToOsmPrecision(99.99999999999999999), 100.0000000, 0);
        assertEquals(LatLon.roundToOsmPrecision(99.9999999999999999), 100.0000000, 0);
        assertEquals(LatLon.roundToOsmPrecision(99.999999999999999), 100.0000000, 0);
        assertEquals(LatLon.roundToOsmPrecision(99.99999999999999), 100.0000000, 0);
        assertEquals(LatLon.roundToOsmPrecision(99.9999999999999), 100.0000000, 0);
        assertEquals(LatLon.roundToOsmPrecision(99.999999999999), 100.0000000, 0);
        assertEquals(LatLon.roundToOsmPrecision(99.99999999999), 100.0000000, 0);
        assertEquals(LatLon.roundToOsmPrecision(99.9999999999), 100.0000000, 0);
        assertEquals(LatLon.roundToOsmPrecision(99.999999999), 100.0000000, 0);
        assertEquals(LatLon.roundToOsmPrecision(99.99999999), 100.0000000, 0);
        assertEquals(LatLon.roundToOsmPrecision(99.9999999), 99.9999999, 0);
    }

    /**
     * Test {@link LatLon#toIntervalLat}
     */
    @Test
    public void testToIntervalLat() {
        assertEquals(-90.0, LatLon.toIntervalLat(-90.0), 0);
        assertEquals(0.0, LatLon.toIntervalLat(0.0), 0);
        assertEquals(90.0, LatLon.toIntervalLat(90.0), 0);

        assertEquals(-90.0, LatLon.toIntervalLat(-91.0), 0);
        assertEquals(90.0, LatLon.toIntervalLat(91.0), 0);
    }

    /**
     * Test of {@link LatLon#toIntervalLon}
     */
    @Test
    public void testToIntervalLon() {
        assertEquals(-180.0, LatLon.toIntervalLon(-180.0), 0);
        assertEquals(0.0, LatLon.toIntervalLon(0.0), 0);
        assertEquals(180.0, LatLon.toIntervalLon(180.0), 0);

        assertEquals(179.0, LatLon.toIntervalLon(-181.0), 0);
        assertEquals(-179.0, LatLon.toIntervalLon(181.0), 0);

        assertEquals(-1.0, LatLon.toIntervalLon(359.0), 0);
        assertEquals(1.0, LatLon.toIntervalLon(-359.0), 0);

        assertEquals(1.0, LatLon.toIntervalLon(361.0), 0);
        assertEquals(-1.0, LatLon.toIntervalLon(-361.0), 0);

        assertEquals(179.0, LatLon.toIntervalLon(539.0), 0);
        assertEquals(-179.0, LatLon.toIntervalLon(-539.0), 0);

        assertEquals(-179.0, LatLon.toIntervalLon(541.0), 0);
        assertEquals(179.0, LatLon.toIntervalLon(-541.0), 0);
    }

    /**
     * Unit test of methods {@link LatLon#equals} and {@link LatLon#hashCode}.
     */
    @Test
    public void testEqualsContract() {
        EqualsVerifier.forClass(LatLon.class).usingGetClass()
            .withPrefabValues(DecimalFormat.class, new DecimalFormat("00.0"), new DecimalFormat("00.000"))
            .verify();
    }

    /**
     * Unit test of {@link LatLon#LatLon(LatLon)}.
     */
    @Test
    public void testCopyConstructor() {
        assertEquals(LatLon.NORTH_POLE, new LatLon(LatLon.NORTH_POLE));
        assertEquals(LatLon.SOUTH_POLE, new LatLon(LatLon.SOUTH_POLE));
        assertEquals(new LatLon(1, 2), new LatLon(new LatLon(1, 2)));
    }

    /**
     * Test of {@link LatLon#bearing}
     */
    @Test
    public void testBearing() {
        LatLon c = new LatLon(47.000000, 19.000000);
        LatLon e = new LatLon(47.000000, 19.000001);
        LatLon n = new LatLon(47.000001, 19.000000);
        assertEquals(0, Math.toDegrees(c.bearing(n)), EPSILON);
        assertEquals(90, Math.toDegrees(c.bearing(e)), EPSILON);
        assertEquals(180, Math.toDegrees(n.bearing(c)), EPSILON);
        assertEquals(270, Math.toDegrees(e.bearing(c)), EPSILON);
    }

    /**
     * Test of {@link LatLon#distance}
     */
    @Test
    public void testDistance() {
        assertEquals(0.0, LatLon.ZERO.distance(LatLon.ZERO), 0);
        assertEquals(90.0, LatLon.ZERO.distance(LatLon.NORTH_POLE), 0);
        assertEquals(180.0, LatLon.SOUTH_POLE.distance(LatLon.NORTH_POLE), 0);
    }

    /**
     * Test of {@link LatLon#distanceSq}
     */
    @Test
    public void testDistanceSq() {
        assertEquals(0.0, LatLon.ZERO.distanceSq(LatLon.ZERO), 0);
        assertEquals(90d*90d, LatLon.ZERO.distanceSq(LatLon.NORTH_POLE), 0);
        assertEquals(180d*180d, LatLon.SOUTH_POLE.distanceSq(LatLon.NORTH_POLE), 0);
    }

    /**
     * Tests the methods {@link LatLon#latToString}, {@link LatLon#lonToString}, {@link LatLon#toStringCSV}.
     */
    @Test
    public void testFormatting() {
        LatLon c = new LatLon(47.000000, 19.000000);
        assertEquals("47.0;19.0", c.toStringCSV(";"));
        assertEquals("47.0", c.latToString(CoordinateFormat.DECIMAL_DEGREES));
        assertEquals("19.0", c.lonToString(CoordinateFormat.DECIMAL_DEGREES));
        assertEquals("47°00'00.0\"N", c.latToString(CoordinateFormat.DEGREES_MINUTES_SECONDS));
        assertEquals("19°00'00.0\"E", c.lonToString(CoordinateFormat.DEGREES_MINUTES_SECONDS));
        assertEquals("47°00.000'N", c.latToString(CoordinateFormat.NAUTICAL));
        assertEquals("19°00.000'E", c.lonToString(CoordinateFormat.NAUTICAL));
        assertEquals("5942074.0724311", c.latToString(CoordinateFormat.EAST_NORTH));
        assertEquals("2115070.3250722", c.lonToString(CoordinateFormat.EAST_NORTH));
        c = new LatLon(-47.000000, -19.000000);
        assertEquals("47°00'00.0\"S", c.latToString(CoordinateFormat.DEGREES_MINUTES_SECONDS));
        assertEquals("19°00'00.0\"W", c.lonToString(CoordinateFormat.DEGREES_MINUTES_SECONDS));
        assertEquals("47°00.000'S", c.latToString(CoordinateFormat.NAUTICAL));
        assertEquals("19°00.000'W", c.lonToString(CoordinateFormat.NAUTICAL));
    }

    /**
     * Test {@link LatLon#interpolate(LatLon, double)}
     */
    @Test
    public void testInterpolate() {
        LatLon ll1 = new LatLon(0, 0);
        LatLon ll2 = new LatLon(30, 60);
        LatLon ll3 = new LatLon(-70, -40);
        // lat:
        assertEquals(15, ll1.interpolate(ll2, 0.5).lat(), 1e-10);
        assertEquals(0, ll1.interpolate(ll2, 0).lat(), 1e-10);
        assertEquals(30, ll1.interpolate(ll2, 1).lat(), 1e-10);
        assertEquals(0, ll3.interpolate(ll2, .7).lat(), 1e-10);
        // lon
        assertEquals(30, ll1.interpolate(ll2, 0.5).lon(), 1e-10);
        assertEquals(0, ll1.interpolate(ll2, 0).lon(), 1e-10);
        assertEquals(60, ll1.interpolate(ll2, 1).lon(), 1e-10);
        assertEquals(0, ll3.interpolate(ll2, .4).lon(), 1e-10);
    }

    /**
     * Test {@link LatLon#isOutSideWorld}
     */
    @Test
    public void testIsOutSideWorld() {
        assertFalse(LatLon.ZERO.isOutSideWorld());
        assertTrue(LatLon.NORTH_POLE.isOutSideWorld());
        assertTrue(LatLon.SOUTH_POLE.isOutSideWorld());
        assertTrue(new LatLon(-181, 0).isOutSideWorld());
        assertTrue(new LatLon(181, 0).isOutSideWorld());
    }

    /**
     * Test {@link LatLon#isValid}
     */
    @Test
    public void testIsValid() {
        assertTrue(LatLon.ZERO.isValid());
        assertTrue(LatLon.NORTH_POLE.isValid());
        assertTrue(LatLon.SOUTH_POLE.isValid());

        assertFalse(new LatLon(-91, 0).isValid());
        assertFalse(new LatLon(91, 0).isValid());
        assertFalse(new LatLon(0, -181).isValid());
        assertFalse(new LatLon(0, 181).isValid());
    }

    /**
     * Test {@link LatLon#isWithin}
     */
    @Test
    public void testIsWithin() {
        assertTrue(LatLon.ZERO.isWithin(new Bounds(LatLon.ZERO)));
        assertFalse(LatLon.ZERO.isWithin(new Bounds(LatLon.NORTH_POLE)));
    }

    /**
     * Test {@link LatLon#getCenter(LatLon)}
     */
    @Test
    public void testGetCenter() {
        LatLon ll1 = new LatLon(0, 0);
        LatLon ll2 = new LatLon(30, 60);
        LatLon ll3 = new LatLon(-70, -40);

        assertEquals(15, ll1.getCenter(ll2).lat(), 1e-10);
        assertEquals(15, ll2.getCenter(ll1).lat(), 1e-10);
        assertEquals(-20, ll3.getCenter(ll2).lat(), 1e-10);

        assertEquals(30, ll1.getCenter(ll2).lon(), 1e-10);
        assertEquals(30, ll2.getCenter(ll1).lon(), 1e-10);
        assertEquals(10, ll3.getCenter(ll2).lon(), 1e-10);
    }

    /**
     * Unit test of {@link LatLon#parse} method.
     */
    @Test
    public void testParse() {
        assertEquals(new LatLon(49.29918, 19.24788), LatLon.parse("49.29918° 19.24788°"));
        assertEquals(new LatLon(49.29918, 19.24788), LatLon.parse("N 49.29918 E 19.24788°"));
        assertEquals(new LatLon(49.29918, 19.24788), LatLon.parse("49.29918° 19.24788°"));
        assertEquals(new LatLon(49.29918, 19.24788), LatLon.parse("N 49.29918 E 19.24788"));
        assertEquals(new LatLon(49.29918, 19.24788), LatLon.parse("n 49.29918 e 19.24788"));
        assertEquals(new LatLon(-19 - 24.788 / 60, -49 - 29.918 / 60), LatLon.parse("W 49°29.918' S 19°24.788'"));
        assertEquals(new LatLon(-19 - 24.788 / 60, -49 - 29.918 / 60), LatLon.parse("w 49°29.918' s 19°24.788'"));
        assertEquals(new LatLon(49 + 29. / 60 + 04. / 3600, 19 + 24. / 60 + 43. / 3600), LatLon.parse("N 49°29'04\" E 19°24'43\""));
        assertEquals(new LatLon(49.29918, 19.24788), LatLon.parse("49.29918 N, 19.24788 E"));
        assertEquals(new LatLon(49.29918, 19.24788), LatLon.parse("49.29918 n, 19.24788 e"));
        assertEquals(new LatLon(49 + 29. / 60 + 21. / 3600, 19 + 24. / 60 + 38. / 3600), LatLon.parse("49°29'21\" N 19°24'38\" E"));
        assertEquals(new LatLon(49 + 29. / 60 + 51. / 3600, 19 + 24. / 60 + 18. / 3600), LatLon.parse("49 29 51, 19 24 18"));
        assertEquals(new LatLon(49 + 29. / 60, 19 + 24. / 60), LatLon.parse("49 29, 19 24"));
        assertEquals(new LatLon(19 + 24. / 60, 49 + 29. / 60), LatLon.parse("E 49 29, N 19 24"));
        assertEquals(new LatLon(49 + 29. / 60, 19 + 24. / 60), LatLon.parse("49° 29; 19° 24"));
        assertEquals(new LatLon(49 + 29. / 60, 19 + 24. / 60), LatLon.parse("49° 29; 19° 24"));
        assertEquals(new LatLon(49 + 29. / 60, -19 - 24. / 60), LatLon.parse("N 49° 29, W 19° 24"));
        assertEquals(new LatLon(-49 - 29.5 / 60, 19 + 24.6 / 60), LatLon.parse("49° 29.5 S, 19° 24.6 E"));
        assertEquals(new LatLon(49 + 29.918 / 60, 19 + 15.88 / 60), LatLon.parse("N 49 29.918 E 19 15.88"));
        assertEquals(new LatLon(49 + 29.4 / 60, 19 + 24.5 / 60), LatLon.parse("49 29.4 19 24.5"));
        assertEquals(new LatLon(-49 - 29.4 / 60, 19 + 24.5 / 60), LatLon.parse("-49 29.4 N -19 24.5 W"));
    }

    /**
     * Unit test of {@link LatLon#parse} method - invalid case 1.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testParseInvalid1() {
        LatLon.parse("48°45'S 23°30'S");
    }

    /**
     * Unit test of {@link LatLon#parse} method - invalid case 2.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testParseInvalid2() {
        LatLon.parse("47°45'N 24°00'S");
    }
}
