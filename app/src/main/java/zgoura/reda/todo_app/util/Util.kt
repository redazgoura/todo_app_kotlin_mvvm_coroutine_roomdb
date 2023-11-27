import java.text.SimpleDateFormat

/* These functions create a formatted string that can be set in a TextView.
*/
fun convertLongToDateString(systemTime: Long): String {
    return SimpleDateFormat("EEEE MMM-dd-yyyy' Time: 'HH:mm")
        .format(systemTime).toString()
}