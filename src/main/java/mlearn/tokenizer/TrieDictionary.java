package mlearn.tokenizer;

import template.debug.InputReaderUnicode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dy[jealousing@gmail.com] on 17-7-23.
 */
class TrieDictionary extends Dictionary {

    public TrieDictionary(InputReaderUnicode in) {
        while (!in.isExhausted()) {
            String line = in.readLine();
            Token token = new Token();
            String[] vecBuf = line.split(" ");
            if (2 > vecBuf.length) {
                continue;
            }
            if (4 < vecBuf.length) {
                continue;
            }
            token.what = vecBuf[0].toCharArray();
            token.freq = Integer.valueOf(vecBuf[1]);
            if (3 <= vecBuf.length) {
                //token.tag = vecBuf[2];
            }
            this.insert(token);
        }
        in.close();
    }

    class MyArrayList<T> {
        Object[] arr;
        int defaultLen = 1;
        int realSize = 0;

        public MyArrayList() {
            arr = new Object[defaultLen];
        }

        public T get(int index) {
            return (T) arr[index];
        }

        public void add(int index, T nElement) {
            if (index >= arr.length || realSize >= arr.length) {
                Object[] nArr = new Object[arr.length + 1];
                for (int i = 0; i < index; ++i) nArr[i] = arr[i];
                nArr[index] = nElement;
                for (int i = index; i < realSize; ++i) nArr[i + 1] = arr[i];
                arr = nArr;
            } else {
                for (int i = realSize; i > index; --i) arr[i] = arr[i - 1];
                arr[index] = nElement;
            }
            realSize += 1;
        }

        public int size() {
            return realSize;
        }
    }

    static class Token {
        char[] what;
        int freq;
        //double logFreq;
        //String tag;
    }

    class TrieNode {
        char what;
        //Map<Character, TrieNode> childs;
        //List<TrieNode> childs;
        MyArrayList<TrieNode> childs;
        Token token;

        public TrieNode() {
            //childs = new HashMap<>();
            //childs = new ArrayList<>();
            token = null;
        }

        public TrieNode(char what) {
            //childs = new HashMap<>();
            //childs = new ArrayList<>();
            token = null;
            this.what = what;
        }

        public Map<Character, TrieNode> getNewChilds(int x, int y) {
            return new HashMap<Character, TrieNode>();
        }

        public List<TrieNode> getNewChilds(int x) {
            return new ArrayList<TrieNode>();
        }

        public MyArrayList<TrieNode> getNewChilds() {
            return new MyArrayList<TrieNode>();
        }

        public boolean equals(TrieNode obj) {
            return what == obj.what;
        }

        @Override
        public int hashCode() {
            return what;
        }
    }

    TrieNode root = new TrieNode();

    public void insert(Token token) {
        TrieNode cur = root;
        for (int i = 0; i < token.what.length; ++i) {
            TrieNode nNode = new TrieNode(token.what[i]);
            if (canFind(cur.childs, nNode)) {
                cur = index(cur.childs, nNode);
                continue;
            }
            if (cur.childs == null) cur.childs = cur.getNewChilds(); //TODo ugly
            addNChild(cur.childs, nNode);
            cur = nNode;
        }
        //cur.token = token;
    }

    private void addNChild(Map<Character, TrieNode> childs, TrieNode nNode) {
        childs.put(nNode.what, nNode);
    }

    private TrieNode index(Map<Character, TrieNode> childs, TrieNode nNode) {
        return childs.get(nNode.what);
    }

    private boolean canFind(Map<Character, TrieNode> childs, TrieNode token) {
        if (childs == null) return false;
        return childs.containsKey(token.what);
    }

    private void addNChild(List<TrieNode> childs, TrieNode nNode) {
        childs.add(lowerBound(childs, nNode), nNode);
    }

    private TrieNode index(List<TrieNode> childs, TrieNode nNode) {
        return childs.get(lowerBound(childs, nNode));
    }

    private boolean canFind(List<TrieNode> childs, TrieNode token) {
        if (childs == null) return false;
        int lb = lowerBound(childs, token);
        int rb = upperBound(childs, token);
        return lb < rb;
    }

    private void addNChild(MyArrayList<TrieNode> childs, TrieNode nNode) {
        childs.add(lowerBound(childs, nNode), nNode);
    }

    private TrieNode index(MyArrayList<TrieNode> childs, TrieNode nNode) {
        return childs.get(lowerBound(childs, nNode));
    }

    private boolean canFind(MyArrayList<TrieNode> childs, TrieNode token) {
        if (childs == null) return false;
        int lb = lowerBound(childs, token);
        int rb = upperBound(childs, token);
        return lb < rb;
    }

    private int lowerBound(MyArrayList<TrieNode> childs, TrieNode token) {
        int l = 0, r = childs.size();
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (token.what > childs.get(mid).what) l = mid + 1;
            else r = mid;
        }
        return l;
    }

    private int upperBound(MyArrayList<TrieNode> childs, TrieNode token) {
        int l = 0, r = childs.size();
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (token.what >= childs.get(mid).what) l = mid + 1;
            else r = mid;
        }
        return l;
    }

    private int lowerBound(List<TrieNode> childs, TrieNode token) {
        int l = 0, r = childs.size();
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (token.what > childs.get(mid).what) l = mid + 1;
            else r = mid;
        }
        return l;
    }

    private int upperBound(List<TrieNode> childs, TrieNode token) {
        int l = 0, r = childs.size();
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (token.what >= childs.get(mid).what) l = mid + 1;
            else r = mid;
        }
        return l;
    }


    public int contains(char[] token, int L, int R) {
        TrieNode cur = root;
        for (int i = L; i < R; ++i) {
            TrieNode nNode = new TrieNode(token[i]);
            if (!canFind(cur.childs, nNode))
                return -1;
            cur = index(cur.childs, nNode);
        }
        return cur.what;
    }

    @Override
    public float getLogFreq(int index) {
        return 0; //TODO
    }

    @Override
    public float getMinLogFreq() {
        return 0;
    }

}
