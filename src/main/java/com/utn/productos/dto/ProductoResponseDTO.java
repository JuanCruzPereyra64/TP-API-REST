package com.utn.productos.dto;

import com.utn.productos.model.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "DTO de respuesta con información del producto")
public class ProductoResponseDTO {
    @Schema(description = "ID único del producto", example = "1")
    private Long id;
    @Schema(description = "Nombre del producto", example = "Laptop Gamer")
    private String nombre;
    @Schema(description = "Descripción del producto", example = "Laptop de alta gama")
    private String descripcion;
    @Schema(description = "Precio del producto", example = "1500.00")
    private Double precio;
    @Schema(description = "Stock disponible", example = "10")
    private Integer stock;
    @Schema(description = "Categoría del producto", example = "ELECTRONICA")
    private Categoria categoria;
}
