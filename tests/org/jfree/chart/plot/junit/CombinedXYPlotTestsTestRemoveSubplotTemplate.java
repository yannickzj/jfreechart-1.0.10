package org.jfree.chart.plot.junit;
import java.util.List;
import org.jfree.chart.plot.XYPlot;
import static junit.framework.Assert.assertTrue;
public class CombinedXYPlotTestsTestRemoveSubplotTemplate {
  public static <TCombinedXYPlot extends XYPlot>void combinedXYPlotTestsTestRemoveSubplotTemplate(  CombinedXYPlotTestsTestRemoveSubplotAdapter<TCombinedXYPlot> adapter,  Class<TCombinedXYPlot> clazzTCombinedXYPlot) throws Exception {
    TCombinedXYPlot plot=clazzTCombinedXYPlot.newInstance();
    XYPlot plot1=new XYPlot();
    XYPlot plot2=new XYPlot();
    adapter.add(plot,plot1);
    adapter.add(plot,plot2);
    adapter.remove(plot,plot2);
    List plots=adapter.getSubplots(plot);
    assertTrue(plots.get(0) == plot1);
  }
}

interface CombinedXYPlotTestsTestRemoveSubplotAdapter<TCombinedXYPlot> {
	void add(TCombinedXYPlot tCombinedXYPlot1, XYPlot xYPlot1);

	void remove(TCombinedXYPlot tCombinedXYPlot1, XYPlot xYPlot1);

	List getSubplots(TCombinedXYPlot tCombinedXYPlot1);
}
