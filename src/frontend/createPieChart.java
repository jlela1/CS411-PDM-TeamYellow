package frontend;

import java.text.DecimalFormat;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import backend.database.UserQuery;

import java.util.*;

import backend.database.UserQuery;
import org.jfree.chart.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;



public class createPieChart extends JFrame
{
    UserQuery feedBack = new UserQuery();


    private PieDataset createDataset() {
    DefaultPieDataset dataset = new DefaultPieDataset();
    dataset.setValue("0-1",100);
        dataset.setValue("1-2",100);
        dataset.setValue("2-3",100);
        dataset.setValue("3-4",100);
        dataset.setValue("4-5",100);


    return dataset;
}

   public createPieChart(/*String garageName, Date dateStart, Date dateEnd*/) {
       super("Feedback");
       PieDataset pieData = createDataset();
       //UserQuery.retrieveWithinDateRangeAndGarage(/*dateStart,dateEnd,garageName*/);


       JFreeChart chart = ChartFactory.createPieChart(
               "Test",
               pieData,
               true,
               true,
               false
       );


       PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator(
               "Satisfaction Level: {0} : ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
       ((PiePlot) chart.getPlot()).setLabelGenerator(labelGenerator);
       ChartPanel panel = new ChartPanel(chart);
    setContentPane(panel);


}

    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeAndWait(() ->{
            List emptyList = new ArrayList<>();
            createPieChart example = new createPieChart();
            example.setSize(800,400);
            example.setLocationRelativeTo(null);
            example.setVisible(true);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        });
    }
}
