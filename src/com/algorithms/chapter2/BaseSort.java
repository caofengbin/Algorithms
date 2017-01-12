package com.algorithms.chapter2;

public class BaseSort {
	
	public void sort(Comparable[] arrays) {
		
	}
	
	public boolean isSorted(Comparable[] arrays) {
		for(int i = 1 ; i < arrays.length ; i++)
			if(less(arrays[i],arrays[i-1]))
				return false;
		return true;
	}
	
	protected boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}

	protected void exchange(Comparable[] a, int i, int j) {
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
	
	public void show(Comparable[] arrays) {
		for(int i = 0 ; i < arrays.length ; i++)
			System.out.print(arrays[i] + "	");
		System.out.println("\n");
	}
}
