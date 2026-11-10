package me.andresdavid.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.firebase.firestore.FirebaseFirestore
import me.andresdavid.myapplication.navigation.AppNavigation
import me.andresdavid.myapplication.ui.theme.SelunaProyectoTheme

class MainActivity : ComponentActivity() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        guardarDatos()

        setContent {
            SelunaProyectoTheme {
                AppNavigation()
            }
        }
    }

    private fun guardarDatos() {
        val usuario = hashMapOf(
            "nombre" to "Juan",
            "correo" to "juan@gmail.com",
            "edad" to 22,
        )

        db.collection("usuarios")
            .add(usuario)
            .addOnSuccessListener { documentReference ->
                Log.d(
                    "FIREBASE",
                    "Documento guardado: ${documentReference.id}"
                )
            }
            .addOnFailureListener { exception ->
                Log.e(
                    "FIREBASE",
                    "Error al guardar",
                    exception
                )
            }
    }
}
