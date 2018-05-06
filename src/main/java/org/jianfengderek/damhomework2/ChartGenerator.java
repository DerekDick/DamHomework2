package org.jianfengderek.damhomework2;

import com.google.common.base.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ChartGenerator {

    // For logging
    private static final Logger logger = LoggerFactory.getLogger(ChartGenerator.class);

    public static void generateCharts(RawDataType rawDataType,
                                      ItemType itemType,
                                      List<? extends StatisticsItem> statisticsItemList,
                                      boolean integrated,
                                      Algorithm algorithm) {
        generateTimeChart(rawDataType, itemType, statisticsItemList, integrated, algorithm);
        generateMemoryChart(rawDataType, itemType, statisticsItemList, integrated, algorithm);
    }

    private static void generateTimeChart(RawDataType rawDataType,
                                          ItemType itemType,
                                          List<? extends StatisticsItem> statisticsItemList,
                                          boolean integrated,
                                          Algorithm algorithm) {
        // Build the title of the chart
        StringBuilder title = buildTitle(rawDataType, itemType, integrated, algorithm);
        if (null == title) {
            return;
        }
        title.append(" time usage V.S. minsup");

        StringBuilder htmlContent = new StringBuilder()
                .append("<!DOCTYPE HTML>\n")
                .append("<html>\n")
                .append("<head>  \n")
                .append("<script>\n")
                .append("window.onload = function () {\n")
                .append("\n")
                .append("var chart = new CanvasJS.Chart(\"chartContainer\", {\n")
                .append("\ttheme:\"light2\",\n")
                .append("\tanimationEnabled: true,\n")
                .append("\ttitle:{\n")
                .append("\t\ttext: \"")
                .append(title)
                .append("\"\n")
                .append("\t},\n")
                .append("\taxisY :{\n")
                .append("\t\tincludeZero: false,\n")
                .append("\t\ttitle: \"Time Usage\",\n")
                .append("\t\tsuffix: \"ms\"\n")
                .append("\t},\n")
                .append("\ttoolTip: {\n")
                .append("\t\tshared: \"true\"\n")
                .append("\t},\n")
                .append("\tlegend:{\n")
                .append("\t\tcursor:\"pointer\",\n")
                .append("\t\titemclick : toggleDataSeries\n")
                .append("\t},\n")
                .append("\tdata: [{\n")
                .append("\t\ttype: \"spline\",\n")
                .append("\t\tvisible: true,\n")
                .append("\t\tshowInLegend: true,\n")
                .append("\t\tyValueFormatString: \"##.00ms\",\n")
                .append("\t\tname: \"Time Usage\",\n")
                .append("\t\tdataPoints: [");

        StringBuilder dataPointStringBuilder = new StringBuilder();
        for (StatisticsItem statisticsItem : statisticsItemList) {
            DataPoint dataPoint = new DataPoint();

            dataPoint.setLabel(String.valueOf(statisticsItem.getMinsup()));
            dataPoint.setY(Double.valueOf(statisticsItem.getTotalTimeInMillisSecond()));

            dataPointStringBuilder.append(dataPoint.toString()).append(',');
        }

        htmlContent
                .append(dataPointStringBuilder.substring(0, dataPointStringBuilder.length() - 1))
                .append("\n")
                .append("\t]\n")
                .append("}]});\n")
                .append("chart.render();\n")
                .append("\n")
                .append("function toggleDataSeries(e) {\n")
                .append("\tif (typeof(e.dataSeries.visible) === \"undefined\" || e.dataSeries.visible ){\n")
                .append("\t\te.dataSeries.visible = false;\n")
                .append("\t} else {\n")
                .append("\t\te.dataSeries.visible = true;\n")
                .append("\t}\n")
                .append("\tchart.render();\n")
                .append("}\n")
                .append("\n")
                .append("}\n")
                .append("</script>\n")
                .append("</head>\n")
                .append("<body>\n")
                .append("<div id=\"chartContainer\" style=\"height: 300px; width: 100%;\"></div>\n")
                .append("<script src=\"https://canvasjs.com/assets/script/canvasjs.min.js\"></script>\n")
                .append("</body>\n")
                .append("</html>\n");

        String filename = title + ".html";
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename))) {
            bufferedWriter.write(htmlContent.toString());
        } catch (IOException e) {
            logger.error("e.getMessage() = " + e.getMessage());
        }
    }

    private static void generateMemoryChart(RawDataType rawDataType,
                                            ItemType itemType,
                                            List<? extends StatisticsItem> statisticsItemList,
                                            boolean integrated,
                                            Algorithm algorithm) {
        // Build the title of the chart
        StringBuilder title = buildTitle(rawDataType, itemType, integrated, algorithm);
        if (null == title) {
            return;
        }
        title.append(" max memory usage V.S. minsup");

        StringBuilder htmlContent = new StringBuilder()
                .append("<!DOCTYPE HTML>\n")
                .append("<html>\n")
                .append("<head>  \n")
                .append("<script>\n")
                .append("window.onload = function () {\n")
                .append("\n")
                .append("var chart = new CanvasJS.Chart(\"chartContainer\", {\n")
                .append("\ttheme:\"light2\",\n")
                .append("\tanimationEnabled: true,\n")
                .append("\ttitle:{\n")
                .append("\t\ttext: \"")
                .append(title)
                .append("\"\n")
                .append("\t},\n")
                .append("\taxisY :{\n")
                .append("\t\tincludeZero: false,\n")
                .append("\t\ttitle: \"Max Memory Usage\",\n")
                .append("\t\tsuffix: \"mb\"\n")
                .append("\t},\n")
                .append("\ttoolTip: {\n")
                .append("\t\tshared: \"true\"\n")
                .append("\t},\n")
                .append("\tlegend:{\n")
                .append("\t\tcursor:\"pointer\",\n")
                .append("\t\titemclick : toggleDataSeries\n")
                .append("\t},\n")
                .append("\tdata: [{\n")
                .append("\t\ttype: \"spline\",\n")
                .append("\t\tvisible: true,\n")
                .append("\t\tshowInLegend: true,\n")
                .append("\t\tyValueFormatString: \"##.00mb\",\n")
                .append("\t\tname: \"Max Memory Usage\",\n")
                .append("\t\tdataPoints: [");

        StringBuilder dataPointStringBuilder = new StringBuilder();
        for (StatisticsItem statisticsItem : statisticsItemList) {
            DataPoint dataPoint = new DataPoint();

            dataPoint.setLabel(String.valueOf(statisticsItem.getMinsup()));
            dataPoint.setY(statisticsItem.getMaxMemoryUsage());

            dataPointStringBuilder.append(dataPoint.toString()).append(',');
        }

        htmlContent
                .append(dataPointStringBuilder.substring(0, dataPointStringBuilder.length() - 1))
                .append("\n")
                .append("\t]\n")
                .append("}]});\n")
                .append("chart.render();\n")
                .append("\n")
                .append("function toggleDataSeries(e) {\n")
                .append("\tif (typeof(e.dataSeries.visible) === \"undefined\" || e.dataSeries.visible ){\n")
                .append("\t\te.dataSeries.visible = false;\n")
                .append("\t} else {\n")
                .append("\t\te.dataSeries.visible = true;\n")
                .append("\t}\n")
                .append("\tchart.render();\n")
                .append("}\n")
                .append("\n")
                .append("}\n")
                .append("</script>\n")
                .append("</head>\n")
                .append("<body>\n")
                .append("<div id=\"chartContainer\" style=\"height: 300px; width: 100%;\"></div>\n")
                .append("<script src=\"https://canvasjs.com/assets/script/canvasjs.min.js\"></script>\n")
                .append("</body>\n")
                .append("</html>\n");

        String filename = title + ".html";
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename))) {
            bufferedWriter.write(htmlContent.toString());
        } catch (IOException e) {
            logger.error("e.getMessage() = " + e.getMessage());
        }
    }

    private static StringBuilder buildTitle(RawDataType rawDataType,
                                            ItemType itemType,
                                            boolean integrated,
                                            Algorithm algorithm) {
        StringBuilder title = new StringBuilder();
        switch (algorithm) {
            case FP_GROWTH: {
                title.append("FP-Growth: ");

                break;
            }

            case PREFIX_SPAN: {
                title.append("PrefixSpan: ");

                break;
            }

            default: {
                logger.error("Illegal Algorithm: " + algorithm);

                return null;
            }
        }
        if (integrated) {
            title.append("Integrated ");
        }
        switch (rawDataType) {
            case OLD_DATA: {
                title.append("Old");

                break;
            }

            case NEW_DATA: {
                title.append("New");

                break;
            }

            default: {
                logger.error("Illegal RawDataType: " + rawDataType);

                return null;
            }
        }
        switch (itemType) {
            case PLUNO: {
                title.append(" pluno");

                break;
            }

            case DPTNO: {
                title.append(" dptno");

                break;
            }

            case BNDNO: {
                title.append(" bndno");

                break;
            }

            default: {
                logger.error("Illegal ItemType: " + itemType);

                return null;
            }
        }

        return title;
    }

    static class DataPoint {

        private String label;

        private Double y;

        public DataPoint() {
        }

        public DataPoint(String label, Double y) {
            this.label = label;
            this.y = y;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public Double getY() {
            return y;
        }

        public void setY(Double y) {
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            DataPoint dataPoint = (DataPoint) o;
            return Objects.equal(label, dataPoint.label) &&
                    Objects.equal(y, dataPoint.y);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(label, y);
        }


        @Override
        public String toString() {
            return "{" +
                    "label: \"" + label + '\"' +
                    ", y: " + y +
                    '}';
        }

    }

}
