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
 * ------------------------------------------
 * DefaultMultiValueCategoryDatasetTests.java
 * ------------------------------------------
 * (C) Copyright 2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 28-Sep-2007 : Version 1 (DG);
 *
 */

package org.jfree.data.statistics.junit;

import org.jfree.data.statistics.DefaultMultiValueCategoryDataset;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.jfree.data.UnknownKeyException;

/**
 * Tests for the {@link DefaultMultiValueCategoryDataset} class.
 */
public class DefaultMultiValueCategoryDatasetTests extends TestCase {
    
    /**
     * Returns the tests as a test suite.
     *
     * @return The test suite.
     */
    public static Test suite() {
        return new TestSuite(DefaultMultiValueCategoryDatasetTests.class);
    }

    /**
     * Constructs a new set of tests.
     *
     * @param name  the name of the tests.
     */
    public DefaultMultiValueCategoryDatasetTests(String name) {
        super(name);
    }
    
    /**
     * Some checks for the getValue() method.
     */
    public void testGetValue() {
        DefaultMultiValueCategoryDataset d 
                = new DefaultMultiValueCategoryDataset();
        List values = new ArrayList();
        values.add(new Integer(1));
        values.add(new Integer(2));
        d.add(values, "R1", "C1");
        assertEquals(new Double(1.5), d.getValue("R1", "C1"));
        boolean pass = false;
        try {
            d.getValue("XX", "C1");
        }
        catch (UnknownKeyException e) {
            pass = true;   
        }
        assertTrue(pass);
        
        pass = false;
        try {
            d.getValue("R1", "XX");
        }
        catch (UnknownKeyException e) {
            pass = true;   
        }
        assertTrue(pass);
    }
    
    /**
     * A simple check for the getValue(int, int) method.
     */
    public void testGetValue2() {
        DefaultMultiValueCategoryDataset d 
                = new DefaultMultiValueCategoryDataset();
        boolean pass = false;
        try {
            /* Number n =*/ d.getValue(0, 0);
        }
        catch (IndexOutOfBoundsException e) {
            pass = true;
        }
        assertTrue(pass);
    }
        
    /**
	 * Some tests for the getRowCount() method.
	 */
	public void testGetRowCount() {
		this.defaultMultiValueCategoryDatasetTestsTestGetCountTemplate(
				new DefaultMultiValueCategoryDatasetTestsTestGetRowCountAdapterImpl(), "R2", "C1", "R2", "C1");
	}

    /**
	 * Some tests for the getColumnCount() method.
	 */
	public void testGetColumnCount() {
		this.defaultMultiValueCategoryDatasetTestsTestGetCountTemplate(
				new DefaultMultiValueCategoryDatasetTestsTestGetColumnCountAdapterImpl(), "R1", "C2", "R1", "C2");
	}

