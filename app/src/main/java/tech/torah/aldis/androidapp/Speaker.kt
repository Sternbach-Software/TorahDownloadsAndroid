package tech.torah.aldis.androidapp

data class Speaker(
    val id: Int =                           64,
    val name: String =                      "Rabbi Yisroel Belsky",
    val last_name: String =                 "Belsky",
    val image_path: String =                "assets/speakers/64.jpg",
    val link: String =                      "s-64-rabbi-yisroel-belsky.html",
    val shiur_count: Int =                  31
) {
    public val description = "Insert description here.................."
    override fun toString(): String {
        return "id=$id,name=$name,last_name=$last_name,image_path=$image_path,link=$link,shiur_count=$shiur_count"
    }
}
