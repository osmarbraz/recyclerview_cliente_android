package com.exemplo.recyclerview_cliente;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Tela principal.
 */

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapterCliente.ItemClickListener {

    private Button botaoAdicionar;
    private Button buttonFechar;
    private RecyclerViewAdapterCliente adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Associa os componentes da interface as propriedades
        botaoAdicionar = findViewById(R.id.buttonAdicionar);
        buttonFechar = findViewById(R.id.buttonFechar);

        // Dados para preencher o RecyclerView com
        List<Cliente> listaCliente = new ArrayList();
        listaCliente.add(new Cliente("1","João","123"));
        listaCliente.add(new Cliente("2","Carlos","223"));
        listaCliente.add(new Cliente("3","Pedro","323"));
        listaCliente.add(new Cliente("4","Luiz","534"));

        // Configura o RecyclerView
        recyclerView = findViewById(R.id.recyclerViewClientes);
        // Visualização em lista
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Instancia o Adaptador de Cliente
        adapter = new RecyclerViewAdapterCliente(this, listaCliente);
        // Seta o Listener do adaptador
        adapter.setClickListener(this);
        // Seta o Adaptador do Recycler View
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int posicao) {
        // Recupera o objeto da posição
        Cliente cliente = adapter.getItem(posicao);
        // Mostra a mensagem do objeto selecionado
        Toast.makeText(this, "Clique no cliente: " + cliente.getNome() + " linha número: " + posicao, Toast.LENGTH_SHORT).show();
    }

    /**
     * Evento do botão adicionar cliente
     * @param v
     */
    public void onClickBotaoAdicionar(View v){
        // Recupera o intent para a tela2
        Intent intent = new Intent(this, MainActivity2.class);
        // Abre a segunda tela
        startActivityForResult(intent, 0);
    }

    /**
     * Verifica o resultado do retorno da chamada de um activity.
     * @param requestCode Código da requisição
     * @param resultCode Código de retorno
     * @param intent Dados do intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        // Executa no retorno das telas
        super.onActivityResult(requestCode, resultCode, intent);
        // Se o retorno foi Ok
        if (resultCode == RESULT_OK) {
            // Verifica se os dados foram preenchidos
            if (intent.hasExtra("cliente")) {
                int posicao = intent.getExtras().getInt("posicao");
                Cliente cliente = (Cliente)intent.getExtras().get("cliente");
                // Se é um novo Cliente
                if (posicao == -1) {
                    // Adiciona os dados na lista
                    adapter.adicionarCliente(cliente);
                } else {
                    // Altera os dados na lista
                    adapter.alterarCliente(posicao, cliente);
                }
            }
        }
    }

    /**
     * Evento do botão fechar
     * @param v
     */
    public void onClickBotaoFechar(View v){

        System.exit(0);
    }
}
