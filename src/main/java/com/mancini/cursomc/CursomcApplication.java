package com.mancini.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mancini.cursomc.domain.Categoria;
import com.mancini.cursomc.domain.Cidade;
import com.mancini.cursomc.domain.Cliente;
import com.mancini.cursomc.domain.Endereco;
import com.mancini.cursomc.domain.Estado;
import com.mancini.cursomc.domain.Pagamento;
import com.mancini.cursomc.domain.PagamentoComBoleto;
import com.mancini.cursomc.domain.PagamentoComCartao;
import com.mancini.cursomc.domain.Pedido;
import com.mancini.cursomc.domain.Produto;
import com.mancini.cursomc.domain.enums.EstadoPagamento;
import com.mancini.cursomc.domain.enums.TipoCliente;
import com.mancini.cursomc.repositories.CategoriaRepository;
import com.mancini.cursomc.repositories.CidadeRepository;
import com.mancini.cursomc.repositories.ClienteRepository;
import com.mancini.cursomc.repositories.EnderecoRepository;
import com.mancini.cursomc.repositories.EstadoRepository;
import com.mancini.cursomc.repositories.PagamentoRepository;
import com.mancini.cursomc.repositories.PedidoRepository;
import com.mancini.cursomc.repositories.ProdutoRepository;


@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null,"Informática");
		Categoria cat2 = new Categoria(null,"Escritório");
		
	   Produto p1 = new Produto(null,"Computador",2000.00);
	   Produto p2 = new Produto(null,"Impressora",800.00);
	   Produto p3 = new Produto(null,"Mouse",80.00);
	   
	   cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
	   cat2.getProdutos().addAll(Arrays.asList(p2));
	   
	   p1.getCategorias().addAll(Arrays.asList(cat1));
	   p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
	   p3.getCategorias().addAll(Arrays.asList(cat1));
	   
	   categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
	   produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
	   
	   Estado est1 = new Estado(null,"Minas Gerais");
	   Estado est2 = new Estado(null,"São Paulo");
	   
	   Cidade cid1 = new Cidade(null,"Uberlândia",est1);
	   Cidade cid2 = new Cidade(null,"São Paulo",est2);
	   Cidade cid3 = new Cidade(null,"Campinas",est2);
	   
	   est1.getCidades().addAll(Arrays.asList(cid1));
	   est2.getCidades().addAll(Arrays.asList(cid2,cid3));
	   
	   estadoRepository.saveAll(Arrays.asList(est1,est2));
	   cidadeRepository.saveAll(Arrays.asList(cid1,cid2,cid3));
	   
	   Cliente cli1 = new Cliente(null,"Matheus Mancini","mancini995@gmail.com","40458626848",TipoCliente.PESSOAFISICA);
	   
	   cli1.getTelefones().addAll(Arrays.asList("996966663","996966662"));
	   
	   Endereco e1 = new Endereco(null, "Atras Praia", "47", "Lado Padaria", "Capim Macio", "59082-323", cli1, cid1);
	   Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "67890-567", cli1, cid2);
	   
	   cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
	   
	   clienteRepository.saveAll(Arrays.asList(cli1));
	   enderecoRepository.saveAll(Arrays.asList(e1,e2));
	   
	   SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	   
	   Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
	   Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
	   
	   Pagamento pagto1 = new PagamentoComCartao(null,EstadoPagamento.QUITADO,ped1,6);
	   
	   ped1.setPagamento(pagto1);
	   
	   Pagamento pagto2 = new PagamentoComBoleto(null,EstadoPagamento.PENDENTE,ped2, sdf.parse("20/10/2017 00:00"));
	   
	   ped2.setPagamento(pagto2);
	   
	   cli1.getPedidos().addAll(Arrays.asList(ped1));
	   
	   cli1.getPedidos().addAll(Arrays.asList(ped2));
	   
	   pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
	   pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
	   
	   
	}
}
