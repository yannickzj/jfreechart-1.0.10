package org.jfree.data.time.junit;
import org.jfree.data.time.Year;
import java.lang.String;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimePeriodFormatException;
import static junit.framework.Assert.assertEquals;
public class TestsTestParseTemplate {
  public static <TRegularTimePeriod extends RegularTimePeriod>void testsTestParseTemplate(  TestsTestParseAdapter<TRegularTimePeriod> adapter,  Class<TRegularTimePeriod> clazzTRegularTimePeriod,  String string1,  int i1,  String string2,  int i2,  String string3,  int i3) throws Exception {
    TRegularTimePeriod v1=null;
    try {
      v1=adapter.parse(string1);
    }
 catch (    TimePeriodFormatException e) {
      v1=clazzTRegularTimePeriod.getDeclaredConstructor(int.class,int.class).newInstance(1,1900);
    }
    assertEquals(1,adapter.get(v1));
    assertEquals(i1,adapter.getYear(v1).getYear());
    try {
      v1=adapter.parse(string2);
    }
 catch (    TimePeriodFormatException e) {
      v1=clazzTRegularTimePeriod.getDeclaredConstructor(int.class,int.class).newInstance(1,1900);
    }
    assertEquals(2,adapter.get(v1));
    assertEquals(i2,adapter.getYear(v1).getYear());
    try {
      v1=adapter.parse(string3);
    }
 catch (    TimePeriodFormatException e) {
      v1=clazzTRegularTimePeriod.getDeclaredConstructor(int.class,int.class).newInstance(1,1900);
    }
    assertEquals(3,adapter.get(v1));
    assertEquals(i3,adapter.getYear(v1).getYear());
  }
}

interface TestsTestParseAdapter<TRegularTimePeriod> {
	TRegularTimePeriod parse(String string1);

	int get(TRegularTimePeriod tRegularTimePeriod1);

	Year getYear(TRegularTimePeriod tRegularTimePeriod1);
}
