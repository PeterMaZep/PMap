package net.pwindows.pmap.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Assistant
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.Assistant
import androidx.compose.material.icons.outlined.Person
import net.pwindows.pmap.R

enum class NavTab { EXPLORE, ASSISTANT, YOU }

@Composable
fun BottomNavigationBar(
    selectedTab: NavTab,
    onTabSelected: (NavTab) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(modifier = modifier) {
        NavTab.entries.forEach { tab ->
            val isSelected = selectedTab == tab

            NavigationBarItem(
                selected = isSelected,
                onClick = { onTabSelected(tab) },
                icon = {
                    val icon = when (tab) {
                        NavTab.EXPLORE -> if (isSelected) Icons.Filled.Explore else Icons.Outlined.Explore
                        NavTab.ASSISTANT -> if (isSelected) Icons.Filled.Assistant else Icons.Outlined.Assistant
                        NavTab.YOU -> if (isSelected) Icons.Filled.Person else Icons.Outlined.Person
                    }
                    Icon(imageVector = icon, contentDescription = null)
                },
                label = {
                    val labelText = when (tab) {
                        NavTab.EXPLORE -> stringResource(id = R.string.nav_explore)
                        NavTab.ASSISTANT -> stringResource(id = R.string.nav_assistant)
                        NavTab.YOU -> stringResource(id = R.string.nav_you)
                    }
                    Text(text = labelText)
                }
            )
        }
    }
}