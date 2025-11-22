package com.utn.productos.service;

import com.utn.productos.exception.ProductoNotFoundException;
import com.utn.productos.model.Categoria;
import com.utn.productos.model.Producto;
import com.utn.productos.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Producto crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> obtenerPorId(Long id) {
        return productoRepository.findById(id);
    }

    public List<Producto> obtenerPorCategoria(Categoria categoria) {
        return productoRepository.findByCategoria(categoria);
    }

    public Producto actualizarProducto(Long id, Producto productoActualizado) {
        return productoRepository.findById(id)
                .map(producto -> {
                    producto.setNombre(productoActualizado.getNombre());
                    producto.setDescripcion(productoActualizado.getDescripcion());
                    producto.setPrecio(productoActualizado.getPrecio());
                    producto.setStock(productoActualizado.getStock());
                    producto.setCategoria(productoActualizado.getCategoria());
                    return productoRepository.save(producto);
                })
                .orElseThrow(() -> new ProductoNotFoundException("Producto no encontrado con id: " + id));
    }

    public Producto actualizarStock(Long id, Integer nuevoStock) {
        return productoRepository.findById(id)
                .map(producto -> {
                    producto.setStock(nuevoStock);
                    return productoRepository.save(producto);
                })
                .orElseThrow(() -> new ProductoNotFoundException("Producto no encontrado con id: " + id));
    }

    public void eliminarProducto(Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
        } else {
             throw new ProductoNotFoundException("Producto no encontrado con id: " + id);
        }
    }
}
