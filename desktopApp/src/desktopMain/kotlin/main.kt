import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
//import com.google.auth.oauth2.GoogleCredentials
//import com.google.cloud.firestore.Firestore
//import com.google.cloud.firestore.FirestoreOptions
//import com.google.firebase.FirebaseApp
//import com.google.firebase.FirebaseOptions
import com.jamarglex.invoiceapp.App
import com.jamarglex.invoiceapp.di.initKoin
import com.jamarglex.invoiceapp.ui.theme.InvoiceAppTheme
import java.io.FileInputStream

fun main() = application {
//    initializeFirebaseApp()
//    val firestore = initializeFirestore() // TODO pass firestore
    initKoin { }
    Window(
        title = "InvoiceApp Desktop",
        onCloseRequest = ::exitApplication
    ) {
        InvoiceAppTheme {
            App()
        }
    }
}

//private fun initializeFirebaseApp() {
//    val serviceAccount = FileInputStream("google-services.json")
//
//    val options = FirebaseOptions.builder()
//        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//        .setDatabaseUrl("https://invoiceapp-7b6c3.firebaseio.com")
//        .build()
//
//    FirebaseApp.initializeApp(options)
//}
//
//private fun initializeFirestore(): Firestore {
//    val firestoreOptions = FirestoreOptions.getDefaultInstance().toBuilder()
//        .setProjectId("tu-proyecto-id")
//        .build()
//
//    return firestoreOptions.service
//}
