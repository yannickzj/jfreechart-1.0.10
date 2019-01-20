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
 * ------------------------------
 * TaskSeriesCollectionTests.java
 * ------------------------------
 * (C) Copyright 2004-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 30-Jul-2004 : Version 1 (DG);
 * 12-Jan-2005 : Added tests from TaskSeriesCollectionTests2.java (DG);
 * 08-Mar-2007 : Added testRemove() (DG);
 *
 */

package org.jfree.data.gantt.junit;

import java.lang.Comparable;
import org.jfree.data.gantt.TaskSeriesCollection;
import java.lang.Number;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Date;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.time.SimpleTimePeriod;

/**
 * Tests for the {@link TaskSeriesCollection} class.
 */
public class TaskSeriesCollectionTests extends TestCase {

    /**
     * Returns the tests as a test suite.
     *
     * @return The test suite.
     */
    public static Test suite() {
        return new TestSuite(TaskSeriesCollectionTests.class);
    }

    /**
     * Constructs a new set of tests.
     *
     * @param name  the name of the tests.
     */
    public TaskSeriesCollectionTests(String name) {
        super(name);
    }

    /**
     * Creates a sample collection for testing purposes.
     *
     * @return A sample collection.
     */
    private TaskSeriesCollection createCollection1() {
        TaskSeriesCollection result = new TaskSeriesCollection();
        TaskSeries s1 = new TaskSeries("S1");
        s1.add(new Task("Task 1", new Date(1), new Date(2)));
        s1.add(new Task("Task 2", new Date(3), new Date(4)));
        result.add(s1);
        TaskSeries s2 = new TaskSeries("S2");
        s2.add(new Task("Task 3", new Date(5), new Date(6)));
        result.add(s2);
        return result;
    }

    /**
     * Creates a sample collection for testing purposes.
     *
     * @return A sample collection.
     */
    private TaskSeriesCollection createCollection2() {
        TaskSeriesCollection result = new TaskSeriesCollection();
        TaskSeries s1 = new TaskSeries("S1");
        Task t1 = new Task("Task 1", new Date(10), new Date(20));
        t1.addSubtask(new Task("Task 1A", new Date(10), new Date(15)));
        t1.addSubtask(new Task("Task 1B", new Date(16), new Date(20)));
        t1.setPercentComplete(0.10);
        s1.add(t1);
        Task t2 = new Task("Task 2", new Date(30), new Date(40));
        t2.addSubtask(new Task("Task 2A", new Date(30), new Date(35)));
        t2.addSubtask(new Task("Task 2B", new Date(36), new Date(40)));
        t2.setPercentComplete(0.20);
        s1.add(t2);
        result.add(s1);
        TaskSeries s2 = new TaskSeries("S2");
        Task t3 = new Task("Task 3", new Date(50), new Date(60));
        t3.addSubtask(new Task("Task 3A", new Date(50), new Date(55)));
        t3.addSubtask(new Task("Task 3B", new Date(56), new Date(60)));
        t3.setPercentComplete(0.30);
        s2.add(t3);
        result.add(s2);
        return result;
    }

    /**
     * Creates a sample collection for testing purposes.
     *
     * @return A sample collection.
     */
    private TaskSeriesCollection createCollection3() {

        // define subtasks
        Task sub1 = new Task("Sub1", new Date(11), new Date(111));
        Task sub2 = new Task("Sub2", new Date(22), new Date(222));
        Task sub3 = new Task("Sub3", new Date(33), new Date(333));
        Task sub4 = new Task("Sub4", new Date(44), new Date(444));
        Task sub5 = new Task("Sub5", new Date(55), new Date(555));
        Task sub6 = new Task("Sub6", new Date(66), new Date(666));
        sub1.setPercentComplete(0.111);
        sub2.setPercentComplete(0.222);
        sub3.setPercentComplete(0.333);
        sub4.setPercentComplete(0.444);
        sub5.setPercentComplete(0.555);
        sub6.setPercentComplete(0.666);

        TaskSeries seriesA = new TaskSeries("Series A");
        Task taskA1 = new Task("Task 1", new SimpleTimePeriod(new Date(100),
                new Date(200)));
        taskA1.setPercentComplete(0.1);
        taskA1.addSubtask(sub1);
        Task taskA2 = new Task("Task 2", new SimpleTimePeriod(new Date(220),
                new Date(350)));
        taskA2.setPercentComplete(0.2);
        taskA2.addSubtask(sub2);
        taskA2.addSubtask(sub3);
        seriesA.add(taskA1);
        seriesA.add(taskA2);

        TaskSeries seriesB = new TaskSeries("Series B");
        // note that we don't define taskB1
        Task taskB2 = new Task("Task 2", new SimpleTimePeriod(new Date(2220),
                new Date(3350)));
        taskB2.setPercentComplete(0.3);
        taskB2.addSubtask(sub4);
        taskB2.addSubtask(sub5);
        taskB2.addSubtask(sub6);
        seriesB.add(taskB2);

        TaskSeriesCollection tsc = new TaskSeriesCollection();
        tsc.add(seriesA);
        tsc.add(seriesB);

        return tsc;
    }

