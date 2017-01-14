package com.algorithms.chapter2;
/**
 * 排序算法的基本模板
 * @author fengbincao
 *
 */
public class BaseSort {
	
	private int compareCount;
	private int exchageCount;
	
	/**
	 * 排序的核心实现，由各个子类自己去实现
	 * @param arrays
	 */
	public void sort(Comparable[] arrays) {
		
	}
	
	/**
	 * 判断给定的数组是否是有序的
	 * @param arrays
	 * @return
	 */
	public boolean isSorted(Comparable[] arrays) {
		for(int i = 1 ; i < arrays.length ; i++)
			if(less(arrays[i],arrays[i-1]))
				return false;
		return true;
	}
	
	/**
	 * 打印给定数组的方法
	 * @param arrays
	 */
	public void show(Comparable[] arrays) {
		for(int i = 0 ; i < arrays.length ; i++)
			System.out.print(arrays[i] + "	");
		System.out.println("\n");
	}
	
	public void reset() {
		compareCount = 0;
		exchageCount = 0;
	}
	
	public int getCompareCount() {
		return compareCount;
	}

	public int getExchageCount() {
		return exchageCount;
	}

	/**
	 * 用于比较给定的两个数据的大小
	 * @param v
	 * @param w
	 * @return
	 */
	protected boolean less(Comparable v, Comparable w) {
		compareCount++;
		return v.compareTo(w) < 0;
	}

	/**
	 * 交换数组
	 * @param a
	 * @param i
	 * @param j
	 */
	protected void exchange(Comparable[] array, int i, int j) {
		Comparable t = array[i];
		array[i] = array[j];
		array[j] = t;
		exchageCount++;
	}
}
