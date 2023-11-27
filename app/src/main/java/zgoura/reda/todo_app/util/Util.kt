import android.graphics.Color
import java.text.SimpleDateFormat

/* These functions create a formatted string that can be set in a TextView.
*/
fun convertLongToDateString(systemTime: Long): String {
    return SimpleDateFormat("MMM-dd-yyyy' T: 'HH:mm")
        .format(systemTime).toString()
}

object ColorProvider{
    private val colors = listOf(
        Color.rgb(217, 237, 146),
        Color.rgb(181, 228, 140),
        Color.rgb(153, 217, 140),
        Color.rgb(82, 182, 154),
        Color.rgb(52, 160, 164),
        Color.rgb(22, 138, 173),
        Color.rgb(26, 117, 159),
        Color.rgb(30, 96, 145),
        Color.rgb(241, 220, 167),
        Color.rgb(232, 172, 101),
    )

    fun getColorRessourceId(pos : Int) : Int {
        return colors[pos % colors.size]
    }
}