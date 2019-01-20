package org.jfree.chart.urls.junit;
import java.lang.String;
import java.util.List;
import java.lang.Object;
import static junit.framework.Assert.assertEquals;
public class CustomGeneratorTestsTestAddURLSeriesTemplate {
  public static <TCustomGenerator extends Object>void customGeneratorTestsTestAddURLSeriesTemplate(  CustomGeneratorTestsTestAddURLSeriesAdapter<TCustomGenerator> adapter,  Class<TCustomGenerator> clazzTCustomGenerator) throws Exception {
    TCustomGenerator g1=clazzTCustomGenerator.newInstance();
    adapter.addURLSeries(g1,null);
    assertEquals(1,adapter.getListCount(g1));
    assertEquals(0,adapter.getURLCount(g1,0));
    List list1=new java.util.ArrayList();
    list1.add("URL1");
    adapter.addURLSeries(g1,list1);
    assertEquals(2,adapter.getListCount(g1));
    assertEquals(0,adapter.getURLCount(g1,0));
    assertEquals(1,adapter.getURLCount(g1,1));
    assertEquals("URL1",adapter.getURL(g1,1,0));
    list1.clear();
    assertEquals("URL1",adapter.getURL(g1,1,0));
  }
}

interface CustomGeneratorTestsTestAddURLSeriesAdapter<TCustomGenerator> {
	void addURLSeries(TCustomGenerator tCustomGenerator1, List list1);

	int getListCount(TCustomGenerator tCustomGenerator1);

	int getURLCount(TCustomGenerator tCustomGenerator1, int i1);

	String getURL(TCustomGenerator tCustomGenerator1, int i1, int i2);
}