    /**
     * A test for the getSeriesCount() method.
     */
    public void testGetSeriesCount() {
        TaskSeriesCollection c = createCollection1();
        assertEquals(2, c.getSeriesCount());
    }

    /**
     * Some tests for the getSeriesKey() method.
     */
    public void testGetSeriesKey() {
        TaskSeriesCollection c = createCollection1();
        assertEquals("S1", c.getSeriesKey(0));
        assertEquals("S2", c.getSeriesKey(1));
    }

    /**
     * A test for the getRowCount() method.
     */
    public void testGetRowCount() {
        TaskSeriesCollection c = createCollection1();
        assertEquals(2, c.getRowCount());
    }

    /**
     * Some tests for the getRowKey() method.
     */
    public void testGetRowKey() {
        TaskSeriesCollection c = createCollection1();
        assertEquals("S1", c.getRowKey(0));
        assertEquals("S2", c.getRowKey(1));
    }

    /**
     * Some tests for the getRowIndex() method.
     */
    public void testGetRowIndex() {
        TaskSeriesCollection c = createCollection1();
        assertEquals(0, c.getRowIndex("S1"));
        assertEquals(1, c.getRowIndex("S2"));
    }

    /**
     * Some tests for the getValue() method.
     */
    public void testGetValue() {
        TaskSeriesCollection c = createCollection1();
        assertEquals(new Long(1L), c.getValue("S1", "Task 1"));
        assertEquals(new Long(3L), c.getValue("S1", "Task 2"));
        assertEquals(new Long(5L), c.getValue("S2", "Task 3"));

        assertEquals(new Long(1L), c.getValue(0, 0));
        assertEquals(new Long(3L), c.getValue(0, 1));
        assertEquals(null, c.getValue(0, 2));
        assertEquals(null, c.getValue(1, 0));
        assertEquals(null, c.getValue(1, 1));
        assertEquals(new Long(5L), c.getValue(1, 2));
    }

    /**
	 * Some tests for the getStartValue() method.
	 */
	public void testGetStartValue() {
		this.taskSeriesCollectionTestsTestGetValueTemplate(new TaskSeriesCollectionTestsTestGetStartValueAdapterImpl(),
				1L, 3L, 5L, 1L, 3L, 5L, 100, 220, 2220);
	}

    /**
	 * Some tests for the getStartValue() method for sub-intervals.
	 */
	public void testGetStartValue2() {
		this.taskSeriesCollectionTestsTestGetValue2Template(
				new TaskSeriesCollectionTestsTestGetStartValue2AdapterImpl(), 10L, 16L, 30L, 36L, 50L, 56L, 10L, 16L,
				30L, 36L, 50L, 56L, 11, 22, 33, 44, 55, 66);
	}

    /**
	 * A check for a null task duration.
	 */
	public void testGetStartValue3() {
		this.taskSeriesCollectionTestsTestGetValue3Template(
				new TaskSeriesCollectionTestsTestGetStartValue3AdapterImpl());
	}

    /**
	 * Some tests for the getEndValue() method.
	 */
	public void testGetEndValue() {
		this.taskSeriesCollectionTestsTestGetValueTemplate(new TaskSeriesCollectionTestsTestGetEndValueAdapterImpl(),
				2L, 4L, 6L, 2L, 4L, 6L, 200, 350, 3350);
	}

    /**
	 * Some tests for the getEndValue() method for sub-intervals.
	 */
	public void testGetEndValue2() {
		this.taskSeriesCollectionTestsTestGetValue2Template(new TaskSeriesCollectionTestsTestGetEndValue2AdapterImpl(),
				15L, 20L, 35L, 40L, 55L, 60L, 15L, 20L, 35L, 40L, 55L, 60L, 111, 222, 333, 444, 555, 666);
	}

