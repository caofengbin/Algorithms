package com.algorithms.chapter2;
/**
 * 基本排序算法1:选择排序的改进版
 * 优化时间效率,增设一个Change标志位，
 * 用于判断每一轮外循环之后，当前数组是否已经有序了
 * 如果已经有序，就结束当前算法
 * @author caofengbin
 */
public class ImproveSelectionSort extends BaseSort {
	
	@Override
	public void sort(Comparable[] arrays) {
		int len = arrays.length;
		for(int i = 0 ; i < len ; i++) {
			int min = i;		
			boolean needExchange = false;
			for(int j = i + 1 ; j < len ; j++) {
				if(less(arrays[j],arrays[min])) {
					min = j;
					needExchange = true;
					// 如果没进入该判断语句内部，则说明当前数组已经有序，直接结束算法即可以
				}
			}	
			if(!needExchange)
				break;
			exchange(arrays, i, min);
		}
	}
}