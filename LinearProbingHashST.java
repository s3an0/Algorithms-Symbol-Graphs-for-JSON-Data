public class LinearProbingHashST <Key, Value> {

    private int N;
    private int M = 16;
    private Key[] keys;
    private Value[] vals;
    private int totalNonNullKeys;

    public LinearProbingHashST() {
        keys = (Key[]) new Object[M];
        vals = (Value[]) new Object[M];
    }

    public LinearProbingHashST(int cap) {
        M = cap;
        keys = (Key[]) new Object[M];
        vals = (Value[]) new Object[M];
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    private void resize(int cap) { // resizes hashtable
        LinearProbingHashST<Key, Value> t;
        t = new LinearProbingHashST<Key, Value>(cap);
        for(int i = 0; i < M; i++){
            if(keys[i] != null) {
                t.put(keys[i], vals[i]);
            }
        }
        keys = t.keys;
        vals = t.vals;
        M = t.M;
    }

    public void put(Key key, Value val) {
        if (N >= M/2) resize(2*M); // double the size of the array
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public Value get(Key key) // finds key and if in hashtable returns it
    {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key)) {
                return vals[i];
            }
        }
        return null;
    }

    public boolean contains(Key key) // checks if hashtable has key
    {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key)) {
                return true;
            }
        }
        return false;
    }

    public void delete(Key key) // deletes given key from hashtable
    {
        if (!contains(key)) {
            return;
        }

        int i = hash(key);

        while (!key.equals(keys[i])) {
            i = (i + 1) % M;
        }

        keys[i] = null;
        vals[i] = null;
        i = (i + 1) % M;

        while (keys[i] != null) {
            Key keyToRedo = keys[i];
            Value valToRedo = vals[i];
            keys[i] = null;
            vals[i] = null;
            N--;
            put(keyToRedo, valToRedo);
            i = (i + 1) % M;
        }

        N--;
        if (N > 0 || N == M/8) {
            resize(M / 2);
        }
    }

    public int size() {
        return N;
    }

    public Iterable<Key> keys() {
        Queue<Key> noNullKeys = new Queue<>();
        for(int i = 0; i < keys.length; i++) {
            if(keys[i] != null){
                noNullKeys.enqueue(keys[i]);
            }
        }

        return noNullKeys;
    }

    public int getTotalNonNullKeys() {
        for(int i = 0; i < keys.length; i++) {
            if(keys[i] != null){
                totalNonNullKeys++;
            }
        }

        return totalNonNullKeys;
    }

}