    /**
	 * A check for a null task duration.
	 */
	public void testGetEndValue3() {
		this.taskSeriesCollectionTestsTestGetValue3Template(new TaskSeriesCollectionTestsTestGetEndValue3AdapterImpl());
	}

    /**
     * Some tests for the getPercentComplete() method.
     */
    public void testGetPercentComplete() {
        TaskSeriesCollection c = createCollection2();
        assertEquals(new Double(0.10), c.getPercentComplete("S1", "Task 1"));
        assertEquals(new Double(0.20), c.getPercentComplete("S1", "Task 2"));
        assertEquals(new Double(0.30), c.getPercentComplete("S2", "Task 3"));

        assertEquals(new Double(0.10), c.getPercentComplete(0, 0));
        assertEquals(new Double(0.20), c.getPercentComplete(0, 1));
        assertEquals(null, c.getPercentComplete(0, 2));
        assertEquals(null, c.getPercentComplete(1, 0));
        assertEquals(null, c.getPercentComplete(1, 1));
        assertEquals(new Double(0.30), c.getPercentComplete(1, 2));

        // test collection 3, which doesn't define all tasks in all series
        TaskSeriesCollection c3 = createCollection3();
        assertEquals(new Double(0.1), c3.getPercentComplete(0, 0));
        assertEquals(new Double(0.2), c3.getPercentComplete(0, 1));
        assertTrue(c3.getPercentComplete(1, 0) == null);
        assertEquals(new Double(0.3), c3.getPercentComplete(1, 1));

        assertEquals(new Double(0.111), c3.getPercentComplete(0, 0, 0));

        assertEquals(new Double(0.222), c3.getPercentComplete(0, 1, 0));
        assertEquals(new Double(0.333), c3.getPercentComplete(0, 1, 1));

        assertEquals(new Double(0.444), c3.getPercentComplete(1, 1, 0));
        assertEquals(new Double(0.555), c3.getPercentComplete(1, 1, 1));
        assertEquals(new Double(0.666), c3.getPercentComplete(1, 1, 2));
    }

    /**
     * A test for the getColumnCount() method.
     */
    public void testGetColumnCount() {
        TaskSeriesCollection c = createCollection1();
        assertEquals(3, c.getColumnCount());
    }

    /**
     * Some tests for the getColumnKey() method.
     */
    public void testGetColumnKey() {
        TaskSeriesCollection c = createCollection1();
        assertEquals("Task 1", c.getColumnKey(0));
        assertEquals("Task 2", c.getColumnKey(1));
        assertEquals("Task 3", c.getColumnKey(2));
    }

    /**
     * Some tests for the getColumnIndex() method.
     */
    public void testGetColumnIndex() {
        TaskSeriesCollection c = createCollection1();
        assertEquals(0, c.getColumnIndex("Task 1"));
        assertEquals(1, c.getColumnIndex("Task 2"));
        assertEquals(2, c.getColumnIndex("Task 3"));
    }

    /**
     * Confirm that the equals method can distinguish all the required fields.
     */
    public void testEquals() {

        TaskSeries s1 = new TaskSeries("S");
        s1.add(new Task("T1", new Date(1), new Date(2)));
        s1.add(new Task("T2", new Date(11), new Date(22)));
        TaskSeries s2 = new TaskSeries("S");
        s2.add(new Task("T1", new Date(1), new Date(2)));
        s2.add(new Task("T2", new Date(11), new Date(22)));
        TaskSeriesCollection c1 = new TaskSeriesCollection();
        c1.add(s1);
        c1.add(s2);

        TaskSeries s1b = new TaskSeries("S");
        s1b.add(new Task("T1", new Date(1), new Date(2)));
        s1b.add(new Task("T2", new Date(11), new Date(22)));
        TaskSeries s2b = new TaskSeries("S");
        s2b.add(new Task("T1", new Date(1), new Date(2)));
        s2b.add(new Task("T2", new Date(11), new Date(22)));
        TaskSeriesCollection c2 = new TaskSeriesCollection();
        c2.add(s1b);
        c2.add(s2b);

        assertTrue(c1.equals(c2));
        assertTrue(c2.equals(c1));

    }

