package org.atique.customsort;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author atiQue
 * @since 21'Mar 2024 at 12:09 PM
 */

public class mplementingComparableSolution {
    public static void main(String[] args) {

        // Get the transactions and sort them based on compareTo on Transaction class
        List<Transaction> transactions = getTransactions();
        Collections.sort(transactions);

        // Grouping the transactions
        List<List<Transaction>> result = new ArrayList<>();

        for (int i = 0; i < transactions.size(); i++) {
            if (i == 0) {
                result.add(new ArrayList<>(Collections.singletonList(transactions.get(i))));
            } else {
                if (isSameGroup(transactions.get(i), transactions.get(i - 1))) {
                    result.get(result.size() - 1).add(transactions.get(i));
                } else {
                    result.add(new ArrayList<>(Collections.singletonList(transactions.get(i))));
                }
            }
        }

        //sort the group by first element time
        result.sort(Comparator.comparingLong(t -> Transaction.getTimeInMillisFromString(t.get(0).getTime())));

        // Printing the output to console
        result.forEach(l -> {
            l.forEach(t -> System.out.println(t.toString()));
            System.out.println("----------------");
        });
    }

    private static List<Transaction> getTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(5, "B", "D", new BigDecimal("250"), "2018-03-02T10:33:00.000Z"));
        transactions.add(new Transaction(1, "B", "C", new BigDecimal("100"), "2018-03-02T10:33:00.000Z"));
        transactions.add(new Transaction(2, "B", "C", new BigDecimal("100"), "2018-03-02T10:33:50.000Z"));
        transactions.add(new Transaction(6, "B", "D", new BigDecimal("250"), "2018-03-02T10:33:05.000Z"));
        transactions.add(new Transaction(3, "B", "C", new BigDecimal("100"), "2018-03-02T10:34:30.000Z"));
        transactions.add(new Transaction(4, "A", "B", new BigDecimal("100"), "2019-03-02T10:34:30.000Z"));
        return transactions;
    }

    private static boolean isSameGroup(Transaction curr, Transaction prev) {

        if (curr.getSourceAccount().equals(prev.getSourceAccount()) &&
                curr.getTargetAccount().equals(prev.getTargetAccount()) &&
                curr.getAmount().equals(prev.getAmount()) &&
                Transaction.getTimeInMillisFromString(curr.getTime()) - Transaction.getTimeInMillisFromString(prev.getTime()) <= 60000L) {
            return true;
        }

        return false;
    }

    static class Transaction implements Comparable<Transaction> {

        private Integer id;
        private String sourceAccount;
        private String targetAccount;
        private BigDecimal amount;
        private String time;

        public Transaction(Integer id, String sourceAccount, String targetAccount, BigDecimal amount, String time) {
            this.id = id;
            this.sourceAccount = sourceAccount;
            this.targetAccount = targetAccount;
            this.amount = amount;
            this.time = time;
        }

        @Override
        public int compareTo(Transaction other) {
            int result = this.sourceAccount.compareTo(other.sourceAccount);
            if (result != 0) return result;

            result = this.targetAccount.compareTo(other.targetAccount);
            if (result != 0) return result;

            result = this.amount.compareTo(other.amount);
            if (result != 0) return result;

            Long thisTime = getTimeInMillisFromString(this.time);
            Long otherTime = getTimeInMillisFromString(other.time);
            return thisTime.compareTo(otherTime);
        }

        private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        public static Long getTimeInMillisFromString(String timeStr) {

            Date date = null;

            try {
                date = sdf.parse(timeStr);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return date == null ? 0L : date.getTime();
        }

        @Override
        public String toString() {
            return "Transaction{" +
                    "id=" + id +
                    ", sourceAccount='" + sourceAccount + '\'' +
                    ", targetAccount='" + targetAccount + '\'' +
                    ", amount=" + amount +
                    ", time='" + time + '\'' +
                    '}';
        }

        public Integer getId() {
            return id;
        }

        public String getSourceAccount() {
            return sourceAccount;
        }

        public String getTargetAccount() {
            return targetAccount;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public String getTime() {
            return time;
        }
    }
}
