package me.andresdavid.myapplication.ui.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Medication
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.SentimentSatisfied
import androidx.compose.material.icons.outlined.Spa
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.andresdavid.myapplication.data.FirebaseManager
import me.andresdavid.myapplication.ui.theme.SelunaProyectoTheme

data class PersonalizationPriority(
    val id: String,
    val title: String,
    val description: String,
    val icon: ImageVector
)

@Composable
fun PersonalizationScreen(
    onNavigateToHome: () -> Unit = {},
    onNavigateBack: () -> Unit = {}
) {
    val priorities = listOf(
        PersonalizationPriority(
            id = "period_prediction",
            title = "Predicción precisa del periodo",
            description = "Anticipar fechas exactas, duración del flujo y comprender cambios en el...",
            icon = Icons.Outlined.CalendarToday
        ),
        PersonalizationPriority(
            id = "fertile_window",
            title = "Ventanas fértiles y ovulación",
            description = "Planificar o prevenir el embarazo identificando tus días de máxima...",
            icon = Icons.Outlined.WbSunny
        ),
        PersonalizationPriority(
            id = "pms_relief",
            title = "Alivio y control de síntomas (SPM)",
            description = "Estrategias para cólicos, hinchazón y cambios hormonales con apoyo...",
            icon = Icons.Outlined.Spa
        ),
        PersonalizationPriority(
            id = "mood_vitality",
            title = "Estado de ánimo y vitalidad",
            description = "Descubrir patrones emocionales, picos de energía y calidad del...",
            icon = Icons.Outlined.SentimentSatisfied
        ),
        PersonalizationPriority(
            id = "medication_reminders",
            title = "Recordatorios de medicación",
            description = "Alertas sutiles para anticonceptivos, hidratación, suplementos de hierro y...",
            icon = Icons.Outlined.Medication
        ),
        PersonalizationPriority(
            id = "gyne_reports",
            title = "Reportes para mi ginecóloga",
            description = "Exportar resúmenes clínicos organizados para tus consultas...",
            icon = Icons.Outlined.Description
        )
    )

    var selectedPriorities by remember {
        mutableStateOf(setOf("period_prediction", "pms_relief"))
    }
    var notificationsEnabled by remember { mutableStateOf(true) }
    var isLoading by remember { mutableStateOf(false) }

    fun handleSave() {
        isLoading = true
        FirebaseManager.savePersonalization(selectedPriorities, notificationsEnabled) { _, _ ->
            isLoading = false
            onNavigateToHome()
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding(),
        color = Color(0xFFFDF8F9) // Light warm blush background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top Navigation Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onNavigateBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Volver",
                        tint = Color(0xFF33262A)
                    )
                }

                // Brand Title in Center: Pink dot + "Seluna"
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFE594AD))
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Seluna",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF33262A)
                    )
                }

                // Profile circular icon button on right
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF33262A)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Perfil",
                        tint = Color.White,
                        modifier = Modifier.size(22.dp)
                    )
                }
            }

            // Step Badge & Progress Segment Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 14.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Left Badge: PASO 2 DE 2 • PERSONALIZACIÓN
                Surface(
                    shape = CircleShape,
                    color = Color(0xFFE2F0EC)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(6.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF2E594B))
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "PASO 2 DE 2 • PERSONALIZACIÓN",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF2E594B)
                        )
                    }
                }

                // Right Segmented Bar (2 Bars: Pink + Dark Mauve)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .width(28.dp)
                            .height(5.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFF7C2D2))
                    )
                    Box(
                        modifier = Modifier
                            .width(28.dp)
                            .height(5.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF3C3035))
                    )
                }
            }

            // Header Titles
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Text(
                    text = "¿En qué te gustaría que Seluna te acompañe?",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF33262A),
                    lineHeight = 30.sp
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Selecciona una o más opciones para calibrar tus predicciones, consejos holísticos y recordatorios diarios.",
                    fontSize = 13.sp,
                    color = Color(0xFF8C7F84),
                    lineHeight = 18.sp
                )
            }

            // Priority Selection Cards List
            priorities.forEach { priority ->
                val isSelected = selectedPriorities.contains(priority.id)
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                        .clickable {
                            selectedPriorities = if (isSelected) {
                                selectedPriorities.minus(priority.id)
                            } else {
                                selectedPriorities.plus(priority.id)
                            }
                        },
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isSelected) Color(0xFFFFF0F3) else Color.White
                    ),
                    border = if (isSelected) {
                        androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFF2BAC9))
                    } else {
                        androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFF0E6E8))
                    }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(1f)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(42.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFFFFF0F3)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = priority.icon,
                                    contentDescription = null,
                                    tint = Color(0xFF6B535E),
                                    modifier = Modifier.size(20.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            Column {
                                Text(
                                    text = priority.title,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF33262A)
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = priority.description,
                                    fontSize = 11.sp,
                                    color = Color(0xFF8C7F84),
                                    lineHeight = 15.sp
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        // Selection Checkbox Circle
                        Box(
                            modifier = Modifier
                                .size(22.dp)
                                .clip(CircleShape)
                                .background(if (isSelected) Color(0xFF33262A) else Color(0xFFFDE8EE))
                                .border(
                                    1.dp,
                                    if (isSelected) Color(0xFF33262A) else Color(0xFFF7C2D2),
                                    CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            if (isSelected) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(14.dp)
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Inspirational Banner Card: Tu cuerpo, tu propio compás
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                shape = RoundedCornerShape(20.dp),
                color = Color(0xFFE2F0EC)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Small Aesthetic Canvas Circle Graphic
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Canvas(modifier = Modifier.size(22.dp)) {
                            drawCircle(color = Color(0xFFE8C2A0), radius = 8f, center = Offset(size.width * 0.4f, size.height * 0.6f))
                            drawCircle(color = Color(0xFF2E594B), radius = 5f, center = Offset(size.width * 0.65f, size.height * 0.35f))
                        }
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Text(
                            text = "Tu cuerpo, tu propio compás",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF2E594B)
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Adaptamos cada predicción a medida que registras tu día a día.",
                            fontSize = 11.sp,
                            color = Color(0xFF386657),
                            lineHeight = 15.sp
                        )
                    }
                }
            }

            // Notifications Switch Card Row
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .shadow(1.dp, RoundedCornerShape(20.dp)),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(1f)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFE8EEFA)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Notifications,
                                contentDescription = null,
                                tint = Color(0xFF4A68A5),
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column {
                            Text(
                                text = "Notificaciones empáticas",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF33262A)
                            )
                            Text(
                                text = "Avisos suaves 2 días antes de cada f...",
                                fontSize = 11.sp,
                                color = Color(0xFF8C7F84)
                            )
                        }
                    }

                    Switch(
                        checked = notificationsEnabled,
                        onCheckedChange = { notificationsEnabled = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = Color(0xFF3C3035),
                            uncheckedThumbColor = Color.White,
                            uncheckedTrackColor = Color(0xFFE5DCDD)
                        )
                    )
                }
            }

            // Primary Button: Guardar y Comenzar →
            Button(
                onClick = { handleSave() },
                enabled = !isLoading,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF3C3035),
                    contentColor = Color.White
                )
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = Color.White,
                        strokeWidth = 2.dp
                    )
                } else {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Guardar y Comenzar",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Bottom Disclaimer Text
            Text(
                text = "Puedes actualizar o ampliar estas prioridades en cualquier momento desde tu perfil.",
                fontSize = 11.sp,
                color = Color(0xFF9E8F95),
                textAlign = TextAlign.Center,
                lineHeight = 15.sp,
                modifier = Modifier.padding(bottom = 16.dp, start = 10.dp, end = 10.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PersonalizationScreenPreview() {
    SelunaProyectoTheme {
        PersonalizationScreen()
    }
}
