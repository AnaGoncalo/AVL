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
public class ArvoreAVL {
   
    private No raiz;
    private int qtd;
    
    public No find(int key) {
        return pesquisar(raiz, key);
    }

    public No pesquisar(No no, int key) {
        //Verifica se o no não tem filho
        if (no.isExternal()) 
        {
            return no;
        }
        //Verifica se achou o no
        if (no.getElemento() == key) 
        {
            return no;       
        } 
        //Verifica se o no que procura esta do lado esquerdo
        else if ((int) key < (int) no.getElemento()) 
        {
            return pesquisar(no.getFilhoE(), key);
        }
        //Verifica se o no que procura esta do lado direito
        else//((int)key > (int)no.getElemento())
        {
            return pesquisar(no.getFilhoD(), key);
        }
    }

    private No buscarPai(No no, int key) {
        //Verifica se o no não tem filho
        if (no.isExternal()) {
            return no;
        }
        //Verifica se o no que procura esta do lado esquerdo
        if ((int) key < (int) no.getElemento()) 
        {
            if(no.getFilhoE() == null)
                return no;
            else
                return buscarPai(no.getFilhoE(), key);
        } 
        //Verifica se o no que procura esta do lado direito
        else
        {
            if(no.getFilhoD() == null)
                return no;
            else
                return buscarPai(no.getFilhoD(), key);
        }
    }

    public No incluir(int key) {
        //Busca o pai do no que vai ser inserido
        No aux = buscarPai(raiz, key);
        //Cria o novo no
        No novo = new No(aux, key);

        //Verifica se o novo no eh filho esquerdo do pai buscado
        if ((int) aux.getElemento() > (int) novo.getElemento()) 
            aux.setFilhoE(novo);
        //Verifica se o novo no eh filho direito do pai buscado
        else 
            aux.setFilhoD(novo);
        qtd++;
        
        AtualizarFB(novo.getPai(), novo, 1);
        return novo;
    }

    public Object remover(int key) {
        if(remover(raiz, key) != null)
            qtd--;
        System.out.println(qtd);
        return null;
    }

    public Object remover(No root, int key) {
        //Busca a chave na arvore
        No r = pesquisar(root, key);
        
        //Verifica se o no esta na arvore
        if (r != null && ((int)r.getElemento() == (int)key)) {
            //verifica se o no eh folha
            if (r.isExternal()) {
                //verifica se o no eh o filho direito
                if (r.getPai().getFilhoD() == r) 
                {
                    r.getPai().setFilhoD(null);
                    r.setPai(null);
                } 
                //o no eh filho esquerdo
                else  
                {
                    r.getPai().setFilhoE(null);
                    r.setPai(null);
                }
            } 
            //Verifica se o no a remover tem 1 filho e eh filho direito
            else if (r.getFilhoD() != null && r.getFilhoE() == null) {
                //Traz o filho direito para o lugar do no a remover
                r.getFilhoD().setPai(r.getPai());
                
                //Verifica se o no a remover eh filho esquerdo
                if((int)r.getElemento() < (int)r.getPai().getElemento())
                    r.getPai().setFilhoE(r.getFilhoD());
                //No a remover eh filho direito
                else
                    r.getPai().setFilhoD(r.getFilhoD());
            } 
            //Verifica se o no tem 1 filho e eh filho esquerdo
            else if (r.getFilhoE() != null && r.getFilhoD() == null) {
                //Traz o filho direito para o lugar do no a remover
                r.getFilhoE().setPai(r.getPai());
                
                //Verifica se o no a remover eh filho esquerdo
                if((int)r.getElemento() < (int)r.getPai().getElemento())
                    r.getPai().setFilhoE(r.getFilhoE());
                //No a remover eh filho direito
                else
                    r.getPai().setFilhoD(r.getFilhoE());
            }
            //O no a remover tem 2 filhos
            else 
            {
                //Acha o sucessor
                No herdeiro = Sucessor(r.getFilhoD());
                System.out.print("Sucessor: ");
                Visite(herdeiro);
                
                //Remove o herdeiro passando o filho direito como raiz da sub arvore
                remover(r.getFilhoD(), herdeiro.getElemento());
                
                //Traz o herdeiro para o lugar do no a remover
                herdeiro.setPai(r.getPai());
                herdeiro.setFilhoE(r.getFilhoE());
                herdeiro.setFilhoD(r.getFilhoD());
                
                //Atualiza o pai dos filhos do no a remover
                r.getFilhoE().setPai(herdeiro);
                r.getFilhoD().setPai(herdeiro);

                //Verifica se o no a remover eh filho esquerdo para atualizar o filho esquerdo do pai do no a remover 
                if (r.getPai().getFilhoE() == r) 
                    r.getPai().setFilhoE(herdeiro);
                //O no a remover eh filho direito entao atualiza o filho direito do pai do no a remover
                else
                    r.getPai().setFilhoD(herdeiro);
                
                
            }
            return r;
        }
        return null;
    }

