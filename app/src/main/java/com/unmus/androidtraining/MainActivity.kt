package com.unmus.androidtraining

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.unmus.androidtraining.tataletak.TataLetakActivity
import com.unmus.androidtraining.ui.theme.AndroidTrainingTheme
import com.unmus.androidtraining.ui.theme.Purple200

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidTrainingTheme{
                Column() {
                    Button(onClick = {
                    /*TODO*/
                    val i = Intent(this@MainActivity, TataLetakActivity::class.java)
                    startActivity(i)
                    }) {

                    }
                    Conversation(GenerateDataDummyUser())
                }

            }
        }
    }
}


fun GenerateDataDummyUser(): ArrayList<User>{
    var users: ArrayList<User> = arrayListOf()

    // buat objek user
    var user = User("Perpustakaan", "Membaca adalah jendela ilmu", 20)
    users.add(user)

    return users
}

data class User(
    val name: String,
    val address: String,
    val umur: Int
)

@Composable
fun Pesan(user: User){
    Row(
        modifier = Modifier.padding(all = 1.dp)
    ) {
        Image(painter =
        painterResource(id = R.drawable.buku),
            contentDescription = "Perpustakaan",
            modifier = Modifier
                // Set image size to 40 dp
                .size(150.dp)
                // Clip image to be shaped as a circle
                .clip(CircleShape)
                .border(1.dp, MaterialTheme.colors.secondary, CircleShape),
            contentScale = ContentScale.Crop)
        Spacer(modifier = Modifier.width(3.dp))

        // We keep track if the message is expanded or not in this
        // variable
        var isExpanded by remember { mutableStateOf(false) }


        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(text = user.name, color = Purple200)
            Spacer(modifier = Modifier.height(8.dp))
            Surface(shape = MaterialTheme.shapes.medium, elevation = 2.dp) {
                Text(
                    text = user.address,
                    modifier = Modifier.padding(all = 4.dp),
                    // If the message is expanded, we display all its content
                    // otherwise we only display the first line
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,

                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewMenampilkanPesan(){
    val user = User("Perpustakaan", "Buku", 30)
    Pesan(user)
}

@Composable
fun Conversation(users: ArrayList<User>){
    LazyColumn {
        items(users) { user ->
            Pesan(user = user)
        }
    }
}