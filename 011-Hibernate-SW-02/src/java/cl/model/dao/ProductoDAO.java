
package cl.model.dao;

/** 
 * @author Davel-11
 * operaciones sobre la tabla
 */
import cl.model.pojos.Producto;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;
import java.util.List;

public class ProductoDAO {
    
    //recive pojo preparado // meotodo para guardar en base de datos
    public void ingresarProducto(Producto p){
        SessionFactory sf=null;
        Session sesion=null;
        Transaction tx=null;
        try{
            sf = HibernateUtil.getSessionFactory();
            sesion = sf.openSession();
            tx = sesion.beginTransaction();
            sesion.save(p);                    
            tx.commit();
            sesion.close();
            
        } catch (Exception ex){
            tx.rollback();
            throw new RuntimeException("No se pudo Guardar");
        }
    }
    
    //retornar un producto
    public String consultarProducto(int codigo){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session sesion = sf.openSession();
        Producto p =  (Producto)sesion.get(Producto.class, codigo);
        sesion.close();
        if(p!=null){
            return "El Producto de Codigo es: "+p.getCodigo()+
                    " El Nombre es: "+p.getNombre()+
                    " Cuesta :"+p.getPrecio()+
                    " Tiene en stok: "+p.getStock()+
                    " - Descripcion: "+p.getDescripcion();
        }
        else {
            return "El Producto No existe - Codigo -> :"+ codigo;
        }
    }
    
    //rescatar todos los productos
    public List<Producto> verProductos(){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session sesion = sf.openSession();
        //consultas en base al pojo
        Query query = sesion.createQuery("from Producto");
        List<Producto> lista = query.list();
        sesion.close();
        return lista;
    }
    
}
