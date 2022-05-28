package br.com.johnmarcos;

import java.util.ArrayList;

public class ContaCorrente {

	private int idContaCorrente;
	private static int contadorIdContaCorrente = 0;
	private final PessoaFisica titularDaConta;
	private double saldo = 0;
	private String tipoDeConta = "Conta de Pessoa Física";
	private ArrayList<Transacao> transacoesRealizadas = new ArrayList<Transacao>();
	
// Construtoress
	
	public ContaCorrente(PessoaFisica titularDaConta){
		super();
		this.titularDaConta = titularDaConta;
		this.idContaCorrente = contadorIdContaCorrente++;
	}
	
// Método para imprimir valores (GET)
	
	public String getNomeTitularDaConta() {
		return this.titularDaConta.getNome();
	}
	
	public String getCPFTitularDaConta() {
		return this.titularDaConta.getCPF();
	}
	
	public int getIdContaCorrente(){
		return this.idContaCorrente;
	}

	public PessoaFisica getTitularDaConta() {
		return this.titularDaConta;
	}
	
	public double getSaldo(){
		return this.saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public String getTipoDeConta(){
		return this.tipoDeConta;
	}
	
	public ArrayList<Transacao> getTransacoesRealizadas() {
		return transacoesRealizadas;
	}
	
// Método para consultar transações realizadas
	
	public void consultarTransacoes() {
		
		if( this.transacoesRealizadas.isEmpty() ) {
			System.out.println("Nenhuma transação existente.\n");
			return;
		
		} else {
			
			for(Transacao transacao : this.transacoesRealizadas)
				System.out.println(
					"\nTipo de Transação: " + transacao.getTipoDeTransacao()
					+ "\nValor: " + transacao.getValorDaTransacao());
		}
	} 
	
// Método para validar se um obejto transação não contém objeto um saque ou depósito
// ou se está com o caractere tipoDeTransacao errado


	public boolean transacaoEhValida(char tipoDeTransacao, Transacao transacao) {
		
		switch(tipoDeTransacao) {
		
			case 'S':
				if( transacao == null || transacao.getClass() != Saque.class )
					return false;
				else return true;
				
			case 'D':
				if( transacao == null || transacao.getClass() != Deposito.class )
					return false;
				else return true;
				
			default:
				return true;
		}
	}
	
// Método para registrar transação bem sucedida no cadastro de Pessoa
	
	public void registraTransacao(char tipoDeTransacao, Transacao transacao) {
		this.transacoesRealizadas.add(transacao);
	}
	
// Método para saque
	public boolean saqueContaCorrente(double valorASacar) throws Exception {
		
		if(valorASacar > this.saldo){
			throw new Exception("Saldo insuficiente.\nSaldo = R$ " + this.saldo );
		
		} else if(valorASacar <= 0){
			throw new Exception("O valor de saque não pode ser zero ou menos.");
		
		} else {
			Saque saque = new Saque(this, valorASacar);
			
			if( transacaoEhValida(saque.getTipoDeTransacao(), saque) == true ) {
				
				this.saldo -= valorASacar;
				registraTransacao(saque.getTipoDeTransacao(), saque);
				
				System.out.println("Saque efetuado com sucesso.\nSaldo = R$ " + this.saldo );
				return true;
			
			} else {
				System.out.println("Erro, transação inválida. Nenhuma operação foi concluída.");
				saque = null;
				return false;
			}
		}
	}

// Método para depósito
	public boolean depositoContaCorrente(double valorADepositar) throws Exception {
		
		if(valorADepositar <= 0){
			throw new Exception("O valor de depósito não pode ser zero ou menos.");
		
		} else {
			Deposito deposito = new Deposito(this, valorADepositar);
			
			if( transacaoEhValida(deposito.getTipoDeTransacao(), deposito) == true ) {
				
				this.saldo += valorADepositar;
				registraTransacao(deposito.getTipoDeTransacao(), deposito);
				
				System.out.println("Depósito efetuado com sucesso.\nSaldo = R$ " + this.saldo );
				return true;
			
			} else {
				System.out.println("Erro, transação inválida. Nenhuma operação foi concluída.");
				deposito = null;
				return false;
			}
		}
	}
	
}