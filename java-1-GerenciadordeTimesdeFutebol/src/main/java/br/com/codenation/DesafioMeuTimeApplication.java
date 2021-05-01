package br.com.codenation;

import br.com.codenation.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.exceptions.TimeNaoEncontradoException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class DesafioMeuTimeApplication implements MeuTimeInterface {
	private ListaDeTimes listaDeTimes = new ListaDeTimes();
	private ListaDeJogadores listaJogadores = new ListaDeJogadores();

	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		Time time = new Time(id,nome,dataCriacao,corUniformePrincipal,corUniformeSecundario);
		listaDeTimes.addTime(time);
	}

	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario)
		throws TimeNaoEncontradoException {
		if(!listaDeTimes.validarIdTime(idTime)) {
			listaJogadores.addJogador(new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario));
		} else throw new TimeNaoEncontradoException();
	}

	public void definirCapitao(Long idJogador) {
		listaJogadores.escolherCapitao(idJogador);
	}

	public Long buscarCapitaoDoTime(Long idTime) throws RuntimeException{
		if (!listaDeTimes.validarIdTime(idTime)) {
			List<Jogador> jogadoresDoTime = listaJogadores.getJogadores().stream()
					.filter(jogador -> jogador.getIdTime().equals(idTime))
					.collect(Collectors.toList());
			List<Jogador> capitao = jogadoresDoTime.stream()
					.filter(Jogador::getCapitao)
					.collect(Collectors.toList());
			if(capitao.size() > 0) {
				return capitao.get(0).getId();
			} else throw new CapitaoNaoInformadoException();
		} else throw new TimeNaoEncontradoException();
	}

	public String buscarNomeJogador(Long idJogador) throws JogadorNaoEncontradoException {
		if (!listaJogadores.validarIdJogador(idJogador)) {
			List<Jogador> jogador = listaJogadores.getJogadores().stream()
					.filter(j -> j.getId().equals(idJogador))
					.collect(Collectors.toList());
			return jogador.get(0).getNome();
		} else throw new JogadorNaoEncontradoException();
	}

	public String buscarNomeTime(Long idTime) throws TimeNaoEncontradoException {
		if(!listaDeTimes.validarIdTime(idTime)) {
			List<Time> time = listaDeTimes.getTimes().stream()
					.filter(t -> t.getId().equals(idTime))
					.collect(Collectors.toList());
			return time.get(0).getNome();
		} else throw new TimeNaoEncontradoException();
	}

	public List<Long> buscarJogadoresDoTime(Long idTime) throws TimeNaoEncontradoException {
		if(!listaDeTimes.validarIdTime(idTime)) {
			List<Jogador> jogadoresDoTime = listaJogadores.getJogadores().stream()
					.filter(j -> j.getIdTime().equals(idTime))
					.collect(Collectors.toList());
			return jogadoresDoTime.stream().map(Jogador::getId).collect(Collectors.toList());
		} else throw new TimeNaoEncontradoException();
	}

	// referência do método sorted(): https://mkyong.com/java8/java-8-how-to-sort-list-with-stream-sorted/
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		if(!listaDeTimes.validarIdTime(idTime)) {
			List<Jogador> jogadoresDoTimeOrdenadosPorHab = listaJogadores.getJogadores().stream()
					.filter(j -> j.getIdTime().equals(idTime))
					.sorted(Comparator.comparing(Jogador::getNivelHabilidade).reversed())
					.collect(Collectors.toList());
			return jogadoresDoTimeOrdenadosPorHab.get(0).getId();
		} else throw new TimeNaoEncontradoException();
	}

	public Long buscarJogadorMaisVelho(Long idTime) {
		if(!listaDeTimes.validarIdTime(idTime)) {
			List<Jogador> jogadoresDoTimeOrdenadosPorIdade = listaJogadores.getJogadores().stream()
					.filter(j -> j.getIdTime().equals(idTime))
					.sorted(Comparator.comparing(Jogador::getDataNascimento))
					.collect(Collectors.toList());
			return jogadoresDoTimeOrdenadosPorIdade.get(0).getId();
		} else throw new TimeNaoEncontradoException();
	}

	public List<Long> buscarTimes() {
		return (List<Long>) listaDeTimes.getTimes().stream().map(Time::getId).collect(Collectors.toList());
	}

	public Long buscarJogadorMaiorSalario(Long idTime) {
		if(!listaDeTimes.validarIdTime(idTime)) {
			List<Jogador> jogadoresDoTimeOrdenadosPorSalario = listaJogadores.getJogadores().stream()
					.filter(j -> j.getIdTime().equals(idTime))
					.sorted(Comparator.comparing(Jogador::getSalario).reversed())
					.collect(Collectors.toList());
			return jogadoresDoTimeOrdenadosPorSalario.get(0).getId();
		} else throw new TimeNaoEncontradoException();
	}

	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		if (!listaJogadores.validarIdJogador(idJogador)) {
			List<Jogador> jogador = listaJogadores.getJogadores().stream().filter(j -> j.getId().equals(idJogador)).collect(Collectors.toList());
			return jogador.get(0).getSalario();
		} else throw new JogadorNaoEncontradoException();
	}

	public List<Long> buscarTopJogadores(Integer top) {
		List<Jogador> jogadoresOrdenadosPorHab = listaJogadores.getJogadores().stream()
				.sorted(Comparator.comparingInt(Jogador::getNivelHabilidade).reversed())
				.limit(top.longValue())
				.sorted(Comparator.comparing(Jogador::getId).reversed())
				.collect(Collectors.toList());
		return (List<Long>) jogadoresOrdenadosPorHab.stream().map(Jogador::getId).collect(Collectors.toList());
	}
}
