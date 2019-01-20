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
 * -----------------------
 * DataUtilitiesTests.java
 * -----------------------
 * (C) Copyright 2005, 2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 03-Mar-2005 : Version 1 (DG);
 *
 */

package org.jfree.data.junit;

import org.jfree.data.Values2D;
import junit.framework.TestCase;

import org.jfree.data.DataUtilities;
import org.jfree.data.DefaultKeyedValues2D;

/**
 * Some tests for the {@link DataUtilities} class.
 */
public class DataUtilitiesTests extends TestCase {
    
    /**
     * Tests the createNumberArray2D() method.
     */
    public void testCreateNumberArray2D() {
        double[][] d = new double[2][];
        d[0] = new double[] {1.1, 2.2, 3.3, 4.4};
        d[1] = new double[] {1.1, 2.2, 3.3, 4.4, 5.5};
        Number[][] n = DataUtilities.createNumberArray2D(d);
        assertEquals(2, n.length);
        assertEquals(4, n[0].length);
        assertEquals(5, n[1].length);
    }

    private static final double EPSILON = 0.000000001;
    
    /**
	 * Some checks for the calculateColumnTotal() method.
	 */
	public void testCalculateColumnTotal() {
		this.dataUtilitiesTestsTestCalculateTotalTemplate(new DataUtilitiesTestsTestCalculateColumnTotalAdapterImpl(),
				4.0, 6.0, 2.0);
	}
    
    /**
	 * Some checks for the calculateRowTotal() method.
	 */
	public void testCalculateRowTotal() {
		this.dataUtilitiesTestsTestCalculateTotalTemplate(new DataUtilitiesTestsTestCalculateRowTotalAdapterImpl(), 3.0,
				7.0, 3.0);
	}

	public void dataUtilitiesTestsTestCalculateTotalTemplate(DataUtilitiesTestsTestCalculateTotalAdapter adapter,
			double d1, double d2, double d3) {
		DefaultKeyedValues2D table = new DefaultKeyedValues2D();
		table.addValue(new Double(1.0), "R0", "C0");
		table.addValue(new Double(2.0), "R0", "C1");
		table.addValue(new Double(3.0), "R1", "C0");
		table.addValue(new Double(4.0), "R1", "C1");
		assertEquals(d1, adapter.calculateTotal(table, 0), EPSILON);
		assertEquals(d2, adapter.calculateTotal(table, 1), EPSILON);
		table.setValue(null, "R1", "C1");
		assertEquals(d3, adapter.calculateTotal(table, 1), EPSILON);
	}

	interface DataUtilitiesTestsTestCalculateTotalAdapter {
		double calculateTotal(Values2D values2D1, int i1);
	}

	class DataUtilitiesTestsTestCalculateColumnTotalAdapterImpl implements DataUtilitiesTestsTestCalculateTotalAdapter {
		public double calculateTotal(Values2D table, int i1) {
			return DataUtilities.calculateColumnTotal(table, i1);
		}
	}

	class DataUtilitiesTestsTestCalculateRowTotalAdapterImpl implements DataUtilitiesTestsTestCalculateTotalAdapter {
		public double calculateTotal(Values2D table, int i1) {
			return DataUtilities.calculateRowTotal(table, i1);
		}
	}
}
