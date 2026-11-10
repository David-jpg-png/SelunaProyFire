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
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.EditCalendar
import androidx.compose.material.icons.outlined.EditNote
import androidx.compose.material.icons.outlined.Insights
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.andresdavid.myapplication.ui.theme.SelunaProyectoTheme

@Composable
fun CalendarScreen(
    onNavigateToTab: (Int) -> Unit = {},
    onNavigateToDailyLog: () -> Unit = {}
) {
    var selectedBottomNav by remember { mutableIntStateOf(1) } // "Calendario" tab selected

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
                    onClick = {
                        selectedBottomNav = 1
                        onNavigateToTab(1)
                    },
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

                // Month Selector Header Row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Prev Month Button
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFFFF0F3))
                            .clickable { /* Prev month */ },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = "Mes anterior",
                            tint = Color(0xFF33262A),
                            modifier = Modifier.size(22.dp)
                        )
                    }

                    // Month & Subtitle Text
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Noviembre 2024",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF33262A)
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(6.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFFE594AD))
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Ciclo actual: Día 18",
                                fontSize = 12.sp,
                                color = Color(0xFF8C7F84)
                            )
                        }
                    }

                    // Next Month Button
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFFFF0F3))
                            .clickable { /* Next month */ },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = "Mes siguiente",
                            tint = Color(0xFF33262A),
                            modifier = Modifier.size(22.dp)
                        )
                    }
                }

                // Interactive Month Calendar Card
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
                        // Weekday Headers (L, M, M, J, V, S, D)
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            listOf("L", "M", "M", "J", "V", "S", "D").forEach { day ->
                                Text(
                                    text = day,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF8C7F84),
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.width(36.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        // Calendar Rows
                        val calendarRows = listOf(
                            listOf(
                                CalendarCell(28, false), CalendarCell(29, false), CalendarCell(30, false), CalendarCell(31, false),
                                CalendarCell(1, true, CellType.MENSTRUATION, startRange = true),
                                CalendarCell(2, true, CellType.MENSTRUATION),
                                CalendarCell(3, true, CellType.MENSTRUATION, endRangeRow = true)
                            ),
                            listOf(
                                CalendarCell(4, true, CellType.MENSTRUATION, startRangeRow = true),
                                CalendarCell(5, true, CellType.MENSTRUATION, endRange = true),
                                CalendarCell(6, true), CalendarCell(7, true), CalendarCell(8, true), CalendarCell(9, true), CalendarCell(10, true)
                            ),
                            listOf(
                                CalendarCell(11, true), CalendarCell(12, true), CalendarCell(13, true),
                                CalendarCell(14, true, CellType.FERTILE),
                                CalendarCell(15, true, CellType.FERTILE),
                                CalendarCell(16, true, CellType.OVULATION),
                                CalendarCell(17, true, CellType.FERTILE)
                            ),
                            listOf(
                                CalendarCell(18, true, CellType.TODAY),
                                CalendarCell(19, true), CalendarCell(20, true), CalendarCell(21, true), CalendarCell(22, true), CalendarCell(23, true), CalendarCell(24, true)
                            ),
                            listOf(
                                CalendarCell(25, true), CalendarCell(26, true), CalendarCell(27, true), CalendarCell(28, true),
                                CalendarCell(29, true, CellType.PREDICTION),
                                CalendarCell(30, true, CellType.PREDICTION),
                                CalendarCell(1, false)
                            )
                        )

                        calendarRows.forEach { row ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                row.forEach { cell ->
                                    CalendarDayView(cell = cell)
                                }
                            }
                        }
                    }
                }

                // LEYENDA DEL CICLO Card
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF0F3))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "LEYENDA DEL CICLO",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF8C7F84),
                            letterSpacing = 0.5.sp,
                            modifier = Modifier.padding(bottom = 10.dp)
                        )

                        // 2x2 Grid of Legend Pills
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                // Menstruación
                                Surface(
                                    modifier = Modifier.weight(1f),
                                    shape = CircleShape,
                                    color = Color.White
                                ) {
                                    Row(
                                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(14.dp)
                                                .clip(CircleShape)
                                                .background(Color(0xFFF7C2D2))
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = "Menstruación",
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            color = Color(0xFF33262A)
                                        )
                                    }
                                }

                                // Días Fértiles
                                Surface(
                                    modifier = Modifier.weight(1f),
                                    shape = CircleShape,
                                    color = Color.White
                                ) {
                                    Row(
                                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(14.dp)
                                                .clip(CircleShape)
                                                .background(Color(0xFFD3ECE1))
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = "Días Fértiles",
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            color = Color(0xFF33262A)
                                        )
                                    }
                                }
                            }

                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                // Ovulación
                                Surface(
                                    modifier = Modifier.weight(1f),
                                    shape = CircleShape,
                                    color = Color.White
                                ) {
                                    Row(
                                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(14.dp)
                                                .clip(CircleShape)
                                                .background(Color(0xFFE2EDF8))
                                                .border(1.dp, Color(0xFF4A3E45), CircleShape)
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = "Ovulación",
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            color = Color(0xFF33262A)
                                        )
                                    }
                                }

                                // Predicción
                                Surface(
                                    modifier = Modifier.weight(1f),
                                    shape = CircleShape,
                                    color = Color.White
                                ) {
                                    Row(
                                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(14.dp)
                                                .clip(CircleShape)
                                                .background(Color(0xFFE2F0EC))
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = "Predicción",
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            color = Color(0xFF33262A)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                // Diciembre 2024 Accordion Card
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                        .clickable { /* Expand december */ },
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFF0E6E8))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(1f)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFFFFF0F3)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.CalendarMonth,
                                    contentDescription = null,
                                    tint = Color(0xFF805C6B),
                                    modifier = Modifier.size(20.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            Column {
                                Text(
                                    text = "Diciembre 2024",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF33262A)
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = "Continuidad prevista • Periodo probable 27–31 dic",
                                    fontSize = 11.sp,
                                    color = Color(0xFF8C7F84)
                                )
                            }
                        }

                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = "Desplegar",
                            tint = Color(0xFF33262A),
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }

                // Primary Action Button: Editar periodo o registrar nuevo ciclo
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
                        Icon(
                            imageVector = Icons.Outlined.EditCalendar,
                            contentDescription = null,
                            tint = Color(0xFF33262A),
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Editar periodo o registrar nuevo ciclo",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF33262A)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Tus datos están protegidos y calibran la precisión de Seluna",
                    fontSize = 11.sp,
                    color = Color(0xFF9E8F95),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
        }
    }
}

