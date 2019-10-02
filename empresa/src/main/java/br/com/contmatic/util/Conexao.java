package br.com.contmatic.util;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import br.com.contmatic.empresa.Empresa;

public class Conexao {
	private static final String HOST = "localhost";
	private static final int PORT = 27017;
	private static final String DB_NAME = "helloWorld";
	private static MongoClient mongoClient;
	private static MongoDatabase database;

	public Conexao() {
		mongoClient = new MongoClient(HOST, PORT);
		database = mongoClient.getDatabase(DB_NAME);
	}

	public static void main(String[] args) {

	}

	public void salvar(Empresa empresa) {
		Document document = Document.parse(empresa.toString());
		document.remove("cnpj");
		document.append("_id", empresa.getCnpj());
		database.getCollection("empresa").insertOne(document);

	}
}
