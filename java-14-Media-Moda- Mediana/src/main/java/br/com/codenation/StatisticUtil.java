package br.com.codenation;

import java.util.Arrays;

public class StatisticUtil {

	public static int average(int[] elements) {
		int soma = 0;
		for (int i=0; i<elements.length; i++) {
			soma += elements[i];
		}
		return soma/elements.length;
	}

	public static int mode(int[] elements) {
		Arrays.sort(elements);
		int frequenciaNumeroAtual;
		int frequenciaNumeroAnterior = 0;
		int moda = elements[0];

		for(int i=1; i<elements.length; i++) {
			frequenciaNumeroAtual = 0;
			for (int j=0; j < elements.length; j ++) {
				if(elements[i] == elements[j]) {
					frequenciaNumeroAtual++;
				}
				if (frequenciaNumeroAtual > frequenciaNumeroAnterior) {
					frequenciaNumeroAnterior = frequenciaNumeroAtual;
					moda = elements[i];
				}
			}
		}
		return moda;
	}

	public static int median(int[] elements) {
		Arrays.sort(elements);
		int tamanhoArray = elements.length;
		int mediana = 0;
		if (tamanhoArray % 2 == 0) {
			mediana = (elements[tamanhoArray/2 - 1] + elements[tamanhoArray/2]) / 2;
		} else mediana = elements[tamanhoArray/2];

		return mediana;
	}
}