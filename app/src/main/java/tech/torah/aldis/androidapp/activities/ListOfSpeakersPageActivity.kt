package tech.torah.aldis.androidapp.activities

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.l4digital.fastscroll.FastScrollRecyclerView
import tech.torah.aldis.androidapp.R
import tech.torah.aldis.androidapp.adapters.speakerAdapter.SpeakerAdapter
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.FunctionLibrary
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.Speaker
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader


private const val TAG = "ListOfSpeakerPageActivi"

class ListOfSpeakerPageActivity : AppCompatActivity() {
        private lateinit var speakerAdapter: SpeakerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fast_scroll_recycler_view_layout)

        val recyclerView: FastScrollRecyclerView? = findViewById(R.id.fast_scroller)
        recyclerView?.layoutManager = LinearLayoutManager(this)

        val listOfSpeakers = listOfSpeakersFromJson(300)/*mutableListOf(
            Speaker(name="Rabbi mea"),
            Speaker(name="Rabbi eal"),
            Speaker(name="Rabbi alw"),
            Speaker(name="Rabbi lwb"),
            Speaker(name="Rabbi me"),
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

                val listOfSpeakerJson =
                    loadData(R.raw.list_of_speakers_page).split("},").take(n)
                listOfSpeakerJson.forEach { listOfSpeakers.add(parseSpeakerFromJSON(it)) }
            return listOfSpeakers
        }
        fun loadData(inFile: Int): String {
            listOf("").elementAtOrNull(4)
            var tContents:String? = ""

            val stringBuilder = StringBuilder()
            val isa:InputStream = this.resources.openRawResource(inFile)
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
            return stringBuilder.toString()
        }
        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            //TODO Should we use FunctionLibrary.setupFilterAndSearch()? What can the speakers be filtered or sorted by?
            if (menu != null) {
                FunctionLibrary.setupSearchView(
                    menuInflater, menu, speakerAdapter,
                    alsoUsingFilterButton = false,
                    shouldInflateLayout = true,
                    true,
                    this,
                    null,
                    null
                )
            }
            return true
        }
}

private fun parseSpeakerFromJSON(json: String): Speaker {
    val id = "(?<=\"id\": \")\\d+(?=\")".toRegex().find(json)?.value?.toInt() ?: 64

    val name =
        "(?<=\"name\": \")[\\w\\s]+(?=\")".toRegex().find(json)?.value ?: "Rabbi Yisroel Belsky"
    val last_name = "(?<=\"last_name\": \")\\w+(?=\")".toRegex().find(json)?.value ?: "Belsky"
    val image_path ="(?<=\"image_path\": \")[\\w/.\\d\\\\]+(?=\")".toRegex().find(json)?.value
        ?: "assets/speakers/64.jpg"
    val link = "(?<=\"link\": \")[\\w.\\d-]+(?=\")".toRegex().find(json)?.value
        ?: "s-64-rabbi-yisroel-belsky.html"
    val shiur_count = "(?<=\"link\": \")\\d+".toRegex().find(json)?.value?.toInt() ?: 31
    val speaker = Speaker(id, name, last_name, image_path, link, shiur_count)
    return speaker
}

