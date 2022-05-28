package br.com.johnmarcos;

public class Deposito extends Transacao {

	private ContaCorrente contaCorrente = null;
	private double valorDepositado = 0;
	private char tipoDeTransacao = 'D';
	
// Construtor e gets
	
	public Deposito(){
		super();
	}
	
	public Deposito(ContaCorrente contaCorrente, double valorADepositar){
		super();
		this.contaCorrente = contaCorrente;
		this.valorDepositado = valorADepositar;
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
		return this.valorDepositado;
	}
	
}
