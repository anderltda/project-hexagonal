package org.projeto.caseuser.impl;

import org.projeto.domain.model.Conta;
import org.projeto.domain.service.Transferencia;
import org.projeto.port.repository.ContaRepository;
import org.projeto.caseuser.port.TransferenciaPort;
import org.springframework.transaction.annotation.Transactional;
import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import static org.projeto.domain.model.Erro.*;
import static java.util.Objects.isNull;

/**
 *  Responsável por implementar a porta de operações para caso de uso de transferencia.
 *  Sera gerenciado pelo IoC
 */

@Named
public class TransferenciaPortImp implements TransferenciaPort {

    private ContaRepository repositorio;
    private Transferencia transferencia;

    // Ioc por construtor
    @Inject
    public TransferenciaPortImp(ContaRepository repositorio, Transferencia transferencia) {
        this.repositorio = repositorio;
        this.transferencia = transferencia;
    }

    @Override
    public Conta getConta(Integer numero) {
        return repositorio.get(numero);
    }

    @Override
    @Transactional
    public void transferir(Integer contaDebito, Integer contaCredito, BigDecimal valor) {
        
        // 1. validação de parametros
        if (isNull(contaDebito)) {
            obrigatorio("Conta débito");
        }
        if (isNull(contaCredito)) {
            obrigatorio("Conta crédito");
        }
        if (isNull(valor)) {
            obrigatorio("Valor");
        }

        // 2. validação de contas
        Conta debito = repositorio.get(contaDebito);
        if (isNull(debito)) {
            inexistente("Conta débito");
        }
        Conta credito = repositorio.get(contaCredito);
        if (isNull(credito)) {
            inexistente("Conta crédito");
        }

        // 3.validacao mesma conta
        if (debito.getNumero().equals(credito.getNumero())) {
            mesmaConta();
        }

        // 4. operação
        transferencia.processar(valor, debito, credito);
        repositorio.alterar(debito);
        repositorio.alterar(credito);
    }
}
