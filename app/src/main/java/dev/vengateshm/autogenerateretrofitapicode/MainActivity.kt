package dev.vengateshm.autogenerateretrofitapicode

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.vengateshm.autogenerateretrofitapicode.ui.theme.AutoGenerateRetrofitAPICodeTheme
import dev.vengateshm.autogenerateretrofitapicode.users.models.User

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AutoGenerateRetrofitAPICodeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Test(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Test(modifier: Modifier = Modifier) {
    var user by remember { mutableStateOf<User?>(null) }
    LaunchedEffect(true) {
        user = usersApiService.getUserById("1")
    }
    user?.let {
        Text(text = it.toString())
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun TestPreview() {
    AutoGenerateRetrofitAPICodeTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Test(modifier = Modifier.padding(innerPadding))
        }
    }
}