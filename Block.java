import java.util.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

class Block {
    private int index;
    private long timestamp;
    private ArrayList<Transaction> transactions;
    private int proof;
    private int previousHash;

    public Block(int index, int previousHashNumber) {
        this.index = index;
        this.previousHash = previousHashNumber;
        transactions = new ArrayList<Transaction>();
        timestamp = System.currentTimeMillis();
    }

    public Block() { // original block probably not the best implementation
        index = 0;
        previousHash = 1;
        proof = 1;
        transactions = new ArrayList<Transaction>();
        timestamp = System.currentTimeMillis();
    }

    public int proofOfWork(hashMaker h) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            int numberToTest = 1;
            while (true) {
                int hashMult = previousHash * numberToTest;
                if (h.hash(hashMult).contains("420")) {
                    proof = numberToTest;
                    return proof;
                }
                numberToTest++;
            }
        } catch (Exception e) {
            System.out.println(e);
            return -1;
        }

    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction t) {
        transactions.add(t);
    }

    public int getHash() {
        return proof;
    }

    public int index() {
        return index;
    }

    public String toString() {
        String s = "{ \n";
        for (Transaction t : transactions)
            s += t.toString() + "\n";
        s += "}";
        return s;
    }

    public long getTimestamp() {
        return timestamp;
    }
}