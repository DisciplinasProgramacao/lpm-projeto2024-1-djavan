package djavan.demo.models;

/** 
 * MIT License
 *
 * Copyright(c) 2024 João Caram <caram@pucminas.br>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import java.util.ArrayList;
import java.util.List;

/**
  * Classe restaurante: controla todo o processo do LPM Comidinhas Veganas a partir das outras classes. Recebe comandos do app
  e retorna respostas a ele para interação com o usuário.
  */
public class Restaurante {

	private static int MAX_FILA = 1000;
	private static int MAX_MESAS = 10;
	private static int MAX_CLIENTES = 1000;
	private Mesa[] mesas;
	private Cliente[] clientes;
	private List<Requisicao> atendidas;
	private List<Requisicao> espera;
	private Cardapio cardapio;
	private int quantClientes;
	private int quantMesas;
	
	private int requisicoesEmEspera;

	/**
	 * Cria um restaurante conforme a configuração de mesas do enunciado. 
	 */
	public Restaurante() {
		mesas = new Mesa[MAX_MESAS];
		clientes = new Cliente[MAX_CLIENTES];
		atendidas = new ArrayList<>(MAX_FILA);
		espera = new ArrayList<>(MAX_FILA);
		quantMesas = quantClientes =  requisicoesEmEspera = 0;
		cardapio = new Cardapio();
		criarMesas();
	}

	/**
	 * Encapsula a criação de mesas para o restaurante, conforme especificado.
	 */
	private void criarMesas() {
		for (int i = 0; i < 4; i++) {
			mesas[quantMesas] = new Mesa(quantMesas, 4, true);
			quantMesas++;
		}
		for (int i = 0; i < 4; i++) {
			mesas[quantMesas] = new Mesa(quantMesas, 6, true);
			quantMesas++;
		}
		for (int i = 0; i < 2; i++) {
			mesas[quantMesas] = new Mesa(quantMesas, 8, true);
			quantMesas++;
		}
	}

	/**
	 * Adiciona um cliente no cadastro do restaurante, caso ele não seja nulo
	 * @param novo Cliente a ser cadastrado.
	 */
	public void addCliente(Cliente novo) {
		if(novo!=null){
			clientes[quantClientes] = novo;
			quantClientes++;
		}
	}

	/**
	 * Localiza um cliente no restaurante, usando seu id
	 * @param idCli ID do cliente a ser localizado
	 * @return Cliente localizado ou null, se ele não existir
	 */
	public Cliente localizarCliente(int idCli) {
		boolean achou = false;
		Cliente cliente = null;
		for (int i = 0; i < quantClientes && !achou; i++) {
			if (clientes[i].hashCode() == idCli) {
				achou = true;
				cliente = clientes[i];
			}
		}
		return cliente;
	}

	/**
	 * Encapsula o processo de procurar uma mesa disponível para "quantPessoas". Retorna uma mesa disponível para pelo menos esta quantidade
	 * de pessoas (podendo ser mais), ou nulo se não houver mesas para esta quantidade.
	 * @param quantPessoas Quantidade de pessoas a ser atendida na mesa.
	 * @return Mesa para esta quantidade de pessoas ou nulo, se não houver mesa liberada.
	 */
	private Mesa localizarMesaDisponivel(int quantPessoas) {
		Mesa liberada = null;
		for (int i = 0; i < quantMesas && liberada == null; i++) {
			if (mesas[i].mesaPodeSerOcupada(quantPessoas))
				liberada = mesas[i];
		}
		return liberada;
	}

	/**
	 * Encerra um atendimento/requisição, a partir do número da mesa sendo atendida. Retorna a requisição encerrada ou nulo.
	 * @param idMesa Número da mesa que quer encerrar o atendimento
	 * @return A requisição encerrada, se houver, ou nulo caso caso não exista ou já estivesse encerrada.
	 */
	public Requisicao encerrarAtendimento(int idMesa) {
		Requisicao encerrada = 
								atendidas.stream()
										.filter(req -> req.ehDaMesa(idMesa) && !req.estahEncerrada())
										.findFirst()
										.orElse(null);
		if(encerrada!=null)
			encerrada.finalizarReq(null);										
		return encerrada;
	}

