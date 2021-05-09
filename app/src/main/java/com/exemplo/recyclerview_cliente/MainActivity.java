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

        //Associa os componentes da interface as propriedades
        botaoAdicionar = findViewById(R.id.buttonAdicionar);
        buttonFechar = findViewById(R.id.buttonFechar);

        //Dados para preencher o RecyclerView com
        List<Cliente> listaCliente = new ArrayList();
        listaCliente.add(new Cliente("1","João","123"));
        listaCliente.add(new Cliente("2","Carlos","223"));
        listaCliente.add(new Cliente("3","Pedro","323"));
        listaCliente.add(new Cliente("4","Luiz","534"));

        // configura o RecyclerView
        recyclerView = findViewById(R.id.recyclerViewClientes);
        // Visualização em lista
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Visualização em grid com 2 colunas
        adapter = new RecyclerViewAdapterCliente(this, listaCliente);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Cliente cliente = adapter.getItem(position);
        Toast.makeText(this, "Clique no cliente: " + cliente.getNome() + " linha número: " + position, Toast.LENGTH_SHORT).show();
    }

    /**
     * Evento do botão adicionar cliente
     * @param v
     */
    public void onClickBotaoAdicionar(View v){
        //Recupera o intennt para a tela2
        Intent intent = new Intent(this, MainActivity2.class);
        // Abre a segunda tela
        startActivityForResult(intent, 0);
    }

    /**
     * Evento do botão remover cliente
     *
     * @param v
     */
    public void onClickBotaoRemover(View v) {
        // Retorna a posição da seleção
        int position = recyclerView.getChildAdapterPosition(v);
        // Apaga o elemento da lista da posição
        adapter.removerCliente(position);
    }

    /**
     * Evento do botão fechar
     * @param v
     */
    public void onClickBotaoFechar(View v){

        System.exit(0);
    }

    /**
     * Verifica o resultado do retorno da chamada de um activity.
     * @param requestCode Código da requisição
     * @param resultCode Código de retorno
     * @param data Dados do intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Executa no retorno das telas
        super.onActivityResult(requestCode, resultCode, data);
        //Se o retorno foi Ok
        if (resultCode == RESULT_OK) {
            //Verifica se os dados foram preenchidos
            if (data.hasExtra("clienteId") && data.hasExtra("nome") && data.hasExtra("cpf")) {
                String clienteId = data.getExtras().getString("clienteId");
                String nome = data.getExtras().getString("nome");
                String cpf = data.getExtras().getString("cpf");
                //Adiciona os dados na lsita
                adapter.adicionarCliente(new Cliente(clienteId, nome, cpf));
            }
        }
    }
}
