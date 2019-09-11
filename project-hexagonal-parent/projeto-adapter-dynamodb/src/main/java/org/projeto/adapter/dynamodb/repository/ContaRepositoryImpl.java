package org.projeto.adapter.dynamodb.repository;

import javax.inject.Named;

import org.projeto.domain.model.Conta;
import org.projeto.port.repository.ContaRepository;

/** 
 * Responsável por implementar a porta de saída (driven) de serviços de banco de dados usando spring jdbc
 * @author anderson
 *
 */
@Named
public class ContaRepositoryImpl implements ContaRepository {

	@Override
	public Conta get(Integer numero) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void alterar(Conta conta) {
		// TODO Auto-generated method stub
		
	}

}
