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
 * --------------------------------
 * DefaultCategoryDatasetTests.java
 * --------------------------------
 * (C) Copyright 2004-2008, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 23-Mar-2004 : Version 1 (DG);
 * 08-Mar-2007 : Added testCloning() (DG);
 * 21-Nov-2007 : Added testBug1835955() method (DG);
 * 09-May-2008 : Added testPublicCloneable() (DG);
 *
 */

package org.jfree.data.category.junit;

import org.jfree.data.category.DefaultCategoryDataset;
import java.lang.Comparable;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.jfree.data.UnknownKeyException;
import org.jfree.util.PublicCloneable;

/**
 * Tests for the {@link DefaultCategoryDataset} class.
 */
public class DefaultCategoryDatasetTests extends TestCase {

    /**
     * Returns the tests as a test suite.
     *
     * @return The test suite.
     */
    public static Test suite() {
        return new TestSuite(DefaultCategoryDatasetTests.class);
    }

    /**
     * Constructs a new set of tests.
     *
     * @param name  the name of the tests.
     */
    public DefaultCategoryDatasetTests(String name) {
        super(name);
    }

    /**
     * Some checks for the getValue() method.
     */
    public void testGetValue() {
        DefaultCategoryDataset d = new DefaultCategoryDataset();
        d.addValue(1.0, "R1", "C1");
        assertEquals(new Double(1.0), d.getValue("R1", "C1"));
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
        DefaultCategoryDataset d = new DefaultCategoryDataset();
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
     * Some checks for the incrementValue() method.
     */
    public void testIncrementValue() {
        DefaultCategoryDataset d = new DefaultCategoryDataset();
        d.addValue(1.0, "R1", "C1");
        d.incrementValue(2.0, "R1", "C1");
        assertEquals(new Double(3.0), d.getValue("R1", "C1"));

        // increment a null value
        d.addValue(null, "R2", "C1");
        d.incrementValue(2.0, "R2", "C1");
        assertEquals(new Double(2.0), d.getValue("R2", "C1"));

        // increment an unknown row
        boolean pass = false;
        try {
            d.incrementValue(1.0, "XX", "C1");
        }
        catch (UnknownKeyException e) {
            pass = true;
        }
        assertTrue(pass);

        // increment an unknown column
        pass = false;
        try {
            d.incrementValue(1.0, "R1", "XX");
        }
        catch (UnknownKeyException e) {
            pass = true;
        }
        assertTrue(pass);
    }

    /**
	 * Some tests for the getRowCount() method.
	 */
	public void testGetRowCount() {
		this.defaultCategoryDatasetTestsTestGetCountTemplate(
				new DefaultCategoryDatasetTestsTestGetRowCountAdapterImpl(), "R2", "C1", "R2", "C1", "R2", "C1");
	}

    /**
	 * Some tests for the getColumnCount() method.
	 */
	public void testGetColumnCount() {
		this.defaultCategoryDatasetTestsTestGetCountTemplate(
				new DefaultCategoryDatasetTestsTestGetColumnCountAdapterImpl(), "R1", "C2", "R1", "C2", "R1", "C2");
	}

    /**
     * Confirm that the equals method can distinguish all the required fields.
     */
    public void testEquals() {
        DefaultCategoryDataset d1 = new DefaultCategoryDataset();
        d1.setValue(23.4, "R1", "C1");
        DefaultCategoryDataset d2 = new DefaultCategoryDataset();
        d2.setValue(23.4, "R1", "C1");
        assertTrue(d1.equals(d2));
        assertTrue(d2.equals(d1));

        d1.setValue(36.5, "R1", "C2");
        assertFalse(d1.equals(d2));
        d2.setValue(36.5, "R1", "C2");
        assertTrue(d1.equals(d2));

        d1.setValue(null, "R1", "C1");
        assertFalse(d1.equals(d2));
        d2.setValue(null, "R1", "C1");
        assertTrue(d1.equals(d2));
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    public void testSerialization() {

        DefaultCategoryDataset d1 = new DefaultCategoryDataset();
        d1.setValue(23.4, "R1", "C1");
        DefaultCategoryDataset d2 = null;

        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            ObjectOutput out = new ObjectOutputStream(buffer);
            out.writeObject(d1);
            out.close();

            ObjectInput in = new ObjectInputStream(
                    new ByteArrayInputStream(buffer.toByteArray()));
            d2 = (DefaultCategoryDataset) in.readObject();
            in.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(d1, d2);

    }

    /**
     * Some checks for the addValue() method.
     */
    public void testAddValue() {
        DefaultCategoryDataset d1 = new DefaultCategoryDataset();
        d1.addValue(null, "R1", "C1");
        assertNull(d1.getValue("R1", "C1"));
        d1.addValue(new Double(1.0), "R2", "C1");
        assertEquals(new Double(1.0), d1.getValue("R2", "C1"));

        boolean pass = false;
        try {
            d1.addValue(new Double(1.1), null, "C2");
        }
        catch (IllegalArgumentException e) {
            pass = true;
        }
        assertTrue(pass);
    }

    /**
     * Some basic checks for the removeValue() method.
     */
    public void testRemoveValue() {
        DefaultCategoryDataset d = new DefaultCategoryDataset();
        d.removeValue("R1", "C1");
        d.addValue(new Double(1.0), "R1", "C1");
        d.removeValue("R1", "C1");
        assertEquals(0, d.getRowCount());
        assertEquals(0, d.getColumnCount());

        d.addValue(new Double(1.0), "R1", "C1");
        d.addValue(new Double(2.0), "R2", "C1");
        d.removeValue("R1", "C1");
        assertEquals(new Double(2.0), d.getValue(0, 0));

        boolean pass = false;
        try {
            d.removeValue(null, "C1");
        }
        catch (IllegalArgumentException e) {
            pass = true;
        }
        assertTrue(pass);

        pass = false;
        try {
            d.removeValue("R1", null);
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
        DefaultCategoryDataset d1 = new DefaultCategoryDataset();
        DefaultCategoryDataset d2 = null;
        try {
            d2 = (DefaultCategoryDataset) d1.clone();
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        assertTrue(d1 != d2);
        assertTrue(d1.getClass() == d2.getClass());
        assertTrue(d1.equals(d2));

        // try a dataset with some content...
        d1.addValue(1.0, "R1", "C1");
        d1.addValue(2.0, "R1", "C2");
        try {
            d2 = (DefaultCategoryDataset) d1.clone();
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        assertTrue(d1 != d2);
        assertTrue(d1.getClass() == d2.getClass());
        assertTrue(d1.equals(d2));

        // check that the clone doesn't share the same underlying arrays.
        d1.addValue(3.0, "R1", "C1");
        assertFalse(d1.equals(d2));
        d2.addValue(3.0, "R1", "C1");
        assertTrue(d1.equals(d2));
    }

    /**
     * Check that this class implements PublicCloneable.
     */
    public void testPublicCloneable() {
    	DefaultCategoryDataset d = new DefaultCategoryDataset();
    	assertTrue(d instanceof PublicCloneable);
    }

    private static final double EPSILON = 0.0000000001;

    /**
     * A test for bug 1835955.
     */
    public void testBug1835955() {
        DefaultCategoryDataset d = new DefaultCategoryDataset();
        d.addValue(1.0, "R1", "C1");
        d.addValue(2.0, "R2", "C2");
        d.removeColumn("C2");
        d.addValue(3.0, "R2", "C2");
        assertEquals(3.0, d.getValue("R2", "C2").doubleValue(), EPSILON);
    }

    /**
	 * Some checks for the removeColumn(Comparable) method.
	 */
	public void testRemoveColumn() {
		this.defaultCategoryDatasetTestsTestRemoveTemplate(new DefaultCategoryDatasetTestsTestRemoveColumnAdapterImpl(),
				"C2");
	}

    /**
	 * Some checks for the removeRow(Comparable) method.
	 */
	public void testRemoveRow() {
		this.defaultCategoryDatasetTestsTestRemoveTemplate(new DefaultCategoryDatasetTestsTestRemoveRowAdapterImpl(),
				"R2");
	}

	public void defaultCategoryDatasetTestsTestRemoveTemplate(DefaultCategoryDatasetTestsTestRemoveAdapter adapter,
			String string1) {
		DefaultCategoryDataset d = new DefaultCategoryDataset();
		d.addValue(1.0, "R1", "C1");
		d.addValue(2.0, "R2", "C2");
		assertEquals(2, adapter.getCount(d));
		adapter.remove(d, string1);
		assertEquals(1, adapter.getCount(d));
		boolean pass = false;
		try {
			adapter.remove(d, "XXX");
		} catch (UnknownKeyException e) {
			pass = true;
		}
		assertTrue(pass);
		pass = false;
		try {
			adapter.remove(d, null);
		} catch (IllegalArgumentException e) {
			pass = true;
		}
		assertTrue(pass);
	}

	interface DefaultCategoryDatasetTestsTestRemoveAdapter {
		int getCount(DefaultCategoryDataset defaultCategoryDataset1);

		void remove(DefaultCategoryDataset defaultCategoryDataset1, Comparable comparable1);
	}

	class DefaultCategoryDatasetTestsTestRemoveColumnAdapterImpl
			implements DefaultCategoryDatasetTestsTestRemoveAdapter {
		public int getCount(DefaultCategoryDataset d) {
			return d.getColumnCount();
		}

		public void remove(DefaultCategoryDataset d, Comparable comparable1) {
			d.removeColumn(comparable1);
		}
	}

	class DefaultCategoryDatasetTestsTestRemoveRowAdapterImpl implements DefaultCategoryDatasetTestsTestRemoveAdapter {
		public int getCount(DefaultCategoryDataset d) {
			return d.getRowCount();
		}

		public void remove(DefaultCategoryDataset d, Comparable comparable1) {
			d.removeRow(comparable1);
		}
	}

	public void defaultCategoryDatasetTestsTestGetCountTemplate(DefaultCategoryDatasetTestsTestGetCountAdapter adapter,
			String string1, String string2, String string3, String string4, String string5, String string6) {
		DefaultCategoryDataset d = new DefaultCategoryDataset();
		assertTrue(adapter.getCount(d) == 0);
		d.addValue(1.0, "R1", "C1");
		assertTrue(adapter.getCount(d) == 1);
		d.addValue(1.0, string1, string2);
		assertTrue(adapter.getCount(d) == 2);
		d.addValue(2.0, string3, string4);
		assertTrue(adapter.getCount(d) == 2);
		d.setValue(null, string5, string6);
		assertTrue(adapter.getCount(d) == 2);
	}

	interface DefaultCategoryDatasetTestsTestGetCountAdapter {
		int getCount(DefaultCategoryDataset defaultCategoryDataset1);
	}

	class DefaultCategoryDatasetTestsTestGetRowCountAdapterImpl
			implements DefaultCategoryDatasetTestsTestGetCountAdapter {
		public int getCount(DefaultCategoryDataset d) {
			return d.getRowCount();
		}
	}

	class DefaultCategoryDatasetTestsTestGetColumnCountAdapterImpl
			implements DefaultCategoryDatasetTestsTestGetCountAdapter {
		public int getCount(DefaultCategoryDataset d) {
			return d.getColumnCount();
		}
	}

}
