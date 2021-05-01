package br.com.codenation;

import br.com.codenation.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.exceptions.JogadorNaoEncontradoException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaDeJogadores {
    private ArrayList<Jogador> jogadores = new ArrayList();

    public ArrayList<Jogador> getJogadores() {
        return jogadores;
    }

    public boolean validarIdJogador(Long id) {
        for (Jogador jogador : jogadores) {
            if(jogador.getId().equals(id)) return false;
        }
        return true;
    }

    public void addJogador(Jogador jogador) throws IdentificadorUtilizadoException {
        if (validarIdJogador(jogador.getId())) {
            jogadores.add(jogador);
        } else throw new IdentificadorUtilizadoException("Id do jogador já existe");
    }
    public void escolherCapitao(Long jogadorId) throws JogadorNaoEncontradoException {
        if (!validarIdJogador(jogadorId)){
            List<Jogador> novoCapitao = jogadores.stream()
                    .filter(jogador -> jogador.getId().equals(jogadorId))
                    .collect(Collectors.toList());
            List<Jogador> jogadoresDoTime = jogadores.stream()
                    .filter(jogador -> jogador.getIdTime().equals(novoCapitao.get(0).getIdTime()))
                    .collect(Collectors.toList());
            for (Jogador jogador : jogadoresDoTime) {
                if (jogador.getCapitao()) jogador.setCapitao();
            }
            novoCapitao.get(0).setCapitao();
        } else throw new JogadorNaoEncontradoException();
    }
}
