<?php
 
/*
 * O seguinte codigo retorna para o cliente a lista de produtos 
 * armazenados no servidor. Essa e uma requisicao do tipo GET. 
 * Devem ser enviados os parâmetro de limit e offset para 
 * realização da paginação de dados no cliente.
 * A resposta e no formato JSON.
 */

// conexão com bd
require_once('conexao_bd_athena.php');

// autenticação
require_once('autenticacao_athena.php');

// array for JSON resposta
$resposta = array();

$codigoPessoa = autenticar($db_con);
// verifica se o usuário conseguiu autenticar
if($codigoPessoa >= 0) {
	
	// Primeiro, verifica-se se todos os parametros foram enviados pelo cliente.
	// limit - quantidade de produtos a ser entregues
	// offset - indica a partir de qual produto começa a lista
	if (isset($_GET['limit']) && isset($_GET['offset'])) {
	 
		$limit = $_GET['limit'];
		$offset = $_GET['offset'];
 
		// Realiza uma consulta ao BD e obtem todos os produtos.
		$consulta = $db_con->prepare("SELECT * FROM livros WHERE codigo_cat ==" . $codigoPessoa . " LIMIT " . $limit . " OFFSET " . $offset );
		if($consulta->execute()) {
			// Caso existam produtos no BD, eles sao armazenados na 
			// chave "produtos". O valor dessa chave e formado por um 
			// array onde cada elemento e um produto.
			$resposta["produtos"] = array();
			$resposta["sucesso"] = 1;

			if ($consulta->rowCount() > 0) {
				while ($linha = $consulta->fetch(PDO::FETCH_ASSOC)) {
					// Para cada produto, sao retornados somente o 
					// pid (id do produto), o nome do produto e o preço. Nao ha necessidade 
					// de retornar nesse momento todos os campos dos produtos 
					// pois a app cliente, inicialmente, so precisa do nome e preço do mesmo para 
					// exibir na lista de produtos. O campo id e usado pela app cliente 
					// para buscar os detalhes de um produto especifico quando o usuario 
					// o seleciona. Esse tipo de estrategia poupa banda de rede, uma vez 
					// os detalhes de um produto somente serao transferidos ao cliente 
					// em caso de real interesse.
					$produto = array();
					$produto["id"] = $linha["id"];
					$produto["nome"] = $linha["nome"];
					$produto["preco"] = $linha["preco"];
			 
					// Adiciona o produto no array de produtos.
					array_push($resposta["produtos"], $produto);
				}
			}
		}
		else {
			// Caso ocorra falha no BD, o cliente 
			// recebe a chave "sucesso" com valor 0. A chave "erro" indica o 
			// motivo da falha.
			$resposta["sucesso"] = 0;
			$resposta["erro"] = "Erro no BD: " . $consulta->error;
		}
	}
	else {
		// Se a requisicao foi feita incorretamente, ou seja, os parametros 
		// nao foram enviados corretamente para o servidor, o cliente 
		// recebe a chave "sucesso" com valor 0. A chave "erro" indica o 
		// motivo da falha.
		$resposta["sucesso"] = 0;
		$resposta["erro"] = "Campo requerido não preenchido";
	}
}
else {
	// senha ou usuario nao confere
	$resposta["sucesso"] = 0;
	$resposta["erro"] = "usuario ou senha não confere";
}

// fecha conexão com o bd
$db_con = null;

// Converte a resposta para o formato JSON.
echo json_encode($resposta);
?>