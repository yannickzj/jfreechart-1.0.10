/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2007, by Object Refinery Limited and Contributors.
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
 * ---------------------------
 * RendererUtilitiesTests.java
 * ---------------------------
 * (C) Copyright 2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 19-Apr-2007 : Version 1 (DG);
 *
 */

package org.jfree.chart.renderer.junit;

import org.jfree.data.xy.XYDataset;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.jfree.chart.renderer.RendererUtilities;
import org.jfree.data.DomainOrder;
import org.jfree.data.xy.DefaultXYDataset;

/**
 * Some checks for the {@link RendererUtilities} class.
 */
public class RendererUtilitiesTests extends TestCase {
    
    /**
     * Returns the tests as a test suite.
     *
     * @return The test suite.
     */
    public static Test suite() {
        return new TestSuite(RendererUtilitiesTests.class);
    }

    /**
     * Constructs a new set of tests.
     *
     * @param name  the name of the tests.
     */
    public RendererUtilitiesTests(String name) {
        super(name);
    }
    
    /**
     * Some checks for the findLiveItemsLowerBound() method when the dataset is
     * unordered.
     */
    public void testFindLiveItemsLowerBoundUnordered() {
        DefaultXYDataset d = new DefaultXYDataset();
        
        // check a series with no items
        d.addSeries("S1", new double[][] {{}, {}});
        assertEquals(0, RendererUtilities.findLiveItemsLowerBound(d, 0, 10.0, 
                11.0));
        
        // check a series with one item
        d.addSeries("S2", new double[][] {{1.0}, {2.0}});
        assertEquals(0, RendererUtilities.findLiveItemsLowerBound(d, 1, 0.0, 
                1.1));
        assertEquals(0, RendererUtilities.findLiveItemsLowerBound(d, 1, 2.0, 
                3.3));
        
        // check a series with two items
        d.addSeries("S3", new double[][] {{1.0, 2.0}, {2.0, 2.0}});
        assertEquals(0, RendererUtilities.findLiveItemsLowerBound(d, 2, 0.0, 
                1.1));
        assertEquals(0, RendererUtilities.findLiveItemsLowerBound(d, 2, 1.0, 
                2.2));
        assertEquals(0, RendererUtilities.findLiveItemsLowerBound(d, 2, 2.0, 
                3.3));
        assertEquals(1, RendererUtilities.findLiveItemsLowerBound(d, 2, 3.0, 
                4.4));
        
        // check a series with three items
        d.addSeries("S4", new double[][] {{1.0, 2.0, 1.5}, {2.0, 2.0, 2.0}});
        assertEquals(0, RendererUtilities.findLiveItemsLowerBound(d, 3, 0.0, 
                1.1));
        assertEquals(0, RendererUtilities.findLiveItemsLowerBound(d, 3, 1.0, 
                2.2));
        assertEquals(0, RendererUtilities.findLiveItemsLowerBound(d, 3, 2.0, 
                3.3));
        assertEquals(2, RendererUtilities.findLiveItemsLowerBound(d, 3, 3.0, 
                4.4));
        
        // check a series with four items
        d.addSeries("S5", new double[][] {{1.0, 2.0, 1.5, 1.8}, {2.0, 2.0, 
                2.0, 2.0}});
        assertEquals(0, RendererUtilities.findLiveItemsLowerBound(d, 4, 0.0, 
                1.1));
        assertEquals(0, RendererUtilities.findLiveItemsLowerBound(d, 4, 1.0, 
                2.2));
        assertEquals(0, RendererUtilities.findLiveItemsLowerBound(d, 4, 2.0, 
                3.3));
        assertEquals(3, RendererUtilities.findLiveItemsLowerBound(d, 4, 3.0, 
                4.4));
        assertEquals(3, RendererUtilities.findLiveItemsLowerBound(d, 4, 4.0, 
                5.5));
    }
    
