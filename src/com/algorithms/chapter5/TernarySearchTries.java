package com.algorithms.chapter5;
/**
 * 单词查找树基本数据结构实现
 * @author fengbincao
 */
public class TernarySearchTries<Value> {

	// ASCII码字符基本大小，A对应65，Z对应90，一共26个
	// 基本符号表大小
	private static final int R = 26;        

	private TreeNode rootNode;
	
	// 当前树结构的大小
	private int size;          
	
	// R-way trie node
	private static class TreeNode {
		private Object value;
		private TreeNode[] next = new TreeNode[R];
	}
	
	public TernarySearchTries() {
		
	}
	
	/**
	 * API方法1：查找方法
	 * @param key			指定查找的键key
	 * @return				特定键对应的value
	 */
	@SuppressWarnings("unchecked")
	public Value get(String key) {
		if (key == null) {
			throw new IllegalArgumentException("argument to get() is null");
		}
		
		TreeNode result = get(rootNode, key, 0);
		if(result == null) {
			return null;
		}
		return (Value)result.value;
	}
	
	public void put(String key, Value value) {
		if (key == null) {
			throw new IllegalArgumentException("first argument to put() is null");
		}
		if (value == null) {
			// 如果value部分为null，则删除相应的节点
		} else {
			rootNode = put(rootNode, key, value, 0);
		}
	}
	
	private TreeNode get(TreeNode node, String key, int d) {
		if (node == null) {
			return null;
		}
		if (d == key.length()) {
			return node;
		}
		char c = (char) (key.charAt(d) - 65);
		
		return get(node.next[c], key, d+1);
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
		char c = (char) (key.charAt(d) - 65);
		node.next[c]= put(node.next[c], key, value, d+1);
        return node;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TernarySearchTries<Integer> st = new TernarySearchTries<Integer>();
		st.put("BABA", 1);
		st.put("ZZA", 1);
		st.put("ZA", 1);
	}

}
