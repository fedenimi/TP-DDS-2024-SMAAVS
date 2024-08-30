package ar.edu.utn.frba.dds.modelo.repositorios.interfaces;

import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;

import java.util.List;
import java.util.Optional;

public interface IRepositorioColaboradores {
    public Optional<Colaborador> buscarPor(String doc, String tipoDoc);
    public Optional<Colaborador> buscar(String id);
    public List<Colaborador> buscarTodos();
    public void eliminar(Colaborador colaborador);
    public void guardar(Colaborador colaborador);
}
