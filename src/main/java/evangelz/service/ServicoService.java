package evangelz.service;

import evangelz.model.Servico;
import evangelz.repository.ServicoRepository;

import java.util.List;

public class ServicoService {
    private final ServicoRepository repository = new ServicoRepository();

    public void cadastrar(String nome, String descricao, double valorBase) {
        if (nome == null || nome.trim().isEmpty())
            throw new IllegalArgumentException("Nome do serviço é obrigatório.");
        if (valorBase < 0)
            throw new IllegalArgumentException("Valor base não pode ser negativo.");
        repository.cadastrar(new Servico(nome.trim(), descricao != null ? descricao.trim() : null, valorBase));
    }

    public List<Servico> listar() { return repository.listar(); }

    public Servico buscarPorId(int id) {
        Servico s = repository.buscarPorId(id);
        if (s == null) throw new IllegalArgumentException("Serviço não encontrado.");
        return s;
    }

    public void atualizar(int id, String nome, String descricao, double valorBase) {
        if (nome == null || nome.trim().isEmpty())
            throw new IllegalArgumentException("Nome do serviço é obrigatório.");
        if (valorBase < 0) throw new IllegalArgumentException("Valor base não pode ser negativo.");
        Servico s = buscarPorId(id);
        s.setNome(nome.trim());
        s.setDescricao(descricao != null ? descricao.trim() : null);
        s.setValorBase(valorBase);
        repository.atualizar(s);
    }

    public void deletar(int id) {
        if (repository.buscarPorId(id) == null)
            throw new IllegalArgumentException("Serviço não encontrado.");
        if (repository.temVinculoComOS(id))
            throw new IllegalStateException("Serviço vinculado a uma OS e não pode ser excluído.");
        repository.deletar(id);
    }
}
