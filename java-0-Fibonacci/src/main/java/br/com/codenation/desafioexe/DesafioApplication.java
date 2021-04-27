package br.com.codenation.desafioexe;

import java.util.List;
import java.util.ArrayList;

public class DesafioApplication {

	public static List<Integer> fibonacci() {
		List<Integer> fibonacciSerie = new ArrayList<Integer>();

		int f0;
		int f1 = 1;

		for(int fn=0; fn < 400; fn = f0 + f1 ) {
			fibonacciSerie.add(fn);
			f0 = f1;
			f1 = fn;
		}
		return fibonacciSerie;
	}
	
	public static Boolean isFibonacci(Integer a) {
		return fibonacci().contains(a);
	}

}

