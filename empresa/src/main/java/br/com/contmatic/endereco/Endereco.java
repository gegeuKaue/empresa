package br.com.contmatic.endereco;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


// TODO: Auto-generated Javadoc
/**
 * The Class Endereco.
 */
public class Endereco {

	/** The nome. */
	@NotBlank(message = "O nome não pode ser vazio.")
	private String nome;

	/** The bairro. */
	@NotBlank(message = "O nome do bairro não pode ser vázio.")
	private String bairro;

	/** The cidade. */
	@NotBlank(message = "O nome da cidade não deve ser vázio.")
	private String cidade;

	/** The cep. */
	@NotBlank(message = "O CEP não pode está vázio.")
	@Pattern(regexp = "\\d+", message = "Digite um cep valido")
	private String cep;

	/** The numero. */
	@Min(value = -1, message = "O número não deve ser negativo.")
	private int numero;

	/** The estado. */
	@NotNull(message =  "O estado não pode ser nulo.")
	private Estado estado;

	/**
	 * Instantiates a new endereco.
	 *
	 * @param nome   the nome
	 * @param bairro the bairro
	 * @param cidade the cidade
	 * @param cep    the cep
	 * @param numero the numero
	 * @param estado the estado
	 */
	public Endereco(@NotEmpty(message = "O nome não pode ser vazio.") String nome,
			@NotEmpty(message = "O nome do bairro não pode ser vázio.") String bairro,
			@NotEmpty(message = "O nome da cidade não deve ser vázio.") String cidade,
			@NotEmpty(message = "O CEP não pode está vázio.") String cep,
			@Min(value = -1, message = "O número não deve ser negativo.") int numero, Estado estado) {

		this.nome = nome;
		this.bairro = bairro;
		this.cidade = cidade;
		this.cep = cep;
		this.numero = numero;
		this.estado = estado;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * Gets the estado.
	 *
	 * @return the estado
	 */
	public Estado getEstado() {
		return estado;
	}

	/**
	 * Sets the estado.
	 *
	 * @param estado the new estado
	 */
	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	/**
	 * Gets the nome.
	 *
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Sets the nome.
	 *
	 * @param nome the new nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Gets the bairro.
	 *
	 * @return the bairro
	 */
	public String getBairro() {
		return bairro;
	}

	/**
	 * Sets the bairro.
	 *
	 * @param bairro the new bairro
	 */
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	/**
	 * Gets the cidade.
	 *
	 * @return the cidade
	 */
	public String getCidade() {
		return cidade;
	}

	/**
	 * Sets the cidade.
	 *
	 * @param cidade the new cidade
	 */
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	/**
	 * Gets the cep.
	 *
	 * @return the cep
	 */
	public String getCep() {
		return cep;
	}

	/**
	 * Sets the cep.
	 *
	 * @param cep the new cep
	 */
	public void setCep(String cep) {
		this.cep = cep;
	}

	/**
	 * Gets the numero.
	 *
	 * @return the numero
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * Sets the numero.
	 *
	 * @param numero the new numero
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}

}
