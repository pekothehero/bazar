
package com.gonza.bazar.service;

import com.gonza.bazar.dto.VentaDTO;
import com.gonza.bazar.model.Producto;
import com.gonza.bazar.model.Venta;
import java.time.LocalDate;
import java.util.List;


public interface IVentaService {

       
    //CRUD Venta
    public void newVenta(Venta venta);
    public List<Venta> getVenta();
    public Venta findVenta(Long codigo_venta);
    public void deleteVenta(Long codigo_venta);
    public void editVenta(Long codigo_venta, Venta venta);
    
    //Obtener la lista de productos de una determinada venta
    public List<Producto> findProductosVenta(Long codigo_venta);
    
    //Obtener la sumatoria del monto y también cantidad total de ventas de un determinado dia
    public String findVentasByDay(LocalDate fecha_venta);
    
    //Obtener el codigo_venta, el total, la cantidad de productos, el nombre del cliente y el
    //apellido del cliente de la venta con el monto más alto de todas.
    public VentaDTO findBigestVenta();
    
}
