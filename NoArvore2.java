import java.util.Random;

public class NoArvore2 {
    int info;
    NoArvore2 direita = null;
    NoArvore2 esquerda = null;

    NoArvore2 InsereRaiz(NoArvore2 tree, int valor) {
        tree.info = valor;
        tree.direita = null;
        tree.esquerda = null;
        return tree;
    }

    NoArvore2 Insere(NoArvore2 tree, int valor) {
        if (tree == null) {
            tree = new NoArvore2();
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

    NoArvore2 rotacaoDireita(NoArvore2 tree) {
        NoArvore2 novaRaiz = tree.esquerda;
        tree.esquerda = novaRaiz.direita;
        novaRaiz.direita = tree;
        return novaRaiz;
    }

    void criaLista(NoArvore2 tree) {
        NoArvore2 pseudoRoot = new NoArvore2();
        pseudoRoot.direita = tree;

        NoArvore2 current = pseudoRoot;
        while (current != null && current.esquerda != null) {
            current = rotacaoDireita(current);
        }

        tree = pseudoRoot.direita;
    }

    NoArvore2 balanceamentoDSW(NoArvore2 tree, int n) {
        tree = rotacaoEsquerdaN(tree, n);
        int m = n / 2;

        while (m > 0) {
            tree = rotacaoEsquerdaN(tree, m);
            m /= 2;
        }

        return tree;
    }

    NoArvore2 rotacaoEsquerdaN(NoArvore2 tree, int n) {
        NoArvore2 pseudoRoot = new NoArvore2();
        pseudoRoot.direita = tree;

        NoArvore2 current = pseudoRoot;
        for (int i = 0; i < n && current != null && current.direita != null; i++) {
            current = rotacaoDireita(current.direita);
        }

        tree = pseudoRoot.direita;
        return tree;
    }

    void preOrdem(NoArvore2 tree) {
        if (tree != null) {
            System.out.print(tree.info + " ");
            preOrdem(tree.esquerda);
            preOrdem(tree.direita);
        }
    }

    public static void main(String[] args) {
        NoArvore2 tree = new NoArvore2();

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

        for (int i = 0; i < 20; i++) {
            int randomNumber = new Random().nextInt(101);
            tree = tree.Insere(tree, randomNumber);
        }

        System.out.println("Árvore após a inserção de 20 números (pré-ordem):");
        tree.preOrdem(tree);
    }
}
