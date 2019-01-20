package org.jfree.chart.renderer.xy.junit;
import org.jfree.chart.renderer.xy.AbstractXYItemRenderer;
import org.jfree.data.xy.OHLCDataItem;
import java.util.Date;
import org.jfree.data.xy.OHLCDataset;
import org.jfree.data.xy.DefaultOHLCDataset;
import org.jfree.data.Range;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
public class RendererTestsTestFindRangeBoundsTemplate {
  public static <TRenderer extends AbstractXYItemRenderer>void rendererTestsTestFindRangeBoundsTemplate(  Class<TRenderer> clazzTRenderer) throws Exception {
    TRenderer renderer=clazzTRenderer.newInstance();
    OHLCDataItem item1=new OHLCDataItem(new Date(1L),2.0,4.0,1.0,3.0,100);
    OHLCDataset dataset=new DefaultOHLCDataset("S1",new OHLCDataItem[]{item1});
    Range range=renderer.findRangeBounds(dataset);
    assertEquals(new Range(1.0,4.0),range);
    OHLCDataItem item2=new OHLCDataItem(new Date(1L),-1.0,3.0,-1.0,3.0,100);
    dataset=new DefaultOHLCDataset("S1",new OHLCDataItem[]{item1,item2});
    range=renderer.findRangeBounds(dataset);
    assertEquals(new Range(-1.0,4.0),range);
    dataset=new DefaultOHLCDataset("S1",new OHLCDataItem[]{});
    range=renderer.findRangeBounds(dataset);
    assertNull(range);
    range=renderer.findRangeBounds(null);
    assertNull(range);
  }
}
