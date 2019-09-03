package br.com.contmatic.regex;

public class Regex {
	public static final String URL = "^(https?|http|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
	public static final String NOME = "(\\w|\\s|ç|[á-ú])+";
	public static final String CEP = "\\d+";
}