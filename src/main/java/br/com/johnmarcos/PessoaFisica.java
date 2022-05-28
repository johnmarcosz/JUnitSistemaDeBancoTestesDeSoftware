 package br.com.johnmarcos;

public class PessoaFisica {
	
	private int idPessoa;
	private static int contadorIdPessoa = 0;
	private String nome;
	private String cpf;
	private ContaCorrente contaCorrente = null;
	
	
// Contrutor
	
	public PessoaFisica(String nome, String cpf) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.idPessoa = contadorIdPessoa++;
	}
	
	public PessoaFisica() {
	}


// Método para imprimir valores (GET)
	
	public String getNome(){
		return this.nome;
	}
 
	public String getCPF(){
		return this.cpf;
	}
	
	public int getIdPessoa(){
		return this.idPessoa;
	}
	
	public ContaCorrente getContaCorrenteRelacionada() {
		return this.contaCorrente;
	}
	
// Valida nome da pessoa
	public boolean validacaoNome(String nomeDigitado) throws Exception {
		
		if( nomeDigitado.isEmpty() ) {
			throw new Exception("Erro! O nome não pode ser vazio.");
		
		} else if ( nomeDigitado.length() < 3 ) {
			throw new Exception("Erro! O nome precisa ter 3 letras ou mais.");
		
		} else if ( nomeDigitado.length() > 1 ) {
			
			if ( nomeDigitado.matches("^[[ ]|\\p{L}*]+$") == true ) { //verifica se string é só letras
				return true;
			} else 
				throw new Exception("Erro! O nome parece conter carcteres especiais.");
		
		} else return false;
	}
	
	
// Verifica se CPF é valido
	
	public boolean validacaoCPF(String cpf) throws Exception {
		
		cpf = removeCaracteresEspeciais(cpf);
		
		if(verificaQuantDigitosCPF(cpf) == false) {
			throw new Exception("Erro! O CPF deve ter 11 dígitos e não conter nenhuma letra.");
			
		} else if(verificaNumerosIguaisCPF(cpf) == false) {
			throw new Exception("Erro! O CPF não pode conter todos os dígitos iguais.");
			
		} else if(validarCalculoCPF(cpf) == false) {
			throw new Exception("Erro na validação do CPF.");
			
		} else {
			return true;
		}
	}
	
	
// Remove caracteres especiais
	
	public String removeCaracteresEspeciais(String stringRecebida) {
		stringRecebida = stringRecebida.replaceAll("[^0-9]", "");
		return stringRecebida;
	}
	

// Verifica se string é menor que 11 dígitos
	
	public boolean verificaQuantDigitosCPF(String cpf) {
		
		if(cpf.length() != 11) {
			return false;
		} else {
			return true;
		}
	}
	
	
// Verifica se todos os números são iguais
	public boolean verificaNumerosIguaisCPF(String cpf) {
		
		if(	   cpf == "00000000000"	|| cpf == "11111111111" || cpf == "22222222222" 
			|| cpf == "33333333333" || cpf == "44444444444" || cpf == "55555555555"
			|| cpf == "66666666666"	|| cpf == "77777777777" || cpf == "88888888888"
			|| cpf == "99999999999") {
			
			return false;
		} else {
			return true;
		}
	}

	
// Método para ver se o CPF é válido
	
	public boolean validarCalculoCPF(String cpf){

		int moduloDV;
		int somaDos9;
		int primeiroDV;
		int[] vetorCPF = new int[11];
		
		
		// colocando o CPF em vetor para os cálculos necessários
		
		for (int i = 0; i < vetorCPF.length; i++) {
			vetorCPF[i] = Integer.parseInt( cpf.substring(i, i+1) );		
		}
		
		// Cálculo do primeiro Dígito Verificador
		
		somaDos9 = (vetorCPF[0] * 10) + (vetorCPF[1] * 9) + (vetorCPF[2] * 8) + (vetorCPF[3] * 7) + (vetorCPF[4] * 6) + (vetorCPF[5] * 5) + (vetorCPF[6] * 4) + (vetorCPF[7] * 3) + (vetorCPF[8] * 2);
		moduloDV = somaDos9 % 11;
		primeiroDV = 11 - moduloDV;
				
		if(primeiroDV == 10 || primeiroDV == 11){
			primeiroDV = 0;
		}
		
		if(vetorCPF[9] != primeiroDV){
			System.out.println("Cálculo do primeiro dígito verificador é diferente ao do CPF.");
			return false;
		}
		
		// Cálculo do segundo Dígito Verificador
		

		int segundoDV;
		
		somaDos9 = (vetorCPF[0] * 11) + (vetorCPF[1] * 10) + (vetorCPF[2] * 9) + (vetorCPF[3] * 8) + (vetorCPF[4] * 7) + (vetorCPF[5] * 6) + (vetorCPF[6] * 5) + (vetorCPF[7] * 4) + (vetorCPF[8] * 3) + (vetorCPF[9] * 2);
		moduloDV = somaDos9 % 11;
		segundoDV = 11 - moduloDV;
		
		if(segundoDV == 10 || segundoDV == 11){
			segundoDV = 0;
		}
		
		if(vetorCPF[10] != segundoDV){
			System.out.println("Cálculo do segundo Dígito Verificador é diferente ao do CPF.");
			return false;
		}
		
		// Se CPF for válido
		return true;
	}
}
