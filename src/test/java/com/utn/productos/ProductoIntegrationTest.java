package com.utn.productos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.utn.productos.dto.ActualizarStockDTO;
import com.utn.productos.dto.ProductoDTO;
import com.utn.productos.model.Categoria;
import com.utn.productos.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductoIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        productoRepository.deleteAll();
    }

    @Test
    void testCicloCompletoProducto() throws Exception {
        // 1. Crear 5 productos de diferentes categorías
        crearProducto("Notebook Gamer", "Alta gama", 1500.0, 10, Categoria.ELECTRONICA);
        crearProducto("Camiseta", "Algodón", 20.0, 50, Categoria.ROPA);
        crearProducto("Arroz", "Integral", 2.5, 100, Categoria.ALIMENTOS);
        crearProducto("Sillón", "3 cuerpos", 500.0, 5, Categoria.HOGAR);
        crearProducto("Pelota", "Fútbol", 30.0, 20, Categoria.DEPORTES);

        // 2. Listar productos (debe haber 5)
        mockMvc.perform(get("/api/productos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)));

        // 3. Filtrar por categoría (ELECTRONICA debe haber 1)
        mockMvc.perform(get("/api/productos/categoria/ELECTRONICA"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nombre", is("Notebook Gamer")));

        // 4. Intentar crear producto inválido (sin nombre)
        ProductoDTO invalido1 = new ProductoDTO();
        invalido1.setPrecio(100.0);
        invalido1.setStock(10);
        invalido1.setCategoria(Categoria.ELECTRONICA);
        
        mockMvc.perform(post("/api/productos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalido1)))
                .andExpect(status().isBadRequest());

        // 5. Intentar crear producto inválido (precio negativo)
        ProductoDTO invalido2 = new ProductoDTO();
        invalido2.setNombre("Test");
        invalido2.setPrecio(-10.0);
        invalido2.setStock(10);
        invalido2.setCategoria(Categoria.ELECTRONICA);

        mockMvc.perform(post("/api/productos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalido2)))
                .andExpect(status().isBadRequest());

        // 6. Obtener producto por ID (creamos uno nuevo para tener el ID)
        String responseJson = mockMvc.perform(post("/api/productos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(crearDTO("Mouse", "Optico", 15.0, 20, Categoria.ELECTRONICA))))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        
        Long id = objectMapper.readTree(responseJson).get("id").asLong();

        mockMvc.perform(get("/api/productos/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is("Mouse")));

        // 7. Intentar obtener ID inexistente
        mockMvc.perform(get("/api/productos/99999"))
                .andExpect(status().isNotFound());

        // 8. Actualizar producto completo (PUT)
        ProductoDTO updateDTO = crearDTO("Mouse Gamer", "RGB", 25.0, 15, Categoria.ELECTRONICA);
        mockMvc.perform(put("/api/productos/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is("Mouse Gamer")))
                .andExpect(jsonPath("$.precio", is(25.0)));

        // 9. Actualizar solo stock (PATCH)
        ActualizarStockDTO stockDTO = new ActualizarStockDTO();
        stockDTO.setStock(50);
        mockMvc.perform(patch("/api/productos/" + id + "/stock")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(stockDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.stock", is(50)));

        // 10. Eliminar producto
        mockMvc.perform(delete("/api/productos/" + id))
                .andExpect(status().isNoContent());

        // 11. Verificar que ya no existe
        mockMvc.perform(get("/api/productos/" + id))
                .andExpect(status().isNotFound());
    }

    private void crearProducto(String nombre, String desc, Double precio, Integer stock, Categoria cat) throws Exception {
        ProductoDTO dto = crearDTO(nombre, desc, precio, stock, cat);
        mockMvc.perform(post("/api/productos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }

    private ProductoDTO crearDTO(String nombre, String desc, Double precio, Integer stock, Categoria cat) {
        ProductoDTO dto = new ProductoDTO();
        dto.setNombre(nombre);
        dto.setDescripcion(desc);
        dto.setPrecio(precio);
        dto.setStock(stock);
        dto.setCategoria(cat);
        return dto;
    }
}