enum class CellType {
    REGULAR,
    MENSTRUATION,
    FERTILE,
    OVULATION,
    PREDICTION,
    TODAY
}

data class CalendarCell(
    val dayNumber: Int,
    val isCurrentMonth: Boolean,
    val type: CellType = CellType.REGULAR,
    val startRange: Boolean = false,
    val endRange: Boolean = false,
    val startRangeRow: Boolean = false,
    val endRangeRow: Boolean = false
)

@Composable
fun CalendarDayView(cell: CalendarCell) {
    Box(
        modifier = Modifier
            .size(36.dp),
        contentAlignment = Alignment.Center
    ) {
        if (!cell.isCurrentMonth) {
            Text(
                text = cell.dayNumber.toString(),
                fontSize = 13.sp,
                color = Color(0xFFD0C4C8),
                textAlign = TextAlign.Center
            )
        } else {
            when (cell.type) {
                CellType.MENSTRUATION -> {
                    // Continuous pill highlight for menstruation
                    val shape = when {
                        cell.startRange -> RoundedCornerShape(topStart = 18.dp, bottomStart = 18.dp)
                        cell.endRange -> RoundedCornerShape(topEnd = 18.dp, bottomEnd = 18.dp)
                        cell.startRangeRow -> RoundedCornerShape(topStart = 18.dp, bottomStart = 18.dp)
                        cell.endRangeRow -> RoundedCornerShape(topEnd = 18.dp, bottomEnd = 18.dp)
                        else -> RoundedCornerShape(0.dp)
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFFF7C2D2), shape = shape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = cell.dayNumber.toString(),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF33262A)
                        )
                    }
                }
                CellType.FERTILE -> {
                    Box(
                        modifier = Modifier
                            .size(34.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFD3ECE1)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = cell.dayNumber.toString(),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF33262A)
                        )
                    }
                }
                CellType.OVULATION -> {
                    Box(
                        modifier = Modifier
                            .size(34.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFE2EDF8))
                            .border(1.5.dp, Color(0xFF4A3E45), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = cell.dayNumber.toString(),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF33262A)
                        )

                        // Small dot on top right
                        Canvas(modifier = Modifier.fillMaxSize()) {
                            drawCircle(
                                color = Color(0xFF4A3E45),
                                radius = 2.dp.toPx(),
                                center = Offset(size.width * 0.8f, size.height * 0.2f)
                            )
                        }
                    }
                }
                CellType.TODAY -> {
                    Box(
                        modifier = Modifier
                            .size(34.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color(0xFF33262A), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = cell.dayNumber.toString(),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF33262A)
                        )
                    }
                }
                CellType.PREDICTION -> {
                    Box(
                        modifier = Modifier
                            .size(34.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFE2F0EC)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = cell.dayNumber.toString(),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF33262A)
                        )
                    }
                }
                CellType.REGULAR -> {
                    Text(
                        text = cell.dayNumber.toString(),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF33262A),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalendarScreenPreview() {
    SelunaProyectoTheme {
        CalendarScreen()
    }
}
