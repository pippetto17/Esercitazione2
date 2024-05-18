package it.pippo.wisewordspro

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import it.pippo.wisewordspro.db.DbProverb
import it.pippo.wisewordspro.db.Repository
import it.pippo.wisewordspro.ui.theme.RoomDemoTheme
import it.pippo.wisewordspro.ui.theme.romaDarkerRed
import it.pippo.wisewordspro.ui.theme.romaRed
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            var proverb by rememberSaveable { mutableStateOf("") }
            var proverbs by rememberSaveable {
                mutableStateOf(listOf<String>())
            }
            var screen by rememberSaveable {
                mutableStateOf(4)
            }
            val context = LocalContext.current
            val db = DbProverb.getInstance(context)
            val repository = Repository(db.proverbDao())

            RoomDemoTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = romaRed,
                    bottomBar = {
                        BottomAppBar(
                            containerColor = romaDarkerRed,
                        ) {
                            BottomNavigationBar(navController = navController)
                        }
                    },
                ) { innerPadding ->
                    NavHost(
                        navController,
                        startDestination = NavigationItem.Random.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(NavigationItem.Random.route) {
                            ShowRandomProverbs(proverb) {
                                CoroutineScope(Dispatchers.IO).launch {
                                    val p = repository.readFilteredNext("%$it%", 0)
                                    proverb = p?.text ?: "Problems!"
                                }
                            }
                        }
                        composable(NavigationItem.Grid.route) {
                            ShowProverbsGrid(proverbs) {
                                CoroutineScope(Dispatchers.IO).launch {
                                    Log.w("XXX", it)
                                    val l = repository.readAll("%$it%")
                                    proverbs = l
                                }
                            }
                        }
                        composable(NavigationItem.List.route) {
                            ShowProverbsList(proverbs) {
                                CoroutineScope(Dispatchers.IO).launch {
                                    Log.w("XXX", it)
                                    val l = repository.readAll("%$it%")
                                    proverbs = l
                                }
                            }
                        }
                        composable(NavigationItem.Simple.route) {
                            SimpleList(proverbs = proverbs) {
                                CoroutineScope(Dispatchers.IO).launch {
                                    val l = repository.readAll("%$it%")
                                    proverbs = l
                                }
                            }

                        }

                    }
                }

            }
        }
    }
}