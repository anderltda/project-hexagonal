package org.projeto.port.repository;

import org.projeto.domain.model.Conta;

// Responsável por definir a porta de saída (driven) de serviços de banco de dados.
public interface ContaRepository {

    Conta get(Integer numero);

    void alterar(Conta conta);
}
