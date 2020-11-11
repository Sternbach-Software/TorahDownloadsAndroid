package tech.torah.aldis.androidapp

data class Category(
    val id: Int = 6,
    val name: String = "Chumash",
    val url: String = "c-6-torah.html",
    val label: String = "<a href=\"c-6-torah.html\">Chumash</a>",
    val children: List<Category>
) : TorahFilter