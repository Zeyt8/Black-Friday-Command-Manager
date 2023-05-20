import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.RecursiveAction;

public class ProductWorker extends RecursiveAction {
    Order order;
    String product;
    int productIndex;

    public ProductWorker (Order order, int productIndex, OrderWorker orderWorker) {
        this.order = order;
        this.productIndex = productIndex;
    }

    @Override
    public void compute() {
        try (BufferedReader br = new BufferedReader(new FileReader(Tema2.productsInputFile))) {
            int currProduct = 0;
            String line = br.readLine();
            while (line != null) {
                String[] tokens = line.split(",");
                if (tokens[0].equals(order.ID)) {
                    currProduct++;
                    if (productIndex == currProduct) {
                        product = tokens[1];
                        break;
                    }
                }
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        writeOrderProduct();
    }

    private synchronized void writeOrderProduct() {
        try {
            Tema2.productsOutputFile.write(order.ID + "," + product + ",shipped\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
