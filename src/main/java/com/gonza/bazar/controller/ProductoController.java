
package com.gonza.bazar.controller;

import com.gonza.bazar.model.Producto;
import com.gonza.bazar.service.IProductoService;
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
public class ProductoController {
    
    @Autowired
    IProductoService produServ;
    
    @PostMapping("/productos/crear")
    public String newProducto(@RequestBody Producto produ){
        produServ.newProducto(produ);
        
        return "Nuevo producto creado.";
    }
    
    @GetMapping("/productos")
    public List<Producto> getProductos(){
        return produServ.getProductos();
    }
    
    @GetMapping("/productos/{codigo_producto}")
    public Producto findProducto(@PathVariable Long codigo_producto){
        return produServ.findProducto(codigo_producto);
    }
    
    @DeleteMapping("/productos/eliminar/{codigo_producto}")
    public String deleteProducto(@PathVariable Long codigo_producto){
        produServ.deleteProducto(codigo_producto);
        
        return "El producto fue eliminado";
    }
    
    @PutMapping("productos/editar/{codigo_producto}")
    public Producto editProducto(@PathVariable Long codigo_producto, @RequestBody Producto produ){
        produServ.editProducto(codigo_producto, produ);
        
        return produServ.findProducto(codigo_producto);
    }
    
    //Obtener todos los productos cuya cantidad_disponible sea menor a 5
    @GetMapping("/productos/falta_stock")
    public List<Producto> findProductosStockless(){
        return produServ.findProductosStockless();
    }
}
