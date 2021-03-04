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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Associa os componentes aos atributos
        editTextClienteId = findViewById(R.id.editTextClienteId);
        editTextNome = findViewById(R.id.editTextNome);
        editTextCpf = findViewById(R.id.editTextCpf);
    }

    public void onClickBotaoSalvar(View v) {
        //Retorna os dados do cliente
        Intent data = new Intent();
        Bundle parms = new Bundle();
        //Se o id do cliente é vazio retorna cancelado
        if (editTextClienteId.getText().toString().equals("")) {
            setResult(RESULT_CANCELED);
        } else {
            //Dados preenchido
            parms.putString("clienteId", editTextClienteId.getText().toString());
            parms.putString("nome", editTextNome.getText().toString());
            parms.putString("cpf", editTextCpf.getText().toString());
            data.putExtras(parms);
            setResult(RESULT_OK, data);
        }
        //Fecha a janela
        finish();
    }

    public void onClickBotaoVoltar(View v) {
        setResult(RESULT_CANCELED);
        finish();
    }
}