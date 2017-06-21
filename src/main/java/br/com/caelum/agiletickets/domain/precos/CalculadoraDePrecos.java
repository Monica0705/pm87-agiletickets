package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
			CalculadorDePreco calculador = sessao.getEspetaculo().getTipo().getCalculadorDePreco();
			BigDecimal precoUnitario = calculador.calcula(sessao);
			
			return precoUnitario.multiply(BigDecimal.valueOf(quantidade));
		}

	}