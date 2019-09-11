package org.projeto.adapter.hsqldb.repository;

import static java.util.Objects.isNull;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.projeto.domain.exception.NegocioException;
import org.projeto.domain.model.Conta;
import org.projeto.port.repository.ContaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

/** 
 * Responsável por implementar a porta de saída (driven) de serviços de banco de dados usando spring jdbc
 * @author anderson
 *
 */
@Named
public class ContaRepositoryImpl implements ContaRepository {

	private static final String ERRO = "Erro inesperado de acesso ao banco. Entre em contato com adminstrador.";
    private JdbcTemplate jdbc;

    @Inject
    public ContaRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbc = jdbcTemplate;
    }

    @Override
    public Conta get(Integer numero) {
        if (isNull(numero)) {
            return null;
        }
        String sql = "select * from conta where numero = ?";
        Object[] pm = new Object[]{numero};
        RowMapper<Conta> orm = (rs, nm) ->
                new Conta(rs.getInt(1), rs.getBigDecimal(2), rs.getString(3));
        try {
            List<Conta> lista = jdbc.query(sql, pm, orm);
            if (lista.isEmpty()) {
                return null;
            }
            return lista.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            throw new NegocioException(ERRO);
        }
    }

    @Transactional
    @Override
    public void alterar(Conta conta) {
        if (isNull(conta)) {
            throw new NegocioException("Conta é obrigatório.");
        }
        String sql = "update conta set saldo=?, correntista=? where numero=?";
        Object[] pm = new Object[]{conta.getSaldo(), conta.getCorrentista(), conta.getNumero()};
        try {
            jdbc.update(sql, pm);
        } catch (Exception e) {
            e.printStackTrace();
            throw new NegocioException(ERRO);
        }
    }

}
