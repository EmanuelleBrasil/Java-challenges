package br.com.codenation.calculadora;


public class CalculadoraSalario {

	public long calcularSalarioLiquido(double salarioBase) {
		double salariioMinimo = 1039.00;
		if(salarioBase < salariioMinimo) return 0;
		double salarioLiquido = calcularIRRF(calcularINSS(salarioBase));

		return Math.round(salarioLiquido);
	}

	private double calcularINSS(double salarioBase) {
		if(salarioBase <= 1500.00) {
			return salarioBase - salarioBase * 0.08;
		} else if(salarioBase > 4000.00) {
			return salarioBase - salarioBase * 0.11;
		} else return salarioBase - salarioBase * 0.09;
	}

	private double calcularIRRF(double salarioDescontadoINSS) {
		if(salarioDescontadoINSS < 3000.00) {
			return salarioDescontadoINSS;
		} else if(salarioDescontadoINSS > 6000.00) {
			return salarioDescontadoINSS - salarioDescontadoINSS * 0.15;
		} else return salarioDescontadoINSS - salarioDescontadoINSS * 0.075;
	}

}
