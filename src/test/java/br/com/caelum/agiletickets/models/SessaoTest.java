package br.com.caelum.agiletickets.models;
import java.util.List;


import org.joda.time.*;

import org.junit.Assert;
import org.junit.Test;

public class SessaoTest {

	@Test
	public void deveVender1ingressoSeHa2vagas() throws Exception {
		Sessao sessao = new Sessao();
        sessao.setTotalIngressos(2);

        Assert.assertTrue(sessao.podeReservar(1));
	}
	
	@Test
	public void deveVender2ingressoSeHa2vagas() throws Exception {
		Sessao sessao = new Sessao();
        sessao.setTotalIngressos(2);

        Assert.assertTrue(sessao.podeReservar(2));
	}
	
	
	@Test
	public void naoDeveVender3ingressoSeHa2vagas() throws Exception {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(2);

		Assert.assertFalse(sessao.podeReservar(3));
	}

	@Test
	public void reservarIngressosDeveDiminuirONumeroDeIngressosDisponiveis() throws Exception {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(5);

		sessao.reserva(3);
		Assert.assertEquals(2, sessao.getIngressosDisponiveis().intValue());
	}
	

	@Test(expected = IllegalArgumentException.class)
	public void naoDeveriaCriarSessoesDiariasQuandoDataInicioMaiorQueDataFim() {
		//DADAS ESTAS ENTRADAS:
		LocalDate hoje = new LocalDate();
	  	LocalDate amanha = hoje.plusDays(1);
	  	LocalTime agora = new LocalTime();
	  	Periodicidade diaria = Periodicidade.DIARIA;
	  
	  	//QUANDO EU DISPARAR O PROCESSAMENTO:
	  	Espetaculo show = new Espetaculo();
	  	List<Sessao> sessoes = show.criaSessoes(amanha, hoje, agora, diaria);
	  
	  	//ESTAS SAO AS SAIDAS ESPERADAS:
	  	//Nao tem asserts pq deveria jogar exception...
	}

	@Test(expected = IllegalArgumentException.class)
	public void naoDeveriaCriarSessoesSemanaisQuandoDataInicioMaiorQueDataFim() {
		//DADAS ESTAS ENTRADAS:
		LocalDate hoje = new LocalDate();
	  	LocalDate amanha = hoje.plusDays(1);
	  	LocalTime agora = new LocalTime();
	  	Periodicidade semanal = Periodicidade.SEMANAL;
	  
	  	//QUANDO EU DISPARAR O PROCESSAMENTO:
	  	Espetaculo show = new Espetaculo();
	  	List<Sessao> sessoes = show.criaSessoes(amanha, hoje, agora, semanal);
	  
	  	//ESTAS SAO AS SAIDAS ESPERADAS:
	  	//Nao tem asserts pq deveria jogar exception...
	}

	@Test
	public void deveriaCriarApenasUmaSessaoParaPeriodicidadeDiariaComDataInicioIgualDataFim() {
		//DADAS ESTAS ENTRADAS:
		LocalDate hoje = new LocalDate();
	  	LocalTime agora = new LocalTime();
	  	Periodicidade diaria = Periodicidade.DIARIA;
	  
	  	//QUANDO EU DISPARAR O PROCESSAMENTO:
	  	Espetaculo show = new Espetaculo();
	  	List<Sessao> sessoes = show.criaSessoes(hoje, hoje, agora, diaria);
	  
	  	//ESTAS SAO AS SAIDAS ESPERADAS:
		Assert.assertEquals(1, sessoes.size());
	  
	  	//Nao basta apenas verificar o size da lista, precisa garantir que criou as sessoes corretamente:
	  	Sessao unica = sessoes.get(0);
	  	Assert.assertEquals(show, unica.getEspetaculo());
	    Assert.assertEquals(hoje.toDateTime(agora), unica.getInicio());
	}

	@Test
	public void deveriaCriarApenasUmaSessaoParaPeriodicidadeSemanalComDataInicioIgualDataFim() {
		//DADAS ESTAS ENTRADAS:
		LocalDate hoje = new LocalDate();
	  	LocalTime agora = new LocalTime();
	  	Periodicidade semanal = Periodicidade.SEMANAL;
	  
	  	//QUANDO EU DISPARAR O PROCESSAMENTO:
	  	Espetaculo show = new Espetaculo();
	  	List<Sessao> sessoes = show.criaSessoes(hoje, hoje, agora, semanal);
	  
	  	//ESTAS SAO AS SAIDAS ESPERADAS:
		Assert.assertEquals(1, sessoes.size());
	  
	  	//Nao basta apenas verificar o size da lista, precisa garantir que criou as sessoes corretamente:
	  	Sessao unica = sessoes.get(0);
	  	Assert.assertEquals(show, unica.getEspetaculo());
	    Assert.assertEquals(hoje.toDateTime(agora), unica.getInicio());
	}

	@Test
	public void deveriaCriarCincoSessoesParaPeriodicidadeDiariaComIntervaloDeCincoDias() {
		//DADAS ESTAS ENTRADAS:
		LocalDate hoje = new LocalDate();
	  	LocalDate daquiQuatroDias = hoje.plusDays(4);
	  	LocalTime agora = new LocalTime();
	  	Periodicidade diaria = Periodicidade.DIARIA;
	  
	  	//QUANDO EU DISPARAR O PROCESSAMENTO:
	  	Espetaculo show = new Espetaculo();
	  	List<Sessao> sessoes = show.criaSessoes(hoje, daquiQuatroDias, agora, diaria);
	  
	  	//ESTAS SAO AS SAIDAS ESPERADAS:
		Assert.assertEquals(5, sessoes.size());
	  
	  	//Nao basta apenas verificar o size da lista, precisa garantir que criou as sessoes corretamente:
		for(int i = 0; i < sessoes.size(); i++) {
			Assert.assertEquals(show, sessoes.get(i).getEspetaculo());
			Assert.assertEquals(hoje.plusDays(i).toDateTime(agora), sessoes.get(i).getInicio());
	    }
	}

	@Test
	public void deveriaCriarCincoSessoesParaPeriodicidadeSemanalComIntervaloDeCincoSemanas() {
		//DADAS ESTAS ENTRADAS:
		LocalDate hoje = new LocalDate();
	  	LocalDate daquiQuatroSemanas = hoje.plusWeeks(4);
	  	LocalTime agora = new LocalTime();
	  	Periodicidade semanal = Periodicidade.SEMANAL;
	  
	  	//QUANDO EU DISPARAR O PROCESSAMENTO:
	  	Espetaculo show = new Espetaculo();
	  	List<Sessao> sessoes = show.criaSessoes(hoje, daquiQuatroSemanas, agora, semanal);
	  
	  	//ESTAS SAO AS SAIDAS ESPERADAS:
		Assert.assertEquals(5, sessoes.size());
	  
	  	//Nao basta apenas verificar o size da lista, precisa garantir que criou as sessoes corretamente:
		for(int i = 0; i < sessoes.size(); i++) {
			Assert.assertEquals(show, sessoes.get(i).getEspetaculo());
			Assert.assertEquals(hoje.plusWeeks(i).toDateTime(agora), sessoes.get(i).getInicio());
	    }
	}

}