    //Metodo para encontrar o sucessor de um no a remover
    public No Sucessor(No no) {
        //Verifica se eh folha ou se nao tem filho esquerdo --> encontrou o menor filho do no a remover
        if (no.isExternal() || no.getFilhoE() == null) 
            return no;
        //Continua a procura a apartir do filho esquerdo
        else 
            return Sucessor(no.getFilhoE());
    }

    
    public void setRaiz(No p) {
        raiz = p;
    }

    public No getRaiz() {
        return raiz;
    }

    //Metodo para visualizar a arvore --> Algoritmo InOrder
    public void exibirArvore(No n) {
        if (n.isInternal() && n.getFilhoE() != null) {
            exibirArvore(n.getFilhoE());
        }

        Visite(n);
        
        if (n.isInternal() && n.getFilhoD() != null) {
            exibirArvore(n.getFilhoD());
        }
    }

    //Metodo que mostra as caracteristicas do no *OBS: Faz a verificações para não dar erro de referencia nula
    public void Visite(No n) {
        System.out.print("Elemento:" + n.getElemento()  + " FB:" + n.getFb());
        if (!n.isRoot())
            System.out.print(" Pai:" + n.getPai().getElemento());
        
        if (n.getFilhoE() != null)
            System.out.print(" FilhoE:" + n.getFilhoE().getElemento());
        
        if (n.getFilhoD() != null) 
            System.out.print(" FilhoD:" + n.getFilhoD().getElemento());
        
        System.out.print(" Profundidade: " + Deep(n));
        System.out.println();
    }
    
    public int Deep(No no)
    {
        if(no.isRoot())
            return 0;
        else
            return 1 + Deep(no.getPai()); 
    }
    
    
    public void AtualizarFB(No no, No novo, int flag){
        //System.out.println(no.getElemento() + " " + novo.getElemento());
        //Adicionando
        if(flag == 1){
            //lado esquerdo
            if(novo.getElemento() < no.getElemento())
                no.atualFb(+1);
            //lado direito
            else
                no.atualFb(-1);
            
            //verifica se precisa balancear
            if(no.getFb() < -1 || no.getFb() > 1)
                no = Balancear(no);
            
            //para ou continua?
            if(no.getFb() != 0 && no.isRoot() == false) //continua
                AtualizarFB(no.getPai(), novo, flag);   
        }
        //Removendo
        else {
            //lado esquerdo
            if(novo.getElemento() < no.getElemento())
                no.atualFb(-1);
            //lado direito
            else
                no.atualFb(+1);
            //para ou continua?
            if(no.getFb() == 0 || !(no.isRoot()))
                AtualizarFB(no.getPai(), novo, flag);
        }
    }
    
