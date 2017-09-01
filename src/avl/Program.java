/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avl;

/**
 *
 * @author Ana Gonçalo
 */
public class Program {
    
    public static void main(String[] args) {
        ArvoreAVL tree = new ArvoreAVL();
        
        //Teste de Simples a esquerda
        /*
        No a = new No(null, 32);
        tree.setRaiz(a);
        tree.incluir(31);
        tree.incluir(35);
        tree.incluir(33);
        tree.incluir(36);
        tree.incluir(38);
        */
        
        //Teste de Simples a direita
        /*
        No a = new No(null, 50);
        tree.setRaiz(a);
        tree.incluir(20);
        tree.incluir(60);
        tree.incluir(10);
        tree.incluir(30);
        tree.incluir(5);
        */
        
        // Teste de Dupla a esquerda
        /*
        No a = new No(null, 50);
        tree.setRaiz(a);
        tree.incluir(20);
        tree.incluir(80);
        tree.incluir(70);
        tree.incluir(90);
        tree.incluir(60);
        */
        
        //Teste de Dupla a direita
        /*
        No a = new No(null, 50);
        tree.setRaiz(a);
        tree.incluir(20);
        tree.incluir(90);
        tree.incluir(10);
        tree.incluir(40);
        tree.incluir(30);
        */
        
        //tree.exibirArvore(tree.getRaiz());
        System.out.println();
    }
}
