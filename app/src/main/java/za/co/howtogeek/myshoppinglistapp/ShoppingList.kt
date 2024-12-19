package za.co.howtogeek.myshoppinglistapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class ShoppingItem(val id: Int,
                        var name: String,
                        var quantity: Int,
                        var isEditing: Boolean = false
)

@Composable
fun ShoppingListApp() {
    var sItems by remember { mutableStateOf(listOf<ShoppingItem>()) }
    var showDialog by remember { mutableStateOf(false) }
    var itemName by remember { mutableStateOf("") }
    var itemQuantity by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
            //.padding(16.dp),
            //.background(Color.White),
                // ... other modifiers,
        verticalArrangement = Arrangement.Center,

    ) {
        Button(
            onClick = {showDialog = true},
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            /*
            Adding complex calculations in the UI thread is not ethical coding, as this can cause the app to freeze.
            But the code below is just serving as an example of a lambda expression:

            Lambda expressions can/must? ve declared using the val or var keywords?
            Lambda expression using thw "it" keyword to replace the variable declaration:
            val doubleNumber: (Int) -> Int = { it * 2 }

            // Own Lambda expression:
            val dylsLamdaExpression: (Int) -> Int = { it * 37 }

            //Using the above Lambda expression:
            //Text(doubleNumber(5).toString())
            Text(dylsLamdaExpression(1).toString())
             */

            Text("Add Item")
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(8.dp)
        ) {
            items(sItems){
                ShoppingListItem(it, {}, {}) //{ }
            }
        }

    }

    if (showDialog){
        AlertDialog(
            onDismissRequest = { showDialog = false},
            confirmButton = {
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Button(onClick = {
                        if (itemName.isNotBlank()){
                            val newItem = ShoppingItem(
                                id = sItems.size+1,
                                name = itemName,
                                quantity = itemQuantity.toInt()
                            )
                            sItems = sItems + newItem
                            showDialog = false
                            itemName = ""
                            itemQuantity = ""
                        }
                    }) {
                        Text("Add")
                    }
                    Button(onClick = {showDialog = false}) {
                        Text("Cancel")
                    }
                }
            },
            title = { Text("Add Shopping Item") },
            text = {
                Column {
                    OutlinedTextField(
                        value = itemName,
                        onValueChange = {itemName = it },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth().padding(8.dp)
                    )
                    OutlinedTextField(
                        value = itemQuantity,
                        onValueChange = {itemQuantity = it },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth().padding(8.dp)
                    )
                }
            },
        )
    }
}

/*
Designing the ShoppingListItem:
 */
@Composable
fun ShoppingListItem(
    item: ShoppingItem,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
){
    Row(
        modifier = Modifier.padding(8.dp)/*.fillMaxWidth().border(
            border = BorderStroke(2.dp, Color (0XFF018786)),
            shape = RoundedCornerShape(20)
        )
        */
    ) {
        Text(text = "${item.name} -> ",
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text="Quantity: ${item.quantity}",
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }

}