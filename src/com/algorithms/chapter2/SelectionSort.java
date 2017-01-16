package com.algorithms.chapter2;
/**
 * 基本排序算法1:选择排序的基本实现
 * @author fengbincao
 */
public class SelectionSort extends BaseSort {
	
	@Override
	public void sort(Comparable[] arrays) {
		int len = arrays.length;
		for(int i = 0 ; i < len ; i++) {
			int min = i;					
			for(int j = i + 1 ; j < len ; j++) {
				if(less(arrays[j],arrays[min]))
					min = j;
			}	
			// 交换元素的代码写在内循环之外，每次交换都能排定一个元素
			exchange(arrays, i, min);
		}
	}
	
}
