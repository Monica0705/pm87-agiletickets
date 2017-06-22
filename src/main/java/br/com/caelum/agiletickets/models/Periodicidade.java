package br.com.caelum.agiletickets.models;

import br.com.caelum.agiletickets.domain.precos.CriadorDeSessoes;
import br.com.caelum.agiletickets.domain.precos.CriadorDeSessoesDiaria;
import br.com.caelum.agiletickets.domain.precos.CriadorDeSessoesSemanal;


public enum Periodicidade {
	
	DIARIA {
		@Override
		public CriadorDeSessoes getCriadorDeSessoes() {
			return new CriadorDeSessoesDiaria();
		}
	}, 
	
	SEMANAL {
		@Override
		public CriadorDeSessoes getCriadorDeSessoes() {
			return new CriadorDeSessoesSemanal();
		}
	};

	public abstract CriadorDeSessoes getCriadorDeSessoes();
	
}
