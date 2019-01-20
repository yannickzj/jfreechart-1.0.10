package org.jfree.data.xy.junit;
import java.lang.Number;
import org.jfree.data.ComparableObjectSeries;
import java.lang.Comparable;
import static junit.framework.Assert.assertEquals;
public class IntervalSeriesTestsTestRemoveTemplate {
  public static <TIntervalSeries extends ComparableObjectSeries>void intervalSeriesTestsTestRemoveTemplate(  IntervalSeriesTestsTestRemoveAdapter<TIntervalSeries> adapter,  Class<TIntervalSeries> clazzTIntervalSeries) throws Exception {
    TIntervalSeries s1=clazzTIntervalSeries.getDeclaredConstructor(Comparable.class).newInstance("Series 1");
    adapter.add(s1,1.0,1.0,1.0,2.0);
    adapter.add(s1,2.0,2.0,2.0,2.0);
    adapter.add(s1,3.0,3.0,3.0,3.0);
    assertEquals(3,s1.getItemCount());
    s1.remove(new Double(2.0));
    assertEquals(new Double(3.0),adapter.getX(s1,1));
    s1.remove(new Double(1.0));
    assertEquals(new Double(3.0),adapter.getX(s1,0));
  }
}

interface IntervalSeriesTestsTestRemoveAdapter<TIntervalSeries> {
	void add(TIntervalSeries tIntervalSeries1, double d1, double d2, double d3, double d4);

	Number getX(TIntervalSeries tIntervalSeries1, int i1);
}
