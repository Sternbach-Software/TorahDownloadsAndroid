package tech.torah.aldis.androidapp.activities

import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.l4digital.fastscroll.FastScrollRecyclerView
import com.l4digital.fastscroll.FastScroller
import tech.torah.aldis.androidapp.R
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.Speaker
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*
import kotlin.system.measureNanoTime


private var speakerPictureCount = 0
private var drawables = listOf(
    R.drawable.a,
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
private const val TAG = "SpeakerPageActivity"
private lateinit var speakerImageView: ImageView

    class SpeakerPageActivity : AppCompatActivity() {
        private lateinit var speakerAdapter: SpeakerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fast_scroll_recycler_view_layout)

        val recyclerView: FastScrollRecyclerView? = findViewById(R.id.fast_scroller)
        recyclerView?.layoutManager = LinearLayoutManager(this)

        val listOfSpeakers = listOfSpeakersFromJson(300)/*mutableListOf(
            Speaker(),
            Speaker(),
            Speaker(),
            Speaker(),
            Speaker(),
            Speaker(),
            Speaker(),
            Speaker(),
            Speaker(),
            Speaker(),
            Speaker(),
            Speaker(),
            Speaker(),
            Speaker(),
            Speaker(),
            Speaker(),
            Speaker(),
            Speaker(),
            Speaker(),
            Speaker(),
            Speaker(),
            Speaker(),
        )*/


        speakerAdapter = SpeakerAdapter(listOfSpeakers)
        recyclerView?.adapter = speakerAdapter

/*This animation wasn't working
        // get the center for the clipping circle
        val cx = (browse_shiurim_recycler_view?.width)?.div(2)
        val cy = (browse_shiurim_recycler_view?.height)?.div(2)

        // get the final radius for the clipping circle
        val finalRadius = cx?.toDouble()?.let { cy?.toDouble()?.let { it1 ->
            hypot(it,
                it1
            ).toFloat()
        } }
        browse_shiurim_recycler_view?.visibility = View.VISIBLE

        // create the animator for this view (the start radius is zero) with null
        val anim =
            cx?.let {it1->
                cy?.let {it2->
                    finalRadius?.let{it3->
                        ViewAnimationUtils.createCircularReveal(browse_shiurim_recycler_view,
                            it1, it2, 0f, it3)
                    }
                }
            }
        // make the view visible and start the animation
        anim?.start()*/
    }

        private fun listOfSpeakersFromJson(n: Int): MutableList<Speaker> {
            Log.d(TAG, "listOfSpeakersFromJson called.")
            val listOfSpeakers = mutableListOf<Speaker>()
            val time = measureNanoTime {

                val listOfSpeakerJson =
                    loadData(R.raw.list_of_speakers_page).split("},").take(n)
                listOfSpeakerJson.forEach { listOfSpeakers.add(parseSpeakerFromJSON(it)) }
            }
            return listOfSpeakers
        }
        fun loadData(inFile: Int): String {
            listOf("").elementAtOrNull(4)
            var tContents:String? = ""

            val stringBuilder = StringBuilder();
            val isa:InputStream = this.resources .openRawResource(inFile)
            val reader = BufferedReader(InputStreamReader(isa))
            while (true) {
                try {
                    tContents = reader.readLine()
                    if (tContents == null) break
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                stringBuilder.append(tContents).append("\n")
            }
            Log.d(TAG,"Speaker File Contents: $tContents")
            return tContents!!
        }
        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            val inflater = menuInflater
            inflater.inflate(R.menu.speaker_page_menu, menu)
            val searchItem: MenuItem = menu!!.findItem(R.id.actionSearch)
            val searchView: SearchView = searchItem.actionView as SearchView
            searchView.imeOptions = EditorInfo.IME_ACTION_DONE
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    speakerAdapter.filter(query ?: "")
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    speakerAdapter.filter(newText ?: "")
                    return false
                }
            })
            return true
        }
}

