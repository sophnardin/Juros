package com.example.juros

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun calcularSimples(view: View) {
        val aa = findViewById<RadioButton>(R.id.taxaAA)
        val edtfinanciamento = findViewById<EditText>(R.id.edtValorFi)
        val edttaxa = findViewById<EditText>(R.id.edtTaxa)
        val edttempo = findViewById<EditText>(R.id.edtTotal)
        val financiamento = edtfinanciamento?.text.toString().toDouble()
        var taxa = edttaxa?.text.toString().toDouble()
        val tempo = edttempo?.text.toString().toDouble()
        var calcmont = 0.0
        var calcvalor = 0.0
        var calcparcela = 0.0
        if (aa.isChecked) {
            taxa=taxa / 12.0/100.0
            Toast.makeText(this,"$taxa",LENGTH_SHORT)
            calcmont = financiamento + (financiamento * taxa * tempo)
            calcvalor = calcmont - financiamento
            calcparcela = calcmont / tempo
        } else {
            taxa=taxa/100.0
            Toast.makeText(this,"$taxa",LENGTH_SHORT)
            calcmont = financiamento + (financiamento * taxa * tempo)
            calcvalor = calcmont - financiamento
            calcparcela = calcmont / tempo
        }

        val intent = Intent(this, ResultActivity::class.java).apply {
            putExtra("calcmont", calcmont)
            putExtra("calcvalor", calcvalor)
            putExtra("calcparcela", calcparcela)
        }
        startActivity(intent)
    }
    fun calcularComposto(view: View) {
        val aa = findViewById<RadioButton>(R.id.taxaAA)
        val editfinanciamento = findViewById<EditText>(R.id.edtValorFi)
        val edittaxa = findViewById<EditText>(R.id.edtTaxa)
        val edittempo = findViewById<EditText>(R.id.edtTotal)
        val financiamento = editfinanciamento?.text.toString().toDouble()
        var taxa = edittaxa?.text.toString().toDouble()
        val tempo = edittempo?.text.toString().toDouble()
        var calcmont = 0.0
        var calcvalor = 0.0
        var calcparcela = 0.0
        if (aa.isChecked){
            taxa = taxa/100.0
            taxa = ((1+taxa).pow(1 / 12.0))-1
           Toast.makeText(this,"$taxa",LENGTH_SHORT).show()
            calcmont = financiamento * (1+taxa).pow(tempo)
            calcvalor = calcmont - financiamento
            calcparcela = calcmont / tempo
        }
        else {
            taxa = taxa/100.0
            Toast.makeText(this,"$taxa",LENGTH_SHORT).show()
            val taxa1=(1.0+taxa).pow(tempo)-1
            calcmont = financiamento * (1+taxa).pow(tempo)
            calcvalor = calcmont - financiamento
            calcparcela = calcmont / tempo
        }
        val intent = Intent(this, ResultActivity::class.java).apply {
            putExtra("calcmont", calcmont)
            putExtra("calcvalor", calcvalor)
            putExtra("calcparcela", calcparcela)
        }
        startActivity(intent)
    }
}