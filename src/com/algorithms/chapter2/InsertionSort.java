package com.algorithms.chapter2;
/**
 * 插入排序的具体实现
 * @author fengbincao
 *
 */
public class InsertionSort extends BaseSort {

	@Override
	public void sort(Comparable[] arrays) {
		int len = arrays.length;
		for(int i = 1; i < len; i ++) {
			for(int j = i ; j > 0 ; j--) {
				if(less(arrays[j],arrays[j-1])) {
					exchange(arrays , j, j - 1);
					continue;
				}
			}
		}
	}
}
