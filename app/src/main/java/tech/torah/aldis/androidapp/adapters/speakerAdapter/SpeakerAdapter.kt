package tech.torah.aldis.androidapp.adapters.speakerAdapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.l4digital.fastscroll.FastScroller
import tech.torah.aldis.androidapp.R
import tech.torah.aldis.androidapp.activities.IndividualSpeakerPageActivity
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.CONSTANTS.INTENT_EXTRA_SPEAKER_DETAILS
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.FunctionLibrary
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.Speaker
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.ShiurFilterOption
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.TorahFilterable


private const val TAG = "SpeakerAdapter"
class SpeakerAdapter(private val originalSpeakerList: List<Speaker>) : RecyclerView.Adapter<SpeakerAdapter.SpeakerViewHolder>(),
    FastScroller.SectionIndexer, TorahFilterable {
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
        R.drawable.ca,
        R.drawable.cb,
        R.drawable.cc,
        R.drawable.cd,
        R.drawable.ce,
        R.drawable.cg,
        R.drawable.ch,
        R.drawable.ci,
        R.drawable.cj,
        R.drawable.ck,
        R.drawable.cl,
        R.drawable.cm,
        R.drawable.cn,
    )
    private lateinit var speakerImageView: ImageView
    private val workingSpeakerList: MutableList<Speaker> = originalSpeakerList.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeakerViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.individual_speaker_card_layout, parent, false)
        return SpeakerViewHolder(v)
    }

    override fun getItemCount(): Int = workingSpeakerList.size

    override fun getSectionText(position: Int): CharSequence = workingSpeakerList[position].last_name.first().toUpperCase().toString()

    override fun onBindViewHolder(holder: SpeakerViewHolder, position: Int) = holder.bindItems(
        workingSpeakerList[position]
    )

    inner class SpeakerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(speaker: Speaker) {
            val speakerNameTextView = itemView.findViewById(R.id.speaker_name) as TextView?
            val speakerDescriptionTextView = itemView.findViewById(R.id.speaker_description) as TextView?
            val name = speaker.name
            val description = speaker.description
            if (speakerNameTextView != null) {
                speakerNameTextView.text = name
            }
            if (speakerDescriptionTextView != null) {
                speakerDescriptionTextView.text = description
            }
            speakerImageView = itemView.findViewById(R.id.speaker_image)
            speakerImageView.setImageResource(drawables[speakerPictureCount])
            (itemView.findViewById(R.id.individual_speaker_card) as MaterialCardView).setOnClickListener{
                val context = itemView.context
                val intent = Intent(context, IndividualSpeakerPageActivity::class.java).apply {
                    putStringArrayListExtra(INTENT_EXTRA_SPEAKER_DETAILS,
                        arrayListOf(name, description))
                }
                context.startActivity(intent)
            }
            speakerPictureCount = (speakerPictureCount +1) % drawables.size
        }
    }

    override fun filter(constraint: String, shiurFilterOption: ShiurFilterOption, exactMatch: Boolean) {
        FunctionLibrary.filter(
            constraint,
            originalSpeakerList,
            workingSpeakerList,
            this,
            shiurFilterOption,
            exactMatch
        )
    }

    override fun reset() {
        FunctionLibrary.reset(
            originalSpeakerList,
            workingSpeakerList,
            this)
    }
}