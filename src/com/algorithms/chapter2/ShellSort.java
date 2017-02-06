package com.algorithms.chapter2;
/**
 * 基本排序算法3:希尔排序的基本实现
 * 选择的递增序列为：1,4,13,40,121,。。。。。。
 * @author fengbincao
 *
 */
public class ShellSort extends BaseSort {

	@Override
	public void sort(Comparable[] arrays) {
		int N = arrays.length;
		int h = 1;
		while(h < N / 3) {
			// 递增序列为 1,4,13,40,121,364....
			h = h * 3 + 1;
		}
		while(h >= 1) {
			// 将数组变为h有序
			for(int i = h ; i < N ; i++) {
				for(int j = i ; j >= h && less(arrays[j],arrays[j-h]) ; j -= h)
					exchange(arrays, j, j - h);
			}
			h = h / 3;
		}	
	}
}
