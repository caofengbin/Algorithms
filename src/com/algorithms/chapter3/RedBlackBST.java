package com.algorithms.chapter3;

import java.util.NoSuchElementException;

/**
 * 红黑树结构的基本实现
 * @author fengbincao
 */
public class RedBlackBST<Key extends Comparable<Key>, Value>  {

	private static final boolean RED   = true;
    private static final boolean BLACK = false;
    
    // root of the BST
    private Node root;  
    
    // 红黑树结构的节点数据结构
    private class Node {
        private Key key;           // key
        private Value val;         // associated data
        private Node left, right;  // links to left and right subtrees
        private boolean color;     // color of parent link
        private int size;          // subtree count

        public Node(Key key, Value val, boolean color, int size) {
            this.key = key;
            this.val = val;
            this.color = color;
            this.size = size;
        }
    }
    
    /**
     * Initializes an empty symbol table.
     */
    public RedBlackBST() {
    }
    
    /**
     * API方法1：返回当前节点数目
     * @return		 节点数目
     */
    public int size() {
        return size(root);
    }
    
    /**
     * API方法2：判断当前结构是否为空
     * @return   当前结构是否为空
     */
    public boolean isEmpty() {
        return root == null;
    }
    
    /**
     * API方法3：获取key值对应的value
     * @param key		  key值
     * @return			      对应的value
     */
    public Value get(Key key) {
        if (key == null) {
        	throw new IllegalArgumentException("argument to get() is null");
        }
        return get(root, key);
    }
    
    /**
     * API方法4：判定指定key值是否存在
     * @param key		待判定key
     * @return			true if the key exit
     */
    public boolean contains(Key key) {
        return get(key) != null;
    }
    
    /**
     * API方法5:存入新节点
     * @param key
     * @param val
     */
    public void put(Key key, Value val) {
        if (key == null) {
        	throw new IllegalArgumentException("first argument to put() is null");
        }
        if (val == null) {
            delete(key);
            return;
        }

        root = put(root, key, val);
        root.color = BLACK;
    }
    
    /**
     * API方法6:删除指定key值的数据
     * @param key
     */
    public void delete(Key key) { 
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (!contains(key)) return;

        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = delete(root, key);
        if (!isEmpty()) {
        	root.color = BLACK;
        }
       
    }
    
    // delete the key-value pair with the given key rooted at h
    private Node delete(Node h, Key key) { 
        // assert get(h, key) != null;

        if (key.compareTo(h.key) < 0)  {
            if (!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);
            h.left = delete(h.left, key);
        }
        else {
            if (isRed(h.left))
                h = rotateRight(h);
            if (key.compareTo(h.key) == 0 && (h.right == null))
                return null;
            if (!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
            if (key.compareTo(h.key) == 0) {
                Node x = min(h.right);
                h.key = x.key;
                h.val = x.val;
                // h.val = get(h.right, min(h.right).key);
                // h.key = min(h.right).key;
                h.right = deleteMin(h.right);
            }
            else h.right = delete(h.right, key);
        }
        return balance(h);
    }
    
    // insert the key-value pair in the subtree rooted at h
    private Node put(Node h, Key key, Value val) { 
        if (h == null) return new Node(key, val, RED, 1);

        int cmp = key.compareTo(h.key);
        if (cmp < 0) {
        	h.left  = put(h.left,  key, val); 
        }
        else if (cmp > 0) {
        	h.right = put(h.right, key, val); 
        }
        else {
        	h.val   = val;
        }

        // fix-up any right-leaning links
        if (isRed(h.right) && !isRed(h.left)) {
        	h = rotateLeft(h);
        }
        if (isRed(h.left)  &&  isRed(h.left.left)) {
        	h = rotateRight(h);
        }
        if (isRed(h.left)  &&  isRed(h.right)) {
        	flipColors(h);
        }
        h.size = size(h.left) + size(h.right) + 1;

        return h;
    }
    
    // value associated with the given key in subtree rooted at x; null if no such key
    private Value get(Node x, Key key) {
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
            	x = x.left;
            }
            else if (cmp > 0) {
            	x = x.right;
            }
            else {
            	return x.val;
            }
        }
        return null;
    }
    
    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    // number of node in subtree rooted at x; 0 if x is null
    private int size(Node x) {
        if (x == null) {
        	return 0;
        }
        return x.size;
    } 
    
 // Assuming that h is red and both h.left and h.left.left
    // are black, make h.left or one of its children red.
    private Node moveRedLeft(Node h) {
        // assert (h != null);
        // assert isRed(h) && !isRed(h.left) && !isRed(h.left.left);

        flipColors(h);
        if (isRed(h.right.left)) { 
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }
    
    // make a left-leaning link lean to the right
    private Node rotateRight(Node h) {
        // assert (h != null) && isRed(h.left);
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = x.right.color;
        x.right.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    // make a right-leaning link lean to the left
    private Node rotateLeft(Node h) {
        // assert (h != null) && isRed(h.right);
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = x.left.color;
        x.left.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }
    
    // flip the colors of a node and its two children
    private void flipColors(Node h) {
        // h must have opposite color of its two children
        // assert (h != null) && (h.left != null) && (h.right != null);
        // assert (!isRed(h) &&  isRed(h.left) &&  isRed(h.right))
        //    || (isRed(h)  && !isRed(h.left) && !isRed(h.right));
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }
    
    private Node balance(Node h) {
        if (isRed(h.right))                      h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))     flipColors(h);

        h.size = size(h.left) + size(h.right) + 1;
        return h;
    }
    
    // Assuming that h is red and both h.right and h.right.left
    // are black, make h.right or one of its children red.
    private Node moveRedRight(Node h) {
        // assert (h != null);
        // assert isRed(h) && !isRed(h.right) && !isRed(h.right.left);
        flipColors(h);
        if (isRed(h.left.left)) { 
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }
    
    // the smallest key in subtree rooted at x; null if no such key
    private Node min(Node x) { 
        // assert x != null;
        if (x.left == null) return x; 
        else                return min(x.left); 
    } 
    
    // delete the key-value pair with the minimum key rooted at h
    private Node deleteMin(Node h) { 
        if (h.left == null)
            return null;

        if (!isRed(h.left) && !isRed(h.left.left))
            h = moveRedLeft(h);

        h.left = deleteMin(h.left);
        return balance(h);
    }	
    
    /**
	 * @param args
	 */
	public static void main(String[] args) {
		RedBlackBST<Integer,Boolean> redBlackBST = new RedBlackBST();
		redBlackBST.put(60098, true);
		redBlackBST.put(61098, true);
		redBlackBST.put(60345, true);
		redBlackBST.put(60111, true);
		System.out.println(redBlackBST.contains(60098));
		System.out.println(redBlackBST.contains(600989999));
	}

}
