
package com.gonza.bazar.service;

import com.gonza.bazar.dto.VentaDTO;
import com.gonza.bazar.model.Producto;
import com.gonza.bazar.model.Venta;
import com.gonza.bazar.repository.IVentaRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaService implements IVentaService{
    
    @Autowired
    IVentaRepository ventaRepo;
    IProductoService produServ;    
    
    @Override
    public void newVenta(Venta venta) {
        
        List<Producto> listaProductos = venta.getListaProductos();
        Double totalVenta = 0.00;      
        for(Producto produ : listaProductos){
            totalVenta = totalVenta + produ.getCosto();
        }
        
        produServ.updateStock(listaProductos, "venta");
        
        venta.setTotal(totalVenta);
        ventaRepo.save(venta);
    }

    @Override
    public List<Venta> getVenta() {
        return ventaRepo.findAll();
    }

    @Override
    public Venta findVenta(Long codigo_venta) {
        return ventaRepo.findById(codigo_venta).orElse(null);
    }

    @Override
    public void deleteVenta(Long codigo_venta) {
        
        List<Producto> listaProductos = this.findVenta(codigo_venta).getListaProductos();
        
        produServ.updateStock(listaProductos, "ingreso");
        
        ventaRepo.deleteById(codigo_venta);
    }

    @Override
    public void editVenta(Long codigo_venta, Venta venta) {
        Venta ventaEdit = this.findVenta(codigo_venta);
        //Llamado al metodo para actualizar el stock cuando se edita una venta
        produServ.updateStock(ventaEdit.getListaProductos(),
                venta.getListaProductos());
        
        
        //Asignacion de los nuevos valores recibidos a la venta
        ventaEdit.setFecha_venta(venta.getFecha_venta());
        ventaEdit.setTotal(venta.getTotal());
        
        ventaEdit.setUnCliente(venta.getUnCliente());
        ventaEdit.setListaProductos(venta.getListaProductos());
        
        //Se sobreescribe la venta del codigo de referencia
        this.newVenta(ventaEdit);        
        
    }

    @Override
    public List<Producto> findProductosVenta(Long codigo_venta) {
        
        List<Producto> listaProdu = this.findVenta(codigo_venta).getListaProductos();
        
        return listaProdu;
    }

    @Override
    public String findVentasByDay(LocalDate fecha_venta) {
        List<Venta> listaVentas = this.getVenta();
        Double montoTotal = 0.00;
        int cantidadVentas = 0;
        
        for (Venta venta : listaVentas){
            if (venta.getFecha_venta().equals(fecha_venta)){
                montoTotal = montoTotal + venta.getTotal();
                cantidadVentas = cantidadVentas + 1;
            }
        }
        
        return "La cantidad de ventas del dia indicado es de: " + cantidadVentas + 
                " y el monto total por las ventas de ese dia es de $" + montoTotal;
    }
    
    
    @Override
    public VentaDTO findBigestVenta() {
        VentaDTO mayorVentaDTO = new VentaDTO();
        Long codigo_mayorVenta = null;
        Double monto_venta = 0.00;
        
        List<Venta> listaVentas = this.getVenta();
        
        for (Venta venta : listaVentas){
            if (venta.getTotal() > monto_venta){
                codigo_mayorVenta = venta.getCodigo_venta();
            }
        }
        
        Venta mayorVenta = this.findVenta(codigo_mayorVenta);
        
        mayorVentaDTO.setCodigo_venta(codigo_mayorVenta);
        mayorVentaDTO.setTotal_venta(mayorVenta.getTotal());
        mayorVentaDTO.setCantidad_productos(mayorVenta.getListaProductos().size());
        
        mayorVentaDTO.setNombre_cliente(mayorVenta.getUnCliente().getNombre());
        mayorVentaDTO.setApellido_cliente(mayorVenta.getUnCliente().getApellido());
        
        return mayorVentaDTO;
    }
    
}
