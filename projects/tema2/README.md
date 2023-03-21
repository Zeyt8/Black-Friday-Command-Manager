# Black Friday command manager in Java

I start a ForkJoinPool to which I add Theme2 through an invoke.

In the compute method of Tema2, I start P Order Workers in a ForkJoinPool. The worker receives an index. Theme2 waits for join on Order Workers.

## Order Workers

The Order Worker reads from the orders file the orders assigned to it, in a detailed manner described in README_BONUS.

For each order, it opens a number of Product Workers equal to the number of products and gives each worker an index. The worker forks to the task.

It waits for join from the Product Workers it has created for this order. At the end, it writes in the output that it has finished that order and moves on to the next one.

## Product Worker

It searches linearly in the products file for the product that belongs to the assigned order. It repeats the operation until it reaches the product with the index number it has.

It writes in the output file the information about the assigned product.