    /**
     * Some checks for the findLiveItemsLowerBound() method when the dataset is
     * ASCENDING.
     */
    public void testFindLiveItemsLowerBoundAscending() {
        DefaultXYDataset d = new DefaultXYDataset() {
            public DomainOrder getDomainOrder() {
                // we're doing this for testing only, and make sure that we 
                // only add data in ascending order by x-value
                return DomainOrder.ASCENDING;
            }
        };
        // check a series with no items
        d.addSeries("S1", new double[][] {{}, {}});
        assertEquals(0, RendererUtilities.findLiveItemsLowerBound(d, 0, 10.0, 
                11.1));
        
        // check a series with one item
        d.addSeries("S2", new double[][] {{1.0}, {2.0}});
        assertEquals(0, RendererUtilities.findLiveItemsLowerBound(d, 1, 0.0, 
                1.1));
        assertEquals(0, RendererUtilities.findLiveItemsLowerBound(d, 1, 2.0, 
                2.2));
        
        // check a series with two items
        d.addSeries("S3", new double[][] {{1.0, 2.0}, {2.0, 2.0}});
        assertEquals(0, RendererUtilities.findLiveItemsLowerBound(d, 2, 0.0, 
                1.1));
        assertEquals(0, RendererUtilities.findLiveItemsLowerBound(d, 2, 1.0, 
                2.2));
        assertEquals(0, RendererUtilities.findLiveItemsLowerBound(d, 2, 2.0, 
                3.3));
        assertEquals(1, RendererUtilities.findLiveItemsLowerBound(d, 2, 3.0, 
                4.4));
        
        // check a series with three items
        d.addSeries("S4", new double[][] {{1.0, 2.0, 3.0}, {2.0, 2.0, 2.0}});
        assertEquals(0, RendererUtilities.findLiveItemsLowerBound(d, 3, 0.0, 
                1.1));
        assertEquals(0, RendererUtilities.findLiveItemsLowerBound(d, 3, 1.0, 
                2.2));
        assertEquals(0, RendererUtilities.findLiveItemsLowerBound(d, 3, 2.0, 
                3.3));
        assertEquals(1, RendererUtilities.findLiveItemsLowerBound(d, 3, 3.0, 
                4.4));
        
        // check a series with four items
        d.addSeries("S5", new double[][] {{1.0, 2.0, 3.0, 4.0}, {2.0, 2.0, 
                2.0, 2.0}});
        assertEquals(0, RendererUtilities.findLiveItemsLowerBound(d, 4, 0.0, 
                1.1));
        assertEquals(0, RendererUtilities.findLiveItemsLowerBound(d, 4, 1.0, 
                2.2));
        assertEquals(0, RendererUtilities.findLiveItemsLowerBound(d, 4, 2.0, 
                3.3));
        assertEquals(1, RendererUtilities.findLiveItemsLowerBound(d, 4, 3.0, 
                4.4));
        assertEquals(2, RendererUtilities.findLiveItemsLowerBound(d, 4, 4.0, 
                5.5));
        
    }
    
    /**
	 * Some checks for the findLiveItemsLowerBound() method when the dataset is DESCENDING.
	 */
	public void testFindLiveItemsLowerBoundDescending() {
		this.rendererUtilitiesTestsTestFindLiveItemsBoundDescendingTemplate(
				new RendererUtilitiesTestsTestFindLiveItemsLowerBoundDescendingAdapterImpl(), 0, 1, 0, 0, 2, 1, 0);
	}

