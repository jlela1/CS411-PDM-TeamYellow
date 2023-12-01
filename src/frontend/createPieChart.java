package frontend;

import java.text.DecimalFormat;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import backend.database.UserQuery;
import java.sql.Date;

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
    int star1, star2, star3, star4, star5 = 0;


    private PieDataset createDataset(Date dateStart, Date dateEnd, String garageNam) {
    DefaultPieDataset dataset = new DefaultPieDataset();
        feedBack.retrieveWithinDateRangeAndGarage(dateStart, dateEnd, garageNam);
        for(int i = 0; i <feedBack.retrieveWithinDateRangeAndGarage(dateStart, dateEnd, garageNam).size(); ++i)
        {
            if( feedBack.retrieveWithinDateRangeAndGarage(dateStart, dateEnd, garageNam).get(i).getRating() <=1)
            {
                star1++;
            }
            if( feedBack.retrieveWithinDateRangeAndGarage(dateStart, dateEnd, garageNam).get(i).getRating() >1 && feedBack.retrieveWithinDateRangeAndGarage(dateStart, dateEnd, garageNam).get(i).getRating() <=2)
            {
                star2++;
            }
            if( feedBack.retrieveWithinDateRangeAndGarage(dateStart, dateEnd, garageNam).get(i).getRating() >2 && feedBack.retrieveWithinDateRangeAndGarage(dateStart, dateEnd, garageNam).get(i).getRating() <=3)
            {
                star3++;
            }
            if( feedBack.retrieveWithinDateRangeAndGarage(dateStart, dateEnd, garageNam).get(i).getRating() >3 && feedBack.retrieveWithinDateRangeAndGarage(dateStart, dateEnd, garageNam).get(i).getRating() <=4)
            {
                star4++;
            }
            if( feedBack.retrieveWithinDateRangeAndGarage(dateStart, dateEnd, garageNam).get(i).getRating() > 4 && feedBack.retrieveWithinDateRangeAndGarage(dateStart, dateEnd, garageNam).get(i).getRating() <=5)
            {
                star5++;
            }
        }
    dataset.setValue("0-1",star1);
        dataset.setValue("1-2",star2);
        dataset.setValue("2-3",star3);
        dataset.setValue("3-4",star4);
        dataset.setValue("4-5",star5);


    return dataset;
}

   public createPieChart(Date dateStart, Date dateEnd, String garageNam) {
       super("Feedback");
       PieDataset pieData = createDataset(dateStart,dateEnd,garageNam);







       JFreeChart chart = ChartFactory.createPieChart(
               garageNam,
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
            Date dateST, dateET;
            dateST = Date.valueOf(java.time.LocalDate.now());
            dateET = Date.valueOf(java.time.LocalDate.now());
                      createPieChart example = new createPieChart(dateST,dateET,"Test");
            example.setSize(800,400);
            example.setLocationRelativeTo(null);
            example.setVisible(true);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        });
    }
}
