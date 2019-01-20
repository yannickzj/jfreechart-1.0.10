package org.jfree.chart.urls.junit;
import java.lang.Object;
import java.util.List;
import java.lang.CloneNotSupportedException;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.assertFalse;
public class CustomGeneratorTestsTestCloningTemplate {
  public static <TCustomGenerator extends Object>void customGeneratorTestsTestCloningTemplate(  CustomGeneratorTestsTestCloningAdapter<TCustomGenerator> adapter,  Class<TCustomGenerator> clazzTCustomGenerator) throws Exception {
    TCustomGenerator g1=clazzTCustomGenerator.newInstance();
    List u1=new java.util.ArrayList();
    u1.add("URL A1");
    u1.add("URL A2");
    u1.add("URL A3");
    adapter.addURLSeries(g1,u1);
    TCustomGenerator g2=null;
    try {
      g2=(TCustomGenerator)adapter.clone(g1);
    }
 catch (    CloneNotSupportedException e) {
      e.printStackTrace();
    }
    assertTrue(g1 != g2);
    assertTrue(g1.getClass() == g2.getClass());
    assertTrue(g1.equals(g2));
    List u2=new java.util.ArrayList();
    u2.add("URL XXX");
    adapter.addURLSeries(g1,u2);
    assertFalse(g1.equals(g2));
    adapter.addURLSeries(g2,new java.util.ArrayList(u2));
    assertTrue(g1.equals(g2));
  }
}

interface CustomGeneratorTestsTestCloningAdapter<TCustomGenerator> {
	void addURLSeries(TCustomGenerator tCustomGenerator1, List list1);

	Object clone(TCustomGenerator tCustomGenerator1) throws CloneNotSupportedException;
}