    /**
     * Some checks for the findLiveItemsUpperBound() method when the dataset is
     * unordered.
     */
    public void testFindLiveItemsUpperBoundUnordered() {
        DefaultXYDataset d = new DefaultXYDataset();
        
        // check a series with no items
        d.addSeries("S1", new double[][] {{}, {}});
        assertEquals(0, RendererUtilities.findLiveItemsUpperBound(d, 0, 10.0, 
                11.0));
        
        // check a series with one item
        d.addSeries("S2", new double[][] {{1.0}, {2.0}});
        assertEquals(0, RendererUtilities.findLiveItemsUpperBound(d, 1, 0.0, 
                1.1));
        assertEquals(0, RendererUtilities.findLiveItemsUpperBound(d, 1, 2.0, 
                3.3));
        
        // check a series with two items
        d.addSeries("S3", new double[][] {{1.0, 2.0}, {2.0, 2.0}});
        assertEquals(1, RendererUtilities.findLiveItemsUpperBound(d, 2, 0.0, 
                1.1));
        assertEquals(1, RendererUtilities.findLiveItemsUpperBound(d, 2, 1.0, 
                2.2));
        assertEquals(1, RendererUtilities.findLiveItemsUpperBound(d, 2, 2.0, 
                3.3));
        assertEquals(1, RendererUtilities.findLiveItemsUpperBound(d, 2, 3.0, 
                4.4));
        
        // check a series with three items
        d.addSeries("S4", new double[][] {{1.0, 2.0, 1.5}, {2.0, 2.0, 2.0}});
        assertEquals(1, RendererUtilities.findLiveItemsUpperBound(d, 3, 0.0, 
                1.1));
        assertEquals(2, RendererUtilities.findLiveItemsUpperBound(d, 3, 1.0, 
                2.2));
        assertEquals(2, RendererUtilities.findLiveItemsUpperBound(d, 3, 2.0, 
                3.3));
        assertEquals(2, RendererUtilities.findLiveItemsUpperBound(d, 3, 3.0, 
                4.4));
        
        // check a series with four items
        d.addSeries("S5", new double[][] {{1.0, 2.0, 1.5, 1.8}, {2.0, 2.0, 
                2.0, 2.0}});
        assertEquals(1, RendererUtilities.findLiveItemsUpperBound(d, 4, 0.0, 
                1.1));
        assertEquals(3, RendererUtilities.findLiveItemsUpperBound(d, 4, 1.0, 
                2.2));
        assertEquals(3, RendererUtilities.findLiveItemsUpperBound(d, 4, 2.0, 
                3.3));
        assertEquals(3, RendererUtilities.findLiveItemsUpperBound(d, 4, 3.0, 
                4.4));
        assertEquals(3, RendererUtilities.findLiveItemsUpperBound(d, 4, 4.0, 
                5.5));
    }

    /**
     * Some checks for the findLiveItemsUpperBound() method when the dataset is
     * ASCENDING.
     */
    public void testFindLiveItemsUpperBoundAscending() {
        DefaultXYDataset d = new DefaultXYDataset() {
            public DomainOrder getDomainOrder() {
                // we're doing this for testing only, and make sure that we 
                // only add data in ascending order by x-value
                return DomainOrder.ASCENDING;
            }
        };
        // check a series with no items
        d.addSeries("S1", new double[][] {{}, {}});
        assertEquals(0, RendererUtilities.findLiveItemsUpperBound(d, 0, 10.0, 
                11.1));
        
        // check a series with one item
        d.addSeries("S2", new double[][] {{1.0}, {2.0}});
        assertEquals(0, RendererUtilities.findLiveItemsUpperBound(d, 1, 0.0, 
                1.1));
        assertEquals(0, RendererUtilities.findLiveItemsUpperBound(d, 1, 2.0, 
                2.2));
        
        // check a series with two items
        d.addSeries("S3", new double[][] {{1.0, 2.0}, {2.0, 2.0}});
        assertEquals(1, RendererUtilities.findLiveItemsUpperBound(d, 2, 0.0, 
                1.0));
        assertEquals(1, RendererUtilities.findLiveItemsUpperBound(d, 2, 1.0, 
                2.2));
        assertEquals(1, RendererUtilities.findLiveItemsUpperBound(d, 2, 2.0, 
                3.3));
        assertEquals(1, RendererUtilities.findLiveItemsUpperBound(d, 2, 3.0, 
                4.4));
        
        // check a series with three items
        d.addSeries("S4", new double[][] {{1.0, 2.0, 3.0}, {2.0, 2.0, 2.0}});
        assertEquals(1, RendererUtilities.findLiveItemsUpperBound(d, 3, 0.0, 
                1.1));
        assertEquals(2, RendererUtilities.findLiveItemsUpperBound(d, 3, 1.0, 
                2.2));
        assertEquals(2, RendererUtilities.findLiveItemsUpperBound(d, 3, 2.0, 
                3.3));
        assertEquals(2, RendererUtilities.findLiveItemsUpperBound(d, 3, 3.0, 
                4.4));
        
        // check a series with four items
        d.addSeries("S5", new double[][] {{1.0, 2.0, 3.0, 4.0}, {2.0, 2.0, 
                2.0, 2.0}});
        assertEquals(1, RendererUtilities.findLiveItemsUpperBound(d, 4, 0.0, 
                1.1));
        assertEquals(2, RendererUtilities.findLiveItemsUpperBound(d, 4, 1.0, 
                2.2));
        assertEquals(3, RendererUtilities.findLiveItemsUpperBound(d, 4, 2.0, 
                3.3));
        assertEquals(3, RendererUtilities.findLiveItemsUpperBound(d, 4, 3.0, 
                4.4));
        assertEquals(3, RendererUtilities.findLiveItemsUpperBound(d, 4, 4.0, 
                5.5));
        
    }

