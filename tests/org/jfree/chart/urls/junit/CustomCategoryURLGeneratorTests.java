/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2008, by Object Refinery Limited and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc.
 * in the United States and other countries.]
 *
 * ------------------------------------
 * CustomCategoryURLGeneratorTests.java
 * ------------------------------------
 * (C) Copyright 2008, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 23-Apr-2008 : Version 1, based on CustomXYURLGeneratorTests.java (DG);
 *
 */

package org.jfree.chart.urls.junit;

import java.lang.String;
import java.util.List;
import org.jfree.chart.urls.CustomCategoryURLGenerator;
import java.lang.Object;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.jfree.util.PublicCloneable;

/**
 * Tests for the {@link CustomCategoryURLGenerator} class.
 */
public class CustomCategoryURLGeneratorTests extends TestCase {

    /**
     * Returns the tests as a test suite.
     *
     * @return The test suite.
     */
    public static Test suite() {
        return new TestSuite(CustomCategoryURLGeneratorTests.class);
    }

    /**
     * Constructs a new set of tests.
     *
     * @param name  the name of the tests.
     */
    public CustomCategoryURLGeneratorTests(String name) {
        super(name);
    }

    /**
     * Some checks for the equals() method.
     */
    public void testEquals() {
        CustomCategoryURLGenerator g1 = new CustomCategoryURLGenerator();
        CustomCategoryURLGenerator g2 = new CustomCategoryURLGenerator();
        assertTrue(g1.equals(g2));
        List u1 = new java.util.ArrayList();
        u1.add("URL A1");
        u1.add("URL A2");
        u1.add("URL A3");
        g1.addURLSeries(u1);
        assertFalse(g1.equals(g2));
        List u2 = new java.util.ArrayList();
        u2.add("URL A1");
        u2.add("URL A2");
        u2.add("URL A3");
        g2.addURLSeries(u2);
        assertTrue(g1.equals(g2));
    }

    /**
	 * Confirm that cloning works.
	 */
	public void testCloning() throws Exception {
		CustomGeneratorTestsTestCloningTemplate.customGeneratorTestsTestCloningTemplate(
				new CustomCategoryURLGeneratorTestsTestCloningAdapterImpl(), CustomCategoryURLGenerator.class);
	}

    /**
     * Checks that the class implements PublicCloneable.
     */
    public void testPublicCloneable() {
        CustomCategoryURLGenerator g1 = new CustomCategoryURLGenerator();
        assertTrue(g1 instanceof PublicCloneable);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    public void testSerialization() {

        List u1 = new java.util.ArrayList();
        u1.add("URL A1");
        u1.add("URL A2");
        u1.add("URL A3");

        List u2 = new java.util.ArrayList();
        u2.add("URL B1");
        u2.add("URL B2");
        u2.add("URL B3");

        CustomCategoryURLGenerator g1 = new CustomCategoryURLGenerator();
        CustomCategoryURLGenerator g2 = null;

        g1.addURLSeries(u1);
        g1.addURLSeries(u2);

        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            ObjectOutput out = new ObjectOutputStream(buffer);
            out.writeObject(g1);
            out.close();

            ObjectInput in = new ObjectInputStream(
                    new ByteArrayInputStream(buffer.toByteArray()));
            g2 = (CustomCategoryURLGenerator) in.readObject();
            in.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(g1, g2);

    }

    public void testAddURLSeries() throws Exception {
		CustomGeneratorTestsTestAddURLSeriesTemplate.customGeneratorTestsTestAddURLSeriesTemplate(
				new CustomCategoryURLGeneratorTestsTestAddURLSeriesAdapterImpl(), CustomCategoryURLGenerator.class);
	}

	class CustomCategoryURLGeneratorTestsTestCloningAdapterImpl
			implements CustomGeneratorTestsTestCloningAdapter<CustomCategoryURLGenerator> {
		public void addURLSeries(CustomCategoryURLGenerator g1, List u1) {
			g1.addURLSeries(u1);
		}

		public Object clone(CustomCategoryURLGenerator g1) throws CloneNotSupportedException {
			return g1.clone();
		}
	}

	class CustomCategoryURLGeneratorTestsTestAddURLSeriesAdapterImpl
			implements CustomGeneratorTestsTestAddURLSeriesAdapter<CustomCategoryURLGenerator> {
		public void addURLSeries(CustomCategoryURLGenerator g1, List list1) {
			g1.addURLSeries(list1);
		}

		public int getListCount(CustomCategoryURLGenerator g1) {
			return g1.getListCount();
		}

		public int getURLCount(CustomCategoryURLGenerator g1, int i1) {
			return g1.getURLCount(i1);
		}

		public String getURL(CustomCategoryURLGenerator g1, int i1, int i2) {
			return g1.getURL(i1, i2);
		}
	}

}
