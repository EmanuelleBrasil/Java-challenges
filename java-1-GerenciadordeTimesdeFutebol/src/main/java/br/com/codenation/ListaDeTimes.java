package br.com.codenation;

import br.com.codenation.exceptions.IdentificadorUtilizadoException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaDeTimes {
    private List<Time> times = new ArrayList<>();

    public List<Time> getTimes() {
        return times;
    }

    public void addTime(Time time) throws IdentificadorUtilizadoException{
        if (validarIdTime(time.getId())) {
            this.times.add(time);
        } else throw new IdentificadorUtilizadoException("Id do time já existe");
    }

    public boolean validarIdTime(Long id) {
        for (Time time : times) {
            if(time.getId().equals(id)) return false;
        }
        return true;
    }

    public List<Long> buscaTimes() {
        List<Long> idsTimes = times.stream()
                .map(t -> t.getId())
                .collect(Collectors.toList());
        return idsTimes;
    }
}