    /**
     * Confirm that the equals method can distinguish all the required fields.
     */
    public void testEquals() {
        DefaultMultiValueCategoryDataset d1 
                = new DefaultMultiValueCategoryDataset();
        DefaultMultiValueCategoryDataset d2 
                = new DefaultMultiValueCategoryDataset();
        assertTrue(d1.equals(d2));
        assertTrue(d2.equals(d1));
        
        List values = new ArrayList();
        d1.add(values, "R1", "C1");
        assertFalse(d1.equals(d2));
        d2.add(values, "R1", "C1");
        assertTrue(d1.equals(d2));
        
        values.add(new Integer(99));
        d1.add(values, "R1", "C1");
        assertFalse(d1.equals(d2));
        d2.add(values, "R1", "C1");
        assertTrue(d1.equals(d2));
        
        values.add(new Integer(99));
        d1.add(values, "R1", "C2");
        assertFalse(d1.equals(d2));
        d2.add(values, "R1", "C2");
        assertTrue(d1.equals(d2));        
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    public void testSerialization() {

        DefaultMultiValueCategoryDataset d1 
                = new DefaultMultiValueCategoryDataset();
        DefaultMultiValueCategoryDataset d2 
                = new DefaultMultiValueCategoryDataset();

        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            ObjectOutput out = new ObjectOutputStream(buffer);
            out.writeObject(d1);
            out.close();

            ObjectInput in = new ObjectInputStream(
                    new ByteArrayInputStream(buffer.toByteArray()));
            d2 = (DefaultMultiValueCategoryDataset) in.readObject();
            in.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(d1, d2);

    }
    
    /**
     * Some checks for the add() method.
     */
    public void testAddValue() {
        DefaultMultiValueCategoryDataset d1 
                = new DefaultMultiValueCategoryDataset();
        
        boolean pass = false;
        try {
            d1.add(null, "R1", "C1");
        }
        catch (IllegalArgumentException e) {
            pass = true;
        }
        assertTrue(pass);
            
        List values = new ArrayList();
        d1.add(values, "R2", "C1");
        assertEquals(values, d1.getValues("R2", "C1"));
        
        pass = false;
        try {
            d1.add(values, null, "C2");
        }
        catch (IllegalArgumentException e) {
            pass = true;
        }
        assertTrue(pass);
    }
      
    /**
     * Confirm that cloning works.
     */
    public void testCloning() {
        DefaultMultiValueCategoryDataset d1 
                = new DefaultMultiValueCategoryDataset();
        DefaultMultiValueCategoryDataset d2 = null;
        try {
            d2 = (DefaultMultiValueCategoryDataset) d1.clone();
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        assertTrue(d1 != d2);
        assertTrue(d1.getClass() == d2.getClass());
        assertTrue(d1.equals(d2));
        
        // try a dataset with some content...
        List values = new ArrayList();
        values.add(new Integer(99));
        d1.add(values, "R1", "C1");
        try {
            d2 = (DefaultMultiValueCategoryDataset) d1.clone();
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        assertTrue(d1 != d2);
        assertTrue(d1.getClass() == d2.getClass());
        assertTrue(d1.equals(d2));
        
        // check that the clone doesn't share the same underlying arrays.
        List values2 = new ArrayList();
        values2.add(new Integer(111));
        d1.add(values2, "R2", "C2");
        assertFalse(d1.equals(d2));
        d2.add(values2, "R2", "C2");
        assertTrue(d1.equals(d2));
    }

	public void defaultMultiValueCategoryDatasetTestsTestGetCountTemplate(
			DefaultMultiValueCategoryDatasetTestsTestGetCountAdapter adapter, String string1, String string2,
			String string3, String string4) {
		DefaultMultiValueCategoryDataset d = new DefaultMultiValueCategoryDataset();
		assertTrue(adapter.getCount(d) == 0);
		List values = new ArrayList();
		d.add(values, "R1", "C1");
		assertTrue(adapter.getCount(d) == 1);
		d.add(values, string1, string2);
		assertTrue(adapter.getCount(d) == 2);
		d.add(values, string3, string4);
		assertTrue(adapter.getCount(d) == 2);
	}

	interface DefaultMultiValueCategoryDatasetTestsTestGetCountAdapter {
		int getCount(DefaultMultiValueCategoryDataset defaultMultiValueCategoryDataset1);
	}

	class DefaultMultiValueCategoryDatasetTestsTestGetRowCountAdapterImpl
			implements DefaultMultiValueCategoryDatasetTestsTestGetCountAdapter {
		public int getCount(DefaultMultiValueCategoryDataset d) {
			return d.getRowCount();
		}
	}

	class DefaultMultiValueCategoryDatasetTestsTestGetColumnCountAdapterImpl
			implements DefaultMultiValueCategoryDatasetTestsTestGetCountAdapter {
		public int getCount(DefaultMultiValueCategoryDataset d) {
			return d.getColumnCount();
		}
	}

}
