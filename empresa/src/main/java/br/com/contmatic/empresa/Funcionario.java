package br.com.contmatic.empresa;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import br.com.caelum.stella.bean.validation.CPF;
import br.com.contmatic.telefone.Telefone;

/**
 * @author geovane.santos
 *
 */
/**
 * @author geovane.santos
 *
 */
/**
 * @author geovane.santos
 *
 */
/**
 * @author geovane.santos
 *
 */
public class Funcionario {
	/** The nome. */
	@NotEmpty(message = "O nome do funcionario não deve ser vázio.")
	private String nome;

	/** The cargo. */
	@NotBlank(message = "O cargo do funcionario não deve ser vázio.")
	private String cargo;

	/** The idade. */
	@Min(value = 0, message = "A idade do funcionario não pode ser negativa.")
	private int idade;

	@NotNull(message = "O horario do funcionario não pode ser nulo.")
	private LocalTime entrada;

	@NotNull(message = "O horario do funcionario não pode ser nulo.")
	private LocalTime saida;

	@Past(message = "A data de contratação do funcionario não deve ser maior que a atual")
	private LocalDate dataContratacao;

	/** The cpf. */
	@CPF(message = "O CPF do funcionario está inválido")
	private String cpf;

	/** The telefone. */
	@NotNull(message = "O telefone do funcionario não pode ser nulo")
	private Set<Telefone> telefones;

	/** The email. */
	@Email(message = "O email do funcionario está invalido.")
	private String email;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public LocalTime getEntrada() {
		return entrada;
	}

	public void setEntrada(LocalTime entrada) {
		this.entrada = entrada;
	}

	public LocalTime getSaida() {
		return saida;
	}

	public void setSaida(LocalTime saida) {
		this.saida = saida;
	}

	public LocalDate getDataContratacao() {
		return dataContratacao;
	}

	public void setDataContratacao(LocalDate dataContratacao) {
		this.dataContratacao = dataContratacao;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Set<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<Telefone> telefones) {
		this.telefones = telefones;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.cpf).hashCode();
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
		Funcionario funcionario = (Funcionario) obj;
		return new EqualsBuilder().append(this.cpf, funcionario.getCpf()).isEquals();
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
