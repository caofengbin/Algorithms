package com.algorithms.chapter5;
/**
 * 单词查找树基本数据结构实现
 * @author fengbincao
 */
public class TernarySearchTries<Value> {

	// ASCII码字符基本大小，A对应65，Z对应90共26个，加上美股市场前缀 .  一共27个基本字符
    // 基本符号表大小
    private static final int R = 27;

    private TreeNode rootNode;

    // 当前树结构的大小
    private int size;

    // 字典树node结构
    private static class TreeNode {
        private Object value;
        private TreeNode[] next = new TreeNode[R];
    }

    /**
     * API方法1：查找方法
     *
     * @param key 指定查找的键key
     * @return 特定键对应的value
     */
    @SuppressWarnings("unchecked")
    public Value get(String key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to get() is null");
        }

        TreeNode result = get(rootNode, key, 0);
        if (result == null) {
            return null;
        }
        return (Value) result.value;
    }

    /**
     * API方法2：插入节点的方法
     *
     * @param key   待插入节点的key
     * @param value 待插入节点的value
     */
    public void put(String key, Value value) {
        if (key == null) {
            throw new IllegalArgumentException("first argument to put() is null");
        }
        if (value == null) {
            // 如果value部分为null，则删除相应的节点
            delete(key);
        } else {
            rootNode = put(rootNode, key, value, 0);
        }
    }

    /**
     * API方法3：删除指定节点
     *
     * @param key 待删除节点的key值
     */
    public void delete(String key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to delete() is null");
        }
        rootNode = delete(rootNode, key, 0);
    }

    /**
     * API方法4：判断当前字典树结构大小
     *
     * @return 当前有效的节点数量
     */
    public int size() {
        return size;
    }


    private TreeNode get(TreeNode node, String key, int d) {
        if (node == null) {
            return null;
        }
        if (d == key.length()) {
            return node;
        }
        char c;
        if (key.charAt(d) == '.') {
            c = 26;
        } else {
            c = (char) (key.charAt(d) - 65);
        }

        return get(node.next[c], key, d + 1);
    }

    private TreeNode put(TreeNode node, String key, Value value, int d) {
        // 为空树时新建root节点
        if (node == null) {
            node = new TreeNode();
        }
        if (d == key.length()) {
            // 遍历至待插入的节点位置
            if (node.value == null) {
                size++;
            }
            node.value = value;
            return node;
        }
        // 递归的遍历至下一个字符节点
        char c;
        if (key.charAt(d) == '.') {
            c = 26;
        } else {
            c = (char) (key.charAt(d) - 65);
        }
        node.next[c] = put(node.next[c], key, value, d + 1);
        return node;
    }

    private TreeNode delete(TreeNode x, String key, int d) {
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            if (x.value != null) {
                size--;
            }
            x.value = null;
        } else {
            char c;
            if (key.charAt(d) == '.') {
                c = 26;
            } else {
                c = (char) (key.charAt(d) - 65);
            }
            x.next[c] = delete(x.next[c], key, d + 1);
        }

        if (x.value != null) {
            return x;
        }
        for (int c = 0; c < R; c++) {
            if (x.next[c] != null) {
                return x;
            }
        }

        return null;
    }
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TernarySearchTries<Integer> st = new TernarySearchTries<Integer>();
		st.put("BABA", 1);
		st.put("ZZA.US", 1);
		st.put("ZA", 1);
		System.out.println(st.get("ZZA.US"));
		st.delete("ZZA.US");
		System.out.println(st.get("ZZA.US"));
	}

}
