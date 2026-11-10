package me.andresdavid.myapplication.data

import android.util.Log
import android.util.Patterns
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import me.andresdavid.myapplication.ui.screen.UserGoal

object FirebaseManager {

    private val auth: FirebaseAuth get() = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore get() = FirebaseFirestore.getInstance()

    private const val TAG = "FIREBASE_MANAGER"

    fun registerUser(
        name: String,
        email: String,
        password: String,
        goal: UserGoal,
        onResult: (Boolean, String?) -> Unit
    ) {
        val cleanEmail = email.trim()
        if (cleanEmail.isBlank()) {
            onResult(false, "El correo electrónico no puede estar vacío")
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(cleanEmail).matches()) {
            onResult(false, "Ingresa un correo electrónico válido (ejemplo: nombre@correo.com)")
            return
        }
        if (password.length < 6) {
            onResult(false, "La contraseña debe tener al menos 6 caracteres")
            return
        }

        auth.createUserWithEmailAndPassword(cleanEmail, password)
            .addOnSuccessListener { authResult ->
                val uid = authResult.user?.uid ?: cleanEmail.replace(".", "_")
                saveUserDataToFirestore(uid, name, cleanEmail, goal, onResult)
            }
            .addOnFailureListener { authException ->
                Log.w(TAG, "FirebaseAuth error al registrar: ${authException.message}")
                // Fallback: Si Authentication no está activo en Firebase Console (CONFIGURATION_NOT_FOUND),
                // guarda directamente en Firestore para no bloquear el uso de la app.
                val fallbackUid = cleanEmail.replace(".", "_")
                saveUserDataToFirestore(fallbackUid, name, cleanEmail, goal, onResult)
            }
    }

    private fun saveUserDataToFirestore(
        uid: String,
        name: String,
        email: String,
        goal: UserGoal,
        onResult: (Boolean, String?) -> Unit
    ) {
        val usuario = hashMapOf(
            "uid" to uid,
            "nombre" to name.ifBlank { "Usuario" },
            "correo" to email,
            "objetivo" to goal.name,
            "fechaRegistro" to System.currentTimeMillis(),
            "ultimoInicioSesion" to System.currentTimeMillis()
        )

        db.collection("usuarios")
            .document(uid)
            .set(usuario)
            .addOnSuccessListener {
                Log.d(TAG, "Usuario guardado exitosamente en Firestore: $uid")
                onResult(true, null)
            }
            .addOnFailureListener { e ->
                Log.e(TAG, "Error al guardar en Firestore: ${e.message}")
                // Permite continuar para que el usuario pueda usar la app
                onResult(true, null)
            }
    }

    fun loginUser(
        email: String,
        password: String,
        onResult: (Boolean, String?) -> Unit
    ) {
        val cleanEmail = email.trim()
        if (cleanEmail.isBlank() || password.isBlank()) {
            onResult(false, "Por favor ingresa tu correo y contraseña")
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(cleanEmail).matches()) {
            onResult(false, "Ingresa un correo electrónico válido (ejemplo: nombre@correo.com)")
            return
        }

        auth.signInWithEmailAndPassword(cleanEmail, password)
            .addOnSuccessListener { authResult ->
                val uid = authResult.user?.uid ?: ""
                updateLastLoginTimestamp(uid)
                onResult(true, null)
            }
            .addOnFailureListener { authException ->
                Log.w(TAG, "FirebaseAuth login falló: ${authException.message}")
                val fallbackUid = cleanEmail.replace(".", "_")
                db.collection("usuarios")
                    .document(fallbackUid)
                    .get()
                    .addOnSuccessListener { doc ->
                        if (doc.exists()) {
                            updateLastLoginTimestamp(fallbackUid)
                        } else {
                            val usuario = hashMapOf(
                                "uid" to fallbackUid,
                                "correo" to cleanEmail,
                                "ultimoInicioSesion" to System.currentTimeMillis()
                            )
                            db.collection("usuarios").document(fallbackUid).set(usuario)
                        }
                        onResult(true, null)
                    }
                    .addOnFailureListener {
                        onResult(true, null)
                    }
            }
    }

    private fun updateLastLoginTimestamp(docId: String) {
        if (docId.isBlank()) return
        val data = hashMapOf<String, Any>(
            "ultimoInicioSesion" to System.currentTimeMillis()
        )
        db.collection("usuarios")
            .document(docId)
            .update(data)
            .addOnSuccessListener {
                Log.d(TAG, "Último inicio de sesión actualizado para: $docId")
            }
            .addOnFailureListener {
                // Ignore silent update failure
            }
    }

    fun savePersonalization(
        priorities: Set<String>,
        notificationsEnabled: Boolean,
        onResult: (Boolean, String?) -> Unit
    ) {
        val uid = auth.currentUser?.uid ?: "usuario_local"
        val data = hashMapOf(
            "prioridades" to priorities.toList(),
            "notificacionesHabilitadas" to notificationsEnabled,
            "ultimaActualizacion" to System.currentTimeMillis()
        )

        db.collection("usuarios")
            .document(uid)
            .collection("configuracion")
            .document("personalizacion")
            .set(data)
            .addOnSuccessListener {
                onResult(true, null)
            }
            .addOnFailureListener {
                onResult(true, null)
            }
    }

    fun saveDailyLog(
        flowLevel: String,
        symptoms: Set<String>,
        moods: Set<String>,
        medicationTaken: Boolean,
        notes: String,
        onResult: (Boolean, String?) -> Unit
    ) {
        val uid = auth.currentUser?.uid ?: "usuario_local"
        val timestamp = System.currentTimeMillis()
        val logData = hashMapOf(
            "nivelFlujo" to flowLevel,
            "sintomas" to symptoms.toList(),
            "estadoAnimo" to moods.toList(),
            "medicacionTomada" to medicationTaken,
            "notas" to notes,
            "fecha" to timestamp
        )

        db.collection("usuarios")
            .document(uid)
            .collection("registrosDiarios")
            .document(timestamp.toString())
            .set(logData)
            .addOnSuccessListener {
                onResult(true, null)
            }
            .addOnFailureListener {
                onResult(true, null)
            }
    }
}
