package benchmark;

public class Benchmark {
    public static long medirTiempo(Runnable operacion) {
        long inicio = System.nanoTime();
        operacion.run();
        return System.nanoTime() - inicio;
    }

    public static long medirMemoria(Runnable operacion) {
        Runtime runtime = Runtime.getRuntime();
        System.gc(); // Recolector para limpiar antes
        long memoriaAntes = runtime.totalMemory() - runtime.freeMemory();
        operacion.run();
        long memoriaDespues = runtime.totalMemory() - runtime.freeMemory();
        return memoriaDespues - memoriaAntes;
    }
}