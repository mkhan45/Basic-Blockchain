import java.io.Serializable;
import java.util.ArrayList;

class Blockchain implements Serializable {
    static final long serialVersionUID = -5557682781880916697L;
    ArrayList<Block> chain;
    ArrayList<Transaction> currentTransactions;
    private transient hashMaker hm = new hashMaker();

    public Blockchain() {
        chain = new ArrayList<Block>();
        chain.add(new Block());
        currentTransactions = new ArrayList<Transaction>();
    }

    public boolean newBlock() {
        Block b = new Block(chain.size(), chain.get(chain.size() - 1).getHash());
        b.proofOfWork(hm);
        if (testBlock(b, hm)) {
            chain.add(b);
            return true;
        }
        return false;
    }

    public boolean newTransaction(double value, User u1, User u2) {
        Block b = chain.get(chain.size() - 1);
        Transaction t = new Transaction(u1, u2, value);
        b.addTransaction(t);
        return true;
    }

    public Block lastBlock() {
        return chain.get(chain.size() - 1);
    }

    public boolean testBlock(Block b, hashMaker h) {
        try {
            Block b2 = chain.get(b.index() - 1);
            int hashMult = b2.getHash() * b.getHash();

            String hash = h.hash(hashMult);
            if (hash.contains("39003500"))
                return true;
            return false;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public String toString() {
        String s = "[\n";
        for (Block b : chain)
            s += b.toString() + "\n\n";
        return s + "\n]";
    }

    public ArrayList<Block> getChain(){
        return chain;
    }

    public hashMaker getHm(){
        return hm;
    }

}