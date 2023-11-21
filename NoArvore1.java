import java.util.Random;

public class NoArvore1 {
    int info;
    NoArvore1 direita = null;
    NoArvore1 esquerda = null;

    NoArvore1 InsereRaiz(NoArvore1 tree, int valor) {
        tree.info = valor;
        tree.direita = null;
        tree.esquerda = null;
        return tree;
    }

    NoArvore1 Insere(NoArvore1 tree, int valor) {
        if (tree == null) {
            tree = new NoArvore1();
            tree.info = valor;
            tree.direita = null;
            tree.esquerda = null;
        } else if (valor < tree.info) {
            tree.esquerda = Insere(tree.esquerda, valor);
        } else {
            tree.direita = Insere(tree.direita, valor);
        }
        return tree;
    }

    NoArvore1 rotacaoDireita(NoArvore1 tree) {
        NoArvore1 novaRaiz = tree.esquerda;
        tree.esquerda = novaRaiz.direita;
        novaRaiz.direita = tree;
        return novaRaiz;
    }

    void criaLista(NoArvore1 tree) {
        NoArvore1 pseudoRoot = new NoArvore1();
        pseudoRoot.direita = tree;

        NoArvore1 current = pseudoRoot;
        while (current != null && current.esquerda != null) {
            current = rotacaoDireita(current);
        }

        tree = pseudoRoot.direita;
    }

    NoArvore1 balanceamentoDSW(NoArvore1 tree, int n) {
        tree = rotacaoEsquerdaN(tree, n);
        int m = n / 2;

        while (m > 0) {
            tree = rotacaoEsquerdaN(tree, m);
            m /= 2;
        }

        return tree;
    }

    NoArvore1 rotacaoEsquerdaN(NoArvore1 tree, int n) {
        NoArvore1 pseudoRoot = new NoArvore1();
        pseudoRoot.direita = tree;

        NoArvore1 current = pseudoRoot;
        for (int i = 0; i < n && current != null && current.direita != null; i++) {
            current = rotacaoDireita(current.direita);
        }

        tree = pseudoRoot.direita;
        return tree;
    }

    void preOrdem(NoArvore1 tree) {
        if (tree != null) {
            System.out.print(tree.info + " ");
            preOrdem(tree.esquerda);
            preOrdem(tree.direita);
        }
    }

    public static void main(String[] args) {
        NoArvore1 tree = new NoArvore1();

        for (int i = 0; i < 100; i++) {
            int randomNumber = new Random().nextInt(101);
            tree = tree.Insere(tree, randomNumber);
        }

        System.out.println("Árvore antes do DSW (pré-ordem):");
        tree.preOrdem(tree);
        System.out.println();

        tree.criaLista(tree);
        tree = tree.balanceamentoDSW(tree, 100);

        System.out.println("Árvore após o DSW (pré-ordem):");
        tree.preOrdem(tree);
        
    }
}
