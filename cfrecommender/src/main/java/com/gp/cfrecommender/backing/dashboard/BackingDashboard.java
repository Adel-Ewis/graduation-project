/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp.cfrecommender.backing.dashboard;

import com.gp.cfrecommender.backing.BaseBackingBean;
import com.gp.cfrecommender.facade.FacadeTblproblemtags;
import com.gp.cfrecommender.model.Tblproblems;
import com.gp.cfrecommender.model.Tblproblemtags;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartSeries;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.chart.CategoryAxis;

/**
 *
 * @author ITS
 */
@Named
@ViewScoped
public class BackingDashboard extends BaseBackingBean implements Serializable {

    private LineChartModel lineChart;
    @EJB
    FacadeTblproblemtags facadeTblproblemtags;

    @PostConstruct
    public void init() {
        if (userSession.findCurrentUserRole() == 'A') {
            createTagsModel();
        }
    }

    private void createTagsModel() {
        lineChart = initTagsModel();
        lineChart.setTitle("Number of Problems By Tags");
        lineChart.setLegendPosition("ne");
        lineChart.setShowPointLabels(true);
        lineChart.setAnimate(true);
        lineChart.getAxes().put(AxisType.X, new CategoryAxis("Tag Name"));
        Axis yAxis = lineChart.getAxis(AxisType.Y);
        yAxis.setLabel("Problems Count");
        yAxis.setMin(0);
        yAxis.setMax(1200);
    }

    private LineChartModel initTagsModel() {
        List<Tblproblemtags> allProblemsTags = facadeTblproblemtags.findAll();
        LineChartModel model = new LineChartModel();

        ChartSeries problems = new ChartSeries();
        problems.setLabel("Problems Tags");
        for (int i = 0; i < allProblemsTags.size(); i++) {
            Tblproblemtags problemTag = allProblemsTags.get(i);
            problems.set(problemTag.getTagDescription(), problemTag.getTblproblemtagsbridgeList().size());
            /*if (i > 10) {
                break;
            }*/
        }
        model.addSeries(problems);
        return model;
    }

    public LineChartModel getLineChart() {
        return lineChart;
    }

    public String goToSimilarPage() {
        return "/p/trainee/SimilarProblems.xhtml?faces-redirect=true&includeViewParams=true";
    }

}