    /**
     * Confirm that cloning works.
     */
    public void testCloning() {
        TaskSeries s1 = new TaskSeries("S1");
        s1.add(new Task("T1", new Date(1), new Date(2)));
        s1.add(new Task("T2", new Date(11), new Date(22)));
        TaskSeries s2 = new TaskSeries("S2");
        s2.add(new Task("T1", new Date(33), new Date(44)));
        s2.add(new Task("T2", new Date(55), new Date(66)));
        TaskSeriesCollection c1 = new TaskSeriesCollection();
        c1.add(s1);
        c1.add(s2);

        TaskSeriesCollection c2 = null;
        try {
            c2 = (TaskSeriesCollection) c1.clone();
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        assertTrue(c1 != c2);
        assertTrue(c1.getClass() == c2.getClass());
        assertTrue(c1.equals(c2));

        // basic check for independence
        s1.add(new Task("T3", new Date(21), new Date(33)));
        assertFalse(c1.equals(c2));
        TaskSeries series = c2.getSeries("S1");
        series.add(new Task("T3", new Date(21), new Date(33)));
        assertTrue(c1.equals(c2));

    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    public void testSerialization() {

        TaskSeries s1 = new TaskSeries("S");
        s1.add(new Task("T1", new Date(1), new Date(2)));
        s1.add(new Task("T2", new Date(11), new Date(22)));
        TaskSeries s2 = new TaskSeries("S");
        s2.add(new Task("T1", new Date(1), new Date(2)));
        s2.add(new Task("T2", new Date(11), new Date(22)));
        TaskSeriesCollection c1 = new TaskSeriesCollection();
        c1.add(s1);
        c1.add(s2);
        TaskSeriesCollection c2 = null;

        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            ObjectOutput out = new ObjectOutputStream(buffer);
            out.writeObject(c1);
            out.close();

            ObjectInput in = new ObjectInputStream(new ByteArrayInputStream(
                    buffer.toByteArray()));
            c2 = (TaskSeriesCollection) in.readObject();
            in.close();
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
        assertEquals(c1, c2);

    }

    /**
     * A test for bug report 697153.
     */
    public void test697153() {

        TaskSeries s1 = new TaskSeries("S1");
        s1.add(new Task("Task 1", new SimpleTimePeriod(new Date(),
                new Date())));
        s1.add(new Task("Task 2", new SimpleTimePeriod(new Date(),
                new Date())));
        s1.add(new Task("Task 3", new SimpleTimePeriod(new Date(),
                new Date())));

        TaskSeries s2 = new TaskSeries("S2");
        s2.add(new Task("Task 2", new SimpleTimePeriod(new Date(),
                new Date())));
        s2.add(new Task("Task 3", new SimpleTimePeriod(new Date(),
                new Date())));
        s2.add(new Task("Task 4", new SimpleTimePeriod(new Date(),
                new Date())));

        TaskSeriesCollection tsc = new TaskSeriesCollection();
        tsc.add(s1);
        tsc.add(s2);

        s1.removeAll();

        int taskCount = tsc.getColumnCount();

        assertEquals(3, taskCount);

    }

    /**
     * A test for bug report 800324.
     */
    public void test800324() {
        TaskSeries s1 = new TaskSeries("S1");
        s1.add(new Task("Task 1", new SimpleTimePeriod(new Date(),
                new Date())));
        s1.add(new Task("Task 2", new SimpleTimePeriod(new Date(),
                new Date())));
        s1.add(new Task("Task 3", new SimpleTimePeriod(new Date(),
                new Date())));

        TaskSeriesCollection tsc = new TaskSeriesCollection();
        tsc.add(s1);

        // these methods should throw an IndexOutOfBoundsException since the
        // column is too high...
        try {
            /* Number start = */ tsc.getStartValue(0, 3);
            assertTrue(false);
        }
        catch (IndexOutOfBoundsException e) {
            // expected
        }
        try {
            /* Number end = */ tsc.getEndValue(0, 3);
            assertTrue(false);
        }
        catch (IndexOutOfBoundsException e) {
            // expected
        }
        try {
            /* int count = */ tsc.getSubIntervalCount(0, 3);
            assertTrue(false);
        }
        catch (IndexOutOfBoundsException e) {
            // expected
        }
    }

    /**
     * Some tests for the bug report 1099331.  We create a TaskSeriesCollection
     * with two series - the first series has two tasks, but the second has
     * only one.  The key is to ensure that the methods in TaskSeriesCollection
     * translate the index values to key values *before* accessing the tasks
     * in the series.
     */
    public void testGetSubIntervalCount() {
        TaskSeriesCollection tsc = createCollection3();
        assertEquals(1, tsc.getSubIntervalCount(0, 0));
        assertEquals(2, tsc.getSubIntervalCount(0, 1));
        assertEquals(0, tsc.getSubIntervalCount(1, 0));
        assertEquals(3, tsc.getSubIntervalCount(1, 1));
    }

    /**
     * Some basic tests for the getSeries() methods.
     */
    public void testGetSeries() {
        TaskSeries s1 = new TaskSeries("S1");
        TaskSeries s2 = new TaskSeries("S2");
        TaskSeriesCollection c = new TaskSeriesCollection();
        c.add(s1);

        assertEquals(c.getSeries(0), s1);
        assertEquals(c.getSeries("S1"), s1);
        assertEquals(c.getSeries("XX"), null);

        c.add(s2);
        assertEquals(c.getSeries(1), s2);
        assertEquals(c.getSeries("S2"), s2);

        boolean pass = false;
        try {
            c.getSeries(null);
        }
        catch (NullPointerException e) {
            pass = true;
        }
        assertTrue(pass);
    }

    /**
     * Some basic checks for the remove() method.
     */
    public void testRemove() {
        TaskSeriesCollection c = new TaskSeriesCollection();
        TaskSeries s1 = new TaskSeries("S1");
        c.add(s1);
        assertEquals("S1", c.getSeries(0).getKey());
        c.remove(0);
        assertEquals(0, c.getSeriesCount());
        c.add(s1);

        boolean pass = false;
        try {
            c.remove(-1);
        }
        catch (IllegalArgumentException e) {
            pass = true;
        }
        assertTrue(pass);

        pass = false;
        try {
            c.remove(1);
        }
        catch (IllegalArgumentException e) {
            pass = true;
        }
        assertTrue(pass);
    }

	public void taskSeriesCollectionTestsTestGetValue2Template(TaskSeriesCollectionTestsTestGetValue2Adapter adapter,
			long l1, long l2, long l3, long l4, long l5, long l6, long l7, long l8, long l9, long l10, long l11,
			long l12, int i1, int i2, int i3, int i4, int i5, int i6) {
		TaskSeriesCollection c = createCollection2();
		assertEquals(new Long(l1), adapter.getValue(c, "S1", "Task 1", 0));
		assertEquals(new Long(l2), adapter.getValue(c, "S1", "Task 1", 1));
		assertEquals(new Long(l3), adapter.getValue(c, "S1", "Task 2", 0));
		assertEquals(new Long(l4), adapter.getValue(c, "S1", "Task 2", 1));
		assertEquals(new Long(l5), adapter.getValue(c, "S2", "Task 3", 0));
		assertEquals(new Long(l6), adapter.getValue(c, "S2", "Task 3", 1));
		assertEquals(new Long(l7), adapter.getValue1(c, 0, 0, 0));
		assertEquals(new Long(l8), adapter.getValue1(c, 0, 0, 1));
		assertEquals(new Long(l9), adapter.getValue1(c, 0, 1, 0));
		assertEquals(new Long(l10), adapter.getValue1(c, 0, 1, 1));
		assertEquals(new Long(l11), adapter.getValue1(c, 1, 2, 0));
		assertEquals(new Long(l12), adapter.getValue1(c, 1, 2, 1));
		TaskSeriesCollection c3 = createCollection3();
		assertEquals(new Long(i1), adapter.getValue1(c3, 0, 0, 0));
		assertEquals(new Long(i2), adapter.getValue1(c3, 0, 1, 0));
		assertEquals(new Long(i3), adapter.getValue1(c3, 0, 1, 1));
		assertTrue(adapter.getValue1(c3, 1, 0, 0) == null);
		assertEquals(new Long(i4), adapter.getValue1(c3, 1, 1, 0));
		assertEquals(new Long(i5), adapter.getValue1(c3, 1, 1, 1));
		assertEquals(new Long(i6), adapter.getValue1(c3, 1, 1, 2));
	}

	interface TaskSeriesCollectionTestsTestGetValue2Adapter {
		Number getValue(TaskSeriesCollection taskSeriesCollection1, Comparable comparable1, Comparable comparable2,
				int i1);

		Number getValue1(TaskSeriesCollection taskSeriesCollection1, int i1, int i2, int i3);
	}

	class TaskSeriesCollectionTestsTestGetStartValue2AdapterImpl
			implements TaskSeriesCollectionTestsTestGetValue2Adapter {
		public Number getValue(TaskSeriesCollection c, Comparable comparable1, Comparable comparable2, int i1) {
			return c.getStartValue(comparable1, comparable2, i1);
		}

		public Number getValue1(TaskSeriesCollection c, int i1, int i2, int i3) {
			return c.getStartValue(i1, i2, i3);
		}
	}

	class TaskSeriesCollectionTestsTestGetEndValue2AdapterImpl
			implements TaskSeriesCollectionTestsTestGetValue2Adapter {
		public Number getValue(TaskSeriesCollection c, Comparable comparable1, Comparable comparable2, int i1) {
			return c.getEndValue(comparable1, comparable2, i1);
		}

		public Number getValue1(TaskSeriesCollection c, int i1, int i2, int i3) {
			return c.getEndValue(i1, i2, i3);
		}
	}

	public void taskSeriesCollectionTestsTestGetValueTemplate(TaskSeriesCollectionTestsTestGetValueAdapter adapter,
			long l1, long l2, long l3, long l4, long l5, long l6, int i1, int i2, int i3) {
		TaskSeriesCollection c = createCollection1();
		assertEquals(new Long(l1), adapter.getValue(c, "S1", "Task 1"));
		assertEquals(new Long(l2), adapter.getValue(c, "S1", "Task 2"));
		assertEquals(new Long(l3), adapter.getValue(c, "S2", "Task 3"));
		assertEquals(new Long(l4), adapter.getValue1(c, 0, 0));
		assertEquals(new Long(l5), adapter.getValue1(c, 0, 1));
		assertEquals(null, adapter.getValue1(c, 0, 2));
		assertEquals(null, adapter.getValue1(c, 1, 0));
		assertEquals(null, adapter.getValue1(c, 1, 1));
		assertEquals(new Long(l6), adapter.getValue1(c, 1, 2));
		TaskSeriesCollection c3 = createCollection3();
		assertEquals(new Long(i1), adapter.getValue1(c3, 0, 0));
		assertEquals(new Long(i2), adapter.getValue1(c3, 0, 1));
		assertTrue(adapter.getValue1(c3, 1, 0) == null);
		assertEquals(new Long(i3), adapter.getValue1(c3, 1, 1));
	}

	interface TaskSeriesCollectionTestsTestGetValueAdapter {
		Number getValue(TaskSeriesCollection taskSeriesCollection1, Comparable comparable1, Comparable comparable2);

		Number getValue1(TaskSeriesCollection taskSeriesCollection1, int i1, int i2);
	}

	class TaskSeriesCollectionTestsTestGetStartValueAdapterImpl
			implements TaskSeriesCollectionTestsTestGetValueAdapter {
		public Number getValue(TaskSeriesCollection c, Comparable comparable1, Comparable comparable2) {
			return c.getStartValue(comparable1, comparable2);
		}

		public Number getValue1(TaskSeriesCollection c, int i1, int i2) {
			return c.getStartValue(i1, i2);
		}
	}

	class TaskSeriesCollectionTestsTestGetEndValueAdapterImpl implements TaskSeriesCollectionTestsTestGetValueAdapter {
		public Number getValue(TaskSeriesCollection c, Comparable comparable1, Comparable comparable2) {
			return c.getEndValue(comparable1, comparable2);
		}

		public Number getValue1(TaskSeriesCollection c, int i1, int i2) {
			return c.getEndValue(i1, i2);
		}
	}

	public void taskSeriesCollectionTestsTestGetValue3Template(TaskSeriesCollectionTestsTestGetValue3Adapter adapter) {
		TaskSeriesCollection c = new TaskSeriesCollection();
		TaskSeries s = new TaskSeries("Series 1");
		s.add(new Task("Task with null duration", null));
		c.add(s);
		Number millis = adapter.getValue(c, "Series 1", "Task with null duration");
		assertTrue(millis == null);
	}

	interface TaskSeriesCollectionTestsTestGetValue3Adapter {
		Number getValue(TaskSeriesCollection taskSeriesCollection1, Comparable comparable1, Comparable comparable2);
	}

	class TaskSeriesCollectionTestsTestGetStartValue3AdapterImpl
			implements TaskSeriesCollectionTestsTestGetValue3Adapter {
		public Number getValue(TaskSeriesCollection c, Comparable comparable1, Comparable comparable2) {
			return c.getStartValue(comparable1, comparable2);
		}
	}

	class TaskSeriesCollectionTestsTestGetEndValue3AdapterImpl
			implements TaskSeriesCollectionTestsTestGetValue3Adapter {
		public Number getValue(TaskSeriesCollection c, Comparable comparable1, Comparable comparable2) {
			return c.getEndValue(comparable1, comparable2);
		}
	}

}
