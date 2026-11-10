package me.andresdavid.myapplication.ui.screen

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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Adjust
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.MedicalServices
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.material.icons.outlined.Sync
import androidx.compose.material.icons.outlined.WaterDrop
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.andresdavid.myapplication.ui.theme.SelunaProyectoTheme

// Custom Pill Icon for Contraceptives
val PillIcon: ImageVector = ImageVector.Builder(
    name = "Pill",
    defaultWidth = 18.dp,
    defaultHeight = 18.dp,
    viewportWidth = 24f,
    viewportHeight = 24f
).apply {
    addPath(
        pathData = PathParser().parsePathString("M6 3h12c1.66 0 3 1.34 3 3v12c0 1.66-1.34 3-3 3H6c-1.66 0-3-1.34-3-3V6c0-1.66 1.34-3 3-3zm0 2c-.55 0-1 .45-1 1v5h14V6c0-.55-.45-1-1-1H6z").toNodes(),
        fill = SolidColor(Color(0xFF6B535E))
    )
}.build()

enum class CycleApproach {
    TRACK_CYCLE,
    CONCEIVE,
    PREVENT_PREGNANCY
}

enum class CycleRegularity {
    REGULAR,
    VARIES,
    NOT_SURE
}

enum class ContraceptiveType {
    NONE,
    PILLS,
    IUD,
    IMPLANT
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun NaturalRhythmScreen(
    onNavigateToPersonalization: () -> Unit = {},
    onNavigateToHome: () -> Unit = {},
    onNavigateBack: () -> Unit = {}
) {
    var selectedApproach by remember { mutableStateOf(CycleApproach.TRACK_CYCLE) }
    var selectedRegularity by remember { mutableStateOf(CycleRegularity.REGULAR) }
    var selectedContraceptive by remember { mutableStateOf(ContraceptiveType.NONE) }
    var periodDays by remember { mutableIntStateOf(5) }
    var selectedConditions by remember { mutableStateOf(setOf("Ninguna de las anteriores")) }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding(),
        color = Color(0xFFFDF8F9) // Warm blush background
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
                    .padding(bottom = 8.dp),
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

                // Brand Title: Pink dot + "Seluna"
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

                // Profile circular button
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF6B535E)),
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

            // Step & Progress Indicator
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Paso 2 de 4 • Perfil Ginecológico",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF6E565E)
                    )
                    Text(
                        text = "50%",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF6E565E)
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                // Progress Bar (50%)
                LinearProgressIndicator(
                    progress = { 0.5f },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(CircleShape),
                    color = Color(0xFF805C6B),
                    trackColor = Color(0xFFF7D9E3),
                    strokeCap = StrokeCap.Round
                )

                Spacer(modifier = Modifier.height(10.dp))

                // 100% Confidencial y Cifrado Clínico Badge
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Surface(
                        shape = CircleShape,
                        color = Color(0xFFE2F0EC)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = PlantSproutIcon,
                                contentDescription = null,
                                tint = Color(0xFF2E594B),
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "100% Confidencial y Cifrado Clínico",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF2E594B)
                            )
                        }
                    }
                }
            }

            // Header Titles
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Text(
                    text = "¿Cómo es tu ritmo natural?",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF33262A)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Estas respuestas permiten a Seluna sincronizar sus algoritmos y estimaciones hormonales con la biología única de tu cuerpo.",
                    fontSize = 13.sp,
                    color = Color(0xFF8C7F84),
                    lineHeight = 19.sp
                )
            }

            // SECTION 1: ¿Cuál es tu enfoque principal?
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .shadow(1.dp, RoundedCornerShape(24.dp)),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    // Header Row
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(28.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFFAF0F3)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Adjust,
                                contentDescription = null,
                                tint = Color(0xFF6B535E),
                                modifier = Modifier.size(16.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "¿Cuál es tu enfoque principal?",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF33262A)
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Adaptamos la ruta de tu ciclo según lo que buscas en este momento.",
                        fontSize = 12.sp,
                        color = Color(0xFF8C7F84),
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    // Option 1: Conocer y registrar mi ciclo
                    val isAppr1 = selectedApproach == CycleApproach.TRACK_CYCLE
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                            .clickable { selectedApproach = CycleApproach.TRACK_CYCLE },
                        shape = RoundedCornerShape(16.dp),
                        color = if (isAppr1) Color(0xFFFFF0F3) else Color(0xFFFAF8F9),
                        border = if (isAppr1) androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFF2BAC9)) else null
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
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
                                        .background(if (isAppr1) Color(0xFF6B535E) else Color(0xFFF0E6E8)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.CalendarToday,
                                        contentDescription = null,
                                        tint = if (isAppr1) Color.White else Color(0xFF6B535E),
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                Column {
                                    Text(
                                        text = "Conocer y registrar mi ciclo",
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF33262A)
                                    )
                                    Text(
                                        text = "Seguimiento de fases, energía y síntomas diarios",
                                        fontSize = 11.sp,
                                        color = Color(0xFF8C7F84)
                                    )
                                }
                            }

                            Box(
                                modifier = Modifier
                                    .size(20.dp)
                                    .clip(CircleShape)
                                    .background(if (isAppr1) Color(0xFF33262A) else Color.Transparent)
                                    .border(1.5.dp, if (isAppr1) Color(0xFF33262A) else Color(0xFFD0C4C8), CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                if (isAppr1) {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier.size(12.dp)
                                    )
                                }
                            }
                        }
                    }

                    // Option 2: Buscando concebir un embarazo
                    val isAppr2 = selectedApproach == CycleApproach.CONCEIVE
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                            .clickable { selectedApproach = CycleApproach.CONCEIVE },
                        shape = RoundedCornerShape(16.dp),
                        color = if (isAppr2) Color(0xFFFFF0F3) else Color(0xFFFAF8F9),
                        border = if (isAppr2) androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFF2BAC9)) else null
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
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
                                        .background(if (isAppr2) Color(0xFF6B535E) else Color(0xFFFFF0F3)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.FavoriteBorder,
                                        contentDescription = null,
                                        tint = if (isAppr2) Color.White else Color(0xFF6B535E),
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                Column {
                                    Text(
                                        text = "Buscando concebir un embarazo",
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF33262A)
                                    )
                                    Text(
                                        text = "Detección de ventana fértil y pico de ovulación",
                                        fontSize = 11.sp,
                                        color = Color(0xFF8C7F84)
                                    )
                                }
                            }

                            Box(
                                modifier = Modifier
                                    .size(20.dp)
                                    .clip(CircleShape)
                                    .background(if (isAppr2) Color(0xFF33262A) else Color.Transparent)
                                    .border(1.5.dp, if (isAppr2) Color(0xFF33262A) else Color(0xFFD0C4C8), CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                if (isAppr2) {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier.size(12.dp)
                                    )
                                }
                            }
                        }
                    }

                    // Option 3: Prevenir un embarazo
                    val isAppr3 = selectedApproach == CycleApproach.PREVENT_PREGNANCY
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { selectedApproach = CycleApproach.PREVENT_PREGNANCY },
                        shape = RoundedCornerShape(16.dp),
                        color = if (isAppr3) Color(0xFFFFF0F3) else Color(0xFFFAF8F9),
                        border = if (isAppr3) androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFF2BAC9)) else null
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
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
                                        .background(if (isAppr3) Color(0xFF6B535E) else Color(0xFFFFF0F3)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.Shield,
                                        contentDescription = null,
                                        tint = if (isAppr3) Color.White else Color(0xFF6B535E),
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                Column {
                                    Text(
                                        text = "Prevenir un embarazo",
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF33262A)
                                    )
                                    Text(
                                        text = "Alertas de fertilidad para protección consciente",
                                        fontSize = 11.sp,
                                        color = Color(0xFF8C7F84)
                                    )
                                }
                            }

                            Box(
                                modifier = Modifier
                                    .size(20.dp)
                                    .clip(CircleShape)
                                    .background(if (isAppr3) Color(0xFF33262A) else Color.Transparent)
                                    .border(1.5.dp, if (isAppr3) Color(0xFF33262A) else Color(0xFFD0C4C8), CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                if (isAppr3) {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier.size(12.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // SECTION 2: ¿Tu ciclo suele ser regular?
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .shadow(1.dp, RoundedCornerShape(24.dp)),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(28.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFFAF0F3)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Sync,
                                contentDescription = null,
                                tint = Color(0xFF6B535E),
                                modifier = Modifier.size(16.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "¿Tu ciclo suele ser regular?",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF33262A)
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "¿El número de días entre periodos es predecible mes a mes?",
                        fontSize = 12.sp,
                        color = Color(0xFF8C7F84),
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    // Option 1: Sí, es regular (26 - 32 días)
                    val isReg1 = selectedRegularity == CycleRegularity.REGULAR
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                            .clickable { selectedRegularity = CycleRegularity.REGULAR },
                        shape = CircleShape,
                        color = if (isReg1) Color(0xFF6B565E) else Color(0xFFFAF8F9)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Sí, es regular (26 - 32 días)",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = if (isReg1) Color.White else Color(0xFF33262A)
                            )
                            if (isReg1) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }

                    // Option 2: No, varía con frecuencia
                    val isReg2 = selectedRegularity == CycleRegularity.VARIES
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                            .clickable { selectedRegularity = CycleRegularity.VARIES },
                        shape = CircleShape,
                        color = if (isReg2) Color(0xFF6B565E) else Color(0xFFFAF8F9)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "No, varía con frecuencia",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = if (isReg2) Color.White else Color(0xFF33262A)
                            )
                            if (isReg2) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }

                    // Option 3: No estoy segura o aún no lo sé
                    val isReg3 = selectedRegularity == CycleRegularity.NOT_SURE
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { selectedRegularity = CycleRegularity.NOT_SURE },
                        shape = CircleShape,
                        color = if (isReg3) Color(0xFF6B565E) else Color(0xFFFAF8F9)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "No estoy segura o aún no lo sé",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = if (isReg3) Color.White else Color(0xFF33262A)
                            )
                            if (isReg3) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }
                }
            }

            // SECTION 3: ¿Usas anticonceptivos hormonales?
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .shadow(1.dp, RoundedCornerShape(24.dp)),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(28.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFFAF0F3)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = PillIcon,
                                contentDescription = null,
                                tint = Color(0xFF6B535E),
                                modifier = Modifier.size(16.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "¿Usas anticonceptivos hormonales?",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF33262A)
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Las hormonas externas influyen directamente en la ovulación natural.",
                        fontSize = 12.sp,
                        color = Color(0xFF8C7F84),
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    // 2x2 Grid Options
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            // Ninguno / Natural (Selected Green in screenshot)
                            val isContra1 = selectedContraceptive == ContraceptiveType.NONE
                            Surface(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(44.dp)
                                    .clickable { selectedContraceptive = ContraceptiveType.NONE },
                                shape = RoundedCornerShape(12.dp),
                                color = if (isContra1) Color(0xFFE2F0EC) else Color(0xFFFAF8F9)
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    Text(
                                        text = "Ninguno / Natural",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = if (isContra1) Color(0xFF2E594B) else Color(0xFF33262A)
                                    )
                                }
                            }

                            // Pastillas diarias
                            val isContra2 = selectedContraceptive == ContraceptiveType.PILLS
                            Surface(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(44.dp)
                                    .clickable { selectedContraceptive = ContraceptiveType.PILLS },
                                shape = RoundedCornerShape(12.dp),
                                color = if (isContra2) Color(0xFFE2F0EC) else Color(0xFFFAF8F9)
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    Text(
                                        text = "Pastillas diarias",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = if (isContra2) Color(0xFF2E594B) else Color(0xFF33262A)
                                    )
                                }
                            }
                        }

                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            // DIU (Mirena o Cobre)
                            val isContra3 = selectedContraceptive == ContraceptiveType.IUD
                            Surface(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(44.dp)
                                    .clickable { selectedContraceptive = ContraceptiveType.IUD },
                                shape = RoundedCornerShape(12.dp),
                                color = if (isContra3) Color(0xFFE2F0EC) else Color(0xFFFAF8F9)
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    Text(
                                        text = "DIU (Mirena o Cobre)",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = if (isContra3) Color(0xFF2E594B) else Color(0xFF33262A)
                                    )
                                }
                            }

                            // Implante o Parche
                            val isContra4 = selectedContraceptive == ContraceptiveType.IMPLANT
                            Surface(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(44.dp)
                                    .clickable { selectedContraceptive = ContraceptiveType.IMPLANT },
                                shape = RoundedCornerShape(12.dp),
                                color = if (isContra4) Color(0xFFE2F0EC) else Color(0xFFFAF8F9)
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    Text(
                                        text = "Implante o Parche",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = if (isContra4) Color(0xFF2E594B) else Color(0xFF33262A)
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // SECTION 4: ¿Cuánto dura tu periodo?
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .shadow(1.dp, RoundedCornerShape(24.dp)),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(28.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFFFAF0F3)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.WaterDrop,
                                    contentDescription = null,
                                    tint = Color(0xFF6B535E),
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "¿Cuánto dura tu periodo?",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF33262A)
                            )
                        }

                        // Pink Badge "Días de sangrado"
                        Surface(
                            shape = CircleShape,
                            color = Color(0xFFFFF0F3)
                        ) {
                            Text(
                                text = "Días de sangrado",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF6B535E),
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Promedio de días desde el inicio hasta el cese del flujo.",
                        fontSize = 12.sp,
                        color = Color(0xFF8C7F84),
                        modifier = Modifier.padding(bottom = 14.dp)
                    )

                    // 5 Days Selector Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        listOf(3 to "3\ndías", 4 to "4\ndías", 5 to "5\ndías", 6 to "6\ndías", 7 to "7+\ndías").forEach { (day, label) ->
                            val isSelected = periodDays == day
                            Surface(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(58.dp)
                                    .clickable { periodDays = day },
                                shape = RoundedCornerShape(16.dp),
                                color = if (isSelected) Color(0xFFFFF0F3) else Color(0xFFFAF8F9),
                                border = if (isSelected) androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFF2BAC9)) else null
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = label.split("\n")[0],
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF33262A)
                                    )
                                    Text(
                                        text = label.split("\n")[1],
                                        fontSize = 10.sp,
                                        color = Color(0xFF8C7F84)
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // SECTION 5: Condiciones ginecológicas
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .shadow(1.dp, RoundedCornerShape(24.dp)),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(28.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFFFAF0F3)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.MedicalServices,
                                    contentDescription = null,
                                    tint = Color(0xFF6B535E),
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "Condiciones ginecológicas",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF33262A)
                            )
                        }

                        Text(
                            text = "Opcional",
                            fontSize = 11.sp,
                            color = Color(0xFF9E8F95)
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Selecciona si cuentas con algún diagnóstico médico previo:",
                        fontSize = 12.sp,
                        color = Color(0xFF8C7F84),
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    // Multi-select Chips
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        val conditions = listOf(
                            "SOP (Ovarios Poliquísticos)",
                            "Endometriosis",
                            "Tiroides",
                            "Ninguna de las anteriores"
                        )

                        conditions.forEach { condition ->
                            val isSelected = selectedConditions.contains(condition)
                            Surface(
                                modifier = Modifier.clickable {
                                    selectedConditions = if (condition == "Ninguna de las anteriores") {
                                        setOf("Ninguna de las anteriores")
                                    } else {
                                        val newSet = selectedConditions.minus("Ninguna de las anteriores")
                                        if (isSelected) newSet.minus(condition) else newSet.plus(condition)
                                    }
                                },
                                shape = CircleShape,
                                color = if (isSelected) Color(0xFFF2ECEE) else Color(0xFFFAF8F9),
                                border = if (isSelected) androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFDCD2D5)) else null
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = if (isSelected) "✓ " else "+ ",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF6E565E)
                                    )
                                    Text(
                                        text = condition,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = Color(0xFF33262A)
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // SECTION 6: Medical Privacy Banner Card
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 18.dp),
                shape = RoundedCornerShape(20.dp),
                color = Color(0xFFE2F0EC)
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Lock,
                            contentDescription = null,
                            tint = Color(0xFF2E594B),
                            modifier = Modifier.size(18.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "Tus datos médicos no salen de tu control",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF2E594B)
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Seluna nunca comercializa con tus registros de salud ni los comparte con aseguradoras o anunciantes. Toda tu información sensible está protegida bajo estándares biomédicos de privacidad.",
                            fontSize = 11.sp,
                            color = Color(0xFF386657),
                            lineHeight = 15.sp
                        )
                    }
                }
            }

            // Primary Button: Continuar a Personalización →
            Button(
                onClick = onNavigateToPersonalization,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6B565E),
                    contentColor = Color.White
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Continuar a Personalización",
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

            Spacer(modifier = Modifier.height(12.dp))

            // Bottom Secondary Link: Configurar esto más tarde
            Text(
                text = "Configurar esto más tarde",
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF8C7F84),
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .clickable { onNavigateToHome() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NaturalRhythmScreenPreview() {
    SelunaProyectoTheme {
        NaturalRhythmScreen()
    }
}
