package mercado;

/**
 * Producto de limpieza: gravado con IVA 13% y con descuento propio.
 *
 * Esta clase practica cuatro pilares POO:
 *   Herencia       — extends Producto (reutiliza nombre, precio, cantidad via super)
 *   Abstracción    — implementa precioFinal() que Producto obliga a definir
 *   Encapsulamiento — porcentajeDescuento es private y solo cambia via setter validado
 *   Overloading    — dos versiones de promo()
 *
 * Referencia: lea Abarrote.java (precioFinal) y Fruta.java (interface) antes de empezar.
 */
public class Limpieza extends Producto implements Descontable {

    // ENCAPSULAMIENTO: atributo privado — solo accesible dentro de esta clase.
    // Solo puede cambiar a través de setPorcentajeDescuento(), que valida el rango.
    private double porcentajeDescuento; // ej. 0.10 = 10%

    // HERENCIA: super(...) delega los atributos comunes a Producto.
    public Limpieza(String nombre, double precioBase, int cantidad, double porcentajeDescuento) {
        super(nombre, precioBase, cantidad);
        this.porcentajeDescuento = porcentajeDescuento;
    }

    // ABSTRACCIÓN: contrato que Producto obliga a cada subclase a definir.
    // Tip: los campos de Producto son private — use los métodos de acceso heredados.
    @Override
    public double precioFinal() {

        
        
        double total = getPrecioBase() * getCantidad() * 1.13 - aplicarDescuento();
        return total;
        
    }

    // ABSTRACCIÓN / INTERFACE: implementa el contrato de Descontable.
    @Override
    public double aplicarDescuento() {
        return getPrecioBase() * getCantidad() * porcentajeDescuento;
        
    }

    public void setPorcentajeDescuento(double pct) {
    if (pct >= 0 && pct <= 1) {
        this.porcentajeDescuento = pct;
    } else {
        System.out.println("Descuento inválido: " + pct);
    }
}
    public void promo() {
    System.out.println("Promoción: " + getNombre()
            + " con descuento de "
            + (porcentajeDescuento * 100) + "%");
}

public void promo(boolean conEtiqueta) {
    if (conEtiqueta) {
        System.out.println("[PROMOCIÓN ESPECIAL]");
        promo();
    } else {
        promo();
    }
}
    
    
}

