package app.joaobfpaulo.pokedex.presentation.navigation.drawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.joaobfpaulo.pokedex.presentation.R
import app.joaobfpaulo.pokedex.presentation.navigation.RootNavigationHost
import app.joaobfpaulo.pokedex.presentation.navigation.screens.Route
import app.joaobfpaulo.pokedex.presentation.navigation.screens.Route.Companion.createRoute
import app.joaobfpaulo.pokedex.presentation.navigation.screens.Route.Companion.getRoute
import app.joaobfpaulo.pokedex.presentation.navigation.search.SearchView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerNavigation(
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    menus: List<DrawerMenuItem>?,
    hasError: Boolean,
    onFinish : () -> Unit
) {
    var selectedMenu by remember { mutableStateOf(menus?.first()) }
    val title = remember { mutableStateOf(String()) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = MaterialTheme.colorScheme.background
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Image(
                        painter = painterResource(R.drawable.ic_international_pok_mon_logo),
                        contentDescription = stringResource(R.string.pokemon),
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(24.dp))

                    SearchView(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        onSearchItemClicked = { name, number ->
                            coroutineScope.launch {
                                drawerState.close()
                                navController.navigate(
                                    Route.Detail.createRoute(
                                        name,
                                        number,
                                        null
                                    )
                                )

                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    menus?.forEach {
                        NavigationDrawerItem(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            label = { Text(text = it.title) },
                            selected = selectedMenu == it,
                            onClick = {
                                coroutineScope.launch {
                                    drawerState.close()
                                    selectedMenu = it
                                    navController.navigate(it.route)
                                }
                            }
                        )
                    }
                }
            }
        },
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                coroutineScope.launch {
                                    when {
                                        title.value.isEmpty() -> drawerState.open()
                                        hasError -> onFinish()
                                        else -> navController.popBackStack()
                                    }
                                }
                            },
                            content = {
                                Icon(
                                    when {
                                        title.value.isEmpty() -> Icons.Filled.Menu
                                        hasError -> Icons.Filled.Close
                                        else -> Icons.AutoMirrored.Filled.ArrowBack
                                    },
                                    contentDescription = stringResource(R.string.menu)
                                )
                            }
                        )
                    },
                    title = {
                        if (!hasError) {
                            Text(
                                text = title.value.ifBlank { selectedMenu?.title.orEmpty() }
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors().copy(
                        containerColor = MaterialTheme.colorScheme.background
                    )
                )
            },
            content = { paddingValues ->
                RootNavigationHost(
                    topBarTitle = title,
                    paddingValues = paddingValues,
                    selectedMenuRoute = if (hasError) Route.Error.getRoute() else selectedMenu?.route.orEmpty(),
                    navController = navController
                )
            }
        )
    }
}