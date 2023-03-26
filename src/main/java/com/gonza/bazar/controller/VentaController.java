
package com.gonza.bazar.controller;

import com.gonza.bazar.dto.VentaDTO;
import com.gonza.bazar.model.Producto;
import com.gonza.bazar.model.Venta;
import com.gonza.bazar.service.IVentaService;
import java.time.LocalDate;
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
public class VentaController {
    
    @Autowired
    IVentaService ventaServ;
    
    @PostMapping("/ventas/crear")
    public String newVenta(@RequestBody Venta venta){
        ventaServ.newVenta(venta);
        
        return "Nueva venta creada.";
    }
    
    @GetMapping("/ventas")
    public List<Venta> getVentas(){
        return ventaServ.getVenta();
    }
    
    @GetMapping("/ventas/{codigo_venta}")
    public Venta findVenta(@PathVariable Long codigo_venta){
        return ventaServ.findVenta(codigo_venta);
    }
    
    @DeleteMapping("/ventas/eliminar/{codigo_venta}")
    public String deleteVenta(@PathVariable Long codigo_venta){
        ventaServ.deleteVenta(codigo_venta);
        
        return "Venta eliminada";
    }
    
    @PutMapping("/ventas/editar/{codigo_venta}")
    public Venta editVenta(@PathVariable Long codigo_venta, @RequestBody Venta venta){
        ventaServ.editVenta(codigo_venta, venta);
        
        return ventaServ.findVenta(codigo_venta);
    }
    
    //Obtener la lista de productos de una determinada venta
    @GetMapping("/ventas/productos/{codigo_venta}")
    public List<Producto> findProductosVenta(@PathVariable Long codigo_venta){
        return ventaServ.findProductosVenta(codigo_venta);
    }
    
    //Obtener la sumatoria del monto y también cantidad total de ventas de un determinado dia
    @GetMapping("/ventas/{fecha_venta}")
    public String findVentasByDay (@PathVariable LocalDate fecha_venta){
        
        return ventaServ.findVentasByDay(fecha_venta);
    }

    //Obtener el codigo_venta, el total, la cantidad de productos, el nombre del cliente y el
    //apellido del cliente de la venta con el monto más alto de todas.
    @GetMapping("/ventas/mayor_venta")
    public VentaDTO findBigestVenta(){
        return ventaServ.findBigestVenta();
    }
}
