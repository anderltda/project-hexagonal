package org.projeto.domain.model;

import org.projeto.domain.exception.NegocioException;

// Responsável por centralizar todas as mensagens de validações.
public class Erro {

    // eros genéricos
    public static void obrigatorio(String nome) {
        throw new NegocioException(nome + " é obrigatório.");
    }

    public static void inexistente(String nome) {
        throw new NegocioException(nome + " é inexistente.");
    }

    // eros especificos
    public static void saldoInsuficiente() {
        throw new NegocioException("Saldo insuficiente.");
    }

    public static void mesmaConta() {
        throw new NegocioException("Conta débito e crédito devem ser diferentes.");
    }
}