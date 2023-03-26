
package com.gonza.bazar.controller;

import com.gonza.bazar.model.Cliente;
import com.gonza.bazar.service.IClienteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteController {
    
    @Autowired
    IClienteService clienteServ;
    
    @PostMapping("/clientes/crear")
    public String newCliente(@RequestBody Cliente cliente){
        clienteServ.newCliente(cliente);
        
        return "Nuevo cliente creado.";
    }
    
    @GetMapping("/clientes")
    public List<Cliente> getClientes(){
        return clienteServ.getClientes();
    }
    
    @GetMapping("/clientes/{id_cliente}")
    public Cliente findCliente(@PathVariable Long id_cliente){
        return clienteServ.findCliente(id_cliente);
    }
    
    @DeleteMapping("/clientes/eliminar/{id_cliente}")
    public String deleteCliente(@PathVariable Long id_cliente){
        clienteServ.deleteCliente(id_cliente);
        
        return "Cliente eliminado.";
    }
    
    @PutMapping("/clientes/editar/{id_cliente}")
    public Cliente editCliente(@PathVariable Long id_cliente, @RequestBody Cliente cliente){
        clienteServ.editCliente(id_cliente, cliente);
        
        return clienteServ.findCliente(id_cliente);
    }
}
