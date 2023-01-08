# Manager de comenzi de Black Friday in Java

Pornesc un ForkJoinPool la care adaug prin invoke Tema2.

Tema2, in compute, porneste P Order Workers, intr-un ForkJoinPool. Workerul primeste un index. Tema2 asteapta join pe Order Workers.

## Order Workers

Order Workerul citeste din fisierul de orders, orderurile care i-au fost asignate, modalitate detaliata in README_BONUS.

Pentru fiecare order, deschide un numar de product workeri egal cu numarul de produse si ii da fiecaruia un indice. Acestui worker ii da fork.

Asteapta join de ProductWorkers pe care i-a creeat pentru acest order. La final scrie in output ca a terminat acel order si trece la urmatorul.

## Product Worker

Cauta liniar in fisierul de products produsul care apartine de orderul asignat. Repeta operatia pana ajunge la produsul cu numarul indicelui pe care il are.

Scrie in fisierul de output informatiile despre produsul asignat.