package org.jianfengderek.damhomework2;

import com.google.gson.GsonBuilder;
import org.apache.log4j.BasicConfigurator;
import org.jianfengderek.damhomework2.fpgrowth.FpGrowthSolver;
import org.jianfengderek.damhomework2.fpgrowth.FpGrowthStatisticsCollector;
import org.jianfengderek.damhomework2.prefixspan.PrefixSpanSolver;
import org.jianfengderek.damhomework2.prefixspan.PrefixSpanStatisticsCollector;
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
        FpGrowthStatisticsCollector statisticsCollector = new FpGrowthStatisticsCollector();
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

        List<? extends StatisticsItem> statisticsItemList = statisticsCollector.getStatisticsItemList();
        for (StatisticsItem statisticsItem : statisticsItemList) {
            logger.info(new GsonBuilder().create().toJson(statisticsItem));
        }

        List<? extends StatisticsItem> oldPlunoStatistics =
                statisticsCollector.extractStatistics(RawDataType.OLD_DATA, ItemType.PLUNO);
        ChartGenerator.generateCharts(RawDataType.OLD_DATA, ItemType.PLUNO, oldPlunoStatistics,
                false, Algorithm.FP_GROWTH);
        List<? extends StatisticsItem> newPlunoStatistics =
                statisticsCollector.extractStatistics(RawDataType.NEW_DATA, ItemType.PLUNO);
        ChartGenerator.generateCharts(RawDataType.NEW_DATA, ItemType.PLUNO, newPlunoStatistics,
                false, Algorithm.FP_GROWTH);
        List<? extends StatisticsItem> oldDptnoStatistics =
                statisticsCollector.extractStatistics(RawDataType.OLD_DATA, ItemType.DPTNO);
        ChartGenerator.generateCharts(RawDataType.OLD_DATA, ItemType.DPTNO, oldDptnoStatistics,
                false, Algorithm.FP_GROWTH);
        List<? extends StatisticsItem> newDptnoStatistics =
                statisticsCollector.extractStatistics(RawDataType.NEW_DATA, ItemType.DPTNO);
        ChartGenerator.generateCharts(RawDataType.NEW_DATA, ItemType.DPTNO, newDptnoStatistics,
                false, Algorithm.FP_GROWTH);
        List<? extends StatisticsItem> oldBndnoStatistics =
                statisticsCollector.extractStatistics(RawDataType.OLD_DATA, ItemType.BNDNO);
        ChartGenerator.generateCharts(RawDataType.OLD_DATA, ItemType.BNDNO, oldBndnoStatistics,
                false, Algorithm.FP_GROWTH);
        List<? extends StatisticsItem> newBndnoStatistics =
                statisticsCollector.extractStatistics(RawDataType.NEW_DATA, ItemType.BNDNO);
        ChartGenerator.generateCharts(RawDataType.NEW_DATA, ItemType.BNDNO, newBndnoStatistics,
                false, Algorithm.FP_GROWTH);
    }

    private static void answera2() {
        FpGrowthSolver fpGrowthSolver = new FpGrowthSolver();
        FpGrowthStatisticsCollector statisticsCollector = new FpGrowthStatisticsCollector();
        fpGrowthSolver.setStatisticsCollector(statisticsCollector);
        fpGrowthSolver.readData(RawDataType.OLD_DATA)
                .solveIntegrated(ItemType.PLUNO, 2)
                .solveIntegrated(ItemType.PLUNO, 4)
                .solveIntegrated(ItemType.PLUNO, 6)
                .solveIntegrated(ItemType.PLUNO, 8)
                .solveIntegrated(ItemType.PLUNO, 10)
                .solveIntegrated(ItemType.PLUNO, 12)
                .solveIntegrated(ItemType.DPTNO, 2)
                .solveIntegrated(ItemType.DPTNO, 4)
                .solveIntegrated(ItemType.DPTNO, 6)
                .solveIntegrated(ItemType.DPTNO, 8)
                .solveIntegrated(ItemType.DPTNO, 10)
                .solveIntegrated(ItemType.DPTNO, 12)
                .solveIntegrated(ItemType.BNDNO, 2)
                .solveIntegrated(ItemType.BNDNO, 4)
                .solveIntegrated(ItemType.BNDNO, 6)
                .solveIntegrated(ItemType.BNDNO, 8)
                .solveIntegrated(ItemType.BNDNO, 10)
                .solveIntegrated(ItemType.BNDNO, 12)
                .cleanData().readData(RawDataType.NEW_DATA)
                .solveIntegrated(ItemType.PLUNO, 2)
                .solveIntegrated(ItemType.PLUNO, 4)
                .solveIntegrated(ItemType.PLUNO, 6)
                .solveIntegrated(ItemType.PLUNO, 8)
                .solveIntegrated(ItemType.PLUNO, 10)
                .solveIntegrated(ItemType.PLUNO, 12)
                .solveIntegrated(ItemType.DPTNO, 2)
                .solveIntegrated(ItemType.DPTNO, 4)
                .solveIntegrated(ItemType.DPTNO, 6)
                .solveIntegrated(ItemType.DPTNO, 8)
                .solveIntegrated(ItemType.DPTNO, 10)
                .solveIntegrated(ItemType.DPTNO, 12)
                .solveIntegrated(ItemType.BNDNO, 2)
                .solveIntegrated(ItemType.BNDNO, 4)
                .solveIntegrated(ItemType.BNDNO, 6)
                .solveIntegrated(ItemType.BNDNO, 8)
                .solveIntegrated(ItemType.BNDNO, 10)
                .solveIntegrated(ItemType.BNDNO, 12);

        List<? extends StatisticsItem> statisticsItemList = statisticsCollector.getStatisticsItemList();
        for (StatisticsItem statisticsItem : statisticsItemList) {
            logger.info(new GsonBuilder().create().toJson(statisticsItem));
        }

        List<? extends StatisticsItem> oldPlunoStatistics =
                statisticsCollector.extractStatistics(RawDataType.OLD_DATA, ItemType.PLUNO);
        ChartGenerator.generateCharts(RawDataType.OLD_DATA, ItemType.PLUNO, oldPlunoStatistics,
                true, Algorithm.FP_GROWTH);
        List<? extends StatisticsItem> newPlunoStatistics =
                statisticsCollector.extractStatistics(RawDataType.NEW_DATA, ItemType.PLUNO);
        ChartGenerator.generateCharts(RawDataType.NEW_DATA, ItemType.PLUNO, newPlunoStatistics,
                true, Algorithm.FP_GROWTH);
        List<? extends StatisticsItem> oldDptnoStatistics =
                statisticsCollector.extractStatistics(RawDataType.OLD_DATA, ItemType.DPTNO);
        ChartGenerator.generateCharts(RawDataType.OLD_DATA, ItemType.DPTNO, oldDptnoStatistics,
                true, Algorithm.FP_GROWTH);
        List<? extends StatisticsItem> newDptnoStatistics =
                statisticsCollector.extractStatistics(RawDataType.NEW_DATA, ItemType.DPTNO);
        ChartGenerator.generateCharts(RawDataType.NEW_DATA, ItemType.DPTNO, newDptnoStatistics,
                true, Algorithm.FP_GROWTH);
        List<? extends StatisticsItem> oldBndnoStatistics =
                statisticsCollector.extractStatistics(RawDataType.OLD_DATA, ItemType.BNDNO);
        ChartGenerator.generateCharts(RawDataType.OLD_DATA, ItemType.BNDNO, oldBndnoStatistics,
                true, Algorithm.FP_GROWTH);
        List<? extends StatisticsItem> newBndnoStatistics =
                statisticsCollector.extractStatistics(RawDataType.NEW_DATA, ItemType.BNDNO);
        ChartGenerator.generateCharts(RawDataType.NEW_DATA, ItemType.BNDNO, newBndnoStatistics,
                true, Algorithm.FP_GROWTH);
    }

    private static void answerb1() {
        PrefixSpanSolver prefixSpanSolver = new PrefixSpanSolver();
        PrefixSpanStatisticsCollector statisticsCollector = new PrefixSpanStatisticsCollector();
        prefixSpanSolver.setStatisticsCollector(statisticsCollector);
        prefixSpanSolver.readData(RawDataType.OLD_DATA)
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

        List<? extends StatisticsItem> statisticsItemList =
                statisticsCollector.getStatisticsItemList();
        for (StatisticsItem statisticsItem : statisticsItemList) {
            logger.info(new GsonBuilder().create().toJson(statisticsItem));
        }

        List<? extends StatisticsItem> oldPlunoStatistics =
                statisticsCollector.extractStatistics(RawDataType.OLD_DATA, ItemType.PLUNO);
        ChartGenerator.generateCharts(RawDataType.OLD_DATA, ItemType.PLUNO, oldPlunoStatistics,
                false, Algorithm.PREFIX_SPAN);
        List<? extends StatisticsItem> newPlunoStatistics =
                statisticsCollector.extractStatistics(RawDataType.NEW_DATA, ItemType.PLUNO);
        ChartGenerator.generateCharts(RawDataType.NEW_DATA, ItemType.PLUNO, newPlunoStatistics,
                false, Algorithm.PREFIX_SPAN);
        List<? extends StatisticsItem> oldDptnoStatistics =
                statisticsCollector.extractStatistics(RawDataType.OLD_DATA, ItemType.DPTNO);
        ChartGenerator.generateCharts(RawDataType.OLD_DATA, ItemType.DPTNO, oldDptnoStatistics,
                false, Algorithm.PREFIX_SPAN);
        List<? extends StatisticsItem> newDptnoStatistics =
                statisticsCollector.extractStatistics(RawDataType.NEW_DATA, ItemType.DPTNO);
        ChartGenerator.generateCharts(RawDataType.NEW_DATA, ItemType.DPTNO, newDptnoStatistics,
                false, Algorithm.PREFIX_SPAN);
        List<? extends StatisticsItem> oldBndnoStatistics =
                statisticsCollector.extractStatistics(RawDataType.OLD_DATA, ItemType.BNDNO);
        ChartGenerator.generateCharts(RawDataType.OLD_DATA, ItemType.BNDNO, oldBndnoStatistics,
                false, Algorithm.PREFIX_SPAN);
        List<? extends StatisticsItem> newBndnoStatistics =
                statisticsCollector.extractStatistics(RawDataType.NEW_DATA, ItemType.BNDNO);
        ChartGenerator.generateCharts(RawDataType.NEW_DATA, ItemType.BNDNO, newBndnoStatistics,
                false, Algorithm.PREFIX_SPAN);
    }

    private static void answerb2() {
        PrefixSpanSolver prefixSpanSolver = new PrefixSpanSolver();
        PrefixSpanStatisticsCollector statisticsCollector = new PrefixSpanStatisticsCollector();
        prefixSpanSolver.setStatisticsCollector(statisticsCollector);
        prefixSpanSolver.readData(RawDataType.OLD_DATA)
                .solveIntegrated(ItemType.PLUNO, 2)
                .solveIntegrated(ItemType.PLUNO, 4)
                .solveIntegrated(ItemType.PLUNO, 8)
                .solveIntegrated(ItemType.PLUNO, 16)
                .solveIntegrated(ItemType.PLUNO, 32)
                .solveIntegrated(ItemType.PLUNO, 64)
                .solveIntegrated(ItemType.DPTNO, 2)
                .solveIntegrated(ItemType.DPTNO, 4)
                .solveIntegrated(ItemType.DPTNO, 8)
                .solveIntegrated(ItemType.DPTNO, 16)
                .solveIntegrated(ItemType.DPTNO, 32)
                .solveIntegrated(ItemType.DPTNO, 64)
                .solveIntegrated(ItemType.BNDNO, 2)
                .solveIntegrated(ItemType.BNDNO, 4)
                .solveIntegrated(ItemType.BNDNO, 8)
                .solveIntegrated(ItemType.BNDNO, 16)
                .solveIntegrated(ItemType.BNDNO, 32)
                .solveIntegrated(ItemType.BNDNO, 64)
                .cleanData().readData(RawDataType.NEW_DATA)
                .solveIntegrated(ItemType.PLUNO, 2)
                .solveIntegrated(ItemType.PLUNO, 4)
                .solveIntegrated(ItemType.PLUNO, 8)
                .solveIntegrated(ItemType.PLUNO, 16)
                .solveIntegrated(ItemType.PLUNO, 32)
                .solveIntegrated(ItemType.PLUNO, 64)
                .solveIntegrated(ItemType.DPTNO, 2)
                .solveIntegrated(ItemType.DPTNO, 4)
                .solveIntegrated(ItemType.DPTNO, 8)
                .solveIntegrated(ItemType.DPTNO, 16)
                .solveIntegrated(ItemType.DPTNO, 32)
                .solveIntegrated(ItemType.DPTNO, 64)
                .solveIntegrated(ItemType.BNDNO, 2)
                .solveIntegrated(ItemType.BNDNO, 4)
                .solveIntegrated(ItemType.BNDNO, 8)
                .solveIntegrated(ItemType.BNDNO, 16)
                .solveIntegrated(ItemType.BNDNO, 32)
                .solveIntegrated(ItemType.BNDNO, 64);

        List<? extends StatisticsItem> statisticsItemList =
                statisticsCollector.getStatisticsItemList();
        for (StatisticsItem statisticsItem : statisticsItemList) {
            logger.info(new GsonBuilder().create().toJson(statisticsItem));
        }

        List<? extends StatisticsItem> oldPlunoStatistics =
                statisticsCollector.extractStatistics(RawDataType.OLD_DATA, ItemType.PLUNO);
        ChartGenerator.generateCharts(RawDataType.OLD_DATA, ItemType.PLUNO, oldPlunoStatistics,
                true, Algorithm.PREFIX_SPAN);
        List<? extends StatisticsItem> newPlunoStatistics =
                statisticsCollector.extractStatistics(RawDataType.NEW_DATA, ItemType.PLUNO);
        ChartGenerator.generateCharts(RawDataType.NEW_DATA, ItemType.PLUNO, newPlunoStatistics,
                true, Algorithm.PREFIX_SPAN);
        List<? extends StatisticsItem> oldDptnoStatistics =
                statisticsCollector.extractStatistics(RawDataType.OLD_DATA, ItemType.DPTNO);
        ChartGenerator.generateCharts(RawDataType.OLD_DATA, ItemType.DPTNO, oldDptnoStatistics,
                true, Algorithm.PREFIX_SPAN);
        List<? extends StatisticsItem> newDptnoStatistics =
                statisticsCollector.extractStatistics(RawDataType.NEW_DATA, ItemType.DPTNO);
        ChartGenerator.generateCharts(RawDataType.NEW_DATA, ItemType.DPTNO, newDptnoStatistics,
                true, Algorithm.PREFIX_SPAN);
        List<? extends StatisticsItem> oldBndnoStatistics =
                statisticsCollector.extractStatistics(RawDataType.OLD_DATA, ItemType.BNDNO);
        ChartGenerator.generateCharts(RawDataType.OLD_DATA, ItemType.BNDNO, oldBndnoStatistics,
                true, Algorithm.PREFIX_SPAN);
        List<? extends StatisticsItem> newBndnoStatistics =
                statisticsCollector.extractStatistics(RawDataType.NEW_DATA, ItemType.BNDNO);
        ChartGenerator.generateCharts(RawDataType.NEW_DATA, ItemType.BNDNO, newBndnoStatistics,
                true, Algorithm.PREFIX_SPAN);
    }

}
