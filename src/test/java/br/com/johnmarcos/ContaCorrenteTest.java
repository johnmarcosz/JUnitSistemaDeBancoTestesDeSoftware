package br.com.johnmarcos;

import static org.junit.Assume.assumeFalse;
import static org.junit.Assume.assumeTrue;
import static org.junit.jupiter.api.Assertions.*;

//import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

class ContaCorrenteTest {
	
	@BeforeAll
	static void inicioConexaoBDTestes() {
    	System.out.println("@BeforeAll | Iniciando os testes, aguarde... \nIniciando a conexão com o banco de dados... \nTestes iniciados.\n");
    }
	
	@AfterAll
	static void encerraConexaoBDTestes() {
    	System.out.println("@AfterAll | Testes concluídos. \nEncerrando a conexão com o banco de dados, aguarde...  \nTestes encerrados.");
    }
	
	@BeforeEach
    void testeIniciado(TestInfo testInfo) {
    	System.out.println("@BeforeEach | Teste iniciado: '" + testInfo.getDisplayName() + "'.");
    }
	
	@AfterEach
    public void testeFinalizado(TestInfo testInfo) {
    	System.out.println("@AfterEach | Teste finalizado: '" + testInfo.getDisplayName() + "'.\n");
    }

	@DisplayName("Conta Corrente criada sem um titular")
	@Test
	public void contaCorrenteSemTitular() {
		ContaCorrente contaCorrente = new ContaCorrente(null);
		assertNull(contaCorrente.getTitularDaConta());
	}

	@DisplayName("Presuma erro quando uma transacao é nula")
	@Test
	public void validaTransacaoSaqueNulo() {
		
		ContaCorrente contaCorrente = new ContaCorrente(null);
		Transacao saque = new Saque(null, 0);
		saque = null;
		
		assumeTrue( contaCorrente.transacaoEhValida('S', saque) == false ); //presuma que essa condição é verdadeira		
	}
	
	@DisplayName("Presuma erro quando uma transacao está com 'tipoDeTransacao' errada")
	@Test
	public void tipoDeTransacaoErrada() {
		
		ContaCorrente contaCorrente = new ContaCorrente(null);
		Transacao saque = new Saque(contaCorrente, 20.0);
		
		assumeFalse( contaCorrente.transacaoEhValida('D', saque) == true ); //presuma que essa condição é verdadeira		
	}
	
	@DisplayName("Tentativa de saque com valor negativo")
	@Disabled
	@Test
	public void saqueComValorNegativo() {
		ContaCorrente contaCorrente = new ContaCorrente(null);
		assertThrows(Exception.class, () -> {
			contaCorrente.saqueContaCorrente(-20.0);
		});
	}
	
//	@DisplayName("Consultar transações")
//	@Test
//	@Timeout(value=1, unit = TimeUnit.MILLISECONDS)
//	public void consultaTransacoes() {
//		ContaCorrente contaCorrente = new ContaCorrente(null);
//		contaCorrente.consultarTransacoes();
//	}
}
