package com.matheusbatista.modelo;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by matheus.batista on 06/04/2017.
 */

public class ListaPessoasViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    final TextView nome;
    final TextView cpf;
    final TextView telefone;
    final ConstraintLayout layout;
    final Button btnDeletar;
    public MyViewHolderClickListener listener;

    public ListaPessoasViewHolder(View view) {
        super(view);
        nome = (TextView) view.findViewById(R.id.main_line_nome);
        cpf = (TextView) view.findViewById(R.id.main_line_cpf);
        telefone = (TextView) view.findViewById(R.id.main_line_telefone);
        layout = (ConstraintLayout) view.findViewById(R.id.main_line_item);
        btnDeletar = (Button) view.findViewById(R.id.btnDeletar);

        layout.setOnClickListener(this);
    }

    public void setCustomOnClickListener(MyViewHolderClickListener listener  ){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
//        Log.i("ATENÇÃO", "Nome: " + nome.getText() + "| CPF: " + cpf.getText() + "| Telefone: " + telefone.getText());


        if( listener!= null ){
            switch (v.getId()) {
                /*case R.id.main_line_nome:

                    listener.onTextViewNameClick(v, getAdapterPosition());
                    Log.i("ATENÇÃO!", "CLIQUEI NO NOME");
                    break;
                case R.id.main_line_cpf:
                    listener.onTextViewNameClick(v, getAdapterPosition());
                    Log.i("ATENÇÃO!", "CLIQUEI NO CPF");
                    break;
                case R.id.main_line_telefone:
                    listener.onTextViewNameClick(v, getAdapterPosition());
                    Log.i("ATENÇÃO!", "CLIQUEI NO TELEFONE");
                    break;*/
                case R.id.main_line_item:
                    listener.onTextViewNameClick(nome.getText().toString(), cpf.getText().toString(), telefone.getText().toString());
                default:
                    break;
            }

        }
    }

    public static interface MyViewHolderClickListener{

        public void onTextViewNameClick(String nome, String cpf, String telefone);
        public void onTextViewRollClick(View view, int position);
    }
}
