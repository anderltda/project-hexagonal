package org.projeto.adapter.dynamodb.entity;

import java.io.Serializable;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "CONTA")
public class ContaEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer numero;
    private Double saldo;
    private String correntista;
    
	public ContaEntity(Integer numero, Double saldo, String correntista) {
		super();
		this.numero = numero;
		this.saldo = saldo;
		this.correntista = correntista;
	}
	
	/**
	 * @return the numero
	 */
    @DynamoDBHashKey(attributeName = "numero")
	public Integer getNumero() {
		return numero;
	}
	/**
	 * @param numero the numero to set
	 */
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	/**
	 * @return the saldo
	 */
    @DynamoDBAttribute(attributeName = "saldo")
	public Double getSaldo() {
		return saldo;
	}
	/**
	 * @param saldo the saldo to set
	 */
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	/**
	 * @return the correntista
	 */
    @DynamoDBAttribute(attributeName = "correntista")
	public String getCorrentista() {
		return correntista;
	}
	/**
	 * @param correntista the correntista to set
	 */
	public void setCorrentista(String correntista) {
		this.correntista = correntista;
	}
}
