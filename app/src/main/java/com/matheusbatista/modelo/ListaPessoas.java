package com.matheusbatista.modelo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.List;

/**
 * Created by matheus.batista on 06/04/2017.
 */

public class ListaPessoas extends RecyclerView.Adapter {

    private List<Pessoa> pessoas;
    private Context context;
    ListaPessoasViewHolder.MyViewHolderClickListener myViewHolderClickListener;
    private IAtualizaLista mCallback;

    public ListaPessoas(List<Pessoa> pessoas, Context context)
    {
        this.pessoas = pessoas;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_lista, parent, false);

        ListaPessoasViewHolder holder = new ListaPessoasViewHolder(view);

        try {
            mCallback = (IAtualizaLista) parent.getContext();
        }
        catch (ClassCastException e) {
            Log.d("MyDialog", "Activity doesn't implement the ISelectedData interface");
        }

        holder.setCustomOnClickListener(myViewHolderClickListener);

        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, int position) {
        final ListaPessoasViewHolder holder = (ListaPessoasViewHolder) viewHolder;

        Pessoa pessoa  = pessoas.get(position) ;

        holder.nome.setText(pessoa.getNome().toString());
        holder.nome.setTag(pessoa.getId());
        holder.cpf.setText(pessoa.getCpf().toString());
        holder.telefone.setText(pessoa.getTelefone().toString());

        holder.btnDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PessoaBanco bd = new PessoaBanco(context);
                bd.deletaRegistro((int) holder.nome.getTag());
                mCallback.onAtualizarLista();
                notifyDataSetChanged();
           }
        });

    }

    @Override
    public int getItemCount() {
        return pessoas.size();
    }

    public void setMyViewHolderClickListener(ListaPessoasViewHolder.MyViewHolderClickListener listener){
        this.myViewHolderClickListener = listener;
    }


}
