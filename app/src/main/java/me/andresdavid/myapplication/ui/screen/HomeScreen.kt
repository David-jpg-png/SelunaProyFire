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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.EditNote
import androidx.compose.material.icons.outlined.Insights
import androidx.compose.material.icons.outlined.LocalCafe
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.SentimentSatisfied
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.material.icons.outlined.ShowChart
import androidx.compose.material.icons.outlined.WaterDrop
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.andresdavid.myapplication.ui.theme.SelunaProyectoTheme
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun HomeScreen(
    onNavigateToTab: (Int) -> Unit = {},
    onNavigateToDailyLog: () -> Unit = {}
) {
    var selectedBottomNav by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                tonalElevation = 8.dp
            ) {
                NavigationBarItem(
                    selected = selectedBottomNav == 0,
                    onClick = {
                        selectedBottomNav = 0
                        onNavigateToTab(0)
                    },
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
                    onClick = { onNavigateToTab(3) },
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
                // Top App Bar: Brand + Avatar
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.width(40.dp)) // Spacer for balance

                    // Brand Title
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
                            drawCircle(color = Color(0xFF6B535E), radius = 14f, center = Offset(size.width * 0.5f, size.height * 0.9f))
                        }
                    }
                }

                // Greeting & Notification Header Row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "LUNES, 24 DE OCTUBRE",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF8C7F84),
                            letterSpacing = 0.5.sp
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "¡Hola, Isabella! 🌸",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF33262A)
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Escucha a tu cuerpo en su ritmo natural.",
                            fontSize = 13.sp,
                            color = Color(0xFF8C7F84)
                        )
                    }

                    // Notification Bell Button
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFFFF0F3))
                            .clickable { /* Notification action */ },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Notifications,
                            contentDescription = "Notificaciones",
                            tint = Color(0xFF33262A),
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

                // Main Cycle Widget Card
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 18.dp)
                        .shadow(2.dp, RoundedCornerShape(28.dp)),
                    shape = RoundedCornerShape(28.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Top Badge: Fase Folicular Temprana
                        Surface(
                            shape = CircleShape,
                            color = Color(0xFFFFF0F3)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(6.dp)
                                        .clip(CircleShape)
                                        .background(Color(0xFF33262A))
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "Fase Folicular Temprana",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color(0xFF6E565E)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        // Circular Cycle Progress Ring
                        Box(
                            modifier = Modifier.size(200.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Canvas(modifier = Modifier.fillMaxSize()) {
                                val strokeWidth = 14.dp.toPx()
                                val diameter = size.minDimension - strokeWidth
                                val topLeft = Offset(strokeWidth / 2, strokeWidth / 2)
                                val arcSize = Size(diameter, diameter)

                                // Segment 1: Menstruation Phase (Pink/Red Arc, top right)
                                drawArc(
                                    color = Color(0xFFF7C2D2),
                                    startAngle = -45f,
                                    sweepAngle = 75f,
                                    useCenter = false,
                                    topLeft = topLeft,
                                    size = arcSize,
                                    style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                                )

                                // Segment 2: Follicular Phase (Mint/Green Arc)
                                drawArc(
                                    color = Color(0xFFD3ECE1),
                                    startAngle = 35f,
                                    sweepAngle = 140f,
                                    useCenter = false,
                                    topLeft = topLeft,
                                    size = arcSize,
                                    style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                                )

                                // Segment 3: Luteal Phase (Pale Blue Arc)
                                drawArc(
                                    color = Color(0xFFE2EDF8),
                                    startAngle = 180f,
                                    sweepAngle = 130f,
                                    useCenter = false,
                                    topLeft = topLeft,
                                    size = arcSize,
                                    style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                                )

                                // Current Day Indicator Dot on Ring (Left side at 180 degrees)
                                val radius = diameter / 2
                                val center = Offset(size.width / 2, size.height / 2)
                                val angleRad = Math.toRadians(180.0)
                                val dotX = center.x + radius * cos(angleRad).toFloat()
                                val dotY = center.y + radius * sin(angleRad).toFloat()

                                drawCircle(
                                    color = Color(0xFF33262A),
                                    radius = 8.dp.toPx(),
                                    center = Offset(dotX, dotY)
                                )
                                drawCircle(
                                    color = Color.White,
                                    radius = 3.dp.toPx(),
                                    center = Offset(dotX, dotY)
                                )
                            }

                            // Center Text inside Progress Ring
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "MENSTRUACIÓN",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF8C7F84),
                                    letterSpacing = 0.5.sp
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = "Día 3",
                                    fontSize = 28.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF33262A)
                                )
                                Text(
                                    text = "de 28 días",
                                    fontSize = 12.sp,
                                    color = Color(0xFF8C7F84)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        // Bottom Row: 2 Summary Pill Badges
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            // Left Badge: Próx. Ovulación
                            Surface(
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(18.dp),
                                color = Color(0xFFFAF0F3)
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.CalendarToday,
                                        contentDescription = null,
                                        tint = Color(0xFF8C7F84),
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Column {
                                        Text(
                                            text = "Próx. Ovulación",
                                            fontSize = 11.sp,
                                            color = Color(0xFF8C7F84)
                                        )
                                        Text(
                                            text = "En 10 días",
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFF33262A)
                                        )
                                    }
                                }
                            }

                            // Right Badge: Fertilidad
                            Surface(
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(18.dp),
                                color = Color(0xFFE2F0EC)
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.Shield,
                                        contentDescription = null,
                                        tint = Color(0xFF2E594B),
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Column {
                                        Text(
                                            text = "Fertilidad",
                                            fontSize = 11.sp,
                                            color = Color(0xFF386657)
                                        )
                                        Text(
                                            text = "Baja probab.",
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFF2E594B)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                // "¿Cómo te sientes hoy?" Section
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "¿Cómo te sientes hoy?",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF33262A)
                    )
                    Text(
                        text = "Registrar +",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF805C6B),
                        modifier = Modifier.clickable { onNavigateToDailyLog() }
                    )
                }

                // 4 Quick Logging Cards Row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 18.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Card 1: Síntomas
                    Surface(
                        modifier = Modifier
                            .weight(1f)
                            .height(100.dp)
                            .clickable { /* Action */ },
                        shape = RoundedCornerShape(20.dp),
                        color = Color(0xFFF0F3FC)
                    ) {
                        Column(
                            modifier = Modifier.padding(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(Color.White),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.ShowChart,
                                    contentDescription = null,
                                    tint = Color(0xFF4A68A5),
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Síntomas",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF33262A),
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    // Card 2: Ánimo
                    Surface(
                        modifier = Modifier
                            .weight(1f)
                            .height(100.dp)
                            .clickable { /* Action */ },
                        shape = RoundedCornerShape(20.dp),
                        color = Color(0xFFFFF0F3)
                    ) {
                        Column(
                            modifier = Modifier.padding(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(Color.White),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.SentimentSatisfied,
                                    contentDescription = null,
                                    tint = Color(0xFF805C6B),
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Ánimo",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF33262A),
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    // Card 3: Flujo: Medio
                    Surface(
                        modifier = Modifier
                            .weight(1f)
                            .height(100.dp)
                            .clickable { /* Action */ },
                        shape = RoundedCornerShape(20.dp),
                        color = Color(0xFFFFEAEE)
                    ) {
                        Column(
                            modifier = Modifier.padding(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(Color.White),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.WaterDrop,
                                    contentDescription = null,
                                    tint = Color(0xFF33262A),
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Flujo:\nMedio",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF33262A),
                                textAlign = TextAlign.Center,
                                lineHeight = 12.sp
                            )
                        }
                    }

                    // Card 4: Agua 1.2L
                    Surface(
                        modifier = Modifier
                            .weight(1f)
                            .height(100.dp)
                            .clickable { /* Action */ },
                        shape = RoundedCornerShape(20.dp),
                        color = Color(0xFFE5F5F0)
                    ) {
                        Column(
                            modifier = Modifier.padding(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(Color.White),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.LocalCafe,
                                    contentDescription = null,
                                    tint = Color(0xFF2E594B),
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Agua 1.2L",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF33262A),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

                // "CONSEJO DE SELUNA" Banner Card
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 18.dp),
                    shape = RoundedCornerShape(22.dp),
                    color = Color(0xFFE2F0EC)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(Color.White),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.AutoAwesome,
                                contentDescription = null,
                                tint = Color(0xFF2E594B),
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column {
                            Text(
                                text = "CONSEJO DE SELUNA • Fase Folicular",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF2E594B),
                                letterSpacing = 0.5.sp
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Tu energía empieza a aumentar gradualmente. Es un momento propicio para caminatas ligeras, yoga restaurativo e hidratación constante con infusiones cálidas.",
                                fontSize = 12.sp,
                                color = Color(0xFF2E594B),
                                lineHeight = 17.sp
                            )
                        }
                    }
                }

                // "Esta semana" Section Header
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Esta semana",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF33262A)
                    )
                    Text(
                        text = "Ciclo de 28 días",
                        fontSize = 12.sp,
                        color = Color(0xFF8C7F84)
                    )
                }

                // 7 Days Circles Row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 18.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    val days = listOf(
                        Triple("S", "1", Color(0xFFFFF0F3)),
                        Triple("D", "2", Color(0xFFFFF0F3)),
                        Triple("L", "3", Color(0xFF33262A)), // Today
                        Triple("M", "4", Color(0xFFE2F0EC)),
                        Triple("M", "5", Color(0xFFE2F0EC)),
                        Triple("J", "6", Color(0xFFFFF0F3)),
                        Triple("V", "7", Color(0xFFE2EDF8))
                    )

                    days.forEach { (dayLetter, dayNum, bgColor) ->
                        val isToday = dayNum == "3"
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = dayLetter,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF8C7F84)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(bgColor),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = dayNum,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isToday) Color.White else Color(0xFF33262A)
                                )
                            }
                        }
                    }
                }

                // Primary Action Button: + Registrar Síntomas y Notas
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .clickable { onNavigateToDailyLog() },
                    shape = CircleShape,
                    color = Color(0xFFFFF0F3)
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF33262A)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Registrar Síntomas y Notas",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF33262A)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    SelunaProyectoTheme {
        HomeScreen()
    }
}
