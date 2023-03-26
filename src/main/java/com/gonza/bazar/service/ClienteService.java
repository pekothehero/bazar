
package com.gonza.bazar.service;

import com.gonza.bazar.model.Cliente;
import com.gonza.bazar.repository.IClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService implements IClienteService{

    @Autowired
    IClienteRepository clienteRepo;
    
    @Override
    public void newCliente(Cliente client) {
        clienteRepo.save(client);
    }

    @Override
    public List<Cliente> getClientes() {
        return clienteRepo.findAll();
    }

    @Override
    public Cliente findCliente(Long id_cliente) {
        return clienteRepo.findById(id_cliente).orElse(null);
    }

    @Override
    public void deleteCliente(Long id_cliente) {
        clienteRepo.deleteById(id_cliente);
    }

    @Override
    public void editCliente(Long id_cliente, Cliente client) {
        Cliente cliEdit = this.findCliente(id_cliente);
        
        cliEdit.setNombre(client.getNombre());
        cliEdit.setApellido(client.getApellido());
        cliEdit.setDni(client.getDni());
        
        this.newCliente(cliEdit);
    }
    
}
