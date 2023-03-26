
package com.gonza.bazar.service;

import com.gonza.bazar.model.Producto;
import com.gonza.bazar.repository.IProductoRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService implements IProductoService{

    @Autowired
    IProductoRepository produRepo;
    
    @Override
    public void newProducto(Producto produ) {
        produRepo.save(produ);
    }

    @Override
    public List<Producto> getProductos() {
        return produRepo.findAll();
    }

    @Override
    public Producto findProducto(Long codigo_producto) {
        return produRepo.findById(codigo_producto).orElse(null);
    }

    @Override
    public void deleteProducto(Long codigo_producto) {
        produRepo.deleteById(codigo_producto);
    }

    @Override
    public void editProducto(Long codigo_producto, Producto produ) {
        Producto produEdit = this.findProducto(codigo_producto);
        
        produEdit.setNombre(produ.getNombre());
        produEdit.setMarca(produ.getMarca());
        produEdit.setCosto(produ.getCosto());
        produEdit.setCantidad_disponible(produ.getCantidad_disponible());
    }        

    @Override
    public List<Producto> findProductosStockless() {
        List<Producto> listaProductos = this.getProductos();
        List<Producto> listaProductosSinStock = new ArrayList<>();
        
        for(Producto produ : listaProductos)
            if(produ.getCantidad_disponible() < 5){
                listaProductosSinStock.add(produ);
            }
        
        return listaProductosSinStock;
    }
    
    
    //Metodo para hacer las actualizaciones de stock de los productos
    @Override
    public void updateStock(List<Producto> listaProductos, String tipo_operacion) {
        
        if(tipo_operacion.equalsIgnoreCase("venta")){
            
            for(Producto produ : listaProductos){                
                Producto produOg = this.findProducto(produ.getCodigo_producto());
                produOg.setCantidad_disponible
                        (produOg.getCantidad_disponible() - produ.getCantidad_disponible());
                
                this.editProducto(produOg.getCodigo_producto(), produOg);
            }
            
        }else if (tipo_operacion.equalsIgnoreCase("ingreso")){
            
            for(Producto produ : listaProductos){                
                Producto produOg = this.findProducto(produ.getCodigo_producto());
                produOg.setCantidad_disponible
                        (produOg.getCantidad_disponible() + produ.getCantidad_disponible());
                
                this.editProducto(produOg.getCodigo_producto(), produOg);
            }
        }
        
    }
    
    @Override
    public void updateStock(List<Producto> listaProductos, List<Producto> nuevaListaProductos) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
