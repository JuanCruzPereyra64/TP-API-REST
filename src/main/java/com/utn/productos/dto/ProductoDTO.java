package com.utn.productos.dto;

import com.utn.productos.model.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Schema(description = "DTO para crear o actualizar un producto")
public class ProductoDTO {
    @Schema(description = "Nombre del producto", example = "Laptop Gamer")
    @NotNull(message = "El nombre no puede ser nulo")
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombre;

    @Schema(description = "Descripción detallada del producto", example = "Laptop de alta gama con RTX 4090")
    @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
    private String descripcion;

    @Schema(description = "Precio del producto", example = "1500.00")
    @NotNull(message = "El precio no puede ser nulo")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
    private Double precio;

    @Schema(description = "Cantidad en stock", example = "10")
    @NotNull(message = "El stock no puede ser nulo")
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock;

    @Schema(description = "Categoría del producto", example = "ELECTRONICA")
    @NotNull(message = "La categoría no puede ser nula")
    private Categoria categoria;
}
