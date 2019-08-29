package br.com.contmatic.empresa;

import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import br.com.caelum.stella.bean.validation.CNPJ;
import br.com.contmatic.endereco.Endereco;

public class Empresa {
	@Size(min = 1, max = 100, message = "O nome da empresa deve ser entre 1 e 100")
	@NotBlank(message = "O nome da empresa não pode ser nulo.")
	private String nome;

	/** The email. */
	@NotBlank(message = "O email da empresa não pode ser nulo")
	@Email(message = "O email da empresa está inválido")
	private String email;

	/** The cnpj. */
	@NotBlank(message = "O cnpj não pode ser nulo")
	@CNPJ(message = "O CNPJ da empresa está invalido")
	private String cnpj;

	/** The endereco. */
	@NotNull(message = "O endereço da empresa está vazio")
	@Valid 
	private Set<Endereco> enderecos;

	/** The lista funcionario. */
	@NotNull(message = "Os funcionários da empresa está nulo.")
	@Valid
	private List<Funcionario> funcionarios;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Set<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(Set<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.cnpj).toHashCode();
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
		Empresa empresa = (Empresa) obj;
		return new EqualsBuilder().append(this.cnpj, empresa.getCnpj()).isEquals();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, JSON_STYLE);
	}
}
