package com.example.contadormtg

import android.app.Dialog
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.contadormtg.ui.theme.ContadorMTGTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Palette
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        WindowCompat.setDecorFitsSystemWindows(window, false)
        val controller  = WindowInsetsControllerCompat(window, window.decorView)
        controller.hide(WindowInsetsCompat.Type.systemBars())
        controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

        setContent {
            ContadorMTGTheme {
                PlayerScreen()
            }
        }
    }
}

enum class BackGroundTheme(val label: String, val imageRes: Int?) {
    DEFAULT("MTG", R.drawable.bg_mtg),
    PLAINS("Mana W", R.drawable.bg_mana_w),
    FOREST("Mana G", R.drawable.bg_mana_g),
    ISLAND("Mana U", R.drawable.bg_mana_u),
    SWAMP("Mana B", R.drawable.bg_mana_b),
    MOUNTAIN("Mana R", R.drawable.bg_mana_r),
    AZORIUS("Azorius", R.drawable.bg_azorius),
    BOROS("Boros", R.drawable.bg_boros),
    DIMIR("Dimir", R.drawable.bg_dimir),
    IZZET("Izzet", R.drawable.bg_izzet),
    RAKDOS("Rakdos", R.drawable.bg_rakdos),
    GOLGARI("Golgari", R.drawable.bg_golgari),
    GRUUL("Gruul", R.drawable.bg_gruul),
    SELESNYA("Selesnya", R.drawable.bg_selesnya),
    ORZHOV("Orzhov", R.drawable.bg_orzhov),
    SIMIC("Simic", R.drawable.bg_simic),
    LILIANA("Liliana",R.drawable.bg_liliana),
    SHALAI("Shalai", R.drawable.bg_shalai),
    EDGAR("Edgar", R.drawable.bg_edgar)
}

@Composable
fun BackgroundSelectorDialog(
    onDismiss: () -> Unit,
    onSelectTheme: (BackGroundTheme) -> Unit
){
    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        ){
            Column(
                modifier = Modifier
                    .background(Color(0xFF2C2C2C))
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(12.dp))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(BackGroundTheme.values()) {theme ->
                        Box(
                            modifier = Modifier
                                .height(80.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color.Black)
                                .clickable{onSelectTheme(theme)},
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            if (theme.imageRes != null) {
                                Image(
                                    painter = painterResource(id = theme.imageRes),
                                    contentDescription = theme.label,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.Black.copy(alpha = 0.6f))
                                    .padding(vertical = 4.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = theme.label,
                                    color = Color.White,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun PlayerScreen() {
    var lifePoints by remember { mutableIntStateOf(40) }
    var poisonPoints by remember { mutableIntStateOf(0) }
    var commanderDamage by remember { mutableIntStateOf(0) }

    var showExtraCounters by remember { mutableStateOf(true) }
    var slectedTheme by remember { mutableStateOf(BackGroundTheme.DEFAULT) }
    var showBigDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()

    ) {
        if (slectedTheme.imageRes != null){
            Image(
                painter = painterResource(id = slectedTheme.imageRes!!),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.45f))
            )
        }
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            if (showExtraCounters){
                Column(
                    modifier = Modifier
                        .width(100.dp)
                        .fillMaxHeight()
                        .background(Color.Black.copy(alpha = 0.3f), RoundedCornerShape(12.dp))
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(text = "Poison", color = Color.Green, fontSize = 12.sp, fontWeight = FontWeight.Bold)

                    Text(
                        text = "+",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Green.copy(alpha = 0.8f),
                        modifier = Modifier
                            .clickable{if (poisonPoints <10) poisonPoints++}
                            .padding(4.dp)
                    )

                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        for (i in 10 downTo 1) {
                            Box(
                                modifier = Modifier
                                    .size(12.dp)
                                    .clip(CircleShape)
                                    .background(
                                        if (i <= poisonPoints) Color.Green else Color.DarkGray
                                    )
                            )
                        }
                    }

                    Text(
                        text = "-",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Green.copy(alpha = 0.8f),
                        modifier = Modifier
                            .clickable{if (poisonPoints >0) poisonPoints--}
                            .padding(4.dp)
                    )
                }
            }


            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "LIFE",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "<",
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White.copy(alpha = 0.6f),
                        modifier = Modifier
                            .clickable {lifePoints--}
                            .padding(horizontal = 24.dp, vertical = 8.dp)
                    )
                    Text(
                        text = "$lifePoints",
                        fontSize = 110.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Text(
                        text = ">",
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White.copy(alpha = 0.6f),
                        modifier = Modifier
                            .clickable{lifePoints++}
                            .padding(horizontal = 24.dp, vertical = 8.dp)
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(onClick = {lifePoints = 40}) {
                        Text("40HP", color = Color.Gray, fontSize = 13.sp)
                    }
                    TextButton(onClick = {lifePoints = 20}) {
                        Text("20HP", color = Color.Gray, fontSize = 13.sp)
                    }
                    TextButton(
                        onClick = {
                            lifePoints = 40
                            poisonPoints = 0
                            commanderDamage = 0
                        }
                    ) {
                        Text("Reset", color = Color.Red.copy(alpha = 0.7f), fontSize = 13.sp)
                    }
                    TextButton(onClick = {showExtraCounters = !showExtraCounters}) {
                        Text(
                            text = if (showExtraCounters) "Ocultar Extras" else "Mostrar Extras",
                            color = Color.White.copy(alpha = 0.6f),
                            fontSize = 13.sp
                        )
                    }
                }
            }

            if (showExtraCounters) {
                Column(
                    modifier = Modifier
                        .width(110.dp)
                        .fillMaxHeight()
                        .background(Color.Black.copy(alpha = 0.3f), RoundedCornerShape(12.dp))
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "COMMANDER",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "+",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White.copy(alpha = 0.8f),
                        modifier = Modifier
                            .clickable { if (commanderDamage < 21) commanderDamage++ }
                            .padding(4.dp)
                    )

                    Text(
                        text = "$commanderDamage / 21",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (commanderDamage >= 21) Color.Red else Color.White
                    )

                    Text(
                        text = "-",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White.copy(alpha = 0.8f),
                        modifier = Modifier
                            .clickable { if (commanderDamage > 0) commanderDamage-- }
                            .padding(4.dp)
                    )
                }
            }
        }
        IconButton(
            onClick = {showBigDialog = true},
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Palette,
                contentDescription = "Mudar Fundo",
                tint = Color.White.copy(alpha = 0.8f)
            )
        }
        if (showBigDialog) {
            BackgroundSelectorDialog(
                onDismiss = {showBigDialog = false},
                onSelectTheme = {theme ->
                    slectedTheme = theme
                    showBigDialog = false
                }
            )
        }
    }

}

@Preview(showBackground = true, widthDp = 640, heightDp = 360)
@Composable
fun PlayerScreenPreview() {
    ContadorMTGTheme {
        PlayerScreen()
    }
}