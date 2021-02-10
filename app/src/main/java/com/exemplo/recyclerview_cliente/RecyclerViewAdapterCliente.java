package com.exemplo.recyclerview_cliente;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapterCliente extends RecyclerView.Adapter<RecyclerViewAdapterCliente.ViewHolderCliente> {

    private List<Cliente> listaClientes;
    private LayoutInflater inflater;
    private ItemClickListener clickListener;

    /**
     * construtor para passar os dados para o RecyclerView de Clientes
     * @param context
     * @param listaClientes
     */
    RecyclerViewAdapterCliente(Context context, List<Cliente> listaClientes) {
        this.inflater = LayoutInflater.from(context);
        this.listaClientes = listaClientes;
    }

    /**
     * Get para lista de clientes
     * @return
     */
    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    /**
     * Set para lista de clientes
     * @param listaClientes
     */
    public void setListaClientes(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    /**
     * infla o layout da linha de xml quando necessário
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public ViewHolderCliente onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Recupera o Activity do item do cliente
        View view = inflater.inflate(R.layout.item_cliente_view, parent, false);
        return new ViewHolderCliente(view);
    }

    /**
     * liga os dados ao TextView em cada linha
     * @param holder
     * @param posicao
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolderCliente holder, int posicao) {
        Cliente cliente = listaClientes.get(posicao);
        holder.textViewCienteId.setText(cliente.getClienteId());
        holder.textViewNome.setText(cliente.getNome());
        holder.textViewCpf.setText(cliente.getCpf());

        holder.buttonExcluir.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                removerCliente(posicao);
            }
        });
    }

    /**
     *  Retorna o total de linhas da lista
     * @return
     */
    @Override
    public int getItemCount() {
        return listaClientes.size();
    }

    /**
     * Adiciona um novo cliente a lista
     * @param novo Um cliente
     */
    public void adicionarCliente(Cliente novo){
        listaClientes.add(novo); //adiciona o item na ultima posicao
        notifyDataSetChanged();// notifica que meus items foi alterado
    }

    /**
     * Remove um cliente da lista pela posição
     * @param posicao Posição do cliente a ser excluído
     */
    public void removerCliente(int posicao){
        listaClientes.remove(posicao); //remove o item na posicao desejada
        notifyDataSetChanged();
    }

    /**
     * Armazena e recicla as visualizações à medida que elas são deslizadas para fora da tela(cima e baixo)
     */
    public class ViewHolderCliente extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewCienteId;
        TextView textViewNome;
        TextView textViewCpf;
        ImageButton buttonExcluir;

        /**
         * Construtor do ViewHolder de cliente.
         * @param itemView
         */
        ViewHolderCliente(View itemView) {
            super(itemView);
            textViewCienteId = itemView.findViewById(R.id.textViewClienteId);
            textViewNome = itemView.findViewById(R.id.textViewNome);
            textViewCpf = itemView.findViewById(R.id.textViewCpf);
            buttonExcluir  = itemView.findViewById(R.id.buttonExcluir);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) {
                clickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    /**
     * Obtêm os dados na posição de clique
     * @param id Posição do item.
     * @return
     */
    Cliente getItem(int id) {
        return listaClientes.get(id);
    }

    /**
     * Permite que os eventos de cliques sejam capturados
     * @param itemClickListener
     */
    void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    /**
     * Interface do método para responder a eventos de cliques
     */
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}
