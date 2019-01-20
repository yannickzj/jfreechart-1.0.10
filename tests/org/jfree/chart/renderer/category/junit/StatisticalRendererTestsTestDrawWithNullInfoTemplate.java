package org.jfree.chart.renderer.category.junit;
import org.jfree.data.statistics.DefaultStatisticalCategoryDataset;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import static junit.framework.Assert.assertTrue;
public class StatisticalRendererTestsTestDrawWithNullInfoTemplate {
  public static <TStatisticalRenderer>void statisticalRendererTestsTestDrawWithNullInfoTemplate(  Class<TStatisticalRenderer> clazzTStatisticalRenderer) throws Exception {
    boolean success=false;
    try {
      DefaultStatisticalCategoryDataset dataset=new DefaultStatisticalCategoryDataset();
      dataset.add(1.0,2.0,"S1","C1");
      dataset.add(3.0,4.0,"S1","C2");
      CategoryPlot plot=new CategoryPlot(dataset,new CategoryAxis("Category"),new NumberAxis("Value"),(CategoryItemRenderer)clazzTStatisticalRenderer.newInstance());
      JFreeChart chart=new JFreeChart(plot);
      chart.createBufferedImage(300,200,null);
      success=true;
    }
 catch (    NullPointerException e) {
      e.printStackTrace();
      success=false;
    }
    assertTrue(success);
  }
}
