package br.com.contmatic.fixture;

import java.util.ArrayList;
import java.util.List;

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
				List<String> bairros = new ArrayList<String>();
				bairros.add("Estância Paraíso");
				bairros.add("Vila Bartira");
				bairros.add("Parque Recanto Mônica");
				bairros.add("Jardim Anita");
				bairros.add("Jardim Gonçalves");
				bairros.add("Jardim Zélia");
				bairros.add("Parque Novo Horizonte");
				add("bairro", bairros);
			}
		});
	}

}
