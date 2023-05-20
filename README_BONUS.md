# Bonus

Pentru implementarea bonusului m-am folosit de streamurile din java.

Order Workerul are un index. Folosindu-se de acest index, workerul citeste doar din P in P randuri cu un offset de index. Astfel, workerii vor citi alternativ randurile.

Creez un stream cu fiecare linie din fisierul orders. Initial dau skip(index) pentru a adauga offsetul.

In iteratie:

- Citesc primul element din stream. Acest lucru avanseaza streamul din BufferReader cu 1.

- Daca nu exista element pentru ca s-a ajuns la finalul fisierului, dau break.

- Dau skip (P - 1). -1 provine din faptul ca am citit deja un element, lucru ce a avansat streamul.