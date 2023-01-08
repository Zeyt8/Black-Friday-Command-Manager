import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

class Tema2 extends RecursiveAction {

    public static File ordersInputFile;
    public static File productsInputFile;
    public static FileWriter ordersOutputFile;
    public static FileWriter productsOutputFile;
    public static String folder;
    public static int P;

    ForkJoinPool fjp;

    private Tema2 (ForkJoinPool fjp) {
        this.fjp = fjp;
    }

    public static void main(String[] args) throws IOException {
        folder = args[0];
        P = Integer.parseInt(args[1]);

        ordersInputFile = new File(folder, "orders.txt");
        productsInputFile = new File(folder, "order_products.txt");
        ordersOutputFile = new FileWriter(new File("orders_out.txt"), false);
        productsOutputFile = new FileWriter(new File("order_products_out.txt"), false);

        ForkJoinPool fjp = new ForkJoinPool(P);
        fjp.invoke(new Tema2(fjp));
        fjp.shutdown();
        
        ordersOutputFile.close();
        productsOutputFile.close();
    }

    @Override
    protected void compute() {
        ArrayList<OrderWorker> workers = new ArrayList<>();
        for (int i = 0; i < P; i++) {
            OrderWorker o = new OrderWorker(fjp, i);
            workers.add(o);
            o.fork();
        }
        for (OrderWorker o : workers) {
            o.join();
        }
    }
}