	/**
	 * Processa a fila de espera de atendimentos, tentando alocar mesa para uma destas requisições. Só atende uma requisição por vez, a que estiver
	 * mais à frente da fila e puder ser atendida com as mesas do momento.
	 * @return Requisição atendida ou nulo, se nenhuma puder ser atentida.
	 */
	public Requisicao processarFila() {
		Requisicao atendida = null;
		for (int i = 0; i < requisicoesEmEspera && atendida == null; i++) {
			Requisicao requisicao = espera.get(i);
			Mesa mesaLivre = localizarMesaDisponivel(requisicao.getQtdPessoas());
			if (mesaLivre != null) {
				atenderRequisicao(requisicao, mesaLivre);
				retirarDaFila(i);
				atendida = requisicao;
			}
		}
		return atendida;
	}

	/**
	 * Encapsula a retirada da fila após o atendimento de uma requisição
	 * @param pos Posição para retirada da fila. 
	 */
	private void retirarDaFila(int pos) {
		espera.remove(pos);
		requisicoesEmEspera--;
		
	}

	/**
	 * Coloca uma nova requisição na fila de espera, caso ela não seja nula
	 * @param novaRequisicao Requisição para entrar na fila (não deve ser nula)
	 */
	public void registrarRequisicao(Requisicao novaRequisicao) {
		if(novaRequisicao!=null){
			espera.add(novaRequisicao);
			requisicoesEmEspera++;
		}
	}

	/**
	 * Encapsula a chamada de atendimento da requisição para uma mesa
	 * @param requisicao Requisição a ser atendida
	 * @param mesa Mesa a ser alocada.
	 */
	private void atenderRequisicao(Requisicao requisicao, Mesa mesa) {
		requisicao.atribuirMesa(mesa);
		atendidas.add(requisicao);
	}

	/**
	 * Cria um relatório com o estado de cada uma das mesas do restaurante (liberada/ocupada + capacidade)
	 * @return Relatório com informações de todas as mesas do restaurante.
	 */
	public String statusMesas() {
		StringBuilder livres = new StringBuilder("Mesas livres: \n");
		StringBuilder ocupadas = new StringBuilder("Mesas em atendimento: \n");
		for (int i = 0; i < quantMesas; i++) {
			if (mesas[i].isMesaLivre(1)) {
				livres.append(mesas[i] + "\n");
			}
			else {
				ocupadas.append(mesas[i] + "\n");
			}
		}
		return livres.toString()+ocupadas.toString();
	}

	/**
	 * Cria um relatório com o estado de cada uma das requisições em espera do restaurante
	 * 	@return Relatório com informações de todas as requisições em espera.
	 */
	public String filaDeEspera() {
		String resposta = "Fila vazia";
		if (requisicoesEmEspera > 0) {
			StringBuilder emEspera = new StringBuilder("Fila de espera: \n");
			for (Requisicao req : espera) {
					emEspera.append(req+"\n");
			}
			resposta =  emEspera.toString();
		}
		return resposta;
	}

	/**
	 * Acrescenta um produto ao pedido de uma mesa sendo atendida em uma requisição.
	 * O usuário deve informar aqui o código do produto e o número da mesa. 
	 * @param idProduto Id do Produto a ser inserido no pedido da requisição
	 * @param idMesa Id da mesa onde a requisição está sendo atendida
	 * @return TRUE/FALSE conforme tenha sido possível fazer a adição. Possíveis problemas são id de produto inexistente, mesa não está sendo atendida.
	 */
	public Item acrescentarProduto(int idProduto, int idMesa){
		Requisicao req = localizarRequisicaoEmAtendimento(idMesa);
		Item item = cardapio.getItens().get(idProduto);
		if(req!=null && item!=null){
			try{item = req.adicionarItem(item);}
			catch(Exception e){System.out.println("Erro: " + e.getMessage());}
		}

		return item;
	}

	/**
	 * Retorna um cardápio exibível em String. 
	 * @return Cardápio em string, com descrições e preços dos produtos. 
	 */
	public String getCardapio(){
		return cardapio.toString();
	}

	/**
	 * Método privado que localiza a requisição sendo atendida atualmente em uma mesa. Retorna nulo caso não exista requisição nesta mesa no momento.
	 * @param idMesa Id da mesa que deve ser localizada. 
	 * @return A requisição sendo atendida naquela mesa, ou nulo se não houver. 
	 */
	private Requisicao localizarRequisicaoEmAtendimento(int idMesa) {
		return atendidas.stream()
					 .filter(req -> req.ehDaMesa(idMesa) && !req.estahEncerrada())
					 .findFirst()
					 .orElse(null);
	}

}