    /**
	 * Some checks for the findLiveItemsUpperBound() method when the dataset is DESCENDING.
	 */
	public void testFindLiveItemsUpperBoundDescending() {
		this.rendererUtilitiesTestsTestFindLiveItemsBoundDescendingTemplate(
				new RendererUtilitiesTestsTestFindLiveItemsUpperBoundDescendingAdapterImpl(), 1, 2, 2, 1, 3, 2, 1);
	}

	public void rendererUtilitiesTestsTestFindLiveItemsBoundDescendingTemplate(
			RendererUtilitiesTestsTestFindLiveItemsBoundDescendingAdapter adapter, int i1, int i2, int i3, int i4,
			int i5, int i6, int i7) {
		DefaultXYDataset d = new DefaultXYDataset() {
			public DomainOrder getDomainOrder() {
				return DomainOrder.DESCENDING;
			}
		};
		d.addSeries("S1", new double[][] { {}, {} });
		assertEquals(0, adapter.findLiveItemsBound(d, 0, 10.0, 11.0));
		d.addSeries("S2", new double[][] { { 1.0 }, { 2.0 } });
		assertEquals(0, adapter.findLiveItemsBound(d, 1, 0.0, 1.0));
		assertEquals(0, adapter.findLiveItemsBound(d, 1, 1.1, 2.0));
		d.addSeries("S3", new double[][] { { 2.0, 1.0 }, { 2.0, 2.0 } });
		assertEquals(1, adapter.findLiveItemsBound(d, 2, 0.1, 0.5));
		assertEquals(i1, adapter.findLiveItemsBound(d, 2, 0.1, 1.0));
		assertEquals(0, adapter.findLiveItemsBound(d, 2, 1.1, 2.0));
		assertEquals(0, adapter.findLiveItemsBound(d, 2, 2.2, 3.0));
		assertEquals(0, adapter.findLiveItemsBound(d, 2, 3.3, 4.0));
		d.addSeries("S4", new double[][] { { 3.0, 2.0, 1.0 }, { 2.0, 2.0, 2.0 } });
		assertEquals(i2, adapter.findLiveItemsBound(d, 3, 0.0, 1.0));
		assertEquals(i3, adapter.findLiveItemsBound(d, 3, 1.0, 2.0));
		assertEquals(i4, adapter.findLiveItemsBound(d, 3, 2.0, 3.0));
		assertEquals(0, adapter.findLiveItemsBound(d, 3, 3.0, 4.0));
		d.addSeries("S5", new double[][] { { 4.0, 3.0, 2.0, 1.0 }, { 2.0, 2.0, 2.0, 2.0 } });
		assertEquals(3, adapter.findLiveItemsBound(d, 4, 0.1, 0.5));
		assertEquals(i5, adapter.findLiveItemsBound(d, 4, 0.1, 1.0));
		assertEquals(i6, adapter.findLiveItemsBound(d, 4, 1.1, 2.0));
		assertEquals(i7, adapter.findLiveItemsBound(d, 4, 2.2, 3.0));
		assertEquals(0, adapter.findLiveItemsBound(d, 4, 3.3, 4.0));
		assertEquals(0, adapter.findLiveItemsBound(d, 4, 4.4, 5.0));
	}

	interface RendererUtilitiesTestsTestFindLiveItemsBoundDescendingAdapter {
		int findLiveItemsBound(XYDataset xYDataset1, int i1, double d1, double d2);
	}

	class RendererUtilitiesTestsTestFindLiveItemsLowerBoundDescendingAdapterImpl
			implements RendererUtilitiesTestsTestFindLiveItemsBoundDescendingAdapter {
		public int findLiveItemsBound(XYDataset d, int i1, double d1, double d2) {
			return RendererUtilities.findLiveItemsLowerBound(d, i1, d1, d2);
		}
	}

	class RendererUtilitiesTestsTestFindLiveItemsUpperBoundDescendingAdapterImpl
			implements RendererUtilitiesTestsTestFindLiveItemsBoundDescendingAdapter {
		public int findLiveItemsBound(XYDataset d, int i1, double d1, double d2) {
			return RendererUtilities.findLiveItemsUpperBound(d, i1, d1, d2);
		}
	}


}
