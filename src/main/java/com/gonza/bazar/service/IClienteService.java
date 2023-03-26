
package com.gonza.bazar.service;

import com.gonza.bazar.model.Cliente;
import java.util.List;


public interface IClienteService {
    
    //CRUD Cliente
    public void newCliente(Cliente client);
    public List<Cliente> getClientes();
    public Cliente findCliente(Long id_cliente);
    public void deleteCliente(Long id_cliente);
    public void editCliente(Long id_cliente, Cliente client);
}
