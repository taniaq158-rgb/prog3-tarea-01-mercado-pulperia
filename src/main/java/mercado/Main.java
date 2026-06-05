package mercado;

public class Main {

    // --- Demostración de paso de parámetros ---

    static void reabastecer(Producto p, int extra) {
        // ref: la referencia permite modificar el objeto original
        p.agregarStock(extra);
    }

    static void intentarRebajar(double precio, double pct) {
        // in: solo modifica la copia local — el original no cambia
        precio = precio * (1 - pct / 100);
    }

    public static void main(String[] args) {

        // -------------------------------------------------------
        // 1. ENCAPSULAMIENTO — paso de parámetros por referencia vs. por valor
        // -------------------------------------------------------
        System.out.println("=== PASO DE PARÁMETROS ===");

        Bebida cola = new Bebida("Gaseosa", 800.0, 6);
        System.out.println("Cantidad antes : " + cola.getCantidad());
        reabastecer(cola, 6);
        System.out.println("Cantidad después: " + cola.getCantidad() + "  ← objeto cambia (ref)");

        double precio = 1000.0;
        intentarRebajar(precio, 10);
        System.out.println("Primitivo       : " + precio + "  ← copia local, no cambia (in)");

        // -------------------------------------------------------
        // 2. OVERLOADING — mismo nombre, distintos parámetros
        // -------------------------------------------------------
        System.out.println("\n=== OVERLOADING ===");
        cola.mostrar();
        cola.mostrar(true);

        // -------------------------------------------------------
        // 3. POLIMORFISMO — un loop, múltiples tipos, comportamiento diferente
        // -------------------------------------------------------
        System.out.println("\n=== POLIMORFISMO ===");
        Producto[] carrito = {
            new Fruta("Banano",   300.0, 10, 5),
            new Bebida("Café",    2500.0, 2),
            new Fruta("Manzana",  450.0, 8, 12),
            new Abarrote("Arroz", 900.0, 12)
        };

        for (Producto p : carrito) {
            p.mostrar();
            if (p instanceof Perecedero) {
                Perecedero per = (Perecedero) p;
                System.out.println("   Vence en: " + per.diasParaVencer() + " días");
            }
        }

        // -------------------------------------------------------
        // 4. STATIC — método de clase, sin instancia de Mercado
        // -------------------------------------------------------
        System.out.println("\n=== TOTAL DEL CARRITO ===");
        System.out.println("Total: ₡" + Mercado.totalCarrito(carrito));

        // =======================================================
        // ===== ZONA TAREA =====
        // Pasos para habilitar esta sección:
        //   1. Complete Limpieza.java  (constructor, precioFinal, aplicarDescuento,
        //                               setPorcentajeDescuento, promo).
        //   2. Complete Mercado.masCaro, masBarato y totalDescuentos.
        //   3. Descomente el bloque de abajo — debería compilar sin errores.
        // =======================================================

        
        System.out.println("\n=== ZONA TAREA ===");

        // HERENCIA + ABSTRACCIÓN: el carrito mezcla tipos distintos bajo Producto
        Limpieza jabon = new Limpieza("Jabón", 1200.0, 3, 0.10);
        Producto[] carritoTarea = {
            new Fruta("Banano", 300.0, 10, 5),
            new Bebida("Café",  2500.0, 2),
            jabon
        };

        // ENCAPSULAMIENTO: el setter valida el rango — el objeto protege su estado
        jabon.setPorcentajeDescuento(-0.5);   // debe imprimir "Descuento inválido: -0.5"
        jabon.setPorcentajeDescuento(0.15);   // debe actualizarse sin error

        // OVERLOADING: misma operación, distintos contratos
        jabon.promo();
        jabon.promo(true);

        // POLIMORFISMO: un loop, múltiples tipos, comportamiento diferente en cada uno
        for (Producto p : carritoTarea) {
            p.mostrar();
            if (p instanceof Descontable) {
                Descontable d = (Descontable) p;
                System.out.println("   Descuento : ₡" + d.aplicarDescuento());
            }
            if (p instanceof Perecedero) {
                Perecedero per = (Perecedero) p;
                System.out.println("   Vence en  : " + per.diasParaVencer() + " días");
            }
        }

        // STATIC + POLIMORFISMO: Mercado opera sobre cualquier Producto[]
        System.out.println("Más caro        : " + Mercado.masCaro(carritoTarea).getNombre());
        System.out.println("Más barato      : " + Mercado.masBarato(carritoTarea).getNombre());
        System.out.println("Total descuentos: ₡" + Mercado.totalDescuentos(carritoTarea));
        
    }
}
