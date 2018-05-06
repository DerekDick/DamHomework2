package org.jianfengderek.damhomework2.fpgrowth;

import org.jianfengderek.damhomework2.ItemType;
import org.jianfengderek.damhomework2.Product;
import org.jianfengderek.damhomework2.RawDataType;
import org.jianfengderek.damhomework2.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FpGrowthSolver {

    // For logging
    private static final Logger logger = LoggerFactory.getLogger(FpGrowthSolver.class);

    /**
     * Key: uid, Value: {@link Transaction}.
     */
    private Map<String, Transaction> transactionMap = new HashMap<>();

    /**
     * Key: uid, Value: {@link Transaction}.
     */
    private Map<String, Transaction> trainingTransactionMap = new HashMap<>();

    /**
     * Key: vipno, Value: {@link List}&lt;{@link Transaction}&gt;.
     */
    private Map<String, List<Transaction>> transactionListMap = new HashMap<>();

    /**
     * Key: vipno, Value: {@link List}&lt;{@link Transaction}&gt;.
     */
    private Map<String, List<Transaction>> trainingTransactionListMap = new HashMap<>();

    private RawDataType rawDataType;

    private FpGrowthStatisticsCollector statisticsCollector;

    public FpGrowthSolver() {
    }

    public Map<String, Transaction> getTransactionMap() {
        return transactionMap;
    }

    public void setTransactionMap(Map<String, Transaction> transactionMap) {
        this.transactionMap = transactionMap;
    }

    public FpGrowthStatisticsCollector getStatisticsCollector() {
        return statisticsCollector;
    }

    public void setStatisticsCollector(FpGrowthStatisticsCollector statisticsCollector) {
        this.statisticsCollector = statisticsCollector;
    }

    /**
     * Reads the data from the raw data file.
     *
     * @param rawDataType The type of the raw data.
     * @return The {@link FpGrowthSolver} itself.
     */
    public FpGrowthSolver readData(RawDataType rawDataType) {
        // Store the raw data type
        this.rawDataType = rawDataType;

        String filename;
        switch (rawDataType) {
            case OLD_DATA: {
                filename = "trade.csv";

                break;
            }

            case NEW_DATA: {
                filename = "trade_new.csv";

                break;
            }

            default: {
                logger.error("Illegal RawDataType: " + rawDataType);

                return this;
            }
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            // Skip the first line
            bufferedReader.readLine();

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] productBought = line.split(",");
                String uid, sldat, vipno, pluno, dptno, bndno;
                switch (rawDataType) {
                    case OLD_DATA: {
                        uid = productBought[0];
                        sldat = productBought[1];
                        vipno = productBought[4];
                        pluno = productBought[6];
                        dptno = productBought[11];
                        bndno = productBought[13];

                        break;
                    }

                    case NEW_DATA: {
                        uid = productBought[1];
                        sldat = productBought[2];
                        vipno = productBought[6];
                        pluno = productBought[8];
                        dptno = productBought[13];
                        bndno = productBought[15];

                        break;
                    }

                    default: {
                        logger.error("Illegal RawDataType: " + rawDataType);

                        return this;
                    }
                }

                Product product = new Product(pluno, dptno, bndno);

                if (transactionMap.containsKey(uid)) {
                    transactionMap.get(uid).getProductList().add(product);
                } else {
                    List<Product> productList = new ArrayList<>();
                    productList.add(product);
                    Transaction transaction = new Transaction(uid, sldat, vipno, productList);
                    transactionMap.put(uid, transaction);
                }
            }
        } catch (IOException exception) {
            logger.error("exception.getMessage() = " + exception.getMessage());
        }
        logger.info("transactionMap.size() = " + transactionMap.size());

        for (Map.Entry<String, Transaction> entry : transactionMap.entrySet()) {
            Transaction transaction = entry.getValue();
            String vipno = transaction.getVipno();
            if (transactionListMap.containsKey(vipno)) {
                transactionListMap.get(vipno).add(transaction);
            } else {
                List<Transaction> transactionList = new ArrayList<>();
                transactionList.add(transaction);
                transactionListMap.put(vipno, transactionList);
            }
        }

        logger.info("transactionListMap.size() = " + transactionListMap.size());

        for (Map.Entry<String, List<Transaction>> entry : transactionListMap.entrySet()) {
            String vipno = entry.getKey();
            List<Transaction> transactionList = entry.getValue();

            // Sort the transaction list first
            Collections.sort(transactionList, new Comparator<Transaction>() {

                @Override
                public int compare(Transaction o1, Transaction o2) {
                    return o1.getSldat().compareTo(o2.getSldat());
                }

            });

            // Get the transaction list or training
            List<Transaction> trainingTransactionList = new ArrayList<>();
            for (int i = 0; i < transactionList.size() * 0.6 - 1; i++) {
                trainingTransactionList.add(transactionList.get(i));
            }

            trainingTransactionListMap.put(vipno, trainingTransactionList);
        }
        logger.info("trainingTransactionListMap.size() = " + trainingTransactionListMap.size());

        for (Map.Entry<String, List<Transaction>> entry : trainingTransactionListMap.entrySet()) {
            List<Transaction> trainingTransactionList = entry.getValue();
            for (Transaction transaction : trainingTransactionList) {
                trainingTransactionMap.put(transaction.getUid(), transaction);
            }
        }
        logger.info("trainingTransactionMap.size() = " + trainingTransactionMap.size());

        return this;
    }

    /**
     * Cleans the data read before.
     *
     * @return The {@link FpGrowthSolver} itself.
     */
    public FpGrowthSolver cleanData() {
        transactionMap = new HashMap<>();
        transactionListMap = new HashMap<>();
        trainingTransactionMap = new HashMap<>();
        trainingTransactionListMap = new HashMap<>();
        rawDataType = RawDataType.NONE;
        return this;
    }

    /**
     * Performs the FP-Growth algorithm on the data read before.
     *
     * @param itemType The type of the item.
     * @param minsup   The support threshold.
     * @return The {@link FpGrowthSolver} itself.
     */
    public FpGrowthSolver solve(ItemType itemType, int minsup) {
        String inputFilename;
        String outputFilename;
        switch (itemType) {
            case PLUNO: {
                inputFilename = "input__pluno_minsup_" + minsup + ".txt";
                outputFilename = "output_pluno_minsup_" + minsup + ".txt";

                break;
            }

            case DPTNO: {
                inputFilename = "input_dptno_minsup_" + minsup + ".txt";
                outputFilename = "output_dptno_minsup_" + minsup + ".txt";

                break;
            }

            case BNDNO: {
                inputFilename = "input_bndno_minsup_" + minsup + ".txt";
                outputFilename = "output_bndno_minsup_" + minsup + ".txt";

                break;
            }

            default: {
                logger.error("Illegal ItemType: " + itemType);

                return this;
            }
        }
        switch (rawDataType) {
            case OLD_DATA: {
                inputFilename = "old_" + inputFilename;
                outputFilename = "old_" + outputFilename;

                break;
            }

            case NEW_DATA: {
                inputFilename = "new_" + inputFilename;
                outputFilename = "new_" + outputFilename;

                break;
            }

            default: {
                logger.error("Illegal RawDataType: " + rawDataType);

                return this;
            }
        }
        inputFilename = "fpgrowth_" + inputFilename;
        outputFilename = "fpgrowth_" + outputFilename;

        int totalNumber = trainingTransactionMap.size();
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(inputFilename))) {
            for (Map.Entry<String, Transaction> entry : trainingTransactionMap.entrySet()) {
                Transaction transaction = entry.getValue();

                boolean empty = true;
                StringBuilder stringBuilder = new StringBuilder();
                Set<String> itemSet = new HashSet<>();
                for (Product product : transaction.getProductList()) {
                    String item;
                    switch (itemType) {
                        case PLUNO: {
                            item = product.getPluno();

                            break;
                        }

                        case DPTNO: {
                            item = product.getDptno();

                            break;
                        }

                        case BNDNO: {
                            item = product.getBndno();

                            break;
                        }

                        default: {
                            logger.error("Illegal ItemType: " + itemType);

                            return this;
                        }
                    }

                    if ((item != null) && !item.isEmpty()) {
                        empty = false;
                        // For the stupid format of raw data
                        if (ItemType.BNDNO == itemType) {
                            itemSet.add(String.valueOf(Double.valueOf(item).intValue()));
                        } else {
                            itemSet.add(item);
                        }
                    }
                }
                // If the line is empty, skip this line
                if (empty) {
                    totalNumber--;

                    continue;
                }

                for (String item : itemSet) {
                    stringBuilder.append(item).append(" ");
                }
                bufferedWriter.write(stringBuilder.substring(0, stringBuilder.length() - 1));
                bufferedWriter.write("\n");
            }
        } catch (IOException e) {
            logger.error("e.getMessage() = " + e.getMessage());
        }

        Runtime runtime = Runtime.getRuntime();
        try {
            String command = "java -jar spmf.jar run " +
                    "FPGrowth_itemsets " + inputFilename + " " + outputFilename + " " +
                    String.format("%f", minsup * 100.0 / totalNumber) + "%";
            logger.info("Executing the command: \"" + command + "\"");
            Process process = runtime.exec(command);

            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            List<String> outputLineList = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                logger.info(line);
                outputLineList.add(line);
            }

            int exitVal = process.waitFor();

            // If the process exits with no error, collect the statistics
            if (0 == exitVal) {
                if (statisticsCollector != null) {
                    statisticsCollector.collect(rawDataType, itemType, minsup, outputLineList);
                }
            }

            System.out.println("Process exited with code " + exitVal);
        } catch (Exception exception) {
            logger.error("exception.getMessage() = " + exception.getMessage());
        }

        return this;
    }

    public FpGrowthSolver solveIntegrated(ItemType itemType, int minsup) {
        String inputFilename;
        String outputFilename;
        switch (itemType) {
            case PLUNO: {
                inputFilename = "input_pluno_minsup_" + minsup + ".txt";
                outputFilename = "output_pluno_minsup_" + minsup + ".txt";

                break;
            }

            case DPTNO: {
                inputFilename = "input_dptno_minsup_" + minsup + ".txt";
                outputFilename = "output_dptno_minsup_" + minsup + ".txt";

                break;
            }

            case BNDNO: {
                inputFilename = "input_bndno_minsup_" + minsup + ".txt";
                outputFilename = "output_bndno_minsup_" + minsup + ".txt";

                break;
            }

            default: {
                logger.error("Illegal ItemType: " + itemType);

                return this;
            }
        }
        switch (rawDataType) {
            case OLD_DATA: {
                inputFilename = "old_" + inputFilename;
                outputFilename = "old_" + outputFilename;

                break;
            }

            case NEW_DATA: {
                inputFilename = "new_" + inputFilename;
                outputFilename = "new_" + outputFilename;

                break;
            }

            default: {
                logger.error("Illegal RawDataType: " + rawDataType);

                return this;
            }
        }
        inputFilename = "fpgrowth_" + inputFilename;
        outputFilename = "fpgrowth_" + outputFilename;
        inputFilename = "integrated_" + inputFilename;
        outputFilename = "integrated_" + outputFilename;

        int totalNumber = trainingTransactionListMap.size();
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(inputFilename))) {
            for (Map.Entry<String, List<Transaction>> entry : trainingTransactionListMap.entrySet()) {
                List<Transaction> transactionList = entry.getValue();

                List<Product> productList = new ArrayList<>();
                for (Transaction transaction : transactionList) {
                    productList.addAll(transaction.getProductList());
                }

                boolean empty = true;
                StringBuilder stringBuilder = new StringBuilder();
                Set<String> itemSet = new HashSet<>();
                for (Product product : productList) {
                    String item;
                    switch (itemType) {
                        case PLUNO: {
                            item = product.getPluno();

                            break;
                        }

                        case DPTNO: {
                            item = product.getDptno();

                            break;
                        }

                        case BNDNO: {
                            item = product.getBndno();

                            break;
                        }

                        default: {
                            logger.error("Illegal ItemType: " + itemType);

                            return this;
                        }
                    }

                    if ((item != null) && !item.isEmpty()) {
                        empty = false;
                        // For the stupid format of raw data
                        if (ItemType.BNDNO == itemType) {
                            itemSet.add(String.valueOf(Double.valueOf(item).intValue()));
                        } else {
                            itemSet.add(item);
                        }
                    }
                }
                // If the line is empty, skip this line
                if (empty) {
                    totalNumber--;

                    continue;
                }

                for (String item : itemSet) {
                    stringBuilder.append(item).append(" ");
                }
                bufferedWriter.write(stringBuilder.substring(0, stringBuilder.length() - 1));
                bufferedWriter.write("\n");
            }
        } catch (IOException e) {
            logger.error("e.getMessage() = " + e.getMessage());
        }

        Runtime runtime = Runtime.getRuntime();
        try {
            String command = "java -jar spmf.jar run " +
                    "FPGrowth_itemsets " + inputFilename + " " + outputFilename + " " +
                    String.format("%f", minsup * 100.0 / totalNumber) + "%";
            logger.info("Executing the command: \"" + command + "\"");
            Process process = runtime.exec(command);

            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            List<String> outputLineList = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                logger.info(line);
                outputLineList.add(line);
            }

            int exitVal = process.waitFor();

            // If the process exits with no error, collect the statistics
            if (0 == exitVal) {
                if (statisticsCollector != null) {
                    statisticsCollector.collect(rawDataType, itemType, minsup, outputLineList);
                }
            }

            System.out.println("Process exited with code " + exitVal);
        } catch (Exception exception) {
            logger.error("exception.getMessage() = " + exception.getMessage());
        }

        return this;
    }

}
