package com.example.grupo2metodofrances

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.grupo2metodofrances.databinding.ActivityLoginBinding
import com.example.grupo2metodofrances.ui.PaymentPlan
import com.example.grupo2metodofrances.ui.theme.Grupo2metodofrancesTheme
import com.google.firebase.auth.FirebaseAuth
import com.grupo2metodofrances.PlanActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        //BOTÓN INGRESAR

        binding.ingresar.setOnClickListener {
            val email = binding.iniciarSesionCorreo.text.toString()
            val password = binding.iniciarSesionContrasenia.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {

                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Inicio de sesión correcto", Toast.LENGTH_SHORT).show()

                        // ✅ Aquí va tu lógica para el click de ingresar
                        setContent {
                            Grupo2metodofrancesTheme {
                                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                                    PaymentPlan(
                                        _tep = 4.4030651,
                                        _capital = 1440000.00,
                                        _term = 8,
                                        modifier = Modifier.padding(innerPadding)
                                    )
                                }
                            }
                        }

                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()

                    }
                }
            } else {
                Toast.makeText(this, "Se requieren datos", Toast.LENGTH_SHORT).show()

            }
        }

        //BOTÓN CREAR NUEVA CUENTA

        binding.crearNuevaCuenta.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        val signUpLink = findViewById<TextView>(R.id.crearNuevaCuenta)
        signUpLink.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}