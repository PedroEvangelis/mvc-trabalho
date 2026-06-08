package evangelz.service;

import evangelz.model.Cliente;
import evangelz.repository.ClienteRepository;

import java.util.List;

public class ClienteService {
    private final ClienteRepository repository = new ClienteRepository();

    public void cadastrar(String nome, String telefone) {
        if (nome == null || nome.trim().isEmpty())
            throw new IllegalArgumentException("Nome do cliente é obrigatório.");
        if (telefone == null || telefone.trim().isEmpty())
            throw new IllegalArgumentException("Telefone do cliente é obrigatório.");
        repository.cadastrar(new Cliente(nome.trim(), telefone.trim()));
    }

    public List<Cliente> listar() { return repository.listar(); }

    public Cliente buscarPorId(int id) {
        Cliente c = repository.buscarPorId(id);
        if (c == null) throw new IllegalArgumentException("Cliente não encontrado.");
        return c;
    }

    public void atualizar(int id, String nome, String telefone) {
        if (nome == null || nome.trim().isEmpty())
            throw new IllegalArgumentException("Nome do cliente é obrigatório.");
        if (telefone == null || telefone.trim().isEmpty())
            throw new IllegalArgumentException("Telefone do cliente é obrigatório.");
        Cliente c = buscarPorId(id);
        c.setNome(nome.trim());
        c.setTelefone(telefone.trim());
        repository.atualizar(c);
    }

    public void deletar(int id) {
        if (repository.buscarPorId(id) == null)
            throw new IllegalArgumentException("Cliente não encontrado.");
        repository.deletar(id);
    }
}
