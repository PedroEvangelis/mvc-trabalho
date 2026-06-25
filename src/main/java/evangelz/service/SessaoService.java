package evangelz.service;

import evangelz.model.Veterinario;
import evangelz.repository.VeterinarioRepository;

import java.util.List;


public class SessaoService {
    private final VeterinarioRepository repository = new VeterinarioRepository();
    private static Veterinario veterinarioLogado;

    public List<Veterinario> listarVeterinarios() {
        return repository.listar();
    }

    public Veterinario selecionarVeterinario(int id) {
        Veterinario v = repository.buscarPorId(id);
        if (v == null) throw new IllegalArgumentException("Veterinário não encontrado.");
        veterinarioLogado = v;
        return v;
    }

    public Veterinario cadastrarVeterinario(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do veterinário é obrigatório.");
        }
        Veterinario v = new Veterinario(nome.trim());
        repository.cadastrar(v);
        veterinarioLogado = v;
        return v;
    }

    public Veterinario getVeterinarioLogado() {
        return veterinarioLogado;
    }

    public boolean temSessaoAtiva() {
        return veterinarioLogado != null;
    }
}
