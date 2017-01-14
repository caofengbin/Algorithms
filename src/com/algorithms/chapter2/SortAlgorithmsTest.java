package com.algorithms.chapter2;

public class SortAlgorithmsTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Character[] arrays = {'S','O','R','T','E','X','A','M','P','L','E'};
		BaseSort sortUtil = new SelectionSort();
		sortUtil.reset();
		sortUtil.show(arrays);
		sortUtil.sort(arrays);
		sortUtil.show(arrays);
		System.out.println("进行比较的次数为:" + sortUtil.getCompareCount());
		System.out.println("进行交换的次数为:" + sortUtil.getExchageCount());
	}

}
