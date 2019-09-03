package br.com.contmatic.endereco;

import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author geovane.santos
 */

/**
 * The Class Endereco.
 */
public class Endereco {
	@Valid
	@NotNull(message = "Os valores da cidade não pode ser nulo")
	private Cidade cidade;

	@Min(value = 1, message = "O número não deve ser negativo.")
	private int numero;

	@NotBlank(message = "O CEP não pode está vázio.")
	@Pattern(regexp = "\\d+", message = "Digite um cep valido")
	@Size(min = 8, max = 8, message = "O CEP deve ter 8 digitos.")
	private String cep;

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.cep).append(this.numero).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		Endereco endereco = (Endereco) obj;
		return new EqualsBuilder().append(this.cep, endereco.getCep()).append(this.numero, endereco.getNumero())
				.isEquals();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, JSON_STYLE);
	}
}
