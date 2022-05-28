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
	
	// As classes filhas Saque e Dep�sito necessitam implementar esse m�todo
	public abstract char getTipoDeTransacao();
	
	// As classes filhas Saque e Dep�sito necessitam implementar esse m�todo
	public abstract double getValorDaTransacao();
	
	
}
