package br.com.contmatic.fixture;

import java.util.Random;

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.endereco.Estado;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

public class FixtureEndereco {
	public static void fixture() {
		Fixture.of(Endereco.class).addTemplate("endereco", new Rule() {
			{
				add("nome", random("Rua Mestre Cartola", "Rua Euzébio Matoso", "Rua Pintassilgo"));
				add("bairro", random("Vila Augusta", "Parque São Pedro", "Jardim Lucinda", "Chácara São Miguel"));
				add("cidade", "itaqaqucetuba");
				add("cep", random("08596800", "08577430", "08583690", "08583003"));
				add("numero", new Random().nextInt(700) + 1);
				add("estado", Estado.SP);
			}
		});
	}
	public static void main(String[] args) {
		fixture();
		Endereco endereco = Fixture.from(Endereco.class).gimme("endereco");
		System.out.println(endereco);
	}
}
