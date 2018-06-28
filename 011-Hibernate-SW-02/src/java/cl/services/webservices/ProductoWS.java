
package cl.services.webservices;

import cl.model.pojos.Producto;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import cl.model.dao.ProductoDAO;
import java.util.List;


@WebService(serviceName = "ProductoWS")
public class ProductoWS {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "IngresarProducto")
    public String IngresarProducto(@WebParam(name = "codigo") int codigo, @WebParam(name = "nombre") String nombre, @WebParam(name = "precio") int precio, @WebParam(name = "stock") int stock, @WebParam(name = "descripcion") String descripcion) {
        //TODO write your implementation code here:
        //prepara el pojo
        Producto p = new Producto(codigo, nombre, precio, stock, descripcion);
        ProductoDAO productoDAO = new ProductoDAO();
        productoDAO.ingresarProducto(p);
        return "Producto Ingresado";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "BuscarProducto")
    public String BuscarProducto(@WebParam(name = "codigo") int codigo) {
        ProductoDAO productoDAO = new ProductoDAO();
        return productoDAO.consultarProducto(codigo);
        
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "ConsultarProducto")
    public List<Producto> ConsultarProducto() {
        //TODO write your implementation code here:
        ProductoDAO productoDAO = new ProductoDAO();
        List<Producto> listaProductos = productoDAO.verProductos();
        return listaProductos;
    }

    
    
}
