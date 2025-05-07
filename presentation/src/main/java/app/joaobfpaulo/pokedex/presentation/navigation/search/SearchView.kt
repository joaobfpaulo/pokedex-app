package app.joaobfpaulo.pokedex.presentation.navigation.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.joaobfpaulo.pokedex.presentation.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
    onSearchItemClicked: (String, String) -> Unit
) {
    val searchResults by viewModel.searchResultsStateFlow.collectAsStateWithLifecycle()
    var searchQuery by rememberSaveable { mutableStateOf(String()) }
    var expanded by rememberSaveable { mutableStateOf(false) }

    SearchBar(
        modifier = modifier,
        inputField = {
            SearchBarDefaults.InputField(
                query = searchQuery,
                onQueryChange = {
                    searchQuery = it
                    viewModel.search(searchQuery)
                },
                onSearch = viewModel::search,
                expanded = expanded,
                onExpandedChange = {
                    expanded = it
                },
                placeholder = {
                    Text(text = stringResource(R.string.search_pokemon))
                },
                leadingIcon = {
                    Icon(Icons.Outlined.Search, contentDescription = null)
                },
                trailingIcon = {
                    if (searchQuery.isNotEmpty() || expanded) {
                        IconButton(
                            onClick = {
                                searchQuery = String()
                                viewModel.search(searchQuery)
                                expanded = false
                            }
                        ) {
                            Icon(
                                Icons.Outlined.Clear,
                                contentDescription = stringResource(R.string.clear)
                            )
                        }
                    }
                },
            )
        },
        expanded = expanded && searchResults.isNotEmpty(),
        onExpandedChange = {
            expanded = it
        }
    ) {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(
                count = searchResults.size,
                key = { index -> searchResults[index].number },
                itemContent = { index ->
                    val entry = searchResults[index]
                    Text(
                        text = entry.pokemonName,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .clickable {
                                searchQuery = String()
                                viewModel.search(searchQuery)
                                expanded = false
                                onSearchItemClicked(
                                    entry.pokemonName,
                                    entry.number.toString()
                                )
                            }
                    )
                }
            )
        }
    }
}