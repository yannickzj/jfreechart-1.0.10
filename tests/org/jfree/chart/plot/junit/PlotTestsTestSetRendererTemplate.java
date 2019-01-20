package org.jfree.chart.plot.junit;
import java.awt.Paint;
import org.jfree.chart.LegendItemSource;
import org.jfree.chart.plot.Plot;
import java.awt.Color;
import static junit.framework.Assert.assertTrue;
public class PlotTestsTestSetRendererTemplate {
  public static <TPlot extends Plot,TItemRenderer extends LegendItemSource,TLineAndShapeRenderer extends TItemRenderer>void plotTestsTestSetRendererTemplate(  PlotTestsTestSetRendererAdapter<TPlot,TItemRenderer> adapter,  Class<TPlot> clazzTPlot,  Class<TLineAndShapeRenderer> clazzTLineAndShapeRenderer) throws Exception {
    TPlot plot=clazzTPlot.newInstance();
    TItemRenderer renderer=clazzTLineAndShapeRenderer.newInstance();
    adapter.setRenderer(plot,(LegendItemSource)renderer);
    MyPlotChangeListener listener=new MyPlotChangeListener();
    plot.addChangeListener(listener);
    adapter.setSeriesPaint(renderer,0,Color.black);
    assertTrue(listener.getEvent() != null);
  }
}

interface PlotTestsTestSetRendererAdapter<TPlot, TItemRenderer> {
	void setRenderer(TPlot tPlot1, LegendItemSource legendItemSource1);

	void setSeriesPaint(TItemRenderer tItemRenderer1, int i1, Paint paint1);
}
