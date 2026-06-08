package evangelz.service;

import evangelz.model.Veiculo;
import evangelz.repository.ClienteRepository;
import evangelz.repository.VeiculoRepository;

import java.util.List;

public class VeiculoService {
    private final VeiculoRepository veiculoRepository = new VeiculoRepository();
    private final ClienteRepository clienteRepository = new ClienteRepository();

    public void cadastrar(String placa, String modelo, int ano, int idCliente) {
        if (placa == null || placa.trim().isEmpty())
            throw new IllegalArgumentException("Placa do veículo é obrigatória.");
        if (modelo == null || modelo.trim().isEmpty())
            throw new IllegalArgumentException("Modelo do veículo é obrigatório.");
        if (ano <= 0)
            throw new IllegalArgumentException("Ano do veículo inválido.");
        if (clienteRepository.buscarPorId(idCliente) == null)
            throw new IllegalArgumentException("Cliente não encontrado.");
        veiculoRepository.cadastrar(new Veiculo(placa.trim(), modelo.trim(), ano, idCliente));
    }

    public List<Veiculo> listarPorCliente(int idCliente) {
        if (clienteRepository.buscarPorId(idCliente) == null)
            throw new IllegalArgumentException("Cliente não encontrado.");
        return veiculoRepository.listarPorCliente(idCliente);
    }

    public Veiculo buscarPorId(int id) {
        Veiculo v = veiculoRepository.buscarPorId(id);
        if (v == null) throw new IllegalArgumentException("Veículo não encontrado.");
        return v;
    }

    public void atualizar(int id, String placa, String modelo, int ano) {
        if (placa == null || placa.trim().isEmpty())
            throw new IllegalArgumentException("Placa do veículo é obrigatória.");
        if (modelo == null || modelo.trim().isEmpty())
            throw new IllegalArgumentException("Modelo do veículo é obrigatório.");
        if (ano <= 0) throw new IllegalArgumentException("Ano do veículo inválido.");
        Veiculo v = buscarPorId(id);
        v.setPlaca(placa.trim());
        v.setModelo(modelo.trim());
        v.setAno(ano);
        veiculoRepository.atualizar(v);
    }

    public void deletar(int id) {
        if (veiculoRepository.buscarPorId(id) == null)
            throw new IllegalArgumentException("Veículo não encontrado.");
        veiculoRepository.deletar(id);
    }
}
