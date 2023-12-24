import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.jamarglex.invoiceapp.App
import com.jamarglex.invoiceapp.di.initKoin

fun main() = application {
    initKoin {  }
    Window(
        title = "InvoiceApp Desktop",
        onCloseRequest = ::exitApplication
    ) {
        App()
    }
}
