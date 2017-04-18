package com.matheusbatista.modelo;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by matheus.batista on 10/04/2017.
 */

public class Cadastro extends DialogFragment {

    private IAtualizaLista mCallback;
    private static String nomePessoa;
    private static String cpfPessoa;
    private static String telefonePessoa;

    public Cadastro()
    {

    }

    public static Cadastro newInstance(String nome, String cpf, String telefone)
    {
        nomePessoa = nome;
        cpfPessoa = cpf;
        telefonePessoa = telefone;
        return newInstance("title");
    }

    public static Cadastro newInstance(String title)
    {
        Cadastro frag = new Cadastro();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_cadastro_pessoa, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get field from view

        // Fetch arguments from bundle and set title
        String title = getArguments().getString("title", "Enter Name");
        getDialog().setTitle(title);
        // Show soft keyboard automatically and request focus to field

        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        iniciarComponentesDaTela(view);
    }

    private void iniciarComponentesDaTela(View view)
    {
        final TextView txtNomePessoa = (TextView) view.findViewById(R.id.txtNomePessoa);
        final TextView txtCpf = (TextView) view.findViewById(R.id.txtCpf);
        final TextView txtTelefone = (TextView) view.findViewById(R.id.txtTelefonePessoa);

        txtNomePessoa.setText(nomePessoa);
        txtCpf.setText(cpfPessoa);
        txtTelefone.setText(telefonePessoa);

        try {
            mCallback = (IAtualizaLista) getActivity();
        }
        catch (ClassCastException e) {
            Log.d("MyDialog", "Activity doesn't implement the ISelectedData interface");
        }

        txtNomePessoa.requestFocus();

        Button btnSalvar = (Button) view.findViewById(R.id.btnSalvar);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Boolean podeSalvar = true;

                if (TextUtils.isEmpty(txtNomePessoa.getText()))
                {
                    Toast.makeText(getActivity(), "Preencha o nome da pessoa", Toast.LENGTH_LONG).show();
                    podeSalvar = false;
                }

                if (TextUtils.isEmpty(txtCpf.getText()))
                {
                    Toast.makeText(getActivity(), "Preencha o CPF da pessoa", Toast.LENGTH_LONG).show();
                    podeSalvar = false;
                }

                if (TextUtils.isEmpty(txtCpf.getText()))
                {
                    Toast.makeText(getActivity(), "Preencha o telefone da pessoa", Toast.LENGTH_LONG).show();
                    podeSalvar = false;
                }

                if (podeSalvar)
                {
                    PessoaBanco bdPessoa = new PessoaBanco(getActivity());
                    String resultado;

                    resultado = bdPessoa.inserePessoa(txtNomePessoa.getText().toString(), txtCpf.getText().toString(), txtTelefone.getText().toString());

                    Toast.makeText(getActivity(), resultado, Toast.LENGTH_LONG).show();
                    dismiss();
                    mCallback.onAtualizarLista();
                }
                else
                {
                    Toast.makeText(getActivity(), "Preencha as informações corretamente!", Toast.LENGTH_LONG).show();
                }

            }
        });

        Button btnCancelar = (Button) view.findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtNomePessoa.setText("");
                txtCpf.setText("");
                txtTelefone.setText("");

            }
        });

    }

}
