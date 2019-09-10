package org.projeto.adapter;

import static java.util.Objects.isNull;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Named;
import org.projeto.domain.exception.NegocioException;
import org.projeto.domain.model.Conta;
import org.projeto.port.repository.ContaRepository;

// Responsável por implementar a porta de saída (driven) de serviços de banco de dados falso.
// Sera gerenciado pelo IoC
@Named
public class ContaAdapterFakeImp implements ContaRepository {

    private Map<Integer, Conta> banco = new HashMap<>();

    public ContaAdapterFakeImp() {
        banco.put(10, new Conta(10, new BigDecimal(100), "Fernando Fake"));
        banco.put(20, new Conta(20, new BigDecimal(100), "Rebeca Fake"));
    }

    public Conta get(Integer numero) {
        System.out.println("Fake banco de dados -> Conta get(numero)");
        return banco.get(numero);
    }

    public void alterar(Conta conta) {
        System.out.println("Fake banco de dados -> alterar(conta)");
        Conta ct = banco.get(conta.getNumero());
        if (!isNull(ct)) {
            banco.put(conta.getNumero(), conta);
        } else {
            throw new NegocioException("Conta inexistente: " + conta.getNumero());
        }
    }
}
