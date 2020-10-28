package tech.torah.aldis.androidapp

//import javax.swing.text.AbstractDocument.Content


data class Shiur(
    val id: String = "1008064",
    val page_title: String = "Chinuch - Shiur 1 - Rabbi Yisroel Belsky - TD1008064",
    val title: String = "Chinuch - Shiur 1",
    val speaker/*Rabbi Yisroel Belsky*/: String = "Rabbi Yisroel Belsky",
    val speaker_image: String = "assets\\/speakers\\/64.jpg",
    val length: String = "83",
    val links: List<String> = listOf(
        "shiur-1008064-download.mp3",
        "\\/c-223-chinuch-parenting.html",
        "\\/s-64-rabbi-yisroel-belsky.html"
    ),
    val download: String = "shiur-1008064-download.mp3",
    val category: String = "\\/c-223-chinuch-parenting.html",
    val speakerHtml/*\/s-64-rabbi-yisroel-belsky.html" - just represented as "speaker" in actual JSON*/: String = "\\/s-64-rabbi-yisroel-belsky.html",
    val attachment: String = "",
    val description: String = "",
    val source: String = "",
    val attachment_name: String = "",
    val uploaded: String = "February 5, 2020",
    val language: String = "",
    val series: String = "",
    val quickseries: String = "",
    val quickseries_name: String = ""
)

/*
internal class MyDeserializer : JsonDeserializer<javax.swing.text.AbstractDocument.Content?> {
    @Throws(JsonParseException::class)
    fun deserialize(
        je: JsonElement,
        type: Type?,
        jdc: JsonDeserializationContext?
    ): javax.swing.text.AbstractDocument.Content {
        // Get the "content" element from the parsed JSON
        val content: JsonElement = je.getAsJsonObject().get("content")

        // Deserialize it. You use a new instance of Gson to avoid infinite recursion
        // to this deserializer
        return Gson().fromJson(content, javax.swing.text.AbstractDocument.Content::class.java)
    }
}
*/
fun main() {
    println(prettyPrintJSON("[{\"id\":\"1008064\",\"page_title\":\"Chinuch - Shiur 1 - Rabbi Yisroel Belsky - TD1008064\",\"title\":\"Chinuch - Shiur 1\",\"speaker\":\"Rabbi Yisroel Belsky\",\"speaker_image\":\"assets\\/speakers\\/64.jpg\",\"length\":\"83\",\"links\":{\"download\":\"shiur-1008064-download.mp3\",\"category\":\"\\/c-223-chinuch-parenting.html\",\"speaker\":\"\\/s-64-rabbi-yisroel-belsky.html\",\"attachment\":\"\"},\"description\":\"\",\"source\":\"\",\"attachment_name\":\"\",\"uploaded\":\"February 5, 2020\",\"language\":\"English\",\"series\":\"\",\"quickseries\":[],\"quickseries_name\":\"\"}]"))
}

fun prettyPrintJSON(unformattedJsonString: String): String? {
    val prettyJSONBuilder = StringBuilder()
    var indentLevel = 0
    var inQuote = false
    for (charFromUnformattedJson in unformattedJsonString.toCharArray()) {
        when (charFromUnformattedJson) {
            '"' -> {
                // switch the quoting status
                inQuote = !inQuote
                prettyJSONBuilder.append(charFromUnformattedJson)
            }
            ' ' ->         // For space: ignore the space if it is not being quoted.
                if (inQuote) {
                    prettyJSONBuilder.append(charFromUnformattedJson)
                }
            '{', '[' -> {
                // Starting a new block: increase the indent level
                prettyJSONBuilder.append(charFromUnformattedJson)
                indentLevel++
                appendIndentedNewLine(indentLevel, prettyJSONBuilder)
            }
            '}', ']' -> {
                // Ending a new block; decrese the indent level
                indentLevel--
                appendIndentedNewLine(indentLevel, prettyJSONBuilder)
                prettyJSONBuilder.append(charFromUnformattedJson)
            }
            ',' -> {
                // Ending a json item; create a new line after
                prettyJSONBuilder.append(charFromUnformattedJson)
                if (!inQuote) {
                    appendIndentedNewLine(indentLevel, prettyJSONBuilder)
                }
            }
            else -> prettyJSONBuilder.append(charFromUnformattedJson)
        }
    }
    return prettyJSONBuilder.toString()
}

/**
 * Print a new line with indention at the beginning of the new line.
 * @param indentLevel
 * @param stringBuilder
 */
private fun appendIndentedNewLine(indentLevel: Int, stringBuilder: StringBuilder) {
    stringBuilder.append("\n")
    for (i in 0 until indentLevel) {
        // Assuming indention using 2 spaces
        stringBuilder.append("  ")
    }
}