package me.andresdavid.myapplication.ui.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.EditNote
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Insights
import androidx.compose.material.icons.outlined.Medication
import androidx.compose.material.icons.outlined.WaterDrop
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.andresdavid.myapplication.data.FirebaseManager
import me.andresdavid.myapplication.ui.theme.SelunaProyectoTheme

enum class FlowLevel(val label: String) {
    NONE("Ninguno"),
    LIGHT("Ligero"),
    MEDIUM("Medio"),
    HEAVY("Abundante")
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DailyLogScreen(
    onNavigateToTab: (Int) -> Unit = {},
    onSaveLog: () -> Unit = {}
) {
    var selectedBottomNav by remember { mutableIntStateOf(3) } // "Registro" tab selected
    var flowLevel by remember { mutableStateOf(FlowLevel.LIGHT) }
    var selectedSymptoms by remember { mutableStateOf(setOf("Cólicos", "Dolor de cabeza", "Fatiga")) }
    var selectedMoods by remember { mutableStateOf(setOf("Tranquila")) }
    var medicationTaken by remember { mutableStateOf(true) }
    var notes by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    fun handleSaveLog() {
        isLoading = true
        FirebaseManager.saveDailyLog(
            flowLevel = flowLevel.label,
            symptoms = selectedSymptoms,
            moods = selectedMoods,
            medicationTaken = medicationTaken,
            notes = notes
        ) { _, _ ->
            isLoading = false
            onSaveLog()
        }
    }

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                tonalElevation = 8.dp
            ) {
                NavigationBarItem(
                    selected = selectedBottomNav == 0,
                    onClick = { onNavigateToTab(0) },
                    icon = { Icon(Icons.Outlined.AccessTime, contentDescription = "Hoy") },
                    label = { Text("Hoy", fontSize = 11.sp, fontWeight = if (selectedBottomNav == 0) FontWeight.Bold else FontWeight.Normal) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF33262A),
                        selectedTextColor = Color(0xFF33262A),
                        indicatorColor = Color(0xFFFFF0F3),
                        unselectedIconColor = Color(0xFF9E8F95),
                        unselectedTextColor = Color(0xFF9E8F95)
                    )
                )
                NavigationBarItem(
                    selected = selectedBottomNav == 1,
                    onClick = { onNavigateToTab(1) },
                    icon = { Icon(Icons.Outlined.CalendarToday, contentDescription = "Calendario") },
                    label = { Text("Calendario", fontSize = 11.sp, fontWeight = if (selectedBottomNav == 1) FontWeight.Bold else FontWeight.Normal) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF33262A),
                        selectedTextColor = Color(0xFF33262A),
                        indicatorColor = Color(0xFFFFF0F3),
                        unselectedIconColor = Color(0xFF9E8F95),
                        unselectedTextColor = Color(0xFF9E8F95)
                    )
                )
                NavigationBarItem(
                    selected = selectedBottomNav == 2,
                    onClick = { onNavigateToTab(2) },
                    icon = { Icon(Icons.Outlined.Insights, contentDescription = "Estadísticas") },
                    label = { Text("Estadísticas", fontSize = 11.sp, fontWeight = if (selectedBottomNav == 2) FontWeight.Bold else FontWeight.Normal) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF33262A),
                        selectedTextColor = Color(0xFF33262A),
                        indicatorColor = Color(0xFFFFF0F3),
                        unselectedIconColor = Color(0xFF9E8F95),
                        unselectedTextColor = Color(0xFF9E8F95)
                    )
                )
                NavigationBarItem(
                    selected = selectedBottomNav == 3,
                    onClick = {
                        selectedBottomNav = 3
                        onNavigateToTab(3)
                    },
                    icon = { Icon(Icons.Outlined.EditNote, contentDescription = "Registro") },
                    label = { Text("Registro", fontSize = 11.sp, fontWeight = if (selectedBottomNav == 3) FontWeight.Bold else FontWeight.Normal) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF33262A),
                        selectedTextColor = Color(0xFF33262A),
                        indicatorColor = Color(0xFFFFF0F3),
                        unselectedIconColor = Color(0xFF9E8F95),
                        unselectedTextColor = Color(0xFF9E8F95)
                    )
                )
            }
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .statusBarsPadding(),
            color = Color(0xFFFDF8F9) // Light warm blush background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Top App Bar
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.width(40.dp))

                    Text(
                        text = "Seluna",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF33262A)
                    )

                    // Avatar Circle
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFE8C2A0)),
                        contentAlignment = Alignment.Center
                    ) {
                        Canvas(modifier = Modifier.size(24.dp)) {
                            drawCircle(color = Color(0xFF6B535E), radius = 8f, center = Offset(size.width * 0.5f, size.height * 0.35f))
                            drawCircle(color = Color(0xFF6B535E), radius = 14f, center = Offset(size.width * 0.9f, size.height * 0.9f))
                        }
                    }
                }

                // Date Selector Header
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                ) {
                    Text(
                        text = "REGISTRO DIARIO",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF8C7F84),
                        letterSpacing = 0.5.sp
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                                contentDescription = "Día anterior",
                                tint = Color(0xFF33262A),
                                modifier = Modifier
                                    .size(28.dp)
                                    .clickable { /* Prev day */ }
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Hoy, 3 de Noviembre",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF33262A)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                contentDescription = "Día siguiente",
                                tint = Color(0xFF33262A),
                                modifier = Modifier
                                    .size(28.dp)
                                    .clickable { /* Next day */ }
                            )
                        }

                        // Calendar Button
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFE8EEFA))
                                .clickable { /* Open picker */ },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.CalendarToday,
                                contentDescription = "Calendario",
                                tint = Color(0xFF4A68A5),
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }

                // Phase Status Card: Fase Lútea • Día 23
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    shape = RoundedCornerShape(24.dp),
                    color = Color(0xFFE8EEFA)
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFFFF0F3)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.FavoriteBorder,
                                contentDescription = null,
                                tint = Color(0xFF805C6B),
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column {
                            Text(
                                text = "Fase Lútea • Día 23",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF33262A)
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "Momento de desacelerar, escuchar tu cuerpo",
                                fontSize = 12.sp,
                                color = Color(0xFF6E6065)
                            )
                        }
                    }
                }

                // SECTION 1: Nivel de Flujo
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 18.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Nivel de Flujo",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF33262A)
                        )
                        Text(
                            text = "Intensidad actual",
                            fontSize = 12.sp,
                            color = Color(0xFF8C7F84)
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // 4 Flow Level Selectors Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        FlowLevel.entries.forEach { level ->
                            val isSelected = flowLevel == level
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.clickable { flowLevel = level }
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(64.dp)
                                        .clip(CircleShape)
                                        .background(if (isSelected) Color(0xFFFDE8EE) else Color(0xFFFAF0F3))
                                        .border(
                                            if (isSelected) 2.dp else 0.dp,
                                            if (isSelected) Color(0xFFE594AD) else Color.Transparent,
                                            CircleShape
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.WaterDrop,
                                        contentDescription = level.label,
                                        tint = if (isSelected) Color(0xFF33262A) else Color(0xFF8C7F84),
                                        modifier = Modifier.size(if (isSelected) 26.dp else 20.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = level.label,
                                    fontSize = 12.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                    color = Color(0xFF33262A)
                                )
                            }
                        }
                    }
                }

                // SECTION 2: Síntomas Físicos
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 18.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Síntomas Físicos",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF33262A)
                        )
                        Text(
                            text = "${selectedSymptoms.size} seleccionados",
                            fontSize = 12.sp,
                            color = Color(0xFF8C7F84)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Symptoms Chips FlowRow
                    val symptomList = listOf(
                        "🌷 Cólicos",
                        "🤕 Dolor de cabeza",
                        "🌙 Fatiga",
                        "📈 Sensibilidad mamaria",
                        "⭕ Hinchazón",
                        "🍩 Antojos",
                        "✨ Acné"
                    )

                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        symptomList.forEach { symptomWithIcon ->
                            val rawSymptom = symptomWithIcon.substringAfter(" ")
                            val isSelected = selectedSymptoms.contains(rawSymptom)
                            Surface(
                                modifier = Modifier.clickable {
                                    selectedSymptoms = if (isSelected) {
                                        selectedSymptoms.minus(rawSymptom)
                                    } else {
                                        selectedSymptoms.plus(rawSymptom)
                                    }
                                },
                                shape = CircleShape,
                                color = if (isSelected) Color(0xFFFFF0F3) else Color(0xFFE2F0EC)
                            ) {
                                Text(
                                    text = symptomWithIcon,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color(0xFF33262A),
                                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
                                )
                            }
                        }
                    }
                }

                // SECTION 3: Estado de Ánimo
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 18.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Estado de Ánimo",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF33262A)
                        )
                        Text(
                            text = "¿Cómo te sientes?",
                            fontSize = 12.sp,
                            color = Color(0xFF8C7F84)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    val moodList = listOf(
                        "😌 Tranquila",
                        "🥺 Sensible",
                        "⚡ Energética",
                        "😤 Irritable",
                        "💭 Ansiosa"
                    )

                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        moodList.forEach { moodWithEmoji ->
                            val rawMood = moodWithEmoji.substringAfter(" ")
                            val isSelected = selectedMoods.contains(rawMood)
                            Surface(
                                modifier = Modifier.clickable {
                                    selectedMoods = if (isSelected) {
                                        selectedMoods.minus(rawMood)
                                    } else {
                                        selectedMoods.plus(rawMood)
                                    }
                                },
                                shape = CircleShape,
                                color = if (isSelected) Color(0xFFFFF0F3) else Color(0xFFE2F0EC)
                            ) {
                                Text(
                                    text = moodWithEmoji,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color(0xFF33262A),
                                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
                                )
                            }
                        }
                    }
                }

                // SECTION 4: Notas y Medicación
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Notas y Medicación",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF33262A)
                        )
                        Icon(
                            imageVector = Icons.Outlined.EditNote,
                            contentDescription = null,
                            tint = Color(0xFF8C7F84),
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Medication Card with Switch
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp),
                        shape = RoundedCornerShape(22.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF0F3))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 14.dp, vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.weight(1f)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(38.dp)
                                        .clip(CircleShape)
                                        .background(Color(0xFFD0E0F8)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.Medication,
                                        contentDescription = null,
                                        tint = Color(0xFF4A68A5),
                                        modifier = Modifier.size(20.dp)
                                    )
                                }

                                Spacer(modifier = Modifier.width(12.dp))

                                Column {
                                    Text(
                                        text = "Medicación tomada",
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF33262A)
                                    )
                                    Text(
                                        text = "Anticonceptivo u otro fármaco",
                                        fontSize = 11.sp,
                                        color = Color(0xFF8C7F84)
                                    )
                                }
                            }

                            Switch(
                                checked = medicationTaken,
                                onCheckedChange = { medicationTaken = it },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = Color.White,
                                    checkedTrackColor = Color(0xFFB5D0F5),
                                    uncheckedThumbColor = Color.White,
                                    uncheckedTrackColor = Color(0xFFE5DCDD)
                                )
                            )
                        }
                    }

                    // Personal Notes Box
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(22.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF0F3))
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Text(
                                text = "📝 Notas personales",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF33262A),
                                modifier = Modifier.padding(bottom = 6.dp)
                            )

                            OutlinedTextField(
                                value = notes,
                                onValueChange = { notes = it },
                                placeholder = {
                                    Text(
                                        text = "Escribe notas sobre tu día, descanso, sensaciones...",
                                        fontSize = 12.sp,
                                        color = Color(0xFFB0A4A8)
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(90.dp),
                                shape = RoundedCornerShape(12.dp),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedContainerColor = Color.White,
                                    unfocusedContainerColor = Color.White,
                                    focusedBorderColor = Color(0xFFEADBDF),
                                    unfocusedBorderColor = Color(0xFFEADBDF),
                                    focusedTextColor = Color(0xFF33262A),
                                    unfocusedTextColor = Color(0xFF33262A)
                                )
                            )
                        }
                    }
                }

                // Hydration & Rest Banner
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 18.dp),
                    shape = RoundedCornerShape(20.dp),
                    color = Color(0xFFE2F0EC)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(Color.White),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = PlantSproutIcon,
                                contentDescription = null,
                                tint = Color(0xFF2E594B),
                                modifier = Modifier.size(16.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Recuerda mantenerte hidratada y descansar hoy.",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF2E594B)
                        )
                    }
                }

                // Primary Action Button: ✓ Guardar Registro
                Button(
                    onClick = { handleSaveLog() },
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
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Guardar Registro",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DailyLogScreenPreview() {
    SelunaProyectoTheme {
        DailyLogScreen()
    }
}
