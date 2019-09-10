package org.projeto.domain.service;

import javax.inject.Named;
import org.projeto.domain.model.Conta;
import java.math.BigDecimal;

import static org.projeto.domain.model.Erro.obrigatorio;
import static java.util.Objects.isNull;

// Responsável por representar a entidade transferência e suas regras.
// Sera gerenciado pelo IoC
@Named
public class Transferencia {

    public void processar(BigDecimal valor, Conta debito, Conta credito) {
        if (isNull(valor)) {
            obrigatorio("Valor da transferência");
        }
        if (isNull(debito)) {
            obrigatorio("Conta débito");
        }
        if (isNull(credito)) {
            obrigatorio("Conta crédito");
        }
        debito.debitar(valor);
        credito.creditar(valor);
    }
}
