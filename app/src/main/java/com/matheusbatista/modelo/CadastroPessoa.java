package com.matheusbatista.modelo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class CadastroPessoa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pessoa);
        iniciarComponentesDaTela();

    }

    private void iniciarComponentesDaTela()
    {
        final TextView txtNomePessoa = (TextView) findViewById(R.id.txtNomePessoa);
        final TextView txtCpf = (TextView) findViewById(R.id.txtCpf);
        final TextView txtTelefone = (TextView) findViewById(R.id.txtTelefonePessoa);

        Button btnSalvar = (Button) findViewById(R.id.btnSalvar);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Boolean podeSalvar = true;

                if (TextUtils.isEmpty(txtNomePessoa.getText()))
                {
                    Toast.makeText(CadastroPessoa.this, "Preencha o nome da pessoa", Toast.LENGTH_LONG).show();
                    podeSalvar = false;
                }

                if (TextUtils.isEmpty(txtCpf.getText()))
                {
                    Toast.makeText(CadastroPessoa.this, "Preencha o CPF da pessoa", Toast.LENGTH_LONG).show();
                    podeSalvar = false;
                }

                if (TextUtils.isEmpty(txtCpf.getText()))
                {
                    Toast.makeText(CadastroPessoa.this, "Preencha o telefone da pessoa", Toast.LENGTH_LONG).show();
                    podeSalvar = false;
                }

                if (podeSalvar)
                {
                    PessoaBanco bdPessoa = new PessoaBanco(CadastroPessoa.this);
                    String resultado;

                    resultado = bdPessoa.inserePessoa(txtNomePessoa.getText().toString(), txtCpf.getText().toString(), txtTelefone.getText().toString());

                    Toast.makeText(CadastroPessoa.this, resultado, Toast.LENGTH_LONG).show();
                    finish();
                }
                else
                {
                    Toast.makeText(CadastroPessoa.this, "Preencha as informações corretamente!", Toast.LENGTH_LONG).show();
                }

            }
        });

        Button btnCancelar = (Button) findViewById(R.id.btnCancelar);
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
