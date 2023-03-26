
package com.gonza.bazar.service;

import com.gonza.bazar.model.Producto;
import com.gonza.bazar.model.Venta;
import java.util.List;


public interface IProductoService {
    
    //CRUD Productos
    public void newProducto(Producto produ);
    public List<Producto> getProductos();
    public Producto findProducto(Long codigo_producto);
    public void deleteProducto(Long codigo_producto);
    public void editProducto(Long codigo_producto, Producto produ);
    
    //Metodo para actualizar el stock de los productos (venta y anulacion)
    public void updateStock(List<Producto> listaProductos, String tipo_operacion);
    //Metodo para actualizar el stock (edicion de venta)
    public void updateStock(List<Producto> listaProductos, List<Producto> nuevaListaProductos);
    
    //Obtener todos los productos cuya cantidad_disponible sea menor a 5
    public List<Producto> findProductosStockless();
}