    public No Balancear(No no){
        
        No retorno = null;
        //Rotação para Esquerda
        if(no.getFb() < 0){
            //Simples
            if(no.getFilhoD().getFb() < 0)
            {
                retorno = no.getFilhoD();
                System.out.println("Rotacao Simples Esquerda No: " + no.getElemento());
                no.setFb(0);
                no.getFilhoD().setFb(0);
                RotacaoSimplesEsquerda(no);
            }
            //Dupla
            else
            {
                System.out.println("Rotacao Dupla Esquerda No: " + no.getElemento());
                // Testando
                retorno = no.getFilhoD().getFilhoE();
                
                if(no.getFilhoD().getFilhoE().getFb() > 0){
                    no.setFb(0);
                    no.getFilhoD().setFb(-1);
                }
                else {
                    no.setFb(1);
                    no.getFilhoD().setFb(0);
                }
                no.getFilhoD().getFilhoE().setFb(0);
                RotacaoDuplaEsquerda(no);
            }
        }
        //Rotação para Direita
        else {
            //Simples
            if(no.getFilhoE().getFb() > 0)
            {
                retorno = no.getFilhoE();
                System.out.println("Rotacao Simples Direita No: " + no.getElemento());
                no.setFb(0);
                no.getFilhoE().setFb(0);
                RotacaoSimplesDireita(no);
            }
            //Dupla
            else
            {
                retorno = no.getFilhoE().getFilhoD();
                System.out.println("Rotacao Dupla Direita No: " + no.getElemento());
                // Testando
                if(no.getFilhoE().getFilhoD().getFb() > 0) {
                    no.setFb(-1);
                    no.getFilhoE().setFb(0);
                }
                else {
                    no.setFb(0);
                    no.getFilhoE().setFb(1);
                }
                no.getFilhoE().getFilhoD().setFb(0);
                RotacaoDuplaDireita(no);
            }
        }
        return retorno;
    }
    
    public void RotacaoSimplesEsquerda(No no) {
        System.out.println("Rotacao Simples Esquerda " + no.getElemento());
        
        No netoE = null;
        
        //se necessario, atualiza a raiz
        if(no.isRoot())
            raiz = no.getFilhoD();
        
        //guarda o netoE e atualiza suas referencia para o pai
        if(no.getFilhoD().getFilhoE() != null) {
            netoE = no.getFilhoD().getFilhoE();
            netoE.setPai(no);
        }
        
        //Atualiza as referencias do filho direito do no
        no.getFilhoD().setPai(no.getPai());
        no.getFilhoD().setFilhoE(no);
        
        //Atualiza as referencias do pai do no se existir
        if(no.getPai() != null) {
            if(no.getElemento() > no.getPai().getElemento())
                no.getPai().setFilhoD(no.getFilhoD());
            else
                no.getPai().setFilhoE(no.getFilhoD());        
        }
        
        //Atualiza as referencias do no
        no.setPai(no.getFilhoD());
        //if(netoE != null)
            no.setFilhoD(netoE);
        //else
        //   no.setFilhoD(null);
        
        exibirArvore(raiz);
    }
    
    public void RotacaoSimplesDireita(No no) {
        System.out.println("Rotacao Simples Direita " + no.getElemento());
        
        No netoD = null;
        
        //se necessario, atuliza a raiz
        if(no.isRoot())
            raiz = no.getFilhoE();
        
        //guarda o netoD 
        if(no.getFilhoE().getFilhoD() != null) {
            netoD = no.getFilhoE().getFilhoD();
            netoD.setPai(no);
        }
        
        //Atualiza as referencias do filho esquerdo do no
        no.getFilhoE().setPai(no.getPai());
        no.getFilhoE().setFilhoD(no);
        
        //Atualiza as referencias do pai do no, se existir
        if(no.getPai() != null){
            if(no.getElemento() > no.getPai().getElemento())
                no.getPai().setFilhoD(no.getFilhoE());
            else
                no.getPai().setFilhoE(no.getFilhoE());
        }
        
        //Atualiza as referencias do no
        no.setPai(no.getFilhoE());
        //if(netoD != null)
            no.setFilhoE(netoD);
        //else
        //    no.setFilhoE(null);
        
        exibirArvore(raiz);
    }
    
    public void RotacaoDuplaEsquerda(No no) {
        RotacaoSimplesDireita(no.getFilhoD());
        RotacaoSimplesEsquerda(no);
    }
    
    public void RotacaoDuplaDireita(No no) {
        RotacaoSimplesEsquerda(no.getFilhoE());
        RotacaoSimplesDireita(no);
    }
    
    
    
}
