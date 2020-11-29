package com.bcopstein;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
public class ServicoDeVendasTest {

    @Test
    public void calculaSubTotalTeste(){

        Produtos produtos = mock(Produtos.class);

        when(produtos.recupera(10)).thenReturn(new Produto(10,"Prod10",1100.0));
        //when(produtos.recupera(30)).thenReturn(new Produto(30,"Prod30",2000.0));
        //when(produtos.recupera(50)).thenReturn(new Produto(50,"Prod15",1500.0));
        
        Estoque estoque = mock(Estoque.class);
        when(estoque.recupera(10)).thenReturn(new ItemEstoque(10,50));
        //when(estoque.recupera(30)).thenReturn(new ItemEstoque(30,50));
        //when(estoque.recupera(50)).thenReturn(new ItemEstoque(50,15));

        List<ItemVenda> itens = new ArrayList<>(3);
        itens.add(new ItemVenda(1,10,2,2200)); // 2000
        //itens.add(new ItemVenda(2,30,3,2000)); // 6000
        //itens.add(new ItemVenda(3,50,1,1500)); // 1500
        
        RegraValidacao regra = new ValidacaoHorarioComercial();
       // assertDoesNotThrow(()->regra.valida(produtos,estoque,itens));

        RegraImpostoComprasGrandes r = new RegraImpostoComprasGrandes();
        FactoryValidacao f = mock(FactoryValidacao.class);
        when(f.getRegraValidacao()).thenReturn(new ValidacaoHorarioComercial());
        ServicoDeVendas servico = new ServicoDeVendas(produtos, estoque, r, f);

        assertEquals(2200 ,servico.calculaSubtotal(itens));
    }
    

    @Test
    public void calculaImpostoOriginalTeste(){

        Produtos produtos = mock(Produtos.class);

        when(produtos.recupera(10)).thenReturn(new Produto(10,"Prod10",100.0));
        when(produtos.recupera(30)).thenReturn(new Produto(30,"Prod30",50.0));
        when(produtos.recupera(50)).thenReturn(new Produto(50,"Prod15",150.0));
        
        Estoque estoque = mock(Estoque.class);
        when(estoque.recupera(10)).thenReturn(new ItemEstoque(10,50));
        when(estoque.recupera(30)).thenReturn(new ItemEstoque(30,50));
        when(estoque.recupera(50)).thenReturn(new ItemEstoque(50,15));

        List<ItemVenda> itens = new ArrayList<>(3);
        //itens.add(new ItemVenda(1,10,3,300)); // 2000
        //itens.add(new ItemVenda(2,30,6,300)); // 6000
        itens.add(new ItemVenda(3,50,5,150)); // 1500
        
        RegraValidacao regra = new ValidacaoHorarioComercial();
        //assertDoesNotThrow(()->regra.valida(produtos,estoque,itens));

        RegraImpostoOriginal r = new RegraImpostoOriginal();
        FactoryValidacao f = mock(FactoryValidacao.class);
        when(f.getRegraValidacao()).thenReturn(new ValidacaoHorarioComercial());
        ServicoDeVendas servico = new ServicoDeVendas(produtos, estoque, r, f);
        assertEquals(75, servico.calculaImpostos(itens));
        
    }
    @Test
    public void calculaImpostoGrandeTeste(){

        Produtos produtos = mock(Produtos.class);

        when(produtos.recupera(10)).thenReturn(new Produto(10,"Prod10",350.0));
        when(produtos.recupera(20)).thenReturn(new Produto(20,"Prod20",300.0));
        when(produtos.recupera(30)).thenReturn(new Produto(30,"Prod30",250.0));
        when(produtos.recupera(50)).thenReturn(new Produto(50,"Prod15",100.0));
        
        Estoque estoque = mock(Estoque.class);
        when(estoque.recupera(10)).thenReturn(new ItemEstoque(10,50));
        when(estoque.recupera(20)).thenReturn(new ItemEstoque(20,50));
        when(estoque.recupera(30)).thenReturn(new ItemEstoque(30,50));
        when(estoque.recupera(50)).thenReturn(new ItemEstoque(50,15));

        List<ItemVenda> itens = new ArrayList<>(3);
        itens.add(new ItemVenda(1,10,1,350)); // 2000
        itens.add(new ItemVenda(2,20,1,300)); // 6000
        itens.add(new ItemVenda(3,30,1,250)); // 1500
        itens.add(new ItemVenda(4,50,1,100)); // 1500
        
        RegraValidacao regra = new ValidacaoHorarioComercial();
        //assertDoesNotThrow(()->regra.valida(produtos,estoque,itens));

        RegraImpostoComprasGrandes r = new RegraImpostoComprasGrandes();

        FactoryValidacao f = mock(FactoryValidacao.class);
        when(f.getRegraValidacao()).thenReturn(new ValidacaoHorarioComercial());
        ServicoDeVendas servico = new ServicoDeVendas(produtos, estoque, r, f);
        assertEquals(95, servico.calculaImpostos(itens));
        
    }

