import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.l4digital.fastscroll.FastScrollRecyclerView
import com.l4digital.fastscroll.FastScroller
import tech.torah.aldis.androidapp.R
import tech.torah.aldis.androidapp.Speaker
import java.io.File


private const val TAG = "SpeakerPageActivity"
private lateinit var speakerImageView: ImageView

private class BrowseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "RAN SUCCESSFULL!")
        setContentView(R.layout.speaker_page_recycler_view_layout)

        val recyclerView: FastScrollRecyclerView? = findViewById(R.id.recycler_view)
        recyclerView?.layoutManager = LinearLayoutManager(this)
        val listOfSpeakers = mutableListOf<Speaker>()
        val listOfSpeakerJson = File("C:\\Users\\shmue\\AndroidStudioProjects\\TorahDownloadsAndroid\\app\\JSON pages\\listOfSpeakersPage.json").readText().split(",").take(100)
        listOfSpeakerJson.forEach { listOfSpeakers.add(parseSpeakerFromJSON(it)) }
        recyclerView?.adapter = SpeakerAdapter(listOfSpeakers)
    }
}

private fun parseSpeakerFromJSON(json: String): Speaker {
    val id = "(?<=\"id\": \")\\d+(?=\")".toRegex().find(json)?.value?.toInt() ?: 64
    val name =
        "(?<=\"name\": \")[\\w\\s]+(?=\")".toRegex().find(json)?.value ?: "Rabbi Yisroel Belsky"
    val last_name = "(?<=\"last_name\": \")\\w+(?=\")".toRegex().find(json)?.value ?: "Belsky"
    val image_path = "(?<=\"image_path\": \")[\\w/.\\d]+(?=\")".toRegex().find(json)?.value
        ?: "assets/speakers/64.jpg"
    val link = "(?<=\"link\": \")[\\w.\\d-]+(?=\")".toRegex().find(json)?.value
        ?: "s-64-rabbi-yisroel-belsky.html"
    val shiur_count = "(?<=\"link\": \")\\d+".toRegex().find(json)?.value?.toInt() ?: 31
    return Speaker(id, name, last_name, image_path, link, shiur_count)
}

class SpeakerAdapter(val speakerList: MutableList<Speaker>) :
    RecyclerView.Adapter<SpeakerAdapter.SpeakerViewHolder>(), FastScroller.SectionIndexer {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SpeakerViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.individual_speaker_card_layout, parent, false)
        return SpeakerViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: SpeakerViewHolder, position: Int) {
        holder.bindItems(speakerList[position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return speakerList.size
    }

    override fun getSectionText(position: Int): CharSequence {
        return speakerList[position].last_name.first().toString()
        //TODO not sure if I implemented this right.
    }

    //the class is hodling the list view
private    class SpeakerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(speaker: Speaker) {
            val speakerName = itemView.findViewById(R.id.speaker_name) as TextView?
            val speakerDescription = itemView.findViewById(R.id.speaker_description) as TextView?
            if (speakerName != null) {
                speakerName.text = speaker.name
                Log.d(TAG, "Run")
            }
            if (speakerDescription != null) {
                speakerDescription.text = speaker.description
            }
            speakerImageView = itemView.findViewById(R.id.speaker_image)
            speakerImageView.setImageResource(
                listOf(
                    R.drawable.rabbi_yisroel_belsky,
                    R.drawable.a,
                    R.drawable.b,
                    R.drawable.c,
                    R.drawable.d,
                    R.drawable.e
                ).shuffled().last()
            )
        }
    }
}