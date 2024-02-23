package com.loc.multiselectionsample

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.loc.multiselectionsample.ui.theme.MultiSelectionSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val numbers = (1..100).toList()
            val selectedItems = remember { mutableStateListOf<Int>() }
            val multiSelectionState = rememberMultiSelectionState()
            val context = LocalContext.current

            MultiSelectionSampleTheme {
                Scaffold(
                    topBar = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            TextButton(
                                onClick = {
                                    multiSelectionState.isMultiSelectionModeEnabled =
                                        !multiSelectionState.isMultiSelectionModeEnabled
                                    if (multiSelectionState.isMultiSelectionModeEnabled) {
                                        selectedItems.clear()
                                    }
                                }
                            ) {
                                Text(text = if (multiSelectionState.isMultiSelectionModeEnabled) "Done" else "Select")
                            }
                        }
                    }
                ) {
                    MultiSelectionList(
                        modifier = Modifier.padding(top = it.calculateTopPadding()),
                        state = multiSelectionState,
                        items = numbers,
                        selectedItems = selectedItems,
                        itemContent = {
                            Text(
                                text = it.toString(),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp)
                            )
                        },
                        key = { num ->
                            num
                        },
                        onClick = {
                            if (multiSelectionState.isMultiSelectionModeEnabled) {
                                selectedItems.add(it)
                            } else {
                                Toast.makeText(context, "Click $it", Toast.LENGTH_SHORT).show()
                            }
                        }
                    )
                }
            }
        }
    }
}
