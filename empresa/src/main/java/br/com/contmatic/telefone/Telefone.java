package br.com.contmatic.telefone;

import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;

public class Telefone {
	@NotNull(message = "O ddd do telefone não pode ser vazio.")
	private TelefoneDDD ddd;
	
	@NotBlank(message = "O número do telefone não pode ser vazio.")
	@Pattern(regexp = "9\\d+", message = "O número do telefone está invalido.")
	@Size(min = 9, max = 9, message = "O número do telefone tem que ter 9 numeros")
	private String numero;

	public TelefoneDDD getDdd() {
		return ddd;
	}

	public void setDdd(TelefoneDDD ddd) {
		this.ddd = ddd;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, JSON_STYLE);

	}
}
