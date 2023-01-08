import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.stream.Stream;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class OrderWorker extends RecursiveAction {

    LinkedList<Order> orders = new LinkedList<Order>();
    int index;
    ForkJoinPool fjp;

    public OrderWorker (ForkJoinPool fjp, int index) {
        this.fjp = fjp;
        this.index = index;
    }

    @Override
    public void compute() {
        readOrders();
        ArrayList<ProductWorker> workers = new ArrayList<>();
        for (Order order : orders) {
            if (order.quantity == 0) {
                continue;
            }
            for (int i = 0; i < order.quantity; i++) {
                ProductWorker pw = new ProductWorker(order, i + 1, this);
                workers.add(pw);
                pw.fork();
            }
            for (ProductWorker pw : workers) {
                pw.join();
            }
            printOrder(order);
        }
    }

    private void readOrders() {
        try (BufferedReader br = new BufferedReader(new FileReader(Tema2.ordersInputFile))) {
            String order;
            Stream<String> lines = br.lines().skip(index);
            while (true) {
                try {
                    order = lines.findFirst().get();
                }
                catch (NoSuchElementException e) {
                    break;
                }
                
                String[] tokens = order.split(",");
                String orderId = tokens[0];
                int quantity = Integer.parseInt(tokens[1]);
                
                orders.add(new Order(orderId, quantity));

                lines = br.lines().skip(Tema2.P - 1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private synchronized void printOrder(Order order) {
        try {
            Tema2.ordersOutputFile.write(order.ID + "," + order.quantity + ",shipped\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
