package br.com.contmatic.fixture;

import br.com.contmatic.endereco.Cidade;
import br.com.contmatic.endereco.Estado;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

public class FixtureCidade {
	public static void fixture() {
		Fixture.of(Cidade.class).addTemplate("cidade", new Rule() {
			{
				add("nome", random("Rua Sardônica", "Rua Farrapos", "Rua Duque de Caxias", "Rua Planalto",
						"Rua Jaguariun", "Rua Pacaembu"));
				add("estado", Estado.SP);
				add("bairro", random("Estância Paraíso", "Vila Bartira", "Parque Recanto Mônica", "Jardim Anita",
						"Jardim Gonçalves", "Jardim Zélia", "Parque Novo Horizonte"));
			}
		});
	}

}
