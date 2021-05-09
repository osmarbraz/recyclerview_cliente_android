package com.exemplo.recyclerview_cliente;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Tela de cadastro de cliente.
 */

public class MainActivity2 extends AppCompatActivity {

    private TextView editTextClienteId;
    private TextView editTextNome;
    private TextView editTextCpf;
    private int posicao = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Associa os componentes aos atributos
        editTextClienteId = findViewById(R.id.editTextClienteId);
        editTextNome = findViewById(R.id.editTextNome);
        editTextCpf = findViewById(R.id.editTextCpf);

        //Recupera o intent
        if (getIntent().getExtras() != null  && getIntent().hasExtra("cliente")) {
            //Recupera o valor
            Cliente cliente = (Cliente)getIntent().getExtras().get("cliente");
            //Recupera a posição da alteração na lista.
            posicao = getIntent().getExtras().getInt("posicao");
            //Coloca o valor na caixa de texto.
            editTextClienteId.setText(cliente.getClienteId());
            editTextNome.setText(cliente.getNome());
            editTextCpf.setText(cliente.getCpf());
        } else {
            editTextClienteId.setText("");
            editTextNome.setText("");
            editTextCpf.setText("");
        }
    }

    public void onClickBotaoSalvar(View v) {
        //Retorna os dados do cliente
        Intent intent = new Intent();
        //Se o id do cliente é vazio retorna cancelado
        if (editTextClienteId.getText().toString().equals("")) {
            setResult(RESULT_CANCELED);
        } else {
            // Dados preenchido
            intent.putExtra("posicao", posicao);
            // Recupera os dados para alteração
            String clienteId = editTextClienteId.getText().toString();
            String nome =  editTextNome.getText().toString();
            String cpf = editTextCpf.getText().toString();
            intent.putExtra("cliente", new Cliente(clienteId, nome, cpf));
            setResult(RESULT_OK, intent);
        }
        //Fecha a janela
        finish();
    }

    public void onClickBotaoVoltar(View v) {
        setResult(RESULT_CANCELED);
        finish();
    }
}