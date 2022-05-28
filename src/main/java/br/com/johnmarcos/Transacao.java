package br.com.johnmarcos;

public abstract class Transacao {
	
	protected int idTransacao;
	protected static int contadorIdTransacao = 0;
	
// Construtor e gets
	
	public Transacao() {
		super();
	}
	
	public int getIdTransacao() {
		return this.idTransacao;
	}
	
	// As classes filhas Saque e Depósito necessitam implementar esse método
	public abstract char getTipoDeTransacao();
	
	// As classes filhas Saque e Depósito necessitam implementar esse método
	public abstract double getValorDaTransacao();
	
	
}
