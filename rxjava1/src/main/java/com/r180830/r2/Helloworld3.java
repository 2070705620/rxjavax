package com.r180830.r2;

import rx.Observable;

public class Helloworld3 {
public static void main(String[] args) {
	Observable.just(1,2,3).subscribe(System.out::println);
}
}
