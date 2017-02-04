package com.algorithms.chapter2;
/**
 * 基本排序算法2:插入排序的基本实现
 * @author fengbincao
 *
 */
public class InsertionSort extends BaseSort {

	@Override
	public void sort(Comparable[] arrays) {
		int len = arrays.length;
		for(int i = 1; i < len; i ++) {
			// 本算法实现的一个关键点就在于将less操作放在了&&后面，一旦发现less操作得到的结果为false，说明
			// 前面所有的数据已经有序了，不用再进行比较，结束内循环操作
			for(int j = i; j > 0 && less(arrays[j],arrays[j-1]); j--) {
				exchange(arrays , j, j - 1);
			}
		}
	}
}
