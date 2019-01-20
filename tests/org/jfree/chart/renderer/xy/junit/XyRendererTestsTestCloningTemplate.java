package org.jfree.chart.renderer.xy.junit;
import java.lang.Object;
import java.awt.Shape;
import org.jfree.chart.renderer.xy.AbstractXYItemRenderer;
import java.awt.geom.Rectangle2D;
import java.lang.CloneNotSupportedException;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.assertFalse;
public class XyRendererTestsTestCloningTemplate {
  public static <TXYRenderer extends AbstractXYItemRenderer>void xyRendererTestsTestCloningTemplate(  XYRendererTestsTestCloningAdapter<TXYRenderer> adapter,  Class<TXYRenderer> clazzTXYRenderer) throws Exception {
    TXYRenderer r1=clazzTXYRenderer.newInstance();
    Rectangle2D v2=new Rectangle2D.Double(1.0,2.0,3.0,4.0);
    adapter.setLegend(r1,v2);
    TXYRenderer r2=null;
    try {
      r2=(TXYRenderer)adapter.clone(r1);
    }
 catch (    CloneNotSupportedException e) {
      e.printStackTrace();
    }
    assertTrue(r1 != r2);
    assertTrue(adapter.getClass(r1) == adapter.getClass(r2));
    assertTrue(r1.equals(r2));
    v2.setRect(4.0,3.0,2.0,1.0);
    assertFalse(r1.equals(r2));
    adapter.setLegend(r2,new Rectangle2D.Double(4.0,3.0,2.0,1.0));
    assertTrue(r1.equals(r2));
  }
}

interface XYRendererTestsTestCloningAdapter<TXYRenderer> {
	void setLegend(TXYRenderer tXYRenderer1, Shape shape1);

	Object clone(TXYRenderer tXYRenderer1) throws CloneNotSupportedException;

	Class<? extends TXYRenderer> getClass(TXYRenderer tXYRenderer1);
}
