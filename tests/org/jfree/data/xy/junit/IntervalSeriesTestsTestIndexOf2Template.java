package org.jfree.data.xy.junit;
import org.jfree.data.ComparableObjectSeries;
import java.lang.Comparable;
import static junit.framework.Assert.assertEquals;
public class IntervalSeriesTestsTestIndexOf2Template {
  public static <TIntervalSeries extends ComparableObjectSeries>void intervalSeriesTestsTestIndexOf2Template(  IntervalSeriesTestsTestIndexOf2Adapter<TIntervalSeries> adapter,  Class<TIntervalSeries> clazzTIntervalSeries) throws Exception {
    TIntervalSeries s1=clazzTIntervalSeries.getDeclaredConstructor(Comparable.class,boolean.class,boolean.class).newInstance("Series 1",false,true);
    adapter.add(s1,1.0,1.0,1.0,2.0);
    adapter.add(s1,3.0,3.0,3.0,3.0);
    adapter.add(s1,2.0,2.0,2.0,2.0);
    assertEquals(0,s1.indexOf(new Double(1.0)));
    assertEquals(1,s1.indexOf(new Double(3.0)));
    assertEquals(2,s1.indexOf(new Double(2.0)));
  }
}

interface IntervalSeriesTestsTestIndexOf2Adapter<TIntervalSeries> {
	void add(TIntervalSeries tIntervalSeries1, double d1, double d2, double d3, double d4);
}