private fun parseSpeakerFromJSON(json: String): Speaker {
    val id = "(?<=\"id\": \")\\d+(?=\")".toRegex().find(json)?.value?.toInt() ?: 64

    val name =
        "(?<=\"name\":\")[\\w\\s]+(?=\")".toRegex().find(json)?.value ?: "Rabbi Yisroel Belsky"
    val last_name = "(?<=\"last_name\":\")\\w+(?=\")".toRegex().find(json)?.value ?: "Belsky"
    val image_path = "(?<=\"image_path\":\")[\\w/.\\d\\\\]+(?=\")".toRegex().find(json)?.value
        ?: "assets/speakers/64.jpg"
    val link = "(?<=\"link\":\")[\\w.\\d-]+(?=\")".toRegex().find(json)?.value
        ?: "s-64-rabbi-yisroel-belsky.html"
    val shiur_count = "(?<=\"link\":\")\\d+".toRegex().find(json)?.value?.toInt() ?: 31
    val speaker = Speaker(id, name, last_name, image_path, link, shiur_count)
    return speaker
}

private class SpeakerAdapter(val originalSpeakerList: MutableList<Speaker>) : RecyclerView.Adapter<SpeakerAdapter.SpeakerViewHolder>(), FastScroller.SectionIndexer {

    val speakerList: MutableList<Speaker> = originalSpeakerList.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeakerViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.individual_speaker_card_layout, parent, false)
        return SpeakerViewHolder(v)
    }

    override fun getItemCount(): Int = speakerList.size

    override fun getSectionText(position: Int): CharSequence = speakerList[position].last_name.first().toUpperCase().toString()

    override fun onBindViewHolder(holder: SpeakerViewHolder, position: Int) = holder.bindItems(
        speakerList[position]
    )

    fun filter(constraint: String){

/*
        var completeListIndex = 0
        var filteredListIndex = 0
        while (completeListIndex < originalSpeakerList.size) {
            val speaker: Speaker = originalSpeakerList[completeListIndex]
            if (speaker.name.toLowerCase(Locale.ROOT).trim().contains(constraint)) {
                if (filteredListIndex < speakerList.size) {
                    val filter: Speaker = speakerList[filteredListIndex]
                    if (speaker.name != filter.name) {
                        speakerList.add(filteredListIndex, speaker)
                        notifyItemInserted(filteredListIndex)
                    }
                } else {
                    speakerList.add(filteredListIndex, speaker)
                    notifyItemInserted(filteredListIndex)
                }
                filteredListIndex++
            } else if (filteredListIndex < speakerList.size) {
                val filter: Speaker = speakerList[filteredListIndex]
                if (speaker.name==filter.name) {
                    speakerList.removeAt(filteredListIndex)
                    notifyItemRemoved(filteredListIndex)
                }
            }
            completeListIndex++
        }
*/
        speakerList.clear()
        if (constraint.isEmpty()) {
            speakerList.addAll(originalSpeakerList)
        } else {
            val filterPattern = constraint.toLowerCase(Locale.ROOT).trim()
            for (speaker in originalSpeakerList) {
                if (speaker.name.toLowerCase(Locale.ROOT).contains(filterPattern)) {
                    speakerList.add(speaker)
                }
            }
        }
        notifyDataSetChanged()
    }

    class SpeakerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(speaker: Speaker) {
            val speakerName = itemView.findViewById(R.id.speaker_name) as TextView?
            val speakerDescription = itemView.findViewById(R.id.speaker_description) as TextView?
            if (speakerName != null) {
                speakerName.text = speaker.name
            }
            if (speakerDescription != null) {
                speakerDescription.text = speaker.description
            }
            speakerImageView = itemView.findViewById(R.id.speaker_image)
            speakerImageView.setImageResource(drawables[speakerPictureCount])
            speakerPictureCount = (speakerPictureCount+1) % drawables.size
        }
    }
}