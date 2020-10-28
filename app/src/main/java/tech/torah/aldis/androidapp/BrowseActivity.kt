package tech.torah.aldis.androidapp

import android.R.drawable
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
import java.lang.reflect.Field


private var speakerPictureCount = 0
private var drawables = listOf(
        R.drawable.ab,
                R.drawable.ac,
                R.drawable.ad,
                R.drawable.ae,
                R.drawable.af,
                R.drawable.ag,
                R.drawable.ah,
                R.drawable.ai,
                R.drawable.aj,
                R.drawable.ak,
                R.drawable.al,
                R.drawable.am,
                R.drawable.an,
                R.drawable.ao,
                R.drawable.ap,
                R.drawable.aq,
                R.drawable.ar,
                R.drawable.`as`,
                R.drawable.at,
                R.drawable.au,
                R.drawable.av,
                R.drawable.aw,
                R.drawable.ax,
                R.drawable.ay,
                R.drawable.az,
                R.drawable.ba,
                R.drawable.bb,
                R.drawable.bc,
                R.drawable.bd,
                R.drawable.be,
                R.drawable.bf,
                R.drawable.bg,
                R.drawable.bh,
                R.drawable.bi,
                R.drawable.bj,
                R.drawable.bk,
                R.drawable.bl,
                R.drawable.bm,
                R.drawable.bn,
                R.drawable.bo,
                R.drawable.bp,
                R.drawable.bq,
                R.drawable.br,
                R.drawable.bs,
                R.drawable.bt,
                R.drawable.bu,
                R.drawable.bv,
                R.drawable.bw,
                R.drawable.bx,
                R.drawable.by,
                R.drawable.bz,
                R.drawable.ca,
                R.drawable.cb,
                R.drawable.cc,
                R.drawable.cd,
                R.drawable.ce,
                R.drawable.cf,
                R.drawable.cg,
                R.drawable.ch,
                R.drawable.ci,
                R.drawable.cj,
                R.drawable.ck,
                R.drawable.cl,
                R.drawable.cm,
                R.drawable.cn,
)
private const val TAG = "BrowseActivity"
private lateinit var speakerImageView: ImageView

    class BrowseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "RAN SUCCESSFULL!")
        setContentView(R.layout.speaker_page_recycler_view_layout)

        val recyclerView: FastScrollRecyclerView? = findViewById(R.id.recycler_view)
        recyclerView?.layoutManager = LinearLayoutManager(this)
        val listOfSpeakers = mutableListOf<Speaker>()
        val listOfSpeakerJson = Constants.speaker.split("},").take(300)
        listOfSpeakerJson.forEach { listOfSpeakers.add(parseSpeakerFromJSON(it)) }
        Log.d(TAG, listOfSpeakers[15].toString())
        recyclerView?.adapter = SpeakerAdapter(listOfSpeakers)
    }
}

private fun parseSpeakerFromJSON(json: String): Speaker {
//    Log.d(TAG,"json===$json")
    val id = "(?<=\"id\": \")\\d+(?=\")".toRegex().find(json)?.value?.toInt() ?: 64

    Log.d(TAG, "json===$json")
    Log.d(TAG, "id===$id")

    val name =
        "(?<=\"name\":\")[\\w\\s]+(?=\")".toRegex().find(json)?.value ?: "Rabbi Yisroel Belsky"
    val last_name = "(?<=\"last_name\":\")\\w+(?=\")".toRegex().find(json)?.value ?: "Belsky"
    val image_path = "(?<=\"image_path\":\")[\\w/.\\d\\\\]+(?=\")".toRegex().find(json)?.value
        ?: "assets/speakers/64.jpg"
    val link = "(?<=\"link\":\")[\\w.\\d-]+(?=\")".toRegex().find(json)?.value
        ?: "s-64-rabbi-yisroel-belsky.html"
    val shiur_count = "(?<=\"link\":\")\\d+".toRegex().find(json)?.value?.toInt() ?: 31
    val speaker = Speaker(id, name, last_name, image_path, link, shiur_count)
    Log.d(TAG, "speaker===$speaker")
    return speaker
}

private class SpeakerAdapter(val speakerList: MutableList<Speaker>) :
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
    class SpeakerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

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
            speakerImageView.setImageResource(drawables[speakerPictureCount])
            if(speakerPictureCount<64)speakerPictureCount++ else speakerPictureCount=0
        }
    }
}