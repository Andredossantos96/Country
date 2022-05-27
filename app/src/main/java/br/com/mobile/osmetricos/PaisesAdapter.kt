package br.com.mobile.osmetricos

import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.squareup.picasso.Picasso



class PaisesAdapter (
    val paises: List<Paises>,
    val onClick: (Paises) -> Unit): RecyclerView.Adapter<PaisesAdapter.PaisesViewHolder>() {


    class PaisesViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val cardNome: TextView
        val cardCapital: TextView
        val cardLongitude: TextView
        val cardLatitude: TextView
        val cardPopulacao: TextView
        val cardLocalization: TextView
        val cardImg : ImageView
        var cardProgress: ProgressBar
        var cardView: CardView

        init {
            cardNome = view.findViewById<TextView>(R.id.cardNome)
            cardLocalization = view.findViewById(R.id.cardLocalization)
            cardImg = view.findViewById<ImageView>(R.id.cardImg)
            cardProgress = view.findViewById<ProgressBar>(R.id.cardProgress)
            cardView = view.findViewById<CardView>(R.id.card_disciplinas)
            cardCapital = view.findViewById(R.id.cardCapital)
            cardLongitude = view.findViewById(R.id.cardLongitude)
            cardLatitude = view.findViewById(R.id.cardLatitude)
            cardPopulacao = view.findViewById(R.id.cardPopulacao)
        }

    }



    override fun getItemCount() = this.paises.size



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaisesViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_paises, parent, false)


        val holder = PaisesViewHolder(view)
        return holder
    }



    override fun onBindViewHolder(holder: PaisesViewHolder, position: Int) {
        val context = holder.itemView.context


        val paises = paises[position]

        val capital = paises.capital
        val continente = paises.continente
        val latitude = paises.latitude
        val longitude = paises.longitude
        val populacao = paises.populacao


        holder.cardNome.text = paises.nome
        holder.cardCapital.text = "Capital: $capital"
        holder.cardLocalization.text = "Continente: $continente"
        holder.cardLatitude.text = "Latitude: $latitude"
        holder.cardLongitude.text = "Longitude: $longitude"
        holder.cardPopulacao.text = "População: $populacao"
        holder.cardProgress.visibility = View.VISIBLE


        Picasso.with(context).load(paises.bandeira).fit().into(holder.cardImg,
            object: com.squareup.picasso.Callback{
                override fun onSuccess() {
                    holder.cardProgress.visibility = View.GONE
                }

                override fun onError() {
                    holder.cardProgress.visibility = View.GONE
                }
            })


        holder.itemView.setOnClickListener{onClick(paises)}
    }
}