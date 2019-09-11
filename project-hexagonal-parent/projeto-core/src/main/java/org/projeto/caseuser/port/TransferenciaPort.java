package org.projeto.caseuser.port;

import java.math.BigDecimal;
import org.projeto.domain.exception.NegocioException;
import org.projeto.domain.model.Conta;

/**
 *  Responsável por definir a porta de entrada (driver) de operações para caso de uso de transferência.
 */
public interface TransferenciaPort {
    
    Conta getConta(Integer numero) throws NegocioException;

    void transferir(Integer contaDebito, Integer contaCredito, BigDecimal valor) throws NegocioException;
}
