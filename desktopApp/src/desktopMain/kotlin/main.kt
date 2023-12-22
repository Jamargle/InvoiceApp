import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.jamarglex.invoiceapp.shared.App

fun main() = application {
    Window(
        title = "InvoiceApp Desktop",
        onCloseRequest = ::exitApplication
    ) {
        App()
    }
}
