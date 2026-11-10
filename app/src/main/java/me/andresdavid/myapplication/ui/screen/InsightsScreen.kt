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
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.AllInclusive
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.CheckCircleOutline
import androidx.compose.material.icons.outlined.EditNote
import androidx.compose.material.icons.outlined.HelpOutline
import androidx.compose.material.icons.outlined.Insights
import androidx.compose.material.icons.outlined.Nightlight
import androidx.compose.material.icons.outlined.PictureAsPdf
import androidx.compose.material.icons.outlined.SentimentDissatisfied
import androidx.compose.material.icons.outlined.ShowChart
import androidx.compose.material.icons.outlined.Spa
import androidx.compose.material.icons.outlined.WaterDrop
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.andresdavid.myapplication.ui.theme.SelunaProyectoTheme

@Composable
fun InsightsScreen(
    onNavigateToTab: (Int) -> Unit = {}
) {
    var selectedBottomNav by remember { mutableIntStateOf(2) } // "Estadísticas" tab selected

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
                    onClick = {
                        selectedBottomNav = 2
                        onNavigateToTab(2)
                    },
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
            color = Color(0xFFFDF8F9) // Warm blush background
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

                    // Profile Avatar Circle
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

                // Header Title Section
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 14.dp)
                ) {
                    Surface(
                        shape = CircleShape,
                        color = Color(0xFFFFF0F3),
                        modifier = Modifier.padding(bottom = 8.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Insights,
                                contentDescription = null,
                                tint = Color(0xFF805C6B),
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Últimos 3 meses",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF6E565E)
                            )
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Resumen de Ciclo",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF33262A)
                        )
                        Text(
                            text = "Actualizado hoy",
                            fontSize = 12.sp,
                            color = Color(0xFF2E594B),
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Tus ritmos biológicos reflejan un patrón armónico y predecible.",
                        fontSize = 13.sp,
                        color = Color(0xFF8C7F84),
                        lineHeight = 18.sp
                    )
                }

                // 2 Summary Cards (Side by Side)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Card 1: Promedio Ciclo (29 días)
                    Surface(
                        modifier = Modifier
                            .weight(1f)
                            .height(140.dp),
                        shape = RoundedCornerShape(24.dp),
                        color = Color(0xFFFFF0F3)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(14.dp),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Promedio Ciclo",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF6E565E)
                                )
                                Icon(
                                    imageVector = Icons.Outlined.AllInclusive,
                                    contentDescription = null,
                                    tint = Color(0xFF8C7F84),
                                    modifier = Modifier.size(16.dp)
                                )
                            }

                            Row(verticalAlignment = Alignment.Bottom) {
                                Text(
                                    text = "29",
                                    fontSize = 32.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF33262A)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "días",
                                    fontSize = 13.sp,
                                    color = Color(0xFF8C7F84),
                                    modifier = Modifier.padding(bottom = 4.dp)
                                )
                            }

                            Text(
                                text = "→ Variación ±1 día",
                                fontSize = 11.sp,
                                color = Color(0xFF8C7F84)
                            )

                            // Wave graphic
                            Canvas(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(20.dp)
                            ) {
                                val path = Path().apply {
                                    moveTo(0f, size.height * 0.5f)
                                    cubicTo(
                                        size.width * 0.25f, size.height * 0.2f,
                                        size.width * 0.5f, size.height * 0.8f,
                                        size.width * 0.75f, size.height * 0.3f
                                    )
                                    cubicTo(
                                        size.width * 0.85f, size.height * 0.1f,
                                        size.width * 0.95f, size.height * 0.6f,
                                        size.width, size.height * 0.5f
                                    )
                                }
                                drawPath(
                                    path = path,
                                    color = Color(0xFF6B535E),
                                    style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)
                                )
                                drawCircle(
                                    color = Color(0xFF33262A),
                                    radius = 4.dp.toPx(),
                                    center = Offset(size.width, size.height * 0.5f)
                                )
                            }
                        }
                    }

                    // Card 2: Menstruación (5 días)
                    Surface(
                        modifier = Modifier
                            .weight(1f)
                            .height(140.dp),
                        shape = RoundedCornerShape(24.dp),
                        color = Color(0xFFFFF0F3)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(14.dp),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Menstruación",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF6E565E)
                                )
                                Icon(
                                    imageVector = Icons.Outlined.WaterDrop,
                                    contentDescription = null,
                                    tint = Color(0xFF33262A),
                                    modifier = Modifier.size(16.dp)
                                )
                            }

                            Row(verticalAlignment = Alignment.Bottom) {
                                Text(
                                    text = "5",
                                    fontSize = 32.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF33262A)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "días",
                                    fontSize = 13.sp,
                                    color = Color(0xFF8C7F84),
                                    modifier = Modifier.padding(bottom = 4.dp)
                                )
                            }

                            // Green Pill Badge: Ciclo regular
                            Surface(
                                shape = CircleShape,
                                color = Color(0xFFE2F0EC)
                            ) {
                                Text(
                                    text = "• Ciclo regular",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF2E594B),
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                                )
                            }

                            // 5 Segmented Bar
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Box(modifier = Modifier.weight(1f).height(6.dp).clip(CircleShape).background(Color(0xFF6B535E)))
                                Box(modifier = Modifier.weight(1f).height(6.dp).clip(CircleShape).background(Color(0xFF6B535E)))
                                Box(modifier = Modifier.weight(1f).height(6.dp).clip(CircleShape).background(Color(0xFFF7C2D2)))
                                Box(modifier = Modifier.weight(1f).height(6.dp).clip(CircleShape).background(Color(0xFFF7C2D2)))
                                Box(modifier = Modifier.weight(1f).height(6.dp).clip(CircleShape).background(Color(0xFFD3ECE1)))
                            }
                        }
                    }
                }

                // Patrones de Síntomas Chart Card
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                        .shadow(2.dp, RoundedCornerShape(28.dp)),
                    shape = RoundedCornerShape(28.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = "Patrones de Síntomas",
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF33262A)
                                )
                                Text(
                                    text = "Frecuencia observada por ciclo",
                                    fontSize = 12.sp,
                                    color = Color(0xFF8C7F84)
                                )
                            }

                            Surface(
                                shape = CircleShape,
                                color = Color(0xFFFFF0F3),
                                modifier = Modifier.clickable { /* Filter */ }
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "3 meses",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF6E565E)
                                    )
                                    Icon(
                                        imageVector = Icons.Default.KeyboardArrowDown,
                                        contentDescription = null,
                                        tint = Color(0xFF6E565E),
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Bar Chart Graphic Container
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(160.dp)
                        ) {
                            // Y-Axis Labels
                            Column(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .padding(end = 8.dp),
                                verticalArrangement = Arrangement.SpaceBetween,
                                horizontalAlignment = Alignment.End
                            ) {
                                listOf("100%", "75%", "50%", "25%", "0%").forEach { pct ->
                                    Text(
                                        text = pct,
                                        fontSize = 10.sp,
                                        color = Color(0xFF9E8F95)
                                    )
                                }
                            }

                            // Chart Area with Bars
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                            ) {
                                // Dashed Grid lines
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalArrangement = Arrangement.SpaceBetween
                                ) {
                                    repeat(5) {
                                        HorizontalDivider(color = Color(0xFFF2ECEE))
                                    }
                                }

                                // 4 Vertical Bars Row
                                Row(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalArrangement = Arrangement.SpaceAround,
                                    verticalAlignment = Alignment.Bottom
                                ) {
                                    // Bar 1: Fatiga (80%)
                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                        Box(
                                            modifier = Modifier
                                                .width(28.dp)
                                                .height(115.dp)
                                                .clip(RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp))
                                                .background(Color(0xFFF7C2D2))
                                        )
                                        Spacer(modifier = Modifier.height(6.dp))
                                        Icon(Icons.Outlined.Nightlight, contentDescription = null, tint = Color(0xFF33262A), modifier = Modifier.size(14.dp))
                                        Text("Fatiga", fontSize = 10.sp, color = Color(0xFF8C7F84))
                                    }

                                    // Bar 2: Cólicos (62%)
                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                        Box(
                                            modifier = Modifier
                                                .width(28.dp)
                                                .height(90.dp)
                                                .clip(RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp))
                                                .background(Color(0xFFF7C2D2))
                                        )
                                        Spacer(modifier = Modifier.height(6.dp))
                                        Icon(Icons.Outlined.Spa, contentDescription = null, tint = Color(0xFF33262A), modifier = Modifier.size(14.dp))
                                        Text("Cólicos", fontSize = 10.sp, color = Color(0xFF8C7F84))
                                    }

                                    // Bar 3: Cefalea (38%)
                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                        Box(
                                            modifier = Modifier
                                                .width(28.dp)
                                                .height(55.dp)
                                                .clip(RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp))
                                                .background(Color(0xFFE2F0EC))
                                        )
                                        Spacer(modifier = Modifier.height(6.dp))
                                        Icon(Icons.Outlined.HelpOutline, contentDescription = null, tint = Color(0xFF33262A), modifier = Modifier.size(14.dp))
                                        Text("Cefalea", fontSize = 10.sp, color = Color(0xFF8C7F84))
                                    }

                                    // Bar 4: Ánimo (46%)
                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                        Box(
                                            modifier = Modifier
                                                .width(28.dp)
                                                .height(65.dp)
                                                .clip(RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp))
                                                .background(Color(0xFFD3ECE1))
                                        )
                                        Spacer(modifier = Modifier.height(6.dp))
                                        Icon(Icons.Outlined.SentimentDissatisfied, contentDescription = null, tint = Color(0xFF33262A), modifier = Modifier.size(14.dp))
                                        Text("Ánimo", fontSize = 10.sp, color = Color(0xFF8C7F84))
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Bottom Info Pill Box
                        Surface(
                            shape = RoundedCornerShape(16.dp),
                            color = Color(0xFFFFF0F3),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Toca una barra para inspeccionar la frecuencia en cada fase.",
                                fontSize = 11.sp,
                                color = Color(0xFF6E565E),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(vertical = 10.dp, horizontal = 12.dp)
                            )
                        }
                    }
                }

                // Medical Analysis Card: Fase lútea estable (14 días)
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    shape = RoundedCornerShape(24.dp),
                    color = Color(0xFFE2F0EC)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
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
                                    tint = Color(0xFF2E594B),
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = "Fase lútea estable (14 días)",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF2E594B)
                                )
                                Text(
                                    text = "Análisis médico de bienestar",
                                    fontSize = 11.sp,
                                    color = Color(0xFF386657)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "Tus patrones muestran alta consistencia este trimestre. Una fase lútea de 14 días indica ovulaciones regulares y niveles hormonales óptimos para tu recuperación física.",
                            fontSize = 12.sp,
                            color = Color(0xFF2E594B),
                            lineHeight = 17.sp
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Surface(
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(14.dp),
                                color = Color.White
                            ) {
                                Row(
                                    modifier = Modifier.padding(8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.CheckCircleOutline,
                                        contentDescription = null,
                                        tint = Color(0xFF2E594B),
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Column {
                                        Text("Ovulación", fontSize = 10.sp, color = Color(0xFF386657))
                                        Text("Día 15 detectado", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFF2E594B))
                                    }
                                }
                            }

                            Surface(
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(14.dp),
                                color = Color.White
                            ) {
                                Row(
                                    modifier = Modifier.padding(8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.CheckCircleOutline,
                                        contentDescription = null,
                                        tint = Color(0xFF2E594B),
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Column {
                                        Text("Confianza", fontSize = 10.sp, color = Color(0xFF386657))
                                        Text("98% concordancia", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFF2E594B))
                                    }
                                }
                            }
                        }
                    }
                }

                // Nutritional Guide Card
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                        .clickable { /* View guide */ },
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE8EEFA))
                ) {
                    Column {
                        // Top Illustration / Aesthetic Card Header
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                                .background(Color(0xFFEADBDF)),
                            contentAlignment = Alignment.BottomStart
                        ) {
                            Canvas(modifier = Modifier.fillMaxSize()) {
                                drawRect(color = Color(0xFFE8C2A0), size = androidx.compose.ui.geometry.Size(size.width * 0.4f, size.height))
                                drawCircle(color = Color(0xFFFFF0F3), radius = 60f, center = Offset(size.width * 0.7f, size.height * 0.5f))
                            }
                            Text(
                                text = "Guía Nutricional Personalizada",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                modifier = Modifier.padding(12.dp)
                            )
                        }

                        // Bottom Text Row
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Infusiones antiinflamatorias",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF33262A)
                                )
                                Text(
                                    text = "Recomendadas durante tu fase lútea",
                                    fontSize = 11.sp,
                                    color = Color(0xFF8C7F84)
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(Color.White),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                    contentDescription = null,
                                    tint = Color(0xFF33262A),
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    }
                }

                // Primary Action Button: Exportar informe de salud en PDF
                Button(
                    onClick = { /* Export PDF */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF3C3035),
                        contentColor = Color.White
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.PictureAsPdf,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Exportar informe de salud en PDF",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Listo para compartir con tu ginecóloga en tu próxima consulta médica.",
                    fontSize = 11.sp,
                    color = Color(0xFF9E8F95),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 16.dp, start = 10.dp, end = 10.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InsightsScreenPreview() {
    SelunaProyectoTheme {
        InsightsScreen()
    }
}
