package testutils

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag

fun ComposeContentTestRule.onNodeWithId(id: String) = onNodeWithTag(id)

fun ComposeContentTestRule.onAllNodesWithId(id: String) = onAllNodesWithTag(id)