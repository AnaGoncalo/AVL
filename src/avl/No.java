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
public class No {
    private int elemento;
    private No pai;
    private No filhoE;
    private No filhoD;
    private int fb;

    public No() {
    }

    public No(No pai, int elemento) {
        this.pai = pai;
        this.elemento = elemento;
        fb = 0;
    }

    public No getPai() {
        return pai;
    }

    public void setPai(No pai) {
        this.pai = pai;
    }
    
    public int getElemento() {
        return elemento;
    }

    public void setElemento(int elemento) {
        this.elemento = elemento;
    }

    public No getFilhoE() {
        return filhoE;
    }

    public void setFilhoE(No filhoE) {
        this.filhoE = filhoE;
    }

    public No getFilhoD() {
        return filhoD;
    }

    public void setFilhoD(No filhoD) {
        this.filhoD = filhoD;
    }

    public int getFb() {
        return fb;
    }

    public void setFb(int fb) {
        this.fb = fb;
    }
    
    public void atualFb(int n) {
        this.fb += n;
    }
    
    //Retorna se o No é raiz (não tem pai)
    public boolean isRoot()
    {
        return this.pai == null;
    }
    
    public boolean hasFilhoE(){
        return this.filhoE != null;
    }
    
    public boolean hasFilhoD(){
        return this.filhoD != null;
    }
    
    //Retorna se o No é interno (tem filho)
    public boolean isInternal()
    {
        return this.filhoE != null || this.filhoD != null;
    }
    //Retorna se o No é externo (não tem filho)
    public boolean isExternal()
    {
        return this.filhoD == null && this.filhoE == null;
    }
}