    @Test
    public void calculaPrecoFinalTeste(){

        Produtos produtos = mock(Produtos.class);

        when(produtos.recupera(10)).thenReturn(new Produto(10,"Prod10",350.0));
        when(produtos.recupera(20)).thenReturn(new Produto(20,"Prod20",300.0));
        when(produtos.recupera(30)).thenReturn(new Produto(30,"Prod30",250.0));
        when(produtos.recupera(50)).thenReturn(new Produto(50,"Prod15",100.0));
        
        Estoque estoque = mock(Estoque.class);
        when(estoque.recupera(10)).thenReturn(new ItemEstoque(10,50));
        when(estoque.recupera(20)).thenReturn(new ItemEstoque(20,50));
        when(estoque.recupera(30)).thenReturn(new ItemEstoque(30,50));
        when(estoque.recupera(50)).thenReturn(new ItemEstoque(50,15));

        List<ItemVenda> itens = new ArrayList<>(3);
        itens.add(new ItemVenda(1,10,1,350)); // 2000
        itens.add(new ItemVenda(2,20,1,300)); // 6000
        itens.add(new ItemVenda(3,30,1,250)); // 1500
        itens.add(new ItemVenda(4,50,1,100)); // 1500

        
        //RegraValidacao regra = new ValidacaoHorarioComercial();
        //assertDoesNotThrow(()->regra.valida(produtos,estoque,itens));

        RegraImpostoComprasGrandes r = new RegraImpostoComprasGrandes();

        FactoryValidacao f = mock(FactoryValidacao.class);
        when(f.getRegraValidacao()).thenReturn(new ValidacaoHorarioComercial());

        ServicoDeVendas servico = new ServicoDeVendas(produtos, estoque, r, f);
        assertEquals(1095,servico.calculaPrecoFinal(itens));
    }
    @Test
    public void TodosOsValoresTeste(){

        Produtos produtos = mock(Produtos.class);

        when(produtos.recupera(10)).thenReturn(new Produto(10,"Prod10",350.0));
        when(produtos.recupera(20)).thenReturn(new Produto(20,"Prod20",300.0));
        when(produtos.recupera(30)).thenReturn(new Produto(30,"Prod30",250.0));
        when(produtos.recupera(50)).thenReturn(new Produto(50,"Prod15",100.0));
        
        Estoque estoque = mock(Estoque.class);
        when(estoque.recupera(10)).thenReturn(new ItemEstoque(10,50));
        when(estoque.recupera(20)).thenReturn(new ItemEstoque(20,50));
        when(estoque.recupera(30)).thenReturn(new ItemEstoque(30,50));
        when(estoque.recupera(50)).thenReturn(new ItemEstoque(50,15));

        List<ItemVenda> itens = new ArrayList<>(3);
        itens.add(new ItemVenda(1,10,1,350)); // 2000
        itens.add(new ItemVenda(2,20,1,300)); // 6000
        itens.add(new ItemVenda(3,30,1,250)); // 1500
        itens.add(new ItemVenda(4,50,1,100)); // 1500
        
        RegraValidacao regra = new ValidacaoHorarioComercial();
        //assertDoesNotThrow(()->regra.valida(produtos,estoque,itens));

        RegraImpostoComprasGrandes r = new RegraImpostoComprasGrandes();

        FactoryValidacao f = mock(FactoryValidacao.class);
        when(f.getRegraValidacao()).thenReturn(new ValidacaoHorarioComercial());
        ServicoDeVendas servico = new ServicoDeVendas(produtos, estoque, r, f);

        assertEquals(1000,servico.todosValores(itens)[0]);
        assertEquals(95,servico.todosValores(itens)[1]);
        //retornando null na terceira posicao do array
        assertEquals(1095,servico.todosValores(itens)[2]);
    }

}