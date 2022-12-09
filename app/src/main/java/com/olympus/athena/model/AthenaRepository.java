package com.olympus.athena.model;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.olympus.athena.util.Config;
import com.olympus.athena.util.HttpRequest;
import com.olympus.athena.util.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AthenaRepository {
    Context context;
    public AthenaRepository(Context context) {
        this.context = context;
    }

    public Integer login(String email, String password) {

        // Cria uma requisição HTTP a adiona o parâmetros que devem ser enviados ao servidor
        HttpRequest httpRequest = new HttpRequest(Config.PRODUCTS_APP_URL + "login.php", "POST", "UTF-8");
        httpRequest.setBasicAuth(email, password);

        String result = "";
        try {
            // Executa a requisição HTTP. É neste momento que o servidor web é contactado. Ao executar
            // a requisição é aberto um fluxo de dados entre o servidor e a app (InputStream is).
            InputStream is = httpRequest.execute();

            // Obtém a resposta fornecida pelo servidor. O InputStream é convertido em uma String. Essa
            // String é a resposta do servidor web em formato JSON.
            //
            // Em caso de sucesso, será retornada uma String JSON no formato:
            //
            // {"sucesso":1}
            //
            // Em caso de falha, será retornada uma String JSON no formato:
            //
            // {"sucesso":0, "erro":"usuario ou senha não confere"}
            result = Util.inputStream2String(is, "UTF-8");

            // Fecha a conexão com o servidor web.
            httpRequest.finish();

            Log.i("HTTP LOGIN RESULT", result);

            // A classe JSONObject recebe como parâmetro do construtor uma String no formato JSON e
            // monta internamente uma estrutura de dados similar ao dicionário em python.
            JSONObject jsonObject = new JSONObject(result);

            // obtem o valor da chave sucesso para verificar se a ação ocorreu da forma esperada ou não.
            int success = jsonObject.getInt("sucesso");

            // Se sucesso igual a 1, significa que o usuário foi autenticado com sucesso.
            if(success == 1 ) {
                return jsonObject.getInt("codigo_pessoa");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("HTTP RESULT", result);
        }
        return -1;
    }

    /**
     * Método que cria uma requisição HTTP para obter uma página/bloco de produtos junto ao servidor web.
     * @param limit a quantidade de produtos a serem obtidos
     * @param offSet a posição a partir da qual a página de produtos deve começar
     * @return lista de produtos
     */
    public List<Categoria> loadCat(Integer limit, Integer offSet) {

        // cria a lista de produtos incicialmente vazia, que será retornada como resultado
        List<Categoria> categoriaList = new ArrayList<>();

        // Para obter a lista de produtos é preciso estar logado. Então primeiro otemos o login e senha
        // salvos na app.
        String email = Config.getLogin(context);
        String password = Config.getPassword(context);

        // Cria uma requisição HTTP a adiona o parâmetros que devem ser enviados ao servidor
        HttpRequest httpRequest = new HttpRequest(Config.PRODUCTS_APP_URL +"pegar_categorias.php", "GET", "UTF-8");
        httpRequest.addParam("limit", limit.toString());
        httpRequest.addParam("offset", offSet.toString());

        // Para esta ação, é preciso estar logado. Então na requisição HTTP setamos o login e senha do
        // usuário. Ao executar a requisição, o login e senha do usuário serão enviados ao servidor web,
        // o qual verificará se o login e senha batem com aquilo que está no BD. Somente depois dessa
        // verificação de autenticação é que o servidor web irá realizar esta ação.
        httpRequest.setBasicAuth(email, password);

        String result = "";
        try {
            // Executa a requisição HTTP. É neste momento que o servidor web é contactado. Ao executar
            // a requisição é aberto um fluxo de dados entre o servidor e a app (InputStream is).
            InputStream is = httpRequest.execute();

            result = Util.inputStream2String(is, "UTF-8");

            // Fecha a conexão com o servidor web.
            httpRequest.finish();

            Log.i("HTTP CATEGORIA RESULT", result);

            // A classe JSONObject recebe como parâmetro do construtor uma String no formato JSON e
            // monta internamente uma estrutura de dados similar ao dicionário em python.
            JSONObject jsonObject = new JSONObject(result);

            // obtem o valor da chave sucesso para verificar se a ação ocorreu da forma esperada ou não.
            int success = jsonObject.getInt("sucesso");

            // Se sucesso igual a 1, os produtos são obtidos da String JSON e adicionados à lista de
            // produtos a ser retornada como resultado.
            if(success == 1) {

                // A chave produtos é um array de objetos do tipo json (JSONArray), onde cada um desses representa
                // um produto
                JSONArray jsonArray = jsonObject.getJSONArray("categorias");

                // Cada elemento do JSONArray é um JSONObject que guarda os dados de um produto
                for(int i = 0; i < jsonArray.length(); i++) {

                    // Obtemos o JSONObject referente a um produto
                    JSONObject jCategoria = jsonArray.getJSONObject(i);

                    // Obtemos os dados de um produtos via JSONObject
                    String codigoCategoria = jCategoria.getString("codigo_categoria");
                    String dsc_categoria = jCategoria.getString("dsc_categoria");

                    // Criamo um objeto do tipo Product para guardar esses dados
                    Categoria cat = new Categoria(codigoCategoria, dsc_categoria);


                    // Adicionamos o objeto product na lista de produtos
                    categoriaList.add(cat);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("HTTP RESULT", result);
        }

        return categoriaList;
    }

    /**
     * Método que cria uma requisição HTTP para obter uma página/bloco de produtos junto ao servidor web.
     * @param limit a quantidade de produtos a serem obtidos
     * @param offSet a posição a partir da qual a página de produtos deve começar
     * @return lista de produtos
     */
    public List<Livro> loadBooks(Integer limit, Integer offSet, String idCat) {

        // cria a lista de produtos incicialmente vazia, que será retornada como resultado
        List<Livro> livrosList = new ArrayList<>();

        // Para obter a lista de produtos é preciso estar logado. Então primeiro otemos o login e senha
        // salvos na app.
        String email = Config.getLogin(context);
        String password = Config.getPassword(context);

        // Cria uma requisição HTTP a adiona o parâmetros que devem ser enviados ao servidor
        HttpRequest httpRequest = new HttpRequest(Config.PRODUCTS_APP_URL +"pegar_livros.php", "GET", "UTF-8");
        httpRequest.addParam("limit", limit.toString());
        httpRequest.addParam("offset", offSet.toString());
        httpRequest.addParam("idCat", idCat);

        // Para esta ação, é preciso estar logado. Então na requisição HTTP setamos o login e senha do
        // usuário. Ao executar a requisição, o login e senha do usuário serão enviados ao servidor web,
        // o qual verificará se o login e senha batem com aquilo que está no BD. Somente depois dessa
        // verificação de autenticação é que o servidor web irá realizar esta ação.
        httpRequest.setBasicAuth(email, password);

        String result = "";
        try {
            // Executa a requisição HTTP. É neste momento que o servidor web é contactado. Ao executar
            // a requisição é aberto um fluxo de dados entre o servidor e a app (InputStream is).
            InputStream is = httpRequest.execute();

            result = Util.inputStream2String(is, "UTF-8");

            // Fecha a conexão com o servidor web.
            httpRequest.finish();

            Log.i("HTTP LIVROS RESULT", result);

            // A classe JSONObject recebe como parâmetro do construtor uma String no formato JSON e
            // monta internamente uma estrutura de dados similar ao dicionário em python.
            JSONObject jsonObject = new JSONObject(result);

            // obtem o valor da chave sucesso para verificar se a ação ocorreu da forma esperada ou não.
            int success = jsonObject.getInt("sucesso");

            // Se sucesso igual a 1, os produtos são obtidos da String JSON e adicionados à lista de
            // produtos a ser retornada como resultado.
            if(success == 1) {

                // A chave produtos é um array de objetos do tipo json (JSONArray), onde cada um desses representa
                // um produto
                JSONArray jsonArray = jsonObject.getJSONArray("livros");

                // Cada elemento do JSONArray é um JSONObject que guarda os dados de um produto
                for(int i = 0; i < jsonArray.length(); i++) {

                    // Obtemos o JSONObject referente a um produto
                    JSONObject jLivro = jsonArray.getJSONObject(i);

                    // Obtemos os dados de um produtos via JSONObject
                    String codigo_livro = jLivro.getString("codigo_livro");
                    String titulo = jLivro.getString("titulo");
                    String autor = jLivro.getString("autor");
                    String sinopse = jLivro.getString("sinopse");
                    String nota = jLivro.getString("nota");

                    // Criamo um objeto do tipo Product para guardar esses dados
                    Livro livro = new Livro();
                    livro.codigoLivro = codigo_livro;
                    livro.titulo = titulo;
                    livro.autor = autor;
                    livro.sinopse = sinopse;
                    livro.nota = nota;

                    // Adicionamos o objeto product na lista de produtos
                    livrosList.add(livro);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("HTTP RESULT", result);
        }

        return livrosList;
    }

    public List<Livro> loadFavBooks(Integer limit, Integer offSet) {

        // cria a lista de produtos incicialmente vazia, que será retornada como resultado
        List<Livro> livrosList = new ArrayList<>();

        // Para obter a lista de produtos é preciso estar logado. Então primeiro otemos o login e senha
        // salvos na app.
        String email = Config.getLogin(context);
        String password = Config.getPassword(context);

        // Cria uma requisição HTTP a adiona o parâmetros que devem ser enviados ao servidor
        HttpRequest httpRequest = new HttpRequest(Config.PRODUCTS_APP_URL +"pegar_favoritos.php", "GET", "UTF-8");
        httpRequest.addParam("limit", limit.toString());
        httpRequest.addParam("offset", offSet.toString());

        // Para esta ação, é preciso estar logado. Então na requisição HTTP setamos o login e senha do
        // usuário. Ao executar a requisição, o login e senha do usuário serão enviados ao servidor web,
        // o qual verificará se o login e senha batem com aquilo que está no BD. Somente depois dessa
        // verificação de autenticação é que o servidor web irá realizar esta ação.
        httpRequest.setBasicAuth(email, password);

        String result = "";
        try {
            // Executa a requisição HTTP. É neste momento que o servidor web é contactado. Ao executar
            // a requisição é aberto um fluxo de dados entre o servidor e a app (InputStream is).
            InputStream is = httpRequest.execute();

            result = Util.inputStream2String(is, "UTF-8");

            // Fecha a conexão com o servidor web.
            httpRequest.finish();

            Log.i("HTTP FAVORITOS RESULT", result);

            // A classe JSONObject recebe como parâmetro do construtor uma String no formato JSON e
            // monta internamente uma estrutura de dados similar ao dicionário em python.
            JSONObject jsonObject = new JSONObject(result);

            // obtem o valor da chave sucesso para verificar se a ação ocorreu da forma esperada ou não.
            int success = jsonObject.getInt("sucesso");

            // Se sucesso igual a 1, os produtos são obtidos da String JSON e adicionados à lista de
            // produtos a ser retornada como resultado.
            if(success == 1) {

                // A chave produtos é um array de objetos do tipo json (JSONArray), onde cada um desses representa
                // um produto
                JSONArray jsonArray = jsonObject.getJSONArray("livros");

                // Cada elemento do JSONArray é um JSONObject que guarda os dados de um produto
                for(int i = 0; i < jsonArray.length(); i++) {

                    // Obtemos o JSONObject referente a um produto
                    JSONObject jLivro = jsonArray.getJSONObject(i);

                    // Obtemos os dados de um produtos via JSONObject
                    String codigo_livro = jLivro.getString("codigo_livro");
                    String titulo = jLivro.getString("titulo");
                    String autor = jLivro.getString("autor");
                    String sinopse = jLivro.getString("sinopse");
                    String nota = jLivro.getString("nota");

                    // Criamo um objeto do tipo Product para guardar esses dados
                    Livro livro = new Livro();
                    livro.codigoLivro = codigo_livro;
                    livro.titulo = titulo;
                    livro.autor = autor;
                    livro.sinopse = sinopse;
                    livro.nota = nota;

                    // Adicionamos o objeto product na lista de produtos
                    livrosList.add(livro);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("HTTP RESULT", result);
        }

        return livrosList;
    }


    /**
     * Método que cria uma requisição HTTP para obter os detalhes de um produto junto ao servidor web.
     * @param id id do produto que se deseja obter os detalhes
     * @return objeto do tipo product contendo os detalhes do produto
     */
    public Livro loadBookDetail(String id) {

        // Para obter a lista de produtos é preciso estar logado. Então primeiro otemos o login e senha
        // salvos na app.
        String email = Config.getLogin(context);
        String password = Config.getPassword(context);

        // Cria uma requisição HTTP a adiona o parâmetros que devem ser enviados ao servidor
        HttpRequest httpRequest = new HttpRequest(Config.PRODUCTS_APP_URL + "pegar_info_livro.php", "GET", "UTF-8");
        httpRequest.addParam("id", id);

        // Para esta ação, é preciso estar logado. Então na requisição HTTP setamos o login e senha do
        // usuário. Ao executar a requisição, o login e senha do usuário serão enviados ao servidor web,
        // o qual verificará se o login e senha batem com aquilo que está no BD. Somente depois dessa
        // verificação de autenticação é que o servidor web irá realizar esta ação.
        httpRequest.setBasicAuth(email, password);

        String result = "";
        try {
            // Executa a requisição HTTP. É neste momento que o servidor web é contactado. Ao executar
            // a requisição é aberto um fluxo de dados entre o servidor e a app (InputStream is).
            InputStream is = httpRequest.execute();

            // Obtém a resposta fornecida pelo servidor. O InputStream é convertido em uma String. Essa
            // String é a resposta do servidor web em formato JSON.
            //
            // Em caso de sucesso, será retornada uma String JSON no formato:
            //
            // {"sucesso":1,"nome":"produto 1","preco":"10.00","descricao":"produto 1","criado_em":"2022-10-03 19:43:31.42905","criado_por":"daniel"}
            //
            // Em caso de falha, será retornada uma String JSON no formato:
            //
            // {"sucesso":0,"erro":"Erro ao obter detalhes do produto"}
            result = Util.inputStream2String(is, "UTF-8");

            // Fecha a conexão com o servidor web.
            httpRequest.finish();

            Log.i("HTTP DETAILS RESULT", result);

            // A classe JSONObject recebe como parâmetro do construtor uma String no formato JSON e
            // monta internamente uma estrutura de dados similar ao dicionário em python.
            JSONObject jsonObject = new JSONObject(result);

            // obtem o valor da chave sucesso para verificar se a ação ocorreu da forma esperada ou não.
            int success = jsonObject.getInt("sucesso");

            // Se sucesso igual a 1, os detalhes do produto são obtidos da String JSON e um objeto
            // do tipo Product é criado para guardar esses dados
            if(success == 1) {

                // obtém os dados detalhados do produto. A imagem não vem junto. Ela é obtida
                // separadamente depois, no momento em que precisa ser exibida na app. Isso permite
                // que os dados trafeguem mais rápido.
                String titulo = jsonObject.getString("titulo");
                String autor = jsonObject.getString("autor");
                String data_publicacao = jsonObject.getString("data_publicacao");
                String ISBN = jsonObject.getString("ISBN");
                String sinopse = jsonObject.getString("sinopse");
                String edicao = jsonObject.getString("edicao");
                String volume = jsonObject.getString("volume");
                String qtd_paginas = jsonObject.getString("qtd_paginas");
                String editora = jsonObject.getString("editora");
                String categoria = jsonObject.getString("categoria");
                String nota = jsonObject.getString("nota");

                // Cria um objeto livro e guarda os detalhes do produto dentro dele.
                Livro livro = new Livro();
                livro.codigoLivro = id;
                livro.titulo = titulo;
                livro.autor = autor;
                livro.editora = editora;
                livro.dataPublicacao = data_publicacao;
                livro.ISBN = ISBN;
                livro.sinopse = sinopse;
                livro.edicao = edicao;
                livro.volume = volume;
                livro.qtd_paginas = qtd_paginas;
                livro.categoria = categoria;
                livro.nota = nota;

                return livro;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
