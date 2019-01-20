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
 * ---------------------------------
 * BoxAndWhiskerCalculatorTests.java
 * ---------------------------------
 * (C) Copyright 2003-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 28-Aug-2003 : Version 1 (DG);
 *
 */

package org.jfree.data.statistics.junit;

import java.util.List;
import java.util.ArrayList;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.jfree.data.statistics.BoxAndWhiskerCalculator;
import org.jfree.data.statistics.BoxAndWhiskerItem;

/**
 * Tests for the {@link BoxAndWhiskerCalculator} class.
 */
public class BoxAndWhiskerCalculatorTests extends TestCase {

    /**
     * Returns the tests as a test suite.
     *
     * @return The test suite.
     */
    public static Test suite() {
        return new TestSuite(BoxAndWhiskerCalculatorTests.class);
    }

    /**
     * Constructs a new set of tests.
     *
     * @param name  the name of the tests.
     */
    public BoxAndWhiskerCalculatorTests(String name) {
        super(name);
    }
    
    /**
     * Some checks for the calculateBoxAndWhiskerStatistics() method.
     */
    public void testCalculateBoxAndWhiskerStatistics() {
        
        // try null list
        boolean pass = false;
        try {
            BoxAndWhiskerCalculator.calculateBoxAndWhiskerStatistics(null);
        }
        catch (IllegalArgumentException e) {
            pass = true;
        }
        assertTrue(pass);
                
        // try a list containing a single value
        List values = new ArrayList();
        values.add(new Double(1.1));
        BoxAndWhiskerItem item 
            = BoxAndWhiskerCalculator.calculateBoxAndWhiskerStatistics(values);
        assertEquals(1.1, item.getMean().doubleValue(), EPSILON);
        assertEquals(1.1, item.getMedian().doubleValue(), EPSILON);
        assertEquals(1.1, item.getQ1().doubleValue(), EPSILON);
        assertEquals(1.1, item.getQ3().doubleValue(), EPSILON);       
    }
    
    private static final double EPSILON = 0.000000001;
    
    /**
	 * Tests the Q1 calculation.
	 */
	public void testCalculateQ1() {
		this.boxAndWhiskerCalculatorTestsTestCalculateTemplate(
				new BoxAndWhiskerCalculatorTestsTestCalculateQ1AdapterImpl(), 1.0, 1.5, 1.5);
	}

    /**
	 * Tests the Q3 calculation.
	 */
	public void testCalculateQ3() {
		this.boxAndWhiskerCalculatorTestsTestCalculateTemplate(
				new BoxAndWhiskerCalculatorTestsTestCalculateQ3AdapterImpl(), 2.0, 2.5, 3.5);
	}
    
    /**
     * The test case included in bug report 1593149.
     */
    public void test1593149() {
        ArrayList theList = new ArrayList(5);
        theList.add(0, new Double(1.0));
        theList.add(1, new Double(2.0));
        theList.add(2, new Double(Double.NaN));
        theList.add(3, new Double(3.0));
        theList.add(4, new Double(4.0));
        BoxAndWhiskerItem theItem =
            BoxAndWhiskerCalculator.calculateBoxAndWhiskerStatistics(theList);
        assertEquals(1.0, theItem.getMinRegularValue().doubleValue(), EPSILON);
        assertEquals(4.0, theItem.getMaxRegularValue().doubleValue(), EPSILON);
    }

	public void boxAndWhiskerCalculatorTestsTestCalculateTemplate(
			BoxAndWhiskerCalculatorTestsTestCalculateAdapter adapter, double d1, double d2, double d3) {
		boolean pass = false;
		try {
			adapter.calculate(null);
		} catch (IllegalArgumentException e) {
			pass = true;
		}
		assertTrue(pass);
		List values = new ArrayList();
		double v1 = adapter.calculate(values);
		assertTrue(Double.isNaN(v1));
		values.add(new Double(1.0));
		v1 = adapter.calculate(values);
		assertEquals(v1, 1.0, EPSILON);
		values.add(new Double(2.0));
		v1 = adapter.calculate(values);
		assertEquals(v1, d1, EPSILON);
		values.add(new Double(3.0));
		v1 = adapter.calculate(values);
		assertEquals(v1, d2, EPSILON);
		values.add(new Double(4.0));
		v1 = adapter.calculate(values);
		assertEquals(v1, d3, EPSILON);
	}

	interface BoxAndWhiskerCalculatorTestsTestCalculateAdapter {
		double calculate(List list1);
	}

	class BoxAndWhiskerCalculatorTestsTestCalculateQ1AdapterImpl
			implements BoxAndWhiskerCalculatorTestsTestCalculateAdapter {
		public double calculate(List list1) {
			return BoxAndWhiskerCalculator.calculateQ1(list1);
		}
	}

	class BoxAndWhiskerCalculatorTestsTestCalculateQ3AdapterImpl
			implements BoxAndWhiskerCalculatorTestsTestCalculateAdapter {
		public double calculate(List list1) {
			return BoxAndWhiskerCalculator.calculateQ3(list1);
		}
	}
}
