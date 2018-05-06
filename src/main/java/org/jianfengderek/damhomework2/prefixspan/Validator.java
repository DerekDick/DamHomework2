package org.jianfengderek.damhomework2.prefixspan;

import org.jianfengderek.damhomework2.ItemType;
import org.jianfengderek.damhomework2.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Validator {

    // For logging
    private static final Logger logger = LoggerFactory.getLogger(Validator.class);

    public static boolean validate(List<Transaction> resultTransactionList,
                                   List<Transaction> truthTransactionList,
                                   ItemType itemType) {
        int index = 0;
        for (Transaction transaction : resultTransactionList) {
            while (!transaction.isSubTransactionOf(truthTransactionList.get(index), itemType)) {
                index++;
                if (index >= truthTransactionList.size()) {
                    return false;
                }
            }
        }

        return true;
    }

}
