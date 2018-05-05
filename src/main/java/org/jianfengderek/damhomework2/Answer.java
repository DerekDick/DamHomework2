package org.jianfengderek.damhomework2;

import com.google.gson.GsonBuilder;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Answer {

    // For logging
    private static final Logger logger = LoggerFactory.getLogger(Answer.class);

    public static void main(String[] args) {
        // Configure log4j on start
        BasicConfigurator.configure();

        logger.info("This is my answer to homework2 of the course Data Analysis and Mining.");

        if ("a1".equals(args[0])) {
            answera1();
        } else if ("a2".equals(args[0])) {
            answera2();
        } else if ("b1".equals(args[0])) {
            answerb1();
        } else if ("b2".equals(args[0])) {
            answerb2();
        } else {
            logger.warn("Illegal parameter.");
        }
    }

    private static void answera1() {
        FpGrowthSolver fpGrowthSolver = new FpGrowthSolver();
        StatisticsCollector statisticsCollector = new StatisticsCollector();
        fpGrowthSolver.setStatisticsCollector(statisticsCollector);
        fpGrowthSolver.readData(RawDataType.OLD_DATA)
                .solve(ItemType.PLUNO, 2)
                .solve(ItemType.PLUNO, 4)
                .solve(ItemType.PLUNO, 8)
                .solve(ItemType.PLUNO, 16)
                .solve(ItemType.PLUNO, 32)
                .solve(ItemType.PLUNO, 64)
                .solve(ItemType.DPTNO, 2)
                .solve(ItemType.DPTNO, 4)
                .solve(ItemType.DPTNO, 8)
                .solve(ItemType.DPTNO, 16)
                .solve(ItemType.DPTNO, 32)
                .solve(ItemType.DPTNO, 64)
                .solve(ItemType.BNDNO, 2)
                .solve(ItemType.BNDNO, 4)
                .solve(ItemType.BNDNO, 8)
                .solve(ItemType.BNDNO, 16)
                .solve(ItemType.BNDNO, 32)
                .solve(ItemType.BNDNO, 64)
                .cleanData().readData(RawDataType.NEW_DATA)
                .solve(ItemType.PLUNO, 2)
                .solve(ItemType.PLUNO, 4)
                .solve(ItemType.PLUNO, 8)
                .solve(ItemType.PLUNO, 16)
                .solve(ItemType.PLUNO, 32)
                .solve(ItemType.PLUNO, 64)
                .solve(ItemType.DPTNO, 2)
                .solve(ItemType.DPTNO, 4)
                .solve(ItemType.DPTNO, 8)
                .solve(ItemType.DPTNO, 16)
                .solve(ItemType.DPTNO, 32)
                .solve(ItemType.DPTNO, 64)
                .solve(ItemType.BNDNO, 2)
                .solve(ItemType.BNDNO, 4)
                .solve(ItemType.BNDNO, 8)
                .solve(ItemType.BNDNO, 16)
                .solve(ItemType.BNDNO, 32)
                .solve(ItemType.BNDNO, 64);

        List<StatisticsItem> statisticsItemList = statisticsCollector.getStatisticsItemList();
        for (StatisticsItem statisticsItem : statisticsItemList) {
            logger.info(new GsonBuilder().create().toJson(statisticsItem));
        }

        List<StatisticsItem> oldPlunoStatistics =
                statisticsCollector.extractStatistics(RawDataType.OLD_DATA, ItemType.PLUNO);
        ChartGenerator.generateCharts(RawDataType.OLD_DATA, ItemType.PLUNO, oldPlunoStatistics);
        List<StatisticsItem> newPlunoStatistics =
                statisticsCollector.extractStatistics(RawDataType.NEW_DATA, ItemType.PLUNO);
        ChartGenerator.generateCharts(RawDataType.NEW_DATA, ItemType.PLUNO, newPlunoStatistics);
        List<StatisticsItem> oldDptnoStatistics =
                statisticsCollector.extractStatistics(RawDataType.OLD_DATA, ItemType.DPTNO);
        ChartGenerator.generateCharts(RawDataType.OLD_DATA, ItemType.DPTNO, oldDptnoStatistics);
        List<StatisticsItem> newDptnoStatistics =
                statisticsCollector.extractStatistics(RawDataType.NEW_DATA, ItemType.DPTNO);
        ChartGenerator.generateCharts(RawDataType.NEW_DATA, ItemType.DPTNO, newDptnoStatistics);
        List<StatisticsItem> oldBndnoStatistics =
                statisticsCollector.extractStatistics(RawDataType.OLD_DATA, ItemType.BNDNO);
        ChartGenerator.generateCharts(RawDataType.OLD_DATA, ItemType.BNDNO, oldBndnoStatistics);
        List<StatisticsItem> newBndnoStatistics =
                statisticsCollector.extractStatistics(RawDataType.NEW_DATA, ItemType.BNDNO);
        ChartGenerator.generateCharts(RawDataType.NEW_DATA, ItemType.BNDNO, newBndnoStatistics);
    }

    private static void answera2() {
    }

    private static void answerb1() {
    }

    private static void answerb2() {
    }

}
