package br.com.johnmarcos;

public class Saque extends Transacao {

	private ContaCorrente contaCorrente;
	private double valorSacado;
	private char tipoDeTransacao = 'S';
	
// Construtor e gets
	
	public Saque(){
		super();
	}
	
	public Saque(ContaCorrente contaCorrente, double valorSacado){
		super();
		this.contaCorrente = contaCorrente;
		this.valorSacado = valorSacado;
		this.idTransacao = contadorIdTransacao++;
	}
	
	public ContaCorrente getContaCorrente() {
		return this.contaCorrente;
	}
	
	@Override
	public char getTipoDeTransacao() {
		return this.tipoDeTransacao;
	}

	@Override
	public double getValorDaTransacao() {
		return this.valorSacado;
	}

